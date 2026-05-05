-- Aula 10 - 28/04/2026
set serveroutput on
set verify off

/*
Exceptions:
Condição para bular erros que acontecem na programação, vinculada a regras de
negócio ou algum processamento
mais usadas: no_data_found - não acha por dado(s) vinvulados a uma pesquisa
             too_many_rows - retorno maior do que suportado, geralmente mais 
                             de uma linha, não utilização de cursores
             dup_val_on_index - duplicidade de dado (conteúdo) em uma coluna
sempre usada no final de um bloco de programação
*/

-- Exception - tratamento de erros - pré definida
DECLARE
	...
BEGIN	
	...
	EXCEPTION
		WHEN NOME_DA_EXCEÇÃO THEN
		RELAÇÃO_DE_COMANDOS;
		WHEN NOME_DA_EXCEÇÃO THEN
		RELAÇÃO_DE_COMANDOS;
		...
END;

-- Exemplo
drop table aluno cascade constraints;
create table aluno (ra number(1), nome varchar(20));
insert into aluno values (1,'Marcel');
insert into aluno values (2,'Adriana');
insert into aluno values (3,'Samuel');
commit;
 
DECLARE
	V_RA ALUNO.RA%TYPE := &ra;
	V_NOME ALUNO.NOME%TYPE;
BEGIN
	SELECT NOME INTO V_NOME FROM ALUNO WHERE RA = V_RA;
	DBMS_OUTPUT.PUT_LINE(V_RA ||' - '|| V_NOME);
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
			DBMS_OUTPUT.PUT_LINE ('Não há nenhum aluno com este RA');
		WHEN TOO_MANY_ROWS THEN
			DBMS_OUTPUT.PUT_LINE ('Há mais de um aluno');
  		WHEN Others THEN
			DBMS_OUTPUT.PUT_LINE ('Chame o técnico');
END;

-- Personalizada
DECLARE
	NOME_DA_EXCEÇÃO EXCEPTION;
BEGIN
	...
	IF ... THEN
		RAISE NOME_DA_EXCEÇÃO;
	END IF;
	...
	EXCEPTION
		WHEN NOME_DA_EXCEÇÃO THEN
		RELAÇÃO_DE_COMANDOS
END;

DECLARE
	V_CONTA NUMBER(2);
	TURMA_CHEIA EXCEPTION;
BEGIN
	SELECT COUNT(RA) INTO V_CONTA FROM ALUNO;
	IF V_CONTA = 5 THEN
		RAISE TURMA_CHEIA;
	ELSE
		INSERT INTO ALUNO VALUES (&ra,'&nome');
	END IF;
	EXCEPTION
	WHEN TURMA_CHEIA THEN
		DBMS_OUTPUT.PUT_LINE('Não foi possível incluir: turma cheia');
END;

select * from aluno;

/*
Criar um bloco PL/SQL (usando cursor) para atualizar a tabela abaixo,
conforme segue, não esqueça de implementar as exceptions possiveis para
tratamento de erros:
Produtos categoria A deverão ser reajustados em 5%
Produtos categoria B deverão ser reajustados em 10%
Produtos categoria C deverão ser reajustados em 15%
*/

drop table produto_cp cascade constraints;
 
CREATE TABLE PRODUTO_Cp (
    CODIGO NUMBER(4) primary key,
    CATEGORIA CHAR(1),
    VALOR NUMBER(4,2)
);

INSERT INTO PRODUTO_Cp VALUES (1001,'A',7.55);
INSERT INTO PRODUTO_Cp VALUES (1002,'B',5.95);
INSERT INTO PRODUTO_Cp VALUES (1003,'C',3.45);

DECLARE
    CURSOR c_produto IS SELECT * FROM PRODUTO_Cp;
BEGIN
    FOR v_produto IN c_produto LOOP
        IF v_produto.CATEGORIA = 'A' THEN
            UPDATE PRODUTO_Cp SET VALOR = (VALOR * 1.05)
            WHERE CODIGO = v_produto.CODIGO;
        ELSIF v_produto.CATEGORIA = 'B' THEN
            UPDATE PRODUTO_Cp SET VALOR = (VALOR * 1.10)
            WHERE CODIGO = v_produto.CODIGO;
        ELSE
            UPDATE PRODUTO_Cp SET VALOR = (VALOR * 1.15)
            WHERE CODIGO = v_produto.CODIGO;
        END IF;
    END LOOP;

    EXCEPTION
		WHEN v_categoria_invalida THEN
			DBMS_OUTPUT.PUT_LINE('Erro: categoria inválida encontrada.');

		WHEN ZERO_DIVIDE THEN
			DBMS_OUTPUT.PUT_LINE('Erro: divisão por zero.');

		WHEN OTHERS THEN
			DBMS_OUTPUT.PUT_LINE('Erro inesperado: ' || SQLERRM);
    
END;