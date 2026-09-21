using Triangulos.Core;
using Xunit;

namespace Triangulos.Tests;

public class ClassificadorTrianguloTests
{
    [Theory]
    [InlineData(3.0, 4.0, 5.0)]
    [InlineData(4.0, 3.0, 5.0)]
    [InlineData(5.0, 4.0, 3.0)]
    [InlineData(2.5, 3.5, 4.0)]
    public void EhTriangulo_LadosValidos_RetornaVerdadeiro(double a, double b, double c)
    {
        Assert.True(ClassificadorTriangulo.EhTriangulo(a, b, c));
    }

    [Theory]
    [InlineData(1.0, 1.0, 2.0)]
    [InlineData(1.0, 2.0, 1.0)]
    [InlineData(2.0, 1.0, 1.0)]
    [InlineData(1.0, 1.0, 3.0)]
    [InlineData(1.0, 3.0, 1.0)]
    [InlineData(3.0, 1.0, 1.0)]
    [InlineData(0.0, 1.0, 1.0)]
    [InlineData(1.0, 0.0, 1.0)]
    [InlineData(1.0, 1.0, 0.0)]
    [InlineData(-1.0, 2.0, 2.0)]
    [InlineData(2.0, -1.0, 2.0)]
    [InlineData(2.0, 2.0, -1.0)]
    [InlineData(double.NaN, 2.0, 2.0)]
    [InlineData(double.PositiveInfinity, 2.0, 2.0)]
    [InlineData(double.NegativeInfinity, 2.0, 2.0)]
    public void EhTriangulo_LadosInvalidosOuDegenerados_RetornaFalso(double a, double b, double c)
    {
        Assert.False(ClassificadorTriangulo.EhTriangulo(a, b, c));
    }

    [Theory]
    [InlineData(3.0, 3.0, 3.0)]
    [InlineData(2.5, 2.5, 2.5)]
    [InlineData(1.0, 1.0, 1.0)]
    public void Classificar_TresLadosIguais_RetornaEquilatero(double a, double b, double c)
    {
        Assert.Equal(TipoTriangulo.Equilatero, ClassificadorTriangulo.Classificar(a, b, c));
    }

    [Theory]
    [InlineData(2.0, 2.0, 3.0)]
    [InlineData(2.0, 3.0, 2.0)]
    [InlineData(3.0, 2.0, 2.0)]
    [InlineData(2.5, 4.0, 2.5)]
    public void Classificar_ExatamenteDoisLadosIguaisEmQualquerPosicao_RetornaIsosceles(double a, double b, double c)
    {
        Assert.Equal(TipoTriangulo.Isosceles, ClassificadorTriangulo.Classificar(a, b, c));
    }

    [Theory]
    [InlineData(3.0, 4.0, 5.0)]
    [InlineData(4.0, 5.0, 6.0)]
    [InlineData(2.5, 3.0, 3.5)]
    public void Classificar_TresLadosDiferentes_RetornaEscaleno(double a, double b, double c)
    {
        Assert.Equal(TipoTriangulo.Escaleno, ClassificadorTriangulo.Classificar(a, b, c));
    }

    [Theory]
    [InlineData(1.0, 1.0, 2.0)]
    [InlineData(0.0, 2.0, 2.0)]
    [InlineData(-1.0, 2.0, 2.0)]
    [InlineData(double.NaN, 2.0, 2.0)]
    public void Classificar_LadosInvalidos_RetornaNaoEhTriangulo(double a, double b, double c)
    {
        Assert.Equal(TipoTriangulo.NaoEhTriangulo, ClassificadorTriangulo.Classificar(a, b, c));
    }
}
