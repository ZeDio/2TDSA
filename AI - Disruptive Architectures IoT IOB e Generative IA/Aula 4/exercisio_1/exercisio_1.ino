/*
tipos de memoria dentro do arduino ->
flash - guarda o codigo carregado e executa
sram - tipo memoria ram, guarda informações temporariamente
eprom - guarda memoria temporaria para quando desliga e liga, tipo a senha do wifi para se lembrar

I/O - portas para acessar pinos tipo GND e PIN alguma coisa
*/

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(LED_BUILTIN, OUTPUT);
}

int intervalo_1 = 3200;
int intervalo_2 = 1000;
int intervalo_3 = 6400;
unsigned long tempo_agora_1 = 0;
unsigned long tempo_agora_2 = 0;
unsigned long tempo_agora_3 = 0;
int estado_led = 1;

void loop() {
  // put your main code here, to run repeatedly:

  // tarefa 1 - 
  if (millis() - tempo_agora_1 > intervalo_1){
    tempo_agora_1 = millis();
    Serial.print("entrei 1 :");
    Serial.println(tempo_agora_1);
  }

  // tarefa 2 - 
  if (millis() - tempo_agora_2 > intervalo_2){
    tempo_agora_2 = millis();
    Serial.print("entrei 2 :");
    Serial.println(tempo_agora_2);
  }

  // tarefa 3 - 
  if (millis() - tempo_agora_3 > intervalo_3){
    tempo_agora_3 = millis();
    Serial.print("entrei 3 :");
    Serial.println(tempo_agora_3);
    estado_led != estado_led;
    digitalWrite(LED_BUILTIN, !estado_led);
  }
}
