using OrientacaoObjetos;

Console.WriteLine("Bem Vindo ao sistema de reviews");
Console.WriteLine("");

var newTitulo = new Movie();
newTitulo.Name = "The Batman";
newTitulo.Reviews.Add(new Review(){Rating = 100, Comment = "Sla!!", Ower = "FJDBHJKF"});
newTitulo.Reviews.Add(new Review(){Rating = 98, Comment = "Sla!!", Ower = "HJGIUH"});
newTitulo.Reviews.Add(new Review(){Rating = 95, Comment = "Sla!!", Ower = "JBIJB"});
newTitulo.Duracao = 120;
Console.WriteLine($"{newTitulo}");

var newTitulo2 = new Game();
newTitulo2.Name = "Roblox";
newTitulo2.Reviews.Add(new Review(){Rating = 70, Comment = "Sla!!", Ower = "FJDBHJKF"});
newTitulo2.Reviews.Add(new Review(){Rating = 98, Comment = "Sla!!", Ower = "HJGIUH"});
newTitulo2.Reviews.Add(new Review(){Rating = 95, Comment = "Sla!!", Ower = "JBIJB"});
newTitulo2.Plataforma = "PC";
Console.WriteLine($"{newTitulo2}");