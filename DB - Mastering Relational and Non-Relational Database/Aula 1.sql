--Aula 1 10/02/2026

--Ativar a saída de dados
set serveroutput on

--desabilitar saida de variaveis
set verify off

-- declare - begin - exception - end


-- Imprimindo dados em tela
begin
    -- Saida de dados
    dbms_output.put_line('José');
end;

begin
    -- Saida de dados
    dbms_output.put_line(562341);
end;

begin
    -- Saida de dados com concatenação
    dbms_output.put_line('José -' || 'Fiap');
end;

begin
    -- Saida de dados
    dbms_output.put_line(562341 + 562341);
end;


-- Crie um progama que concatene uma string com o resultado de um processamento matematico qualquer...
declare
    v_cod number(2):=10;
    v_nome varchar2(10):= 'Fiap';
begin
    dbms_output.put_line(v_cod || ' - ' || v_nome);
end;