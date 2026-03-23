// See https://aka.ms/new-console-template for more information
#region IO

var turma = "2TDSA";
var aula = ".NET";
Console.WriteLine($"""
                   Olá, {turma}, 
                   bem vindos a aula de {aula}!"
                   """);
Console.WriteLine("Aluno, me diga seu nome:");
var aluno = Console.ReadLine(); 
Console.WriteLine($"Sejá bem vindo a classe, {aluno}");

#endregion

#region Variáveis

Console.WriteLine("Qual a sua idade?");
var idade = Convert.ToInt16(Console.ReadLine());
Console.WriteLine($"Ok, então sua idade é de {idade} anos");

Console.WriteLine("Qual foi a sua média em JAVA?");
var media = Convert.ToDouble(Console.ReadLine());
Console.WriteLine($"Ok, então sua média em JAVA foi de {media} pontos");

Console.WriteLine("Você gostou da discipina de JAVA ano passado? (true/false)");
var gostou = Convert.ToBoolean(Console.ReadLine());

Console.WriteLine($"Ok, então você {(gostou ? "gostou" : "não gostou")} de JAVA");
#endregion

#region  Condicionais

// if, else if, else
// ==, !=, >, <, >=, <=
if(idade >= 18)
    Console.WriteLine("Você é um aluno de maior idade");
else
    Console.WriteLine("Você é um aluno de menor idade");

var mensagemSobreNota = media switch
{ 
    <= 7 and > 6 => "ok",
    <= 8 and > 7 => "bem",
    <= 9 and > 8 => "muito bem",
    <= 10 and > 9 => "excelente",
    _ => "ruim"
};

Console.WriteLine($"Parece que você foi {mensagemSobreNota} em JAVA");

#endregion

#region Loops

// while, do while, for, foreach
var contador = 0;
do
    Console.WriteLine(contador++);
while (contador <= 5);

foreach (var numero in Enumerable.Range(1,10))
    Console.WriteLine(numero);

#endregion