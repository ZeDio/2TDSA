// See https://aka.ms/new-console-template for more information

using HtmlAgilityPack;

Console.WriteLine("Iniciando Scrapping do site...");

var urls = new[]
{
    "https://www.metacritic.com/game/the-legend-of-zelda-ocarina-of-time/",
    "https://www.metacritic.com/game/soulcalibur/",
    "https://www.metacritic.com/game/grand-theft-auto-iv/",
    "https://www.metacritic.com/game/super-mario-galaxy/",
    "https://www.metacritic.com/game/super-mario-galaxy-2/",
    "https://www.metacritic.com/game/the-legend-of-zelda-breath-of-the-wild/",
    "https://www.metacritic.com/game/the-legend-of-zelda-twilight-princess/",
    "https://www.metacritic.com/game/half-life-2/",
    "https://www.metacritic.com/game/the-witcher-3-wild-hunt/",
    "https://www.metacritic.com/game/the-legend-of-zelda-the-wind-waker/",
    "https://www.metacritic.com/game/soulcalibur-ii/",
    "https://www.metacritic.com/game/soulcalibur-iii/",
    "https://www.metacritic.com/game/soulcalibur-iv/",
    "https://www.metacritic.com/game/soulcalibur-v/",
    "https://www.metacritic.com/game/soulcalibur-vi/",
    "https://www.metacritic.com/game/fifa-14/",
    "https://www.metacritic.com/game/fifa-15/",
    "https://www.metacritic.com/game/fifa-16/",
    "https://www.metacritic.com/game/fifa-17/",
    "https://www.metacritic.com/game/fifa-18/",
    "https://www.metacritic.com/game/fifa-19/",
    "https://www.metacritic.com/game/fifa-20/",
    "https://www.metacritic.com/game/fifa-21/",
    "https://www.metacritic.com/game/fifa-22/",
    "https://www.metacritic.com/game/fifa-23/",
};

var titles = new List<string>();

Parallel.ForEach(urls,  url => {
    Task.Run(() => titles.Add(ScrappingUrl(url)));
});

while (titles.Count < urls.Length)
{
    //Console.WriteLine("Scrapping em andamento...");
}

titles.ForEach(Console.WriteLine);

return;

static string ScrappingUrl(string url)
{
    var web = new HtmlWeb();
    var doc =
         web.Load(url);

    if (doc == null)
    {
        Console.WriteLine("Html não encontrado");
        return "Não encontrado";
    }

//Testar se o documento foi baixado        
//Console.WriteLine(doc.DocumentNode.InnerHtml);
    var gameTitle = doc.DocumentNode.SelectSingleNode("//h1")?.InnerText ?? "Não encontrado";

    var gamePlataform = doc.DocumentNode
        .SelectSingleNode("//div[@class='game-platform-logo__text']")?.InnerText ?? "Não encontrado";

    var gameReleaseDate = doc.DocumentNode.SelectSingleNode("//div[@class='hero-release-date__value']")?
        .InnerText ?? "Não encontrar";

    var gameMetaScore = doc.DocumentNode.SelectSingleNode(
            "/html/body/div[1]/div[2]/main/div/div/div/section[1]/div/div[3]/div[4]/div/div[1]/div[1]/div/div[2]/div[2]/div/span")
        ?
        .InnerText ?? "Não encontrado";

    var gameUserScore = doc.DocumentNode.SelectSingleNode(
            "/html/body/div[1]/div[2]/main/div/div/div/section[1]/div/div[3]/div[4]/div/div[2]/div[1]/div/div[2]/div/div/span")
        ?
        .InnerText ?? "Não encontrado";

    //Console.WriteLine($"Title: {gameTitle}");
    //Console.WriteLine($"Plataform: {gamePlataform}");
    //Console.WriteLine($"Release Date: {gameReleaseDate}");
    //Console.WriteLine($"MetaScore: {gameMetaScore}");
    //Console.WriteLine($"UserScore: {gameUserScore}");
    return gameTitle;
}
  