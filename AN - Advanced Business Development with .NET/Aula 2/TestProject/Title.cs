// namespace é a organização de classes, interfaces de um projeto

namespace OrientacaoObjetos;

public abstract class Title
{
    public string? Name {get; set;}
    public int MataScore => Reviews.Count == 0 ? 0 : 
        (int) Reviews.Average(r => r.Rating);
    public List<Review> Reviews { get; } = [];
    public override string ToString() => $"Title: {Name} - MetaScore: {MataScore} - Reviews: {Reviews.Count}";
}