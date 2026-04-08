# ESP32 Webserver Simples

## Descrição

Este projeto demonstra um servidor web simples no ESP32, permitindo controlar dois LEDs (conectados aos pinos 26 e 27) através de uma interface web via Wi-Fi. O ESP32 se conecta à rede "Wokwi-GUEST" (configurada para simulação) e serve uma página HTML com botões para ligar/desligar os LEDs.

## Funcionalidades

- **Conexão Wi-Fi**: Conecta-se automaticamente à rede Wi-Fi configurada.
- **Servidor Web**: Serve uma página web responsiva em português.
- **Controle de LEDs**: Dois LEDs controláveis via botões na interface web.
- **Estado Atual**: Exibe o estado atual de cada LED (LIGADO/DESLIGADO).
- **Interface Amigável**: Página com design simples e botões coloridos (verde para ligar, vermelho para desligar).

## Como Executar

Para instruções gerais de configuração e execução no Wokwi, consulte o [README principal](../../README.md) do repositório.

### Passos Específicos para Este Projeto

1. Navegue até esta pasta (`esp32-webserver-wokwi/`).
2. Abra o arquivo `diagram.json` no VS Code.
3. Inicie a simulação no Wokwi.
4. No simulador, aguarde a conexão Wi-Fi e o início do servidor (verifique o console serial para o IP).
5. Abra um navegador e acesse o IP exibido.
6. Use os botões na página para controlar os LEDs.

### Endpoints da Página Web

- `/`: Página principal com controles dos LEDs.
- `/led1/on`: Liga o LED 1.
- `/led1/off`: Desliga o LED 1.
- `/led2/on`: Liga o LED 2.
- `/led2/off`: Desliga o LED 2.

## Estrutura do Projeto

- `esp32-webserver-wokwi.ino`: Código principal do Arduino, incluindo setup Wi-Fi, servidor web e funções de controle dos LEDs.
- `diagram.json`: Configuração do circuito no Wokwi (ESP32 com dois LEDs conectados).
- `wokwi.toml`: Configurações específicas do projeto para o simulador Wokwi.
- `build/`: Pasta gerada com binários após compilação (opcional para simulação).

## Notas Adicionais

- **Wi-Fi**: Configurado para "Wokwi-GUEST" sem senha. Para uso real, edite as constantes `WIFI_SSID` e `WIFI_PASSWORD` no código.
- **Pinos**: LED1 no pino 26, LED2 no pino 27.
- **Compatibilidade**: Projetado para ESP32 DevKit V1 no Wokwi.
- **Personalização**: Modifique o HTML em `gerarPagina()` para alterar a interface.

Divirta-se controlando LEDs via web com o ESP32!