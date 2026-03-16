Random dice = new Random();
int roll = dice.Next(1, 7);
Console.WriteLine(roll);
Console.WriteLine();



Random dicee = new Random();
int roll1 = dicee.Next();
int roll2 = dicee.Next(101);
int roll3 = dicee.Next(50, 101);

Console.WriteLine($"First roll: {roll1}");
Console.WriteLine($"Second roll: {roll2}");
Console.WriteLine($"Third roll: {roll3}");
Console.WriteLine();



int firstValue = 500;
int secondValue = 600;
int largerValue;
largerValue = Math.Max(firstValue, secondValue);
Console.WriteLine(largerValue);