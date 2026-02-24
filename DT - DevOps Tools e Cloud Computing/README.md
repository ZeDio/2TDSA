# Infra Lab 01 — Provisionamento Manual com Raciocínio de Arquitetura

## Objetivo de Aprendizagem

Praticar tomada de decisão de infraestrutura baseada em requisitos de negócio, documentar escolhas técnicas de forma estruturada, e refletir sobre implicações de escalabilidade e automação.

## Contexto

Uma startup está desenvolvendo uma aplicação interna para aproximadamente 15 funcionários.

O servidor precisará:

- Permitir acesso remoto seguro
- Executar serviços backend
- Ser estável durante horário comercial
- Possibilitar crescimento futuro
- Estar preparado para futura automação

Você deve provisionar manualmente uma Máquina Virtual no VirtualBox usando a imagem fornecida do Oracle Linux.

Nenhum requisito técnico será fornecido. Você deve tomar decisões arquiteturais fundamentadas.

## Tarefa

Provisione uma VM no VirtualBox utilizando a imagem do Oracle Linux fornecida.

Decida e justifique:

- Quantidade de RAM
- Número de CPUs
- Tamanho de disco
- Tipo de rede (NAT ou Bridge)
- Uso ou não de interface gráfica

Configure SSH funcional.

## Restrições

- Não utilize tutoriais passo a passo
- Todas as decisões devem ser justificadas tecnicamente
- Utilize IA, mas documente seu uso e valide as respostas
- A VM deve estar funcional e acessível via SSH

## Formato de Entrega

Estrutura obrigatória:

```
infra-lab-01/
│
├── vm-spec.yaml
├── evidence/
│   └── infra-check.txt
├── ai-usage.md
└── reflection.md
```

### 1. vm-spec.yaml

Especificação declarativa da infraestrutura:

```yaml
vm_name: ""
ram_mb: 0
cpus: 0
disk_gb: 0
network_mode: ""
os: "Oracle Linux (fornecido)"
ssh_enabled: true
justification:
  ram: ""
  cpu: ""
  disk: ""
  network: ""
```

Valores devem refletir suas decisões reais, não o exemplo acima.

### 2. evidence/infra-check.txt

Execute os comandos abaixo dentro da VM e cole a saída completa:

```bash
hostname
hostname -I
free -h
nproc
df -h
systemctl status ssh
```

Apenas texto. Sem capturas de tela.

### 3. ai-usage.md

Crie um arquivo chamado `ai-usage.md`.

Cole **integralmente** o histórico da conversa com a LLM utilizada durante a atividade.

Regras:

- Não edite o conteúdo.
- Não resuma.
- Não reorganize.
- Não remova mensagens.
- Inclua prompts e respostas.
- Se houver múltiplas conversas, inclua todas na ordem cronológica.

Formato esperado:

```markdown
# AI Usage Log

<cole aqui o histórico completo da conversa com a LLM>
```

A ausência do histórico completo invalida a atividade.

### 4. reflection.md

Responda:

1. Por que sua configuração atende o cenário da startup?
2. O que aconteceria se o número de usuários dobrasse?
3. Qual recurso você escalaria primeiro e por quê?
4. Qual é a principal diferença entre esta VM local e uma instância em nuvem?
5. Quais vantagens você enxerga em provisionar servidores na nuvem em vez de máquinas virtuais locais?

## Critérios de Avaliação

A atividade será analisada quanto a:

- Coerência entre decisões e cenário de negócio
- Clareza e fundamentação técnica das justificativas
- VM funcional com SSH operacional
- Documentação do uso de IA
- Reflexão sobre escalabilidade e infraestrutura em nuvem

Não existe configuração única correta. Existe decisão fundamentada ou arbitrária.

## Entrega

Commit e push no repositório do curso.
