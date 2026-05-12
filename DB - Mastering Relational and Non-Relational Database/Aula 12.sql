-- Aula 12 - 12/05/2026
set serveroutput on
set verify off

-- Procedures
Create or Replace Procedure soma(
p_1 in number, p_2 in number
) is p_t number(8,2);
begin
    p_t := p_1 + p_2;
    dbms_output.put_line(p_1||'+'||p_2||'='||p_t);
end soma;

-- Chamando o procedimento e passando os valores
Exec soma(1,1);

-- Pode fazer dentro dos blocos de progamação 
declare
    v_1 number := &v1;
    v_2 number := &v2;
begin
    -- chamando o procedimento
    soma(v_1,v_2);
end;

select * from aluno;

create or replace procedure proc_nome_aluno (p_ra in char)
is v_nome varchar(50);
begin
    select nome into v_nome from aluno where ra = p_ra;
    dbms_output.put_line (v_nome);
    EXCEPTION
		WHEN NO_DATA_FOUND THEN
			DBMS_OUTPUT.PUT_LINE ('Não há nenhum aluno com este RA');
end proc_nome_aluno;

exec proc_nome_aluno(6);


-- Verificar o status de uma procedure
SELECT OBJECT_NAME, OBJECT_TYPE, STATUS
FROM USER_OBJECTS WHERE OBJECT_NAME = 'PROC_NOME_ALUNO';

-- Para visualizar o código-fonte de uma procedure:
SELECT TEXT FROM USER_SOURCE
WHERE NAME = 'PROC_NOME_ALUNO' ORDER BY LINE;

-- Para localizar os eventuais erros em uma procedure:
SHOW ERRORS PROCEDURE NOME_DA_PROCEDURE;
 
-- Para eliminar uma procedure:
DROP PROCEDURE PROC_NOME_ALUNO;


/*
Criar um procedimento que receba 3 notas, calcule a média aritmética e
exiba o resultado. A passsagem de dados deve ser via bloco de programação
 
Atuelize seu procedimento, eliminando a menor nota e usando exception no caso
de divisão por zero: zero_divide - nome da exception
*/

create or replace procedure media_notas (nota1 number, nota2 number, nota3 number)
is media number(4,2);
begin 
    media := (nota1 + nota2 + nota3)/3;
    dbms_output.put_line('('||nota1||'+'||nota2||'+'||nota3||')/3 = '||media);
end media_notas;

declare
    nota1 number(4,2) := &nota1;
    nota2 number(4,2) := &nota2;
    nota3 number(4,2) := &nota3;
begin
    media_notas(nota1, nota2, nota3);
end;

/*
Atuelize seu procedimento, eliminando a menor nota e usando exception no caso
de divisão por zero: zero_divide - nome da exception
*/

create or replace procedure media_notas (nota1 number, nota2 number, nota3 number)
is media number(4,2);
begin 
    if (nota1 < nota2 and nota1 < nota3) then
        media := (nota2 + nota3)/2;
        dbms_output.put_line('('||nota2||'+'||nota3||')/2 = '||media);
    elsif (nota2 < nota1 and nota2 < nota3) then
        media := (nota1 + nota3)/2;
        dbms_output.put_line('('||nota1||'+'||nota3||')/2 = '||media);
    else
        media := (nota1 + nota2)/2;
        dbms_output.put_line('('||nota1||'+'||nota2||')/2 = '||media);
    end if;
    exception
        when zero_divide then
            dbms_output.put_line('Divisão por zero');
            media := 0;
end media_notas;

declare
    nota1 number(4,2) := &nota1;
    nota2 number(4,2) := &nota2;
    nota3 number(4,2) := &nota3;
begin
    media_notas(nota1, nota2, nota3);
end;

/*
Crie um novo procedimento para exibir a seguinte msg:
Média >= 6 - Aprovado
Média <  6 - Reprovado
*/

create or replace procedure media_notas (nota1 number, nota2 number, nota3 number)
is media number(4,2);
begin 
    if (nota1 < nota2 and nota1 < nota3) then
        media := (nota2 + nota3)/2;
        dbms_output.put_line('('||nota2||'+'||nota3||')/2 = '||media);
    elsif (nota2 < nota1 and nota2 < nota3) then
        media := (nota1 + nota3)/2;
        dbms_output.put_line('('||nota1||'+'||nota3||')/2 = '||media);
    else
        media := (nota1 + nota2)/2;
        dbms_output.put_line('('||nota1||'+'||nota2||')/2 = '||media);
    end if;
    
    if (media >= 6) then
        dbms_output.put_line('Aprovado com a media: '||media);
    else
        dbms_output.put_line('Reprovado com a media: '||media);
    end if;
    exception
        when zero_divide then
            dbms_output.put_line('Divisão por zero');
            media := 0;
end media_notas;

declare
    nota1 number(4,2) := &nota1;
    nota2 number(4,2) := &nota2;
    nota3 number(4,2) := &nota3;
begin
    media_notas(nota1, nota2, nota3);
end;

/*
Dada a tabela: Veiculo, com os campos: placa, marca, modelo, ano, preco_fipe, preco_venda
Crie um procedimento que grave na coluna preco_venda o valor da fipe com acrescimo de 5%.
placa  abc3322 - def4437  - jjj1234 - kks9999
marca  vw      - GM       - BYD     - Bmw
modelo fusca   - chevette - não sei - 325i
ano    2020    - 1996     - 2026    - 2023
Fipe   120000  - 55000    - 170000  - 250000
Venda
*/

create table veiculos(
    placa varchar(7) primary key,
    marca varchar(10),
    modelo varchar(40),
    ano number(4),
    preco_fipe number(10,2),
    preco_venda number(10,2)
);

begin
    INSERT INTO veiculos VALUES ('abc3322', 'vw', 'fusca', 2020, 120000, NULL);
    INSERT INTO veiculos VALUES ('def4437', 'GM', 'chevette', 1996, 55000, NULL);
    INSERT INTO veiculos VALUES ('jjj1234', 'BYD', 'não sei', 2026, 170000, NULL);
    INSERT INTO veiculos VALUES ('kks9999', 'Bmw', '325i', 2023, 250000, NULL);
end;

create or replace procedure proc_preco_venda (preco_fipe number)
is preco_venda number(10,2);
begin
    preco_venda := preco_fipe(preco_fipe*0.05);
    UPDATE Veiculo SET preco_venda = 130000 WHERE placa = c_cur;