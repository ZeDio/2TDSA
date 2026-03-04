int firstNumber = 12;
int secondNumber = 7;
Console.WriteLine(firstNumber + secondNumber);
// Soma de numeros

string firstName = "Bob";
int widgetsSold = 7;
Console.WriteLine(firstName + " sold " + widgetsSold + " widgets.");
// Juntando variavel de string com variavel de number em um WriteLine

string firstName1 = "Bob";
int widgetsSold1 = 7;
Console.WriteLine(firstName1 + " sold " + (widgetsSold1 + 7) + " widgets.");
// Fazendo operação com a variavel de numero no propio WriteLine

int sum = 7 + 5;
int difference = 7 - 5;
int product = 7 * 5;
int quotient = 7 / 5;
Console.WriteLine("Sum: " + sum);
Console.WriteLine("Difference: " + difference);
Console.WriteLine("Product: " + product);
Console.WriteLine("Quotient: " + quotient);
// exemplos de operações

decimal decimalQuotient = 7.0m / 5;
Console.WriteLine($"Decimal quotient: {decimalQuotient}");
// Resultado da divição completo com o M de decimal

Console.WriteLine($"Modulus of 200 / 5 : {200 % 5}");
Console.WriteLine($"Modulus of 7 / 5 : {7 % 5}");
// código para determinar o resto após a divisão de inteiros

int value1 = 3 + 4 * 5;
int value2 = (3 + 4) * 5;
Console.WriteLine(value1);
Console.WriteLine(value2);
// Operação feita por ordem por conta do ()

int value = 1;

value = value + 1;
Console.WriteLine("First increment: " + value);

value += 1;
Console.WriteLine("Second increment: " + value);
value++;
Console.WriteLine("Third increment: " + value);
value = value - 1;
Console.WriteLine("First decrement: " + value);
value -= 1;
Console.WriteLine("Second decrement: " + value);
value--;
Console.WriteLine("Third decrement: " + value);
// Mostrando como incrementar e decrementar valores

// Desafio 
int fahrenheit = 94;
decimal Celsius = (fahrenheit - 32m) * (5m/9m);
Console.WriteLine($"The temperature is {Celsius} Celsius.");