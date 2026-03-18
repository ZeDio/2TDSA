#include <LiquidCrystal_I2C.h>

// define o número de colunas e linhas do LCD
#define COLUNAS 16
#define LINHAS  2
int led = 8;
int botao = 2;
int intervalo_1 = 1000;
unsigned long tempo_agora_1 = 0;

// define o endereço do LCD, número de colunas e linhas
LiquidCrystal_I2C lcd(0x27, COLUNAS, LINHAS);  

void setup(){
  pinMode(led, OUTPUT);
  pinMode(botao, INPUT_PULLUP);

  Serial.begin(9600);

  // inicializa o LCD
  lcd.init();
  // liga a luz de fundo do LCD                      
  lcd.backlight();
}

void loop(){
  // posiciona o cursor na primeira coluna, primeira linha
  lcd.setCursor(0, 0);
  lcd.print("Pronto!");
  lcd.setCursor(0, 1);
  lcd.print("Tempo: ");
  lcd.setCursor(6, 1);
  lcd.print(tempo_agora_1);

  if(tempo_agora_1 == 0){
    digitalWrite(led, LOW);
  }

  if(digitalRead(botao) == LOW){
    lcd.setCursor(0, 0);
    lcd.print("Aquecendo...");
    digitalWrite(led, HIGH);

    if(tempo_agora_1 == 0){
      tempo_agora_1 = 25000;
    }

    if (millis() - tempo_agora_1 > intervalo_1){
      tempo_agora_1 = tempo_agora_1 - intervalo_1;
    }

    if(digitalRead(botao) == LOW && tempo_agora_1 > 0){
      tempo_agora_1 = tempo_agora_1 + 5000;
    }

    lcd.setCursor(0, 1);
    lcd.print("Tempo: ");
    lcd.setCursor(6, 1);
    lcd.print(tempo_agora_1 / 1000);

    if(tempo_agora_1 == 0){
      lcd.setCursor(0, 0);
      lcd.print("Aquecimento");
      lcd.setCursor(0, 1);
      lcd.print("Concluido!!");
    }
  }

}