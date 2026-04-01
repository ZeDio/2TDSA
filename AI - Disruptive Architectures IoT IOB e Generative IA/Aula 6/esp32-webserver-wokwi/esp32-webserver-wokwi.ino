#include <WiFi.h>
#include <WiFiClient.h>
#include <WebServer.h>
#include <uri/UriBraces.h>

#define WIFI_SSID "Wokwi-GUEST"
#define WIFI_PASSWORD ""
// Defining the WiFi channel speeds up the connection:
#define WIFI_CHANNEL 6

WebServer server(80);

const int LED1 = 26;
const int LED2 = 27;

bool led1State = false;
bool led2State = false;
bool led3State = false;

void sendHtml() {
  String response = R"(
    <!DOCTYPE html>
<html>
<head>
  <title>ESP32 Web Server</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <style>
    body {
      margin: 0;
      font-family: 'Segoe UI', sans-serif;
      background: #121212;
      color: #E0E0E0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .container {
      background: #1E1E1E;
      padding: 2em;
      border-radius: 16px;
      box-shadow: 0 0 20px rgba(0,0,0,0.6);
      text-align: center;
      width: 320px;
    }

    h1 {
      margin-bottom: 1.5em;
      font-weight: 600;
      color: #ffffff;
    }

    .grid {
      display: grid;
      grid-template-columns: 1fr;
      gap: 1.5em;
    }

    .card {
      background: #2A2A2A;
      padding: 1em;
      border-radius: 12px;
      box-shadow: inset 0 0 10px rgba(0,0,0,0.4);
    }

    h2 {
      margin-bottom: 0.8em;
      font-size: 1.2em;
      color: #BBBBBB;
    }

    .btn {
      display: inline-block;
      width: 100%;
      padding: 0.6em;
      border-radius: 8px;
      text-decoration: none;
      font-size: 1.2em;
      font-weight: bold;
      color: white;
      transition: 0.2s;
      background: linear-gradient(135deg, #4CAF50, #2E7D32);
      box-shadow: 0 4px 10px rgba(0,0,0,0.5);
    }

    .btn:hover {
      transform: scale(1.05);
      box-shadow: 0 6px 15px rgba(0,0,0,0.7);
    }

    .btn.OFF {
      background: linear-gradient(135deg, #444, #222);
      color: #999;
    }

  </style>
</head>

<body>
  <div class="container">
    <h1>ESP32 Web Server</h1>

    <div class="grid">
      <div class="card">
        <h2>LED 1</h2>
        <a href="/toggle/1" class="btn LED1_TEXT">LED1_TEXT</a>
      </div>

      <div class="card">
        <h2>LED 2</h2>
        <a href="/toggle/2" class="btn LED2_TEXT">LED2_TEXT</a>
      </div>

      <div class="card">
        <h2>Pisca LED</h2>
        <a href="/toggle/3" class="btn LED3_TEXT">LED3_TEXT</a>
      </div>
    </div>
  </div>
</body>
</html>
  )";
  response.replace("LED1_TEXT", led1State ? "ON" : "OFF");
  response.replace("LED2_TEXT", led2State ? "ON" : "OFF");
  response.replace("LED3_TEXT", led3State ? "ON" : "OFF");
  server.send(200, "text/html", response);
}

void setup(void) {
  Serial.begin(115200);
  pinMode(LED1, OUTPUT);
  pinMode(LED2, OUTPUT);

  WiFi.begin(WIFI_SSID, WIFI_PASSWORD, WIFI_CHANNEL);
  Serial.print("Connecting to WiFi ");
  Serial.print(WIFI_SSID);
  // Wait for connection
  while (WiFi.status() != WL_CONNECTED) {
    delay(100);
    Serial.print(".");
  }
  Serial.println(" Connected!");

  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());

  server.on("/", sendHtml);

  server.on(UriBraces("/toggle/{}"), []() {
    String led = server.pathArg(0);
    Serial.print("Toggle LED #");
    Serial.println(led);

    switch (led.toInt()) {
      case 1:
        led1State = !led1State;
        digitalWrite(LED1, led1State);
        break;
      case 2:
        led2State = !led2State;
        digitalWrite(LED2, led2State);
        break;
      case 3:
        led3State = !led3State;
        digitalWrite(LED1, led3State);
        digitalWrite(LED2, led3State);
        delay(500);
        led3State = !led3State;
        digitalWrite(LED1, led3State);
        digitalWrite(LED2, led3State);
        delay(500);
        led3State = !led3State;
        digitalWrite(LED1, led3State);
        digitalWrite(LED2, led3State);
        delay(500);
        led3State = !led3State;
        digitalWrite(LED1, led3State);
        digitalWrite(LED2, led3State);
        delay(500);
        break;
    }

    sendHtml();
  });

  server.begin();
  Serial.println("Iniciando");
}

void loop(void) {
  server.handleClient();
  delay(2);
}