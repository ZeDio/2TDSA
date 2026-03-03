--Aula 3 03/03/2026

--Ativar a saída de dados
set serveroutput on

--desabilitar saida de variaveis
set verify off


-- Aprendendo If e Else em BD
declare
    V_N number(2) := &V_N;
begin
    if mod(V_N,2) = 0 then
        dbms_output.put_line('O número '|| V_N ||' é Par!');
    else
        dbms_output.put_line('O número '|| V_N ||' é Ímpar!');
    end if;
end;


-- Exercicio 1
declare
    Cliente char(1) := upper('&Cliente');
begin
    if Cliente = 'F' then
        dbms_output.put_line('Você é Feminino');
    elsif Cliente = 'M' then
        dbms_output.put_line('Você é Masculino');
    else
        dbms_output.put_line('Você é Outros');
    end if;
end;


-- Exercicio 2
declare
    valor_carro number(6.2) := 10000;
    valor_entrada number(6.2) := valor_carro*0.2;
    Escolha_Parcela number(2) := &Escolha_Parcela;
    valor_juros_em_6_parcelas number(6.2) := (((valor_carro - valor_entrada) * 0.1) +(valor_carro - valor_entrada))/6;
    valor_juros_em_12_parcelas number(6.2) := ((valor_carro - valor_entrada) * 0.15 +(valor_carro - valor_entrada))/12;
    valor_juros_em_18_parcelas number(6.2) := ((valor_carro - valor_entrada) * 0.20 +(valor_carro - valor_entrada))/18;
begin
    if Escolha_Parcela = 6 then
        dbms_output.put_line('Valor do carro R$:'|| valor_carro ||'.00');
        dbms_output.put_line('Valor da entrada R$:'|| valor_entrada ||'.00');
        dbms_output.put_line('Valor parcelado em 6 vezes com juros de 10%. R$:'|| valor_juros_em_6_parcelas ||'.00');
    elsif Escolha_Parcela = 12 then
        dbms_output.put_line('Valor do carro R$:'|| valor_carro ||'.00');
        dbms_output.put_line('Valor da entrada R$:'|| valor_entrada ||'.00');
        dbms_output.put_line('Valor parcelado em 12 vezes com juros de 15%. R$:'|| valor_juros_em_12_parcelas ||'.00');
    elsif Escolha_Parcela = 18 then
        dbms_output.put_line('Valor do carro R$:'|| valor_carro ||'.00');
        dbms_output.put_line('Valor da entrada R$:'|| valor_entrada ||'.00');
        dbms_output.put_line('Valor parcelado em 18 vezes com juros de 18%. R$:'|| valor_juros_em_18_parcelas ||'.00');
    else
        dbms_output.put_line('Parcelas indisponivel... Escolha entre 6/12/18');
    end if;
end;


-- Lista de Exercicio da aula
/*
-- Exercicio 1

Crie um bloco anônimo PL/SQL que verifique se um número fornecido é positivo, 
negativo ou zero. Exiba uma mensagem apropriada usando a estrutura IF.
Exemplo de Saída:
"Número positivo"
"Número negativo"
"Número é zero"
*/
declare
    numero number(1) := &numero;
begin
    if numero > 0 then
        dbms_output.put_line('O numero é Positivo');
    elsif numero < 0 then
        dbms_output.put_line('O numero é Negativo');
    else
        dbms_output.put_line('O numero é Zero');
    end if;
end;


/*
-- Exercicio 2

Crie um bloco PL/SQL que avalie a idade de uma pessoa e determine se ela é criança,
adolescente, adulto ou idoso. Considere as faixas etárias:
-Criança: 0 a 12 anos
-Adolescente: 13 a 17 anos
-Adulto: 18 a 64 anos
-Idoso: 65 anos ou mais
*/
declare
    idade number(1) := &idade;
begin
    if (idade > 0 or idade <= 12) then
        dbms_output.put_line('Criança');
    elsif (idade >= 13 or idade <= 17) then
        dbms_output.put_line('Adolescente');
    elsif (idade >= 18 or idade <= 64) then
        dbms_output.put_line('Adulto');
    elsif (idade >= 65) then
        dbms_output.put_line('Adulto');
    else
        dbms_output.put_line('Coloque uma idade valida');
    end if;
end;


/*
-- Exercicio 3

Crie um bloco PL/SQL que verifique se um ano informado é bissexto. 
Um ano é bissexto se for divisível por 4, mas não divisível por 100, 
a menos que também seja divisível por 400.
*/
declare
    ano number(4) := &ano;
begin
    if (mod(ano,4) = 0 or mod(ano,100) != 0 and mod(ano,400) = 0) then
        dbms_output.put_line('Ano é bissexto');
    else
        dbms_output.put_line('Ano não é bissexto');
    end if;
end;


/*
-- Exercicio 4

Crie um bloco PL/SQL que receba dois números e compare:
Se o primeiro número for maior que o segundo, exiba "Maior".
Se o primeiro número for menor que o segundo, exiba "Menor".
Se forem iguais, exiba "Igual".
*/
declare
    n_1 number(4) := &n_1;
    n_2 number(4) := &n_2;
begin
    if (n_1 > n_2) then
        dbms_output.put_line('Numero '|| n_1 || ' é maior que o numero '|| n_2 || '.');
    elsif (n_1 < n_2) then
        dbms_output.put_line('Numero '|| n_2 || ' é maior que o numero '|| n_1 || '.');
    else
        dbms_output.put_line('Os dois numeros são iguais!');
    end if;
end;


/*
-- Exercicio 5

Crie um bloco PL/SQL que simule uma calculadora básica, realizando a operação indicada pelo usuário:
Se o operador for  +, realize uma soma.
Se o operador for  -, realize uma subtração.
Se o operador for  *, realize uma multiplicação.
Se o operador for  ", realize uma divisão.
*/
declare
    n_1 number(4) := &n_1;
    n_2 number(4) := &n_2;
    espressao char(1) := '&esprecao';
begin
    if (espressao = '+') then
        dbms_output.put_line('Resultado :'|| (n_1 + n_2));
    elsif (espressao = '-') then
        dbms_output.put_line('Resultado :'|| (n_1 - n_2));
    elsif (espressao = '*') then
        dbms_output.put_line('Resultado :'|| (n_1 * n_2));
    elsif (espressao = '"') then
        dbms_output.put_line('Resultado :'|| (n_1 / n_2));
    else
        dbms_output.put_line('Expressão Invalida!! ');
        dbms_output.put_line('Digite  +   -   *  "  ');
    end if;
end;


/*
-- Exercicio 6

Entrada: valor do saldo da conta corrente
Entrada: tipo de conta: 1 - básica, 2 - especial
Entrada: 1 - saque, 2 - depósito
Entrada: valor do saque ou depósito
Processamento para depósito: somar valor de saldo com depósito
Processamento para saque: Conta normal: verificar se possui saldo,
			  saldo ok, realize o saque e atualize o saldo,
                          saldo insuficiente não realiza saque e informe o cliente.
Processamento para saque: Conta especial: verificar se possui saldo,
			  saldo ok, realize o saque e atualize o saldo,
                          saldo insuficiente e dentro do limite que é 15% do saldo,
			  realize o saque e atualize o saldo,			  
			  saldo acima do limite de 15 do saldo não realize saque e informe o cliente.
Saída de dados, sempre informar o que está acontecendo:
			  - Entrada tipo de conta
			  - Entrada movimentação
			  - Valor do saque ou depósito
			  - Resultado da movimentação
			  - Atualização do saldo
*/
