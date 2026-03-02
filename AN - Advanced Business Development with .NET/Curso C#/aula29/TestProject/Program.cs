// Parte 1 - Usar parâmetros em métodos
double pi = 3.14159;
PrintCircleInfo(12);
PrintCircleInfo(24);

void PrintCircleArea(int radius)
{
    double area = pi * (radius * radius);
    Console.WriteLine($"Area = {area}");
}

void PrintCircleCircumference(int radius)
{
    double circumference = 2 * pi * radius;
    Console.WriteLine($"Circumference = {circumference}");
}

void PrintCircleInfo(int radius) 
{
    Console.WriteLine($"Circle with radius {radius}");
    PrintCircleArea(radius);
    PrintCircleCircumference(radius);
}


// Parte 2 - Exercício 
/*
int a = 3;
int b = 4;
int c = 0;

Multiply(a, b, c);
Console.WriteLine($"global statement: {a} x {b} = {c}");

void Multiply(int a, int b, int c) 
{
    c = a * b;
    Console.WriteLine($"inside Multiply method: {a} x {b} = {c}");
}
*/

/*
int[] array = {1, 2, 3, 4, 5};

PrintArray(array);
Clear(array);
PrintArray(array);

void PrintArray(int[] array) 
{
    foreach (int a in array) 
    {
        Console.Write($"{a} ");
    }
    Console.WriteLine();
}

void Clear(int[] array) 
{
    for (int i = 0; i < array.Length; i++) 
    {
        array[i] = 0;
    }
}
*/

/*
string status = "Healthy";

Console.WriteLine($"Start: {status}");
SetHealth(false);
Console.WriteLine($"End: {status}");

void SetHealth(bool isHealthy) 
{
    status = (isHealthy ? "Healthy" : "Unhealthy");
    Console.WriteLine($"Middle: {status}");
}
*/


// Parte 3 - Exercício 
string[] guestList = {"Rebecca", "Nadia", "Noor", "Jonte"};
string[] rsvps = new string[10];
int count = 0;

void RSVP(string name, int partySize, string allergies, bool inviteOnly) 
{
    if (inviteOnly)
{
    bool found = false;
    foreach (string guest in guestList)
    {
        if (guest.Equals(name)) {
            found = true;
            break;
        }
    }
    if (!found)
    {
        Console.WriteLine($"Sorry, {name} is not on the guest list");
        return;
    }
}

    rsvps[count] = $"Name: {name}, \tParty Size: {partySize}, \tAllergies: {allergies}";
    count++;
}

void ShowRSVPs()
{
    Console.WriteLine("\nTotal RSVPs:");
    for (int i = 0; i < count; i++)
    {
        Console.WriteLine(rsvps[i]);
    }
}


// Parte 4 - Desafio
string[,] corporate = 
	{
		{"Robert", "Bavin"}, {"Simon", "Bright"},
		{"Kim", "Sinclair"}, {"Aashrita", "Kamath"},
		{"Sarah", "Delucchi"}, {"Sinan", "Ali"}};

	string[,] external = 
	{
		{"Vinnie", "Ashton"}, {"Cody", "Dysart"},
		{"Shay", "Lawrence"}, {"Daren", "Valdes"}
	};

	string externalDomain = "hayworth.com";

	for (int i = 0; i < corporate.GetLength(0); i++) 
	{
		DisplayEmail(first: corporate[i,0], last: corporate[i,1]);
	}

	for (int i = 0; i < external.GetLength(0); i++) 
	{
		DisplayEmail(first: external[i,0], last: external[i,1], domain: externalDomain);
	}

	void DisplayEmail(string first, string last, string domain = "contoso.com") 
	{
		string email = first.Substring(0, 2) + last;
		email = email.ToLower();
		Console.WriteLine($"{email}@{domain}");
	} 