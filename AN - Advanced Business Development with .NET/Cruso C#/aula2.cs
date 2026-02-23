Console.WriteLine('b');
// Imprimi b no console

// Console.WriteLine('Hello World!');
// Da erro no console por conta que é um monte de caracteres, em vez de um so, por conta das aspas simples

Console.WriteLine(123);
// Imprime os numeros

Console.WriteLine(0.25F);
// f ou F de Float

Console.WriteLine(2.625);
Console.WriteLine(12.39816m);
// m ou M indica para trabalhar com decimal

Console.WriteLine(true);
Console.WriteLine(false);
// termo literal booleano ou conhecido como bool

string firstName;
// Criação de uma variavel
firstName = "Bob";
// Atribuindo valor a variavel firstName
Console.WriteLine(firstName);
// Imprimindo a variavel no console
firstName = "José";
// Substituindo o valor da variavel
Console.WriteLine(firstName);
// Imprimindo a variavel no console

string sla;
// Console.WriteLine(sla);
// Erro por conta que não tem nenhum valor atribuido a variavel

string firstNamee = "Bob";
Console.WriteLine(firstNamee);
// Cria a variavel, atribui valor a variavel e imprime no console

var message = "Hello world!";
var num = 18;
// var é usado como palavra-chave, ele pega tanto para numero ou para string, mas não pode declarar ele sem dar algum valor


// Desafio final
Console.WriteLine("Desafio final - Atribuindo alguns valores aleatorios em variaveis aleatorias para juntar em uma fraze numa linha de WriteLine");
var nome = "José Diogo";
var idade = 18;
var dinheiro = 4010.00m;
Console.WriteLine("Oi " + nome + ", você tem " + idade + " e tem R$:" + dinheiro + " em sua conta.");