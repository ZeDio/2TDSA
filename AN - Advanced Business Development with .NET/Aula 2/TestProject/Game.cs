namespace OrientacaoObjetos;

public class Game  : Title
{
    public string? Plataforma { get; set; }
    
    public override string ToString() => $"Title: {Name} - MetaScore: {MataScore} - Plataforma: {Plataforma}";
    
}