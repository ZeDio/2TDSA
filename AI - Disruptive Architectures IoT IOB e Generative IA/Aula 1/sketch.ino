void setup() {
  // put your setup code here, to run once:
  pinMode(13, OUTPUT);
  pinMode(5, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(13, HIGH);
  digitalWrite(5, HIGH);
  delay(1000); //espera 1 segundo
  digitalWrite(13, LOW);
  digitalWrite(5, LOW);
  delay(1000);
}
