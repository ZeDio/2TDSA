/*
// Parte 1 - Loop For
for (int i = 0; i < 10; i++)
{
    Console.WriteLine(i);
}
0 1 2 3 4 5 6 7 8 9 10


for (int i = 10; i >= 0; i--)
{
    Console.WriteLine(i);
}
10 9 8 7 6 5 4 3 2 1 0


for (int i = 0; i < 10; i += 3)
{
    Console.WriteLine(i);
}
0 3 6 9


for (int i = 0; i < 10; i++)
{
    Console.WriteLine(i);
    if (i == 7) break;
}
0 1 2 3 4 5 6 7


string[] names = { "Alex", "Eddie", "David", "Michael" };
for (int i = names.Length - 1; i >= 0; i--)
{
    Console.WriteLine(names[i]);
}
Michael 
David
Eddie
Alex


string[] names = { "Alex", "Eddie", "David", "Michael" };
for (int i = 0; i < names.Length; i++)
    if (names[i] == "David") names[i] = "Sammy";

foreach (var name in names) Console.WriteLine(name);
Alex
Eddie
Sammy
Michael
*/

// Parte 2 - Desafio

for (int i = 1; i < 101; i++)
{
    if ((i % 3 == 0) && (i % 5 == 0))
        Console.WriteLine($"{i} - FizzBuzz");
    else if (i % 3 == 0)
        Console.WriteLine($"{i} - Fizz");
    else if (i % 5 == 0)
        Console.WriteLine($"{i} - Buzz");
    else
        Console.WriteLine($"{i}");
}