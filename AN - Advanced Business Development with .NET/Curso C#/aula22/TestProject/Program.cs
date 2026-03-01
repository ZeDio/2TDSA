// Converção de dados
int first7 = 2;
string second7 = "4";
string result7 = first7 + second7;
Console.WriteLine(result7);


int myInt6 = 3;
Console.WriteLine($"int: {myInt6}");
decimal myDecimal6 = myInt6;
Console.WriteLine($"decimal: {myDecimal6}");


decimal myDecimal2 = 3.14m;
Console.WriteLine($"decimal: {myDecimal2}");
int myInt2 = (int)myDecimal2;
Console.WriteLine($"int: {myInt2}");


decimal myDecimal3 = 1.23456789m;
float myFloat3 = (float)myDecimal3;
Console.WriteLine($"Decimal: {myDecimal3}");
Console.WriteLine($"Float  : {myFloat3}");


int first1 = 5;
int second1 = 7;
string message1 = first1.ToString() + second1.ToString();
Console.WriteLine(message1);


string first2 = "5";
string second2 = "7";
int sum2 = int.Parse(first2) + int.Parse(second2);
Console.WriteLine(sum2);


string value11 = "5";
string value22 = "7";
int result2 = Convert.ToInt32(value11) * Convert.ToInt32(value22);
Console.WriteLine(result2);


int value3 = (int)1.5m; // casting truncates
Console.WriteLine(value3);
int value23 = Convert.ToInt32(1.5m); // converting rounds up
Console.WriteLine(value23);


// Parte 2 - método TryParse()
string value = "bad";
int result = 0;
if (int.TryParse(value, out result))
{
   Console.WriteLine($"Measurement: {result}");
}
else
{
   Console.WriteLine("Unable to report the measurement.");
}
if (result > 0)
   Console.WriteLine($"Measurement (w/ offset): {50 + result}");


// Parte 3 - Desafio
string[] values = { "12.3", "45", "ABC", "11", "DEF" };
decimal total = 0m;
string message = "";
foreach (var value in values)
{
    decimal number; // stores the TryParse "out" value
    if (decimal.TryParse(value, out number))
    {
        total += number;
    } else
    {
        message += value;
    }
}
Console.WriteLine($"Message: {message}");
Console.WriteLine($"Total: {total}");


// Parte 4 - Mais um desafio
int value1 = 11;
decimal value2 = 6.2m;
float value3 = 4.3f;

// The Convert class is best for converting the fractional decimal numbers into whole integer numbers
// Convert.ToInt32() rounds up the way you would expect.
int result111 = Convert.ToInt32(value111 / value222);
Console.WriteLine($"Divide value1 by value2, display the result as an int: {result111}");

decimal result222 = value222 / (decimal)value333;
Console.WriteLine($"Divide value2 by value3, display the result as a decimal: {result222}");

float result333 = value333 / value111;
Console.WriteLine($"Divide value3 by value1, display the result as a float: {result333}");