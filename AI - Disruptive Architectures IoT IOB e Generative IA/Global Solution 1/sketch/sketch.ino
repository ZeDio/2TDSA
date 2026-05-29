#include <WiFi.h>
#include <WebServer.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>
#include <DHT.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>

// ================= WIFI =================

const char* ssid = "Wokwi-GUEST";
const char* password = "";

// ================= WEB SERVER =================

WebServer server(80);

// ================= DHT22 =================

#define DHTPIN 4
#define DHTTYPE DHT22

DHT dht(DHTPIN, DHTTYPE);

// ================= MQ2 =================

#define MQ2_PIN 33

// ================= LEDS =================

#define LED_VERDE 25
#define LED_AMARELO 26
#define LED_VERMELHO 27

// ================= LCD =================

LiquidCrystal_I2C lcd(0x27, 16, 2);

// ================= VARIÁVEIS HABITAT =================

float temperatura = 0;
float umidade = 0;
int gas = 0;

String statusHabitat = "SEGURO";

// ================= VARIÁVEIS MARTE =================

float marsTemp = 0;
float marsPressure = 0;
float marsWind = 0;

// ================= VARIÁVEIS TERRA =================

float earthTemp = 0;
float earthHumidity = 0;
float earthWind = 0;

// ================= STATUS =================

void calcularStatus() {

  // APAGA TODOS OS LEDS

  digitalWrite(LED_VERDE, LOW);
  digitalWrite(LED_AMARELO, LOW);
  digitalWrite(LED_VERMELHO, LOW);

  // DEFINE STATUS

  if (gas > 3000 || temperatura > 40) {

    statusHabitat = "CRITICO";

    digitalWrite(LED_VERMELHO, HIGH);
  }

  else if (gas > 1500 || temperatura > 30) {

    statusHabitat = "ATENCAO";

    digitalWrite(LED_AMARELO, HIGH);
  }

  else {

    statusHabitat = "SEGURO";

    digitalWrite(LED_VERDE, HIGH);
  }
}

// ================= BUSCAR DADOS MARTE =================

void buscarDadosMarte() {

  HTTPClient http;

  String url =
  "https://api.nasa.gov/insight_weather/?api_key=DEMO_KEY&feedtype=json&ver=1.0";

  http.begin(url);

  int httpCode = http.GET();

  if (httpCode > 0) {

    String payload = http.getString();

    DynamicJsonDocument doc(50000);

    DeserializationError error = deserializeJson(doc, payload);

    if (!error) {

      JsonArray sol_keys = doc["sol_keys"];

      if (sol_keys.size() > 0) {

        String ultimoSol = sol_keys[sol_keys.size() - 1];

        marsTemp =
        doc[ultimoSol]["AT"]["av"] | 0;

        marsPressure =
        doc[ultimoSol]["PRE"]["av"] | 0;

        marsWind =
        doc[ultimoSol]["HWS"]["av"] | 0;
      }
    }
  }

  http.end();
}

// ================= BUSCAR DADOS TERRA =================

void buscarDadosTerra() {

  HTTPClient http;

  String url =
  "https://api.open-meteo.com/v1/forecast?latitude=-23.55&longitude=-46.63&current=temperature_2m,relative_humidity_2m,wind_speed_10m";

  http.begin(url);

  int httpCode = http.GET();

  if (httpCode > 0) {

    String payload = http.getString();

    DynamicJsonDocument doc(10000);

    DeserializationError error =
    deserializeJson(doc, payload);

    if (!error) {

      earthTemp =
      doc["current"]["temperature_2m"] | 0;

      earthHumidity =
      doc["current"]["relative_humidity_2m"] | 0;

      earthWind =
      doc["current"]["wind_speed_10m"] | 0;
    }
  }

  http.end();
}

// ================= DASHBOARD HTML =================

void handleRoot() {

  String html = R"rawliteral(
  <!DOCTYPE html>
  <html>

  <head>

    <meta charset="UTF-8">
    <meta http-equiv="refresh" content="3">

    <title>Habitat Dashboard</title>

    <style>

      body{
        background:#0f172a;
        color:white;
        font-family:Arial;
        text-align:center;
        padding:20px;
      }

      .card{
        background:#1e293b;
        padding:20px;
        margin:20px auto;
        border-radius:12px;
        width:320px;
      }

      h1{
        color:#38bdf8;
      }

      .safe{
        color:#22c55e;
      }

      .warn{
        color:#facc15;
      }

      .danger{
        color:#ef4444;
      }

    </style>

  </head>

  <body>

    <h1>Habitat Espacial</h1>

    <div class="card">
      <h2>Habitat</h2>
      <p>Temperatura: )rawliteral";

  html += temperatura;

  html += R"rawliteral( °C</p>
      <p>Umidade: )rawliteral";

  html += umidade;

  html += R"rawliteral( %</p>
      <p>Gas: )rawliteral";

  html += gas;

  html += R"rawliteral(</p>
      <p>Status: )rawliteral";

  html += statusHabitat;

  html += R"rawliteral(</p>
    </div>

    <div class="card">
      <h2>Marte</h2>
      <p>Temperatura: )rawliteral";

  html += marsTemp;

  html += R"rawliteral( °C</p>
      <p>Pressao: )rawliteral";

  html += marsPressure;

  html += R"rawliteral(</p>
      <p>Vento: )rawliteral";

  html += marsWind;

  html += R"rawliteral( km/h</p>
    </div>

    <div class="card">
      <h2>Terra</h2>
      <p>Temperatura: )rawliteral";

  html += earthTemp;

  html += R"rawliteral( °C</p>
      <p>Umidade: )rawliteral";

  html += earthHumidity;

  html += R"rawliteral( %</p>
      <p>Vento: )rawliteral";

  html += earthWind;

  html += R"rawliteral( km/h</p>
    </div>

  </body>
  </html>
  )rawliteral";

  server.send(200, "text/html", html);
}

// ================= JSON HABITAT =================

void handleStatus() {

  String json = "{";

  json += "\"temperatura\": " + String(temperatura) + ",";
  json += "\"umidade\": " + String(umidade) + ",";
  json += "\"gas\": " + String(gas) + ",";
  json += "\"status\": \"" + statusHabitat + "\"";

  json += "}";

  server.send(200, "application/json", json);
}

// ================= JSON MARTE =================

void handleMarsStatus() {

  buscarDadosMarte();

  String json = "{";

  json += "\"temperatura_marte\": " + String(marsTemp) + ",";
  json += "\"pressao_marte\": " + String(marsPressure) + ",";
  json += "\"vento_marte\": " + String(marsWind);

  json += "}";

  server.send(200, "application/json", json);
}

// ================= JSON TERRA =================

void handleEarthStatus() {

  buscarDadosTerra();

  String json = "{";

  json += "\"temperatura_terra\": " + String(earthTemp) + ",";
  json += "\"umidade_terra\": " + String(earthHumidity) + ",";
  json += "\"vento_terra\": " + String(earthWind);

  json += "}";

  server.send(200, "application/json", json);
}

// ================= SETUP =================

void setup() {

  Serial.begin(115200);

  // DHT

  dht.begin();

  // LCD

  lcd.init();
  lcd.backlight();

  // LEDS

  pinMode(LED_VERDE, OUTPUT);
  pinMode(LED_AMARELO, OUTPUT);
  pinMode(LED_VERMELHO, OUTPUT);

  // WIFI

  lcd.setCursor(0,0);
  lcd.print("Conectando...");

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {

    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi conectado!");

  Serial.print("IP: ");
  Serial.println(WiFi.localIP());

  lcd.clear();
  lcd.setCursor(0,0);
  lcd.print("WiFi conectado");

  // BUSCA DADOS EXTERNOS

  buscarDadosMarte();
  buscarDadosTerra();

  // ROTAS

  server.on("/", handleRoot);

  server.on("/status", HTTP_GET, handleStatus);

  server.on("/status-marte", HTTP_GET, handleMarsStatus);

  server.on("/status-terra", HTTP_GET, handleEarthStatus);

  server.begin();

  Serial.println("Servidor iniciado!");
}

// ================= LOOP =================

void loop() {

  // LEITURAS

  temperatura = dht.readTemperature();
  umidade = dht.readHumidity();

  gas = analogRead(MQ2_PIN);

  // STATUS

  calcularStatus();

  // LCD

  lcd.clear();

  lcd.setCursor(0,0);
  lcd.print("Temp:");
  lcd.print(temperatura);

  lcd.setCursor(0,1);
  lcd.print(statusHabitat);

  // WEB SERVER

  server.handleClient();

  delay(1000);
}