String comando = ""; // Variável para armazenar o comando recebido
const int ledPin = 10;

void setup() {
  Serial.begin(9600);
  pinMode(ledPin, OUTPUT); // Define o pino como saída
  Serial.println("Digite LIGAR ou DESLIGAR para controlar o LED");
}

void loop() {
  if (Serial.available()) { // Verifica se há dados disponíveis para leitura
    comando = Serial.readStringUntil('\n'); // Lê a string até encontrar uma quebra de linha
    comando.trim(); // Remove espaços e quebras de linha extras

    if (comando.equalsIgnoreCase("LIGAR")) {
      digitalWrite(ledPin, HIGH); // Acende o LED no pino
      Serial.println("LED Ligado!");
    } else if (comando.equalsIgnoreCase("DESLIGAR")) {
      digitalWrite(ledPin, LOW); // Apaga o LED no pino
      Serial.println("LED Desligado!");
    } else {
      Serial.println("Comando não reconhecido. Use LIGAR ou DESLIGAR");
    }
  }
}