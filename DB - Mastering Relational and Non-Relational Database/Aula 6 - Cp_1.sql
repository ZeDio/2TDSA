set serveroutput on
set verify off

DROP TABLE produtos cascade constraints;
select * from produtos;

CREATE TABLE produtos (
 id_produto NUMBER(2),
 nome VARCHAR2(100),
 preco NUMBER(10,2),
 quantidade NUMBER(8,2),
 CONSTRAINT id_produto_pk PRIMARY KEY (id_produto)
 );
 
 -- Inserindo Dados
 INSERT INTO produtos (id_produto,nome,preco,quantidade) VALUES (1,'Samsung S24',2899.99,100);
 INSERT INTO produtos (id_produto,nome,preco,quantidade) VALUES (2,'Samsung Z Flip 5',3599.99,100);
 INSERT INTO produtos (id_produto,nome,preco,quantidade) VALUES (3,'Samsung S26 Ultra',10899.99,100);
 INSERT INTO produtos (id_produto,nome,preco,quantidade) VALUES (4,'Iphone 17 Pro Max',13899.99,100);
 INSERT INTO produtos (id_produto,nome,preco,quantidade) VALUES (5,'Motorola Razr 60 Ultra',7899.99,100);
 
 -- Sistema
 declare
    v_id_produto number(2);
    v_id_2 number(2) := &v_id_2;
    v_nome VARCHAR2(100);
    v_preco NUMBER(10,2);
    v_quantidade NUMBER(8,2);
    v_quantidade_2 NUMBER(8,2) := &v_quantidade;
    v_valor_total NUMBER(10,2);
    
    id_produto number(2);
    quantidade NUMBER(8,2);
 begin
    DBMS_OUTPUT.PUT_LINE ('');
    DBMS_OUTPUT.PUT_LINE ('Bem Vindo a SmartCLL !!');
    DBMS_OUTPUT.PUT_LINE ('');
    SELECT id_produto, nome, preco, quantidade INTO v_id_produto, v_nome, v_preco, v_quantidade FROM produtos WHERE id_produto = v_id_2; 
    
    if (v_id_produto = v_id_2) then
        if (v_quantidade > 0) then
            if (v_quantidade >= 5) then
                v_preco := v_preco*0.9;
            end if;
            v_valor_total := v_preco*v_quantidade_2;
            v_quantidade := v_quantidade - v_quantidade_2;
            UPDATE produtos SET quantidade = v_quantidade WHERE id_produto = v_id_2;
            DBMS_OUTPUT.PUT_LINE ('Produto selecionado é: ' || v_nome);
            DBMS_OUTPUT.PUT_LINE ('Valor: ' || v_valor_total);
            DBMS_OUTPUT.PUT_LINE ('Quantidade disponível em estoque: ' || v_quantidade);
            DBMS_OUTPUT.PUT_LINE ('Venda realizada com sucesso');
        else
            DBMS_OUTPUT.PUT_LINE ('Estoque insuficiente !!');
        end if;
    else
        DBMS_OUTPUT.PUT_LINE ('Produto não encontrado !!');
    end if;
 end;