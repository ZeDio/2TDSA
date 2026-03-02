namespace OrientacaoObjetos;

public class Movie : Title
{
    public int? Duracao { get; set; }
    
    public override string ToString() => $"Title: {Name} - MetaScore: {MataScore} - Duração: {Duracao}";
}