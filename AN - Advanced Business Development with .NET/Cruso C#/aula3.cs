Console.WriteLine("Hello\nWorld!");
// \n vai pular linha
Console.WriteLine("Hello\tWorld!");
// \t vai dar um espaçamento entre o texto

Console.WriteLine("c:\\source\\repos");
// pra imprimir um barra tem que usar duas

Console.WriteLine("\nGenerating invoices for customer \"Contoso Corp\" ... \n");
Console.WriteLine("Invoice: 1021\t\tComplete!");
Console.WriteLine("Invoice: 1022\t\tComplete!");
Console.Write("\nOutput Directory:\t \n");
// lealt de mine sistema

Console.WriteLine(@"    c:\source\repos    
        (this is where your code goes)");
// com @ ele respeita os espaços

Console.WriteLine("\nGenerating invoices for customer \"Contoso Corp\" ... \n");
Console.WriteLine("Invoice: 1021\t\tComplete!");
Console.WriteLine("Invoice: 1022\t\tComplete!");
Console.Write("\nOutput Directory:\t");
Console.Write(@"c:\invoices\n");

Console.WriteLine("\u3053\u3093\u306B\u3061\u306F World!");
Console.Write("\n\n\u65e5\u672c\u306e\u8acb\u6c42\u66f8\u3092\u751f\u6210\u3059\u308b\u306b\u306f\uff1a\n\t");
Console.WriteLine(@"c:\invoices\app.exe -j");
// Simbolos em outras linguas com codigos

string firstName = "Bob";
string message = "Hello " + firstName;
Console.WriteLine(message);
// Imprime tudo em uma linha com WriteLine

string firstNamee = "Bob";
string messagee = $"Hello {firstNamee}!";
Console.WriteLine(message);
// como $ mais uma variavel dentro {} permite

int version = 11;
string updateText = "Update to Windows";
string messageee = $"{updateText} {version}";
Console.WriteLine(messageee);
// Funciona tambem com numeros

int version1 = 11;
string updateText1 = "Update to Windows";
Console.WriteLine($"{updateText1} {version1}!");
// verção resumida

string projectName = "First-Project";
Console.WriteLine($@"C:\Output\{projectName}\Data");
//Exemplo

//Desafio
Console.WriteLine("\n");
string projectName1 = "ACME";
Console.WriteLine($@"View English output:");
Console.WriteLine($@"   C:\Exercise\{projectName1}\data.txt");
string russianMessage = "\u041f\u043e\u0441\u043c\u043e\u0442\u0440\u0435\u0442\u044c \u0440\u0443\u0441\u0441\u043a\u0438\u0439 \u0432\u044b\u0432\u043e\u0434";
Console.WriteLine($@"Посмотреть русский вывод:");
Console.WriteLine($@"   C:\Exercise\{russianMessage }\data.txt");