using Triangulos.Core;
using Xunit;

namespace Triangulos.Tests;

public class CalculadoraTrianguloTests
{
    [Theory]
    [InlineData(3.0, 4.0, 5.0, 12.0)]
    [InlineData(2.0, 2.0, 3.0, 7.0)]
    [InlineData(1.5, 2.5, 3.0, 7.0)]
    [InlineData(5.0, 4.0, 3.0, 12.0)]
    public void Perimetro_TrianguloValido_RetornaSomaDosLados(double a, double b, double c, double esperado)
    {
        Assert.Equal(esperado, CalculadoraTriangulo.Perimetro(a, b, c));
    }

    [Theory]
    [InlineData(1.0, 1.0, 1.0, 0.43)]
    [InlineData(3.0, 4.0, 5.0, 6.00)]
    [InlineData(2.0, 2.0, 3.0, 1.98)]
    [InlineData(1.0, 2.0, 2.0, 0.97)]
    public void Area_TrianguloValido_RetornaHeronArredondadaEmDuasCasas(double a, double b, double c, double esperado)
    {
        Assert.Equal(esperado, CalculadoraTriangulo.Area(a, b, c), 2);
    }

    [Theory]
    [InlineData(2.0, 3.0, 4.0, 28.96, 46.57, 104.48)]
    [InlineData(3.0, 4.0, 5.0, 36.87, 53.13, 90.00)]
    [InlineData(2.0, 2.0, 3.0, 41.41, 41.41, 97.18)]
    public void Angulos_TrianguloValido_RetornaAngulosCorretos(double a, double b, double c, double esperadoA, double esperadoB, double esperadoC)
    {
        var angulos = CalculadoraTriangulo.Angulos(a, b, c);

        Assert.Equal(esperadoA, angulos.A, 2);
        Assert.Equal(esperadoB, angulos.B, 2);
        Assert.Equal(esperadoC, angulos.C, 2);
        Assert.InRange(angulos.A + angulos.B + angulos.C, 179.99, 180.01);
    }

    [Theory]
    [InlineData(3.0, 4.0, 5.0)]
    [InlineData(5.0, 3.0, 4.0)]
    [InlineData(4.0, 5.0, 3.0)]
    public void EhRetangulo_TrianguloRetanguloComHipotenusaEmQualquerPosicao_RetornaVerdadeiro(double a, double b, double c)
    {
        Assert.True(CalculadoraTriangulo.EhRetangulo(a, b, c));
    }

    [Theory]
    [InlineData(3.0, 4.0, 5.0000000001)]
    [InlineData(5.0, 3.0, 4.0000000001)]
    public void EhRetangulo_TrianguloProximoDaIgualdadeDentroDaTolerancia_RetornaVerdadeiro(double a, double b, double c)
    {
        Assert.True(CalculadoraTriangulo.EhRetangulo(a, b, c));
    }

    [Theory]
    [InlineData(3.0, 4.0, 6.0, TipoPorAngulo.Obtusangulo)]
    [InlineData(3.0, 4.0, 4.0, TipoPorAngulo.Acutangulo)]
    [InlineData(3.0, 4.0, 5.0, TipoPorAngulo.Retangulo)]
    [InlineData(5.0, 3.0, 4.0, TipoPorAngulo.Retangulo)]
    public void ClassificarPorAngulo_TrianguloValido_RetornaClassificacaoCorreta(double a, double b, double c, TipoPorAngulo esperado)
    {
        Assert.Equal(esperado, CalculadoraTriangulo.ClassificarPorAngulo(a, b, c));
    }

    [Theory]
    [InlineData(1.0, 1.0, 2.0)]
    [InlineData(0.0, 2.0, 2.0)]
    [InlineData(-1.0, 2.0, 2.0)]
    public void Perimetro_LadosInvalidos_LancaExcecao(double a, double b, double c)
    {
        Assert.Throws<TrianguloInvalidoException>(() => CalculadoraTriangulo.Perimetro(a, b, c));
    }

    [Theory]
    [InlineData(1.0, 1.0, 2.0)]
    [InlineData(0.0, 2.0, 2.0)]
    public void Area_LadosInvalidos_LancaExcecao(double a, double b, double c)
    {
        Assert.Throws<TrianguloInvalidoException>(() => CalculadoraTriangulo.Area(a, b, c));
    }

    [Theory]
    [InlineData(1.0, 1.0, 2.0)]
    [InlineData(0.0, 2.0, 2.0)]
    public void Angulos_LadosInvalidos_LancaExcecao(double a, double b, double c)
    {
        Assert.Throws<TrianguloInvalidoException>(() => CalculadoraTriangulo.Angulos(a, b, c));
    }

    [Theory]
    [InlineData(1.0, 1.0, 2.0)]
    [InlineData(0.0, 2.0, 2.0)]
    public void EhRetangulo_LadosInvalidos_LancaExcecao(double a, double b, double c)
    {
        Assert.Throws<TrianguloInvalidoException>(() => CalculadoraTriangulo.EhRetangulo(a, b, c));
    }

    [Theory]
    [InlineData(1.0, 1.0, 2.0)]
    [InlineData(0.0, 2.0, 2.0)]
    public void ClassificarPorAngulo_LadosInvalidos_LancaExcecao(double a, double b, double c)
    {
        Assert.Throws<TrianguloInvalidoException>(() => CalculadoraTriangulo.ClassificarPorAngulo(a, b, c));
    }
}
