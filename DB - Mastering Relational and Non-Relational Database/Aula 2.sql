-- Aula 2 24/02/2026

-- Comandos de ambientes
set SERVEROUTPUT on
set VERIFY off

-- Estrutura de bloco
begin 
    dbms_output.put_line('oi');
    end;

-- exemplos aula passada
declare
    v_n1 number(4,2) := 10;
    v_n2 varchar2(10) := 'oi';
    v_n3 v_n1%type;
    v_n4 number(4,2) := &value;
begin
    dbms_output.put_line(v_n1);
    dbms_output.put_line(v_n2);
    dbms_output.put_line(v_n3);
    dbms_output.put_line(v_n4);
end;


-- Aula de hoje 
-- EXERCICIO 01 – Criar um bloco PL-SQL para calcular o valor do novo salário mínimo que deverá ser de 25% em cima do atual, que é de R$???.
declare
    salario_minimo number(6.2) := &value;
    valor_com_acrecimo number(6.2) := salario_minimo + (salario_minimo*0.25);
begin
   dbms_output.put_line('Se o salario minimo vale R$:'|| salario_minimo ||'. Ele ficara R$:'|| valor_com_acrecimo); 
end;


-- EXERCÍCIO 02 – Criar um bloco PL-SQL para calcular o valor em REAIS de 45 dólares, sendo que o valor do câmbio a ser considerado é de R$ ??? no qual estes valores deverão ser constantes dentro do bloco.
declare
    valor_dolar number(4.2) := 45;
    valor_em_real number(4.2) := 45*5.18;
begin
    dbms_output.put_line('Se tem $:'|| valor_dolar ||'. Você vai ter R$:'|| valor_em_real ||' em reais'); 
end;


-- EXERCÍCIO 03 – Criar um bloco PL-SQL para converter em REAIS os dólares informados, sendo que o valor do Câmbio a ser considerado é de ????.
declare
    dolares number(6.2) := &value;
    reais_valor number(6.2) := &value;
    cambio number(6.2) := dolares * reais_valor;
begin
     dbms_output.put_line('Se tem $:'|| dolares ||'Dolares. Você vai ter R$:'|| cambio ||' em reais'); 
end;


-- EXERCÍCIO 04 – Criar um bloco PL-SQL para calcular o valor das parcelas de uma compra de um carro, nas seguintes condições: OBSERVAÇÃO: 
-- 1 - Parcelas para aquisição em 10 pagamentos. 
-- 2 - O valor total dos juros é de 3% e deverá ser aplicado sobre o montante financiado 
-- 3 – No final informar o valor de cada parcela.

declare
    valor_carro number(6.2) := &value;
    valor_carro_juros number(6.2) := valor_carro + (valor_carro*0.03);
    valor_juros number(6.2) := valor_carro_juros - valor_carro;
    valor_carro_parcela number(6.2) := valor_carro_juros/10;
begin
    dbms_output.put_line('Valor do carro R$:'|| valor_carro ||'.00'); 
    dbms_output.put_line('Valor do carro com juros R$:'|| valor_carro_juros||'.00'); 
    dbms_output.put_line('Valor do juros bruto R$:'|| valor_juros||'.00'); 
    dbms_output.put_line('Valor de cada parcela R$:'|| valor_carro_parcela||'.00'); 
end;


-- EXERCÍCIO 05 – Criar um bloco PL-SQL para calcular o valor de cada parcela de uma compra de um carro, nas seguintes condições:
-- - Parcelas para aquisição em 6 pagamentos. 
-- - Parcelas para aquisição em 12 pagamentos. 
-- - Parcelas para aquisição em 18 pagamentos. 
-- OBSERVAÇÃO: 
-- 1 – Deverá ser dada uma entrada de 20% do valor da compra. 
-- 2 – Deverá ser aplicada uma taxa juros, no saldo restante, nas seguintes condições: 
-- 3 – No final informar o valor das parcelas para as 3 formas de pagamento, com o Valor de aquisição de 10.000.
-- A – Pagamento em 6 parcelas: 10%. 
-- B – Pagamento em 12 parcelas: 15%. 
-- C – Pagamento em 18 parcelas: 20%.

declare
    valor_carro number(6.2) := 10000;
    valor_entrada number(6.2) := valor_carro*0.2;
    valor_juros_em_6_parcelas number(6.2) := (((valor_carro - valor_entrada) * 0.1) +(valor_carro - valor_entrada))/6;
    valor_juros_em_12_parcelas number(6.2) := ((valor_carro - valor_entrada) * 0.15 +(valor_carro - valor_entrada))/12;
    valor_juros_em_18_parcelas number(6.2) := ((valor_carro - valor_entrada) * 0.20 +(valor_carro - valor_entrada))/18;
begin
    dbms_output.put_line('Valor do carro R$:'|| valor_carro ||'.00');
    dbms_output.put_line('Valor da entrada R$:'|| valor_entrada ||'.00');
    dbms_output.put_line('Valor parcelado em 6 vezes com juros de 10%. R$:'|| valor_juros_em_6_parcelas ||'.00');
    dbms_output.put_line('Valor parcelado em 12 vezes com juros de 15%. R$:'|| valor_juros_em_12_parcelas ||'.00');
    dbms_output.put_line('Valor parcelado em 18 vezes com juros de 18%. R$:'|| valor_juros_em_18_parcelas ||'.00');
end;