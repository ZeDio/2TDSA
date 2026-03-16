void setup() {
  Serial.begin(9600);
}

unsigned long tempoAnterior = 0;
unsigned long intervalo = 1000;

void loop() {

  unsigned long tempoAtual = millis();
  unsigned long segundos = tempoAtual / 1000;

  // Define intervalo dinamicamente
  if (segundos <= 10) {
    intervalo = 1000; // 1 segundo
  }
  else if (segundos <= 20) {
    intervalo = 2000; // 2 segundos
  }
  else {
    intervalo = 5000; // 5 segundos
  }

  if (tempoAtual - tempoAnterior >= intervalo) {

    tempoAnterior = tempoAtual;

    Serial.print("Tempo decorrido: ");
    Serial.print(segundos);
    Serial.println(" segundos");
  }

}