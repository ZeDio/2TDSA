using Triangulos.Core;
using Xunit;

namespace Triangulos.Tests;

public class PropriedadesTrianguloTests
{
    [Theory]
    [InlineData(3.0, 4.0, 5.0)]
    [InlineData(2.0, 3.0, 4.0)]
    [InlineData(2.0, 2.0, 3.0)]
    public void Classificar_TrocandoOrdemDosLados_MantemResultado(double a, double b, double c)
    {
        var original = ClassificadorTriangulo.Classificar(a, b, c);
        var permutacoes = new[]
        {
            ClassificadorTriangulo.Classificar(a, c, b),
            ClassificadorTriangulo.Classificar(b, a, c),
            ClassificadorTriangulo.Classificar(b, c, a),
            ClassificadorTriangulo.Classificar(c, a, b),
            ClassificadorTriangulo.Classificar(c, b, a)
        };

        Assert.All(permutacoes, resultado => Assert.Equal(original, resultado));
    }

    [Theory]
    [InlineData(3.0, 4.0, 5.0, 2.0)]
    [InlineData(2.0, 3.0, 4.0, 3.0)]
    public void Angulos_EscalarTodosOsLados_MantemAngulos(double a, double b, double c, double fator)
    {
        var original = CalculadoraTriangulo.Angulos(a, b, c);
        var escalado = CalculadoraTriangulo.Angulos(a * fator, b * fator, c * fator);

        Assert.Equal(original.A, escalado.A, 2);
        Assert.Equal(original.B, escalado.B, 2);
        Assert.Equal(original.C, escalado.C, 2);
    }
}
