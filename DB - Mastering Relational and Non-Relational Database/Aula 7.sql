--Aula 7 31/03/2026

--Ativar a saída de dados
set serveroutput on
--desabilitar saida de variaveis
set verify off

-- Aprendendo laço de repetção - Loop
declare
    v_contador number(2):= 1;
begin
    loop 
        dbms_output.put_line(v_contador);
        v_contador := v_contador + 1;
        exit when v_contador > 20;
    end loop;
end;


-- Aprendendo laço de repetção - While
declare
    v_contador number(2):= 1;
begin
    while v_contador <= 20 loop
        dbms_output.put_line(v_contador);
        v_contador := v_contador + 1;
    end loop;
end;


-- Aprendendo laço de repetção - For
begin
    for v_contador in 0 .. 20 loop
        dbms_output.put_line(v_contador);
    end loop;
end;


-- Aprendendo laço de repetção - For reverse
begin
    for v_contador in reverse 0 .. 20 loop
        dbms_output.put_line(v_contador);
    end loop;
end;


-- Exercícios
-- 1 - Montar um bloco de programação que realize o processamento de uma tabuada qualquer, por exemplo a tabuada do número 150.
-- meu
declare 
    v_contador number(3) := 0;
    v_num number(3) := 150;
    v_amostra  number(4);
begin
    loop
        v_amostra := v_num * v_contador;
        dbms_output.put_line(v_amostra);
        v_contador := v_contador + 1;
        exit when v_contador > 10;
    end loop;
end;
-- do prof
declare 
    v_num number(3) := &valor;
    v_conta  number(2) := 0;
    
    v_num_2 number(3) := v_num;
    v_conta_2  number(2) := 0;
    
    v_num_3 number(3) := v_num;
    -- v_conta_3  number(2) := 0;
begin
    dbms_output.put_line('');
    dbms_output.put_line('- Loop -');
    loop
        dbms_output.put_line(v_num || ' x ' || v_conta || ' = ' || v_num * v_conta);
        v_conta := v_conta + 1;
        exit when v_conta > 10;
    end loop;
    
    dbms_output.put_line('');
    dbms_output.put_line('- While -');
    
    while v_conta_2 <= 10 loop
        dbms_output.put_line(v_num_2 || ' x ' || v_conta_2 || ' = ' || v_num_2 * v_conta_2);
        v_conta_2 := v_conta_2 + 1;
    end loop;
    
    dbms_output.put_line('');
    dbms_output.put_line('- For -');
    
    for v_conta_3 in 0 .. 10 loop
        dbms_output.put_line(v_num_3 || ' x ' || v_conta_3 || ' = ' || v_num_3 * v_conta_3);
    end loop;
end;


-- 2 - Em um intervalo numérico interio, informar quantos números são pares e quantos são ímpares.
-- Com a media dos pares e a soma dos impares
declare
    v_comeco number(4) := &valor;
    v_fim number(4) := &valor2;
    
    v_impar number(4):= 0;
    v_par number(4):= 0;
begin
    for v_ir in v_comeco .. v_fim loop
        if mod(v_ir, 2) = 0 then
            v_par := v_par + 1;
        else 
            v_impar := v_impar +1;
        end if;
    end loop;
    
    dbms_output.put_line('Verificar quantos numeros tem pares e impares na no intervalo de '|| v_comeco ||' até '|| v_fim);
    dbms_output.put_line('');
    dbms_output.put_line('Quantidade de numeros Pares: ' || v_par);
    dbms_output.put_line('Quantidade de numeros Impares: ' || v_impar);
end;


-- 3 - Em um intervalo numérico interio, informar quantos números são pares e quantos são ímpares.
-- Com a media dos pares e a soma dos impares
declare
    v_comeco number(4) := &valor;
    v_fim number(4) := &valor2;
    
    v_impar number(4):= 0;
    v_impar_soma number(4):= 0;
    v_par number(4):= 0;
    v_par_soma number(6):= 0;
    v_media_par number(6):= 0;
begin
    for v_ir in v_comeco .. v_fim loop
        if mod(v_ir, 2) = 0 then
            v_par := v_par + 1;
            v_par_soma := v_ir + v_par_soma;
            v_media_par := v_par_soma/v_par;
        else 
            v_impar := v_impar +1;
            v_impar_soma  := v_impar + v_ir;
        end if;
    end loop;
    
    dbms_output.put_line('Verificar quantos numeros tem pares e impares na no intervalo de '|| v_comeco ||' até '|| v_fim);
    dbms_output.put_line('');
    dbms_output.put_line('Quantidade de numeros Pares: ' || v_par);
    dbms_output.put_line('Media dos valores pares: ' || v_media_par);
    dbms_output.put_line('Quantidade de numeros Impares: ' || v_impar);
    dbms_output.put_line('Soma dos numeros impares: ' || v_impar_soma);
end;