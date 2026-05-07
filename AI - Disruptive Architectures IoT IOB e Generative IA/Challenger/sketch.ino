#include <WiFi.h>
#include <WebServer.h>
#include <time.h>

// =========================
// WIFI
// =========================
#define WIFI_SSID "Wokwi-GUEST"
#define WIFI_PASSWORD ""
#define WIFI_CHANNEL 6

// =========================
// NTP
// =========================
const char* ntpServer = "pool.ntp.org";
const long gmtOffset_sec = -3 * 3600;
const int daylightOffset_sec = 0;

// =========================
// SERVIDOR
// =========================
WebServer server(80);

// =========================
// PINOS SENSOR COMIDA
// =========================
#define TRIG_COMIDA 5
#define ECHO_COMIDA 18

// =========================
// PINOS SENSOR AGUA
// =========================
#define TRIG_AGUA 4
#define ECHO_AGUA 15

// =========================
// CONTADORES
// =========================
int visitasComida = 0;
int visitasAgua = 0;

// =========================
// CONTROLE
// =========================
bool detectouComida = false;
bool detectouAgua = false;

// =========================
// HORÁRIOS
// =========================
String horariosComida[50];
String horariosAgua[50];

// =========================
// LIMITE
// =========================
const int LIMITE = 15;

// =========================
// PEGAR HORÁRIO
// =========================
String pegarHorario() {

  struct tm timeinfo;

  if (!getLocalTime(&timeinfo)) {
    return "Horario indisponivel";
  }

  char horario[10];

  strftime(horario, sizeof(horario), "%H:%M:%S", &timeinfo);

  return String(horario);
}

// =========================
// SETUP
// =========================
void setup() {

  Serial.begin(115200);

  // Sensores
  pinMode(TRIG_COMIDA, OUTPUT);
  pinMode(ECHO_COMIDA, INPUT);

  pinMode(TRIG_AGUA, OUTPUT);
  pinMode(ECHO_AGUA, INPUT);

  // WIFI
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD, WIFI_CHANNEL);

  Serial.println("Conectando WiFi...");

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi conectado!");

  // =========================
  // NTP
  // =========================
  configTime(gmtOffset_sec, daylightOffset_sec, ntpServer);

  // =========================
  // STATUS
  // =========================
  server.on("/status", []() {

    String json = "{";

    json += "\"visitasComida\":";
    json += visitasComida;
    json += ",";

    json += "\"visitasAgua\":";
    json += visitasAgua;

    json += "}";

    server.send(200, "application/json", json);
  });

  // =========================
  // COMIDA
  // =========================
  server.on("/comida", []() {

    String json = "{";

    json += "\"visitasComida\":";
    json += visitasComida;
    json += ",";

    json += "\"visitaHorario\":[";

    for (int i = 0; i < visitasComida; i++) {

      json += "\"";
      json += horariosComida[i];
      json += "\"";

      if (i < visitasComida - 1) {
        json += ",";
      }
    }

    json += "]}";

    server.send(200, "application/json", json);
  });

  // =========================
  // AGUA
  // =========================
  server.on("/agua", []() {

    String json = "{";

    json += "\"visitasAgua\":";
    json += visitasAgua;
    json += ",";

    json += "\"visitaHorario\":[";

    for (int i = 0; i < visitasAgua; i++) {

      json += "\"";
      json += horariosAgua[i];
      json += "\"";

      if (i < visitasAgua - 1) {
        json += ",";
      }
    }

    json += "]}";

    server.send(200, "application/json", json);
  });

  // =========================
  // START SERVER
  // =========================
  server.begin();

  Serial.println("Servidor iniciado!");
  Serial.println(WiFi.localIP());
}

// =========================
// LOOP
// =========================
void loop() {

  server.handleClient();

  // =========================
  // COMIDA
  // =========================
  float distanciaComida = medirDistancia(TRIG_COMIDA, ECHO_COMIDA);

  if (distanciaComida > 0 && distanciaComida <= LIMITE) {

    if (!detectouComida) {

      detectouComida = true;

      horariosComida[visitasComida] = pegarHorario();

      visitasComida++;

      Serial.println("🍖 Pet foi comer");
    }

  } else {

    detectouComida = false;
  }

  // =========================
  // AGUA
  // =========================
  float distanciaAgua = medirDistancia(TRIG_AGUA, ECHO_AGUA);

  if (distanciaAgua > 0 && distanciaAgua <= LIMITE) {

    if (!detectouAgua) {

      detectouAgua = true;

      horariosAgua[visitasAgua] = pegarHorario();

      visitasAgua++;

      Serial.println("💧 Pet foi beber agua");
    }

  } else {

    detectouAgua = false;
  }

  delay(1000);
}

// =========================
// MEDIR DISTÂNCIA
// =========================
float medirDistancia(int trigPin, int echoPin) {

  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);

  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);

  digitalWrite(trigPin, LOW);

  long duracao = pulseIn(echoPin, HIGH);

  float distancia = duracao * 0.034 / 2;

  return distancia;
}