// Definir o pino do buzzer e do LED
int buzzerPin = 12;  // O buzzer está conectado ao pino digital 12
int ledPin = 13;     // O LED está conectado ao pino digital 13

// Função para emitir som Morse para cada letra e piscar o LED
void playMorse(char letter) {
  String morseCode;

  // Código Morse para cada letra
  switch (letter) {
    case 'A': morseCode = ".-"; break;
    case 'B': morseCode = "-..."; break;
    case 'C': morseCode = "-.-."; break;
    case 'D': morseCode = "-.."; break;
    case 'E': morseCode = "."; break;
    case 'F': morseCode = "..-."; break;
    case 'G': morseCode = "--."; break;
    case 'H': morseCode = "...."; break;
    case 'I': morseCode = ".."; break;
    case 'J': morseCode = ".---"; break;
    case 'K': morseCode = "-.-"; break;
    case 'L': morseCode = ".-.."; break;
    case 'M': morseCode = "--"; break;
    case 'N': morseCode = "-."; break;
    case 'O': morseCode = "---"; break;
    case 'P': morseCode = ".--."; break;
    case 'Q': morseCode = "--.-"; break;
    case 'R': morseCode = ".-."; break;
    case 'S': morseCode = "..."; break;
    case 'T': morseCode = "-"; break;
    case 'U': morseCode = "..-"; break;
    case 'V': morseCode = "...-"; break;
    case 'W': morseCode = ".--"; break;
    case 'X': morseCode = "-..-"; break;
    case 'Y': morseCode = "-.--"; break;
    case 'Z': morseCode = "--.."; break;
    default: morseCode = ""; break;
  }

  // Percorre o código Morse e emite o som e pisca o LED correspondente
  for (int i = 0; i < morseCode.length(); i++) {
    if (morseCode.charAt(i) == '.') {
      tone(buzzerPin, 1000);   // Ponto, som curto
      digitalWrite(ledPin, HIGH);  // Acende o LED
      delay(300);                  // Espera por 300ms
    } else if (morseCode.charAt(i) == '-') {
      tone(buzzerPin, 1000);   // Traço, som longo
      digitalWrite(ledPin, HIGH);  // Acende o LED
      delay(700);                  // Espera por 700ms
    }
    noTone(buzzerPin);         // Para o som
    digitalWrite(ledPin, LOW);  // Apaga o LED
    delay(300);                // Intervalo entre os sinais
  }
  delay(700); // Pausa entre as letras
}

void setup() {
  pinMode(buzzerPin, OUTPUT); // Define o pino do buzzer como saída
  pinMode(ledPin, OUTPUT);    // Define o pino do LED como saída
}

void loop() {
  // Exemplo de letras que vão ser emitidas e o LED piscando
  playMorse('V');
  playMorse('I');
  playMorse('T');
  playMorse('O');
  playMorse('R');

  delay(3000); // Pausa de 3 segundos antes de repetir
}