#include <LiquidCrystal_I2C.h>

#define COLUNAS 16
#define LINHAS  2
int led = 8;
int botao = 2;
const int intervalo = 1000;
unsigned long tempo_agora = 0;
unsigned long tempo_anterior = 0;
bool finalizado = false;
bool iniciou = false;

LiquidCrystal_I2C lcd(0x27, COLUNAS, LINHAS);  

void setup(){
  pinMode(led, OUTPUT);
  pinMode(botao, INPUT_PULLUP);
  Serial.begin(9600);

  lcd.init();                   
  lcd.backlight();
  lcd.setCursor(0, 0);
  lcd.print("Iniciando..");
  lcd.setCursor(0, 1);
  lcd.print("Tempo:");
  lcd.setCursor(7, 1);
  lcd.print(tempo_agora);
}

void loop(){
  if (tempo_agora > 0){
    digitalWrite(led, HIGH);
  }
  else {
    digitalWrite(led, LOW);
  }

  if (digitalRead(botao) == LOW){
    lcd.clear();
    tempo_agora = tempo_agora + 5000;
    finalizado = false;
    iniciou = true;

    lcd.print("Aquecendo...");
    lcd.setCursor(0, 1);
    lcd.print("Tempo: ");
    lcd.setCursor(7, 1);
    lcd.print(tempo_agora / 1000);

    delay(300);
  }

  if (millis() - tempo_anterior >= intervalo) {
    tempo_anterior = millis();

    if (tempo_agora > 0) {
      tempo_agora -= 1000;
    } else {
      tempo_agora = 0;
    }

    lcd.setCursor(7, 1);
    lcd.print("     ");
    lcd.setCursor(7, 1);
    lcd.print(tempo_agora / 1000);
  }

  if (tempo_agora == 0 && finalizado == false && iniciou == true) {
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Aquecimento");
    lcd.setCursor(0, 1);
    lcd.print("Concluido !!");

    finalizado = true;
  }
}