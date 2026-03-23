// See https://aka.ms/new-console-template for more information

using LinqFaroShuffle;

Console.WriteLine("Hello, World!");
var startingDeck = (
    from s in Suits() 
    from r in Ranks()
    select (Suit: s, Rank: r)).ToList();

//foreach (var card in startingDeck)
    //Console.WriteLine(card);

var top = startingDeck.Take(26);
var bottom = startingDeck.Skip(26);

var shuffledDeck = top.InterleaveSequenceWith(bottom);
//    foreach (var card in shuffledDeck)
//      Console.WriteLine(card);

var times = 0;
shuffledDeck = startingDeck;
do
{
    Console.WriteLine($"Shuffle {times++}");
    shuffledDeck = shuffledDeck.Take(26).InterleaveSequenceWith(shuffledDeck.Skip(26));
    foreach (var card in shuffledDeck)
        Console.WriteLine(card);
} while (!startingDeck.SequenceEquals(shuffledDeck));

Console.WriteLine($"It took {times} to deck going back to starting state");


static IEnumerable<string> Suits()
{
    yield return "clubs"; // yield é uma keyword que em um foreach ele vai passar e retorna operação item a item
    yield return "diamonds";
    yield return "hearts";
    yield return "spades";
}

static IEnumerable<string> Ranks()
{
    yield return "two";
    yield return "three";
    yield return "four";
    yield return "five";
    yield return "six";
    yield return "seven";
    yield return "eight";
    yield return "nine";
    yield return "ten";
    yield return "jack";
    yield return "queen";
    yield return "king";
    yield return "ace";
}