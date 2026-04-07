--Aula 8 07/04/2026

--Ativar a saída de dados
set serveroutput on;
--desabilitar saida de variaveis
set verify off;
--Ver modelo de data
select sysdate from dual;

-- Cursores - Vetores em BD
-- Exemplo

--Excluindo tabela se existir e verificando
drop table funcionarios;
delete from funcionarios;
select * from funcionarios;

--Criando tabela funcionarios
create table funcionarios(
    cod_fun number(3) primary key,
    nm_fun varchar2(20),
    salario number(10,2),
    dt_adm date
);

--Inserindo dados na tablela funcionarios
begin
insert into funcionarios values (1, 'Marcel', 10000, '17/04/2000');
insert into funcionarios values (2, 'Claudia', 16000, '02/10/1998');
insert into funcionarios values (3, 'Joaquim', 5500, '10/07/2010');
insert into funcionarios values (4, 'Valéria', 7300, '08/06/2015');
commit;
end;

--Verificando entrada de dados na tabela
select * from funcionarios;

--Criando primeiro cursor usando Loop
declare
    cursor c_exibe is select nm_fun, salario from funcionarios;
    v_exibe c_exibe%rowtype;
begin
    open c_exibe;
    loop
        fetch c_exibe into v_exibe;
    exit when c_exibe%notfound;
    dbms_output.put_line('Nome: '||v_exibe.nm_fun||' - Salario: '||v_exibe.salario);
    end loop;
    close c_exibe;
end;

--Mesmo codigo com For
declare
    cursor c_exibe is select nm_fun, salario from funcionarios;
begin
    for v_exibe in c_exibe loop
        dbms_output.put_line('Nome: '||v_exibe.nm_fun||' - Salario: '||v_exibe.salario);
    end loop;
end;

--Atualizando a tabela para adicionar novo campo Tempo
alter table funcionarios add (tempo number(5));

--Verificando se adicionou o novo campo na tabela
select * from funcionarios;

--Fazendo calcular quantos dias o funcionario tem na empresa
declare 
    cursor c_exibe is select * from funcionarios;
begin
    for v_exibe in c_exibe loop
        update funcionarios set tempo = sysdate - v_exibe.dt_adm
        where cod_fun = v_exibe.cod_fun;
        dbms_output.put_line('Nome: '||v_exibe.nm_fun||' - Tempo na empresa: '|| v_exibe.tempo);
    end loop;
end;

--Fazer acresentar 10% no salario se o funcionario passar mais de 150 meses, os de mais 5%
--Falta eu terminar
declare 
    cursor c_exibe is select * from funcionarios;
begin
    for v_exibe in c_exibe loop
        if () then
        
    end loop;
end;