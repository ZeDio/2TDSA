--Aula 4 10/03/2026

--Ativar a saída de dados
set serveroutput on
--desabilitar saida de variaveis
set verify off


-- Instrução DQL, exemplo
-- Deletando a tabela se tiver uma
drop table aluno

-- Criando uma tabela
create table ALUNO( 
rm Char(9),
nome varchar(50),
constraint aluno_pk primary key(rm)
);

-- Inserindo 3 dados na tabela
insert into aluno (rm,nome) values ('111','Antonio Alves');
insert into aluno (rm,nome) values ('222','Beatriz Bernades');
insert into aluno (rm,nome) values ('333','Cláudio Cardoso');

-- Instrução DQL, buscando dados na tabela ALUNO
declare
    v_rm char(3) := &v_rm;
    v_nome varchar(50);
begin
    select nome into v_nome from aluno where rm = v_rm;
    dbms_output.put_line ('O nome do aluno de rm '|| v_rm ||', é o '|| v_nome||'.');
end;

-- Inserindo Dados
declare
    v_rm char(3) := &v_rm;
    v_nome varchar(50) := '&v_nome';
begin
    insert into aluno (rm,nome) values (v_rm,v_nome);
    dbms_output.put_line ('O nome do aluno de rm '|| v_rm ||', é o '|| v_nome||'.');
end;

-- Update nos Dados
declare
    v_rm char(3) := &v_rm;
    v_nome varchar(50) := '&v_nome';
begin
    update aluno set nome = v_nome where rm = v_nome;
    dbms_output.put_line ('O nome do aluno de rm '|| v_rm ||' foi alterado.');
    dbms_output.put_line ('O nome do aluno de rm '|| v_rm ||', é o '|| v_nome||'.');
end;

-- Delete nos Dados
declare
    v_rm char(3) := &v_rm;
begin
    delete from aluno where rm = v_rm;
end;


-- Exercicio 1
create table Boletim( 
rm number(3) not null,
cd_disciplina number(3) not null,
nm_disciplina varchar(30) not null,
carga_hora number(3) not null,
cp_1 number(4,2),
cp_2 number(4,2),
cp_3 number(4,2),
media number(4,2),
faltas number(3),
situacao varchar(50),
constraint aluno_pk primary key(rm)
);
-- insert into boletim (rm,cd_disciplina,nm_disciplina,carga_hora,cp_1,cp_2,cp_3,faltas) values ('111','1','progamação','40','10','5','7','4');

declare
    v_rm number(3) := '111';
    v_cd_disciplina number(3) := '1';
    v_nm_disciplina varchar(30) := 'progamação';
    v_carga_hora number(3) := '40';
    v_cp_1 number(4,2) := '10';
    v_cp_2 number(4,2) := '5';
    v_cp_3 number(4,2) := '7';
    v_media number(3);
    v_faltas number(3) := '4';
    v_situacao varchar(50);
begin
-- Inserindo dados
    insert into Boletim (rm, cd_disciplina, nm_disciplina, carga_hora, cp_1, cp_2, cp_3, faltas) values (v_rm, v_cd_disciplina, v_nm_disciplina, v_carga_hora, v_cp_1, v_cp_2, v_cp_3, v_faltas);
-- sla
    
end;