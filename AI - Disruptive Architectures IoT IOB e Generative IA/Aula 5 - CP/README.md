# Checkpoint — Arduino no Wokwi

## INFORMAÇÕES DO ALUNO (Individual)

**Nome:** José Diogo Da Silva Neves
**RM:** 562341  



---

## Regras gerais

- **Prova individual.**
- Esta avaliação deve ser desenvolvida **no simulador Wokwi**.
- O código deve ser implementado em **Arduino/C++**.
- O exercício deve funcionar corretamente com os componentes solicitados.
- O uso de `delay()` **não é permitido** para controlar a lógica principal do sistema.
- O programa deve apresentar organização, clareza e coerência com o comportamento descrito no enunciado.

---

## Contexto da atividade

Nesta prova, você deverá desenvolver a lógica de um **micro-ondas digital simplificado**, simulando o comportamento básico de um produto real a partir da integração entre **entrada**, **processamento**, **temporização** e **saída visual**.

![](https://img.olx.com.br/images/59/595649021285781.webp)

Você foi contratado para desenvolver o protótipo lógico de um **mini micro-ondas digital**.

Esse sistema deverá permitir ao usuário iniciar o aquecimento e adicionar mais tempo durante a execução, exibindo no LCD as informações relevantes ao longo do funcionamento.

Como se trata de um protótipo simplificado, alguns elementos físicos do produto serão representados da seguinte forma:

- o **LCD** representará a interface de exibição do micro-ondas;
- o **LED** representará o aquecimento em funcionamento;
- o **botão** representará a interação principal do usuário com o produto.

---
## Códigos de exemplo

Você pode utilizar como referência os seguintes códigos 

- **display LCD**: [https://wokwi.com/projects/395809973749364737](https://wokwi.com/projects/395809973749364737)
- **botão e LED**: [https://wokwi.com/projects/458020980985935873](https://wokwi.com/projects/458020980985935873)
- **site da matéria**: [https://arnaldojr.github.io/DisruptiveArchitectures](https://arnaldojr.github.io/DisruptiveArchitectures)


## Objetivo

Desenvolver um programa que simule o funcionamento básico de um **micro-ondas digital simplificado**, utilizando:

- **1 botão** para interação;
- **1 LED** como indicador de operação;
- **1 LCD** como interface de exibição;
- **1 Arduino Uno** para processamento.

---

## Requisitos funcionais

### 1. Estado inicial

Ao iniciar a simulação:

- o sistema deve permanecer em estado de espera;
- o **LED deve estar apagado**;
- o tempo inicial deve ser **0 segundo**;
- o LCD deve indicar que o sistema está pronto.

Exemplo de exibição possível:

```cpp
Pronto
Tempo: 0 s
```

---

### 2. Funcionamento do botão

Cada vez que o botão for pressionado, o sistema deve `adicionar` **5 segundos** ao tempo do micro-ondas.

Essa regra vale tanto:

- quando o sistema estiver parado;
- quanto quando o sistema já estiver aquecendo.

---

### 3. Início do aquecimento

Se o sistema estiver parado e o botão for pressionado:

- o tempo deve aumentar em **5 segundos**;
- o micro-ondas deve **iniciar imediatamente** o aquecimento;
- o **LED deve acender**;
- o LCD deve indicar que o sistema está aquecendo;

Exemplo de exibição possível:

```cpp
Aquecendo...
Restam: 3 s
```

---

### 4. Adição de tempo durante a execução

Se o sistema já estiver aquecendo e o botão for pressionado novamente:

- o tempo restante deve ser `aumentado` em **mais 5 segundos**;
- o sistema deve continuar funcionando normalmente;
- o LED deve permanecer aceso;
- a contagem regressiva deve continuar a partir do novo valor atualizado.

Exemplo:

- tempo restante atual: **8 s**
- usuário pressiona o botão
- novo tempo restante: **13 s**

---

### 5. Contagem regressiva

Enquanto o sistema estiver aquecendo:

- o **LED deve permanecer aceso**;
- o LCD deve mostrar o **estado atual** e o **tempo restante**;
- o tempo deve ser reduzido corretamente até chegar a zero;


### 6. Finalização do aquecimento

Quando o tempo chegar a zero:

- o aquecimento deve ser encerrado;
- o **LED deve apagar**;
- o LCD deve informar que o aquecimento foi concluído.

Exemplo de exibição possível:

```cpp
Aquecimento
Concluido!
```

## Critérios que serão observados na correção

Durante a avaliação, serão observados aspectos como:

- **Não utilização de `delay()`** para controlar a lógica principal do sistema.
- funcionamento correto da lógica proposta;
- comportamento correto do botão;
- incremento de **5 segundos** a cada pressionamento;
- início correto do aquecimento;
- adição de tempo correta durante o funcionamento;
- acendimento e desligamento do LED no momento apropriado;
- exibição correta das informações no LCD;
- organização e clareza do código;
- coerência entre os estados do sistema e a interface apresentada ao usuário.

---

## Entrega

A entrega deve conter:

- apresentação ao professor, em aula, do funcionamento do sistema;
- circuito montado no **Wokwi**;
- código-fonte completo e funcional;
- funcionamento correto do sistema durante a simulação.

---

Boa prova.
