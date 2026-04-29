#include <WiFi.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>
#include <LiquidCrystal_I2C.h>

// =========================
// CONFIGURACAO DO WIFI
// =========================
#define WIFI_SSID "FIAP-IOT"
#define WIFI_PASSWORD "F!@p25.IOT"
#define WIFI_CHANNEL 6

// =========================
// URL DA API
// =========================
const char *WEATHER_URL =
    "http://10.161.0.69/api/status";

// LCD
LiquidCrystal_I2C lcd(0x27, 16, 2);

// Controle de tempo
unsigned long lastRequest = 0;
const unsigned long interval = 5000; // 5 segundos

// =========================
// WIFI
// =========================
void connectWiFi()
{
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD, WIFI_CHANNEL);

  Serial.print("Conectando ao WiFi");

  while (WiFi.status() != WL_CONNECTED)
  {
    delay(100);
    Serial.print(".");
  }

  Serial.println("\nWiFi conectado!");
  Serial.println(WiFi.localIP());
}

void ensureWiFiConnected()
{
  if (WiFi.status() != WL_CONNECTED)
  {
    connectWiFi();
  }
}

// =========================
// LCD
// =========================
void exibeDadosNoLCD(float temperatura, float umidade)
{
  lcd.clear();

  lcd.setCursor(0, 0);
  lcd.print("Temp: ");
  lcd.print(temperatura, 1);
  lcd.print(" C");

  lcd.setCursor(0, 1);
  lcd.print("Umidade: ");
  lcd.print(umidade, 1);
  lcd.print(" %");
}

// =========================
// GET REQUEST
// =========================
void makeGetRequest(const char *url)
{
  ensureWiFiConnected();

  HTTPClient http;

  Serial.println("\n--- FAZENDO GET ---");
  http.begin(url);

  int httpCode = http.GET();

  if (httpCode <= 0)
  {
    Serial.println("Erro na requisicao");
    http.end();
    return;
  }

  String payload = http.getString();
  Serial.print(payload);
  http.end();

  DynamicJsonDocument doc(1024);
  DeserializationError error = deserializeJson(doc, payload);

  if (error)
  {
    Serial.println("Erro no JSON");
    return;
  }

  float temperatura = doc["Temperatura"];
  float umidade = doc["Umidade"];

  Serial.print("Temperatura: ");
  Serial.println(temperatura);
  Serial.print("Umidade: ");
  Serial.println(umidade);

  exibeDadosNoLCD(temperatura, umidade);
}

// =========================
// SETUP
// =========================
void setup()
{
  Serial.begin(115200);

  lcd.begin(16, 2);
  lcd.init();
  lcd.backlight();
  lcd.printf("Ligado");

  connectWiFi();

  // Primeira leitura
  makeGetRequest(WEATHER_URL);
}

// =========================
// LOOP
// =========================
void loop()
{
  unsigned long currentMillis = millis();

  if (currentMillis - lastRequest >= interval)
  {
    lastRequest = currentMillis;

    makeGetRequest(WEATHER_URL);
  }
}