using Triangulos.Core;
using Xunit;

namespace Triangulos.Tests;

public class AnalisadorTrianguloTests
{
    [Fact]
    public void Analisar_Triangulo345_RetornaAnaliseCompleta()
    {
        var analise = AnalisadorTriangulo.Analisar(3, 4, 5);

        Assert.Equal(TipoTriangulo.Escaleno, analise.Tipo);
        Assert.Equal(TipoPorAngulo.Retangulo, analise.TipoPorAngulo);
        Assert.Equal(12.0, analise.Perimetro);
        Assert.Equal(6.0, analise.Area);
        Assert.Equal(36.87, analise.Angulos.A, 2);
        Assert.Equal(53.13, analise.Angulos.B, 2);
        Assert.Equal(90.0, analise.Angulos.C, 2);
    }

    [Fact]
    public void Analisar_TrianguloInvalido_LancaExcecao()
    {
        Assert.Throws<TrianguloInvalidoException>(() => AnalisadorTriangulo.Analisar(1, 1, 2));
    }
}
