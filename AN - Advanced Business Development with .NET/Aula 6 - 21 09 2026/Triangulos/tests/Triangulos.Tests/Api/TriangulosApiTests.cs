using System.Net;
using System.Net.Http.Json;
using System.Text.Json;
using Microsoft.AspNetCore.Mvc.Testing;
using Xunit;

namespace Triangulos.Tests.Api;

public class TriangulosApiTests : IClassFixture<WebApplicationFactory<Program>>
{
    private readonly HttpClient _client;

    public TriangulosApiTests(WebApplicationFactory<Program> factory) => _client = factory.CreateClient();

    [Fact]
    public async Task Health_EndpointValido_Retorna200EOk()
    {
        var resposta = await _client.GetAsync("/health");
        var json = await resposta.Content.ReadFromJsonAsync<JsonElement>();

        Assert.Equal(HttpStatusCode.OK, resposta.StatusCode);
        Assert.Equal("ok", json.GetProperty("status").GetString());
    }

    [Fact]
    public async Task Classificar_Triangulo345_RetornaEscaleno()
    {
        var resposta = await _client.GetAsync("/api/triangulos/classificar?a=3&b=4&c=5");
        var json = await resposta.Content.ReadFromJsonAsync<JsonElement>();

        Assert.Equal(HttpStatusCode.OK, resposta.StatusCode);
        Assert.Equal("Escaleno", json.GetProperty("tipo").GetString());
    }

    [Fact]
    public async Task Classificar_TrianguloInexistente_RetornaNaoEhTrianguloCom200()
    {
        var resposta = await _client.GetAsync("/api/triangulos/classificar?a=1&b=1&c=2");
        var json = await resposta.Content.ReadFromJsonAsync<JsonElement>();

        Assert.Equal(HttpStatusCode.OK, resposta.StatusCode);
        Assert.Equal("NaoEhTriangulo", json.GetProperty("tipo").GetString());
    }

    [Fact]
    public async Task Classificar_ParametroAusente_Retorna400()
    {
        var resposta = await _client.GetAsync("/api/triangulos/classificar?a=3&b=4");

        Assert.Equal(HttpStatusCode.BadRequest, resposta.StatusCode);
    }

    [Fact]
    public async Task Classificar_ParametroNaoNumerico_Retorna400()
    {
        var resposta = await _client.GetAsync("/api/triangulos/classificar?a=abc&b=4&c=5");

        Assert.Equal(HttpStatusCode.BadRequest, resposta.StatusCode);
    }

    [Fact]
    public async Task Analisar_Triangulo345_Retorna200EAnaliseCompleta()
    {
        var resposta = await _client.PostAsJsonAsync("/api/triangulos/analisar", new { a = 3, b = 4, c = 5 });
        var json = await resposta.Content.ReadFromJsonAsync<JsonElement>();

        Assert.Equal(HttpStatusCode.OK, resposta.StatusCode);
        Assert.Equal("Escaleno", json.GetProperty("tipo").GetString());
        Assert.Equal("Retangulo", json.GetProperty("tipoPorAngulo").GetString());
        Assert.Equal(12, json.GetProperty("perimetro").GetDouble());
        Assert.Equal(6, json.GetProperty("area").GetDouble());
    }

    [Fact]
    public async Task Analisar_LadosQueNaoFormamTriangulo_Retorna422()
    {
        var resposta = await _client.PostAsJsonAsync("/api/triangulos/analisar", new { a = 1, b = 1, c = 2 });

        Assert.Equal(HttpStatusCode.UnprocessableEntity, resposta.StatusCode);
    }

    [Fact]
    public async Task Analisar_JsonMalformado_Retorna400()
    {
        using var conteudo = new StringContent("{\"a\":3,", System.Text.Encoding.UTF8, "application/json");
        var resposta = await _client.PostAsync("/api/triangulos/analisar", conteudo);

        Assert.Equal(HttpStatusCode.BadRequest, resposta.StatusCode);
    }

    [Fact]
    public async Task Analisar_CorpoAusente_Retorna400()
    {
        var resposta = await _client.PostAsync("/api/triangulos/analisar", null);

        Assert.Equal(HttpStatusCode.BadRequest, resposta.StatusCode);
    }
}
