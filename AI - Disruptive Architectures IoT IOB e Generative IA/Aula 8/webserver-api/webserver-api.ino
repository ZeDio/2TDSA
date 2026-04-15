#include <WiFi.h>
#include <WiFiClient.h>
#include <WebServer.h>
#include <uri/UriBraces.h>

#define WIFI_SSID "Wokwi-GUEST"
#define WIFI_PASSWORD ""
#define WIFI_CHANNEL 6

WebServer server(80);

const int LED1 = 26;
const int LED2 = 27;

bool led1State = false;
bool led2State = false;

String boolToJson(bool value) {
  return value ? "true" : "false";
}

void sendJsonStatus() {
  String response = "{";
  response += "\"led1\":" + boolToJson(led1State) + ",";
  response += "\"led2\":" + boolToJson(led2State);
  response += "}";

  server.send(200, "application/json", response);
}

void sendJsonLedResponse(int ledNumber, bool state) {
  String response = "{";
  response += "\"ok\":true,";
  response += "\"led\":" + String(ledNumber) + ",";
  response += "\"state\":" + boolToJson(state);
  response += "}";

  server.send(200, "application/json", response);
}

void sendJsonError(String message, int code = 400) {
  String response = "{";
  response += "\"ok\":false,";
  response += "\"error\":\"" + message + "\"";
  response += "}";

  server.send(code, "application/json", response);
}

void setup(void) {
  Serial.begin(115200);

  pinMode(LED1, OUTPUT);
  pinMode(LED2, OUTPUT);

  digitalWrite(LED1, LOW);
  digitalWrite(LED2, LOW);

  WiFi.begin(WIFI_SSID, WIFI_PASSWORD, WIFI_CHANNEL);
  Serial.print("Connecting to WiFi ");
  Serial.print(WIFI_SSID);

  while (WiFi.status() != WL_CONNECTED) {
    delay(100);
    Serial.print(".");
  }

  Serial.println(" Connected!");
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());

  server.on("/api/status", HTTP_GET, []() {
    sendJsonStatus();
  });

  server.on(UriBraces("/api/led/{}/{}"), HTTP_POST, []() {
    String led = server.pathArg(0);
    String action = server.pathArg(1);

    int ledNumber = led.toInt();

    if (ledNumber != 1 && ledNumber != 2) {
      sendJsonError("LED invalido", 404);
      return;
    }

    if (action != "on" && action != "off") {
      sendJsonError("Acao invalida", 400);
      return;
    }

    bool newState = (action == "on");

    if (ledNumber == 1) {
      led1State = newState;
      digitalWrite(LED1, led1State);
      sendJsonLedResponse(1, led1State);
    } else {
      led2State = newState;
      digitalWrite(LED2, led2State);
      sendJsonLedResponse(2, led2State);
    }
  });

  server.onNotFound([]() {
    sendJsonError("Rota nao encontrada", 404);
  });

  server.begin();
  Serial.println("HTTP server started");
}

void loop(void) {
  server.handleClient();
  delay(2);
}