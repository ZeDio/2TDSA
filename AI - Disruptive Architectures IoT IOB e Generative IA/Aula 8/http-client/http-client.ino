#include <WiFi.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>
#include <LiquidCrystal_I2C.h>

// =========================
// CONFIGURACAO DO WIFI
// =========================
#define WIFI_SSID "Wokwi-GUEST"
#define WIFI_PASSWORD ""
#define WIFI_CHANNEL 6

// =========================
// URL DA API
// =========================
const char* WEATHER_URL_SP =
  "https://api.open-meteo.com/v1/forecast?latitude=-23.55&longitude=-46.63&current=temperature_2m";

const char* WEATHER_URL_RJ =
  "https://api.open-meteo.com/v1/forecast?latitude=-22.90&longitude=-43.20&current=temperature_2m";

// LCD
LiquidCrystal_I2C lcd(0x27, 16, 2);

// Botões
const int buttomRJ = 33;
const int buttomSP = 32;

// =========================
// WIFI
// =========================
void connectWiFi() {
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD, WIFI_CHANNEL);

  Serial.print("Conectando ao WiFi");

  while (WiFi.status() != WL_CONNECTED) {
    delay(100);
    Serial.print(".");
  }

  Serial.println("\nWiFi conectado!");
  Serial.println(WiFi.localIP());
}

void ensureWiFiConnected() {
  if (WiFi.status() != WL_CONNECTED) {
    connectWiFi();
  }
}

// =========================
// LCD
// =========================
void exibeDadosNoLCD(String cidade, float temperatura) {
  lcd.clear();

  lcd.setCursor(0, 0);
  lcd.print(cidade);

  lcd.setCursor(0, 1);
  lcd.print("Temp: ");
  lcd.print(temperatura, 1);
  lcd.print(" C");
}

// =========================
// GET REQUEST
// =========================
void makeGetRequest(const char* url, String cidade) {
  HTTPClient http;

  Serial.println("\n--- FAZENDO GET ---");
  http.begin(url);

  int httpCode = http.GET();

  if (httpCode <= 0) {
    Serial.println("Erro na requisicao");
    http.end();
    return;
  }

  String payload = http.getString();
  http.end();

  DynamicJsonDocument doc(1024);
  DeserializationError error = deserializeJson(doc, payload);

  if (error) {
    Serial.println("Erro no JSON");
    return;
  }

  float temperatura = doc["current"]["temperature_2m"];

  Serial.print("Temperatura: ");
  Serial.println(temperatura);

  exibeDadosNoLCD(cidade, temperatura);
}

// =========================
// SETUP
// =========================
void setup() {
  Serial.begin(115200);

  lcd.init();
  lcd.backlight();

  pinMode(buttomRJ, INPUT_PULLUP);
  pinMode(buttomSP, INPUT_PULLUP);

  connectWiFi();

  // Mostra SP ao iniciar
  makeGetRequest(WEATHER_URL_SP, "Sao Paulo");
}

// =========================
// LOOP
// =========================
void loop() {

  if (digitalRead(buttomRJ) == LOW) {
    makeGetRequest(WEATHER_URL_RJ, "Rio");
    delay(1000); // debounce simples
  }

  if (digitalRead(buttomSP) == LOW) {
    makeGetRequest(WEATHER_URL_SP, "Sao Paulo");
    delay(1000);
  }
}