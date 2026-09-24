from pathlib import Path
import re
from urllib.parse import quote, unquote

import mistune
from fastapi import APIRouter, HTTPException

BASE_DIR = Path(__file__).resolve().parent.parent
MATERIAL_DIR = (BASE_DIR / "material").resolve()

router = APIRouter(prefix="/api")

# Markdown renderer. The material is maintained by the course team, so raw HTML
# is preserved when it is intentionally present in the Markdown.
markdown = mistune.create_markdown(
    escape=False,
    plugins=["strikethrough", "table", "url"]
)


def safe_material_path(relative_path: str) -> Path:
    """Resolve a material path while preventing access outside /material."""
    relative_path = unquote(relative_path).replace("\\", "/").lstrip("/")
    candidate = (MATERIAL_DIR / relative_path).resolve()

    try:
        candidate.relative_to(MATERIAL_DIR)
    except ValueError:
        raise HTTPException(status_code=400, detail="Caminho de material inválido.")

    return candidate


def clean_text(text: str) -> str:
    """Turn a Markdown fragment into a short readable card preview."""
    text = re.sub(r"```[\s\S]*?```", " ", text)
    text = re.sub(r"!\[[^\]]*\]\([^)]*\)", " ", text)
    text = re.sub(r"\[([^\]]+)\]\([^)]*\)", r"\1", text)
    text = re.sub(r"<[^>]+>", " ", text)
    text = re.sub(r"[#*_>`~|-]", " ", text)
    text = re.sub(r"\s+", " ", text).strip()
    return text


def title_from_markdown(text: str, path: Path) -> str:
    # Prefer the first heading of any level.
    for line in text.splitlines():
        match = re.match(r"^\s*#{1,6}\s+(.+?)\s*$", line)
        if match:
            title = clean_text(match.group(1)).strip()
            if title and title.lower() != "index":
                return title

    # If the file is an index without a useful heading, derive a friendly name
    # from its parent folder.
    if path.stem.lower() in {"index", "readme"}:
        parent = path.parent.name.replace("_", " ").replace("-", " ")
        if parent:
            return parent.title()

    return path.stem.replace("_", " ").replace("-", " ").title()


def category_from_path(path: Path) -> tuple[str, str]:
    rel = path.relative_to(MATERIAL_DIR).as_posix()

    if rel.startswith("agenda/"):
        return "Agenda", "agenda"
    if rel.startswith("aulas/IA/"):
        return "Inteligência Artificial", "ia"
    if rel.startswith("aulas/genAI/"):
        return "IA Generativa", "genai"
    if rel.startswith("aulas/iot/"):
        return "IoT", "iot"
    if rel.startswith("aulas/checkpoint/"):
        return "Checkpoints", "checkpoint"

    return "Materiais", "materiais"


def preview_from_markdown(text: str) -> str:
    # Prefer the first meaningful paragraph after the title.
    lines = text.splitlines()
    paragraph = []
    for line in lines:
        stripped = line.strip()
        if not stripped:
            if paragraph:
                break
            continue
        if stripped.startswith("#") or stripped.startswith("!") or stripped.startswith("```"):
            if paragraph:
                break
            continue
        if stripped.startswith(">"):
            stripped = stripped.lstrip("> ")
        paragraph.append(stripped)

    result = clean_text(" ".join(paragraph))
    if len(result) < 20:
        result = clean_text(text)

    return result[:190].rstrip() + ("…" if len(result) > 190 else "")


def rewrite_local_links(source: str, source_path: Path) -> str:
    """Make relative Markdown links/images work from the web UI."""
    source_dir = source_path.parent.relative_to(MATERIAL_DIR).as_posix()

    def resolve_target(target: str) -> str:
        target = target.strip().strip("<>")
        if not target or target.startswith(("http://", "https://", "data:", "#", "/")):
            return target

        # Keep query/fragment separate.
        match = re.match(r"([^?#]*)(.*)$", target)
        raw_path, suffix = match.group(1), match.group(2)
        joined = Path(source_dir, raw_path).as_posix()
        normalized = Path(joined).as_posix()
        if raw_path.endswith(".md"):
            return f"/aula.html?arquivo={quote(normalized)}{suffix}"
        return f"/material/{quote(normalized, safe='/._-~')}" + suffix

    def image_repl(match):
        alt, target = match.group(1), match.group(2)
        return f"![{alt}]({resolve_target(target)})"

    def link_repl(match):
        label, target = match.group(1), match.group(2)
        return f"[{label}]({resolve_target(target)})"

    source = re.sub(r"!\[([^\]]*)\]\(([^)]+)\)", image_repl, source)
    source = re.sub(r"(?<!!)\[([^\]]+)\]\(([^)]+)\)", link_repl, source)
    return source


def material_record(path: Path) -> dict:
    relative = path.relative_to(MATERIAL_DIR).as_posix()
    source = path.read_text(encoding="utf-8", errors="replace")
    category, category_id = category_from_path(path)
    return {
        "arquivo": relative,
        "titulo": title_from_markdown(source, path),
        "previa": preview_from_markdown(source),
        "categoria": category,
        "categoria_id": category_id,
        "url": f"/aula.html?arquivo={quote(relative)}",
    }


@router.get("/materials")
def list_materials():
    files = [p for p in MATERIAL_DIR.rglob("*.md") if p.is_file()]
    records = [material_record(p) for p in files]
    records.sort(key=lambda item: (item["categoria"], item["titulo"].lower(), item["arquivo"]))
    return {"total": len(records), "materiais": records}


@router.get("/material")
def get_material(arquivo: str):
    path = safe_material_path(arquivo)

    if not path.is_file() or path.suffix.lower() != ".md":
        raise HTTPException(status_code=404, detail="Arquivo Markdown não encontrado.")

    source = path.read_text(encoding="utf-8", errors="replace")
    prepared = rewrite_local_links(source, path)
    rendered = markdown(prepared)
    record = material_record(path)

    return {
        **record,
        "conteudo": rendered,
    }
