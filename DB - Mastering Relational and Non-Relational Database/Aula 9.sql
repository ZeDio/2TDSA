--Aula 9 14/04/2026

--Ativar a saída de dados
set serveroutput on;
--desabilitar saida de variaveis
set verify off;

/*
Criar um bloco PL/SQL (usando cursor) para atualizar a tabela abaixo,
conforme segue:
 Produtos categoria A deverão ser reajustados em 5%
 Produtos categoria B deverão ser reajustados em 10%
 Produtos categoria C deverão ser reajustados em 15%
*/
--Excluindo tabela se existir e verificando
drop table PRODUTO_Cp;
delete from PRODUTO_Cp;
select * from PRODUTO_Cp;

CREATE TABLE PRODUTO_Cp (
CODIGO NUMBER(4),
CATEGORIA CHAR(1),
VALOR NUMBER(4,2));

INSERT INTO PRODUTO_Cp VALUES (1001,'A',7.55);
INSERT INTO PRODUTO_Cp VALUES (1002,'B',5.95);
INSERT INTO PRODUTO_Cp VALUES (1003,'C',3.45);

declare
    valor_atual NUMBER(6.2); 
    cursor c_exibe is select CATEGORIA ,VALOR from PRODUTO_Cp;
begin
    for v_exibe in c_exibe loop
        valor_atual := v_exibe.VALOR+(v_exibe.VALOR*0.05);
        dbms_output.put_line('Categoria: '||v_exibe.CATEGORIA||' - Valor: '||valor_atual);
    end loop;
end;


/*
Criar um bloco que receberá um RA, um NOME e quatro notas conforme a
sequência: (RA,NOME,A1,A2,A3,A4), mínimo de dados: 2 linhas uma para
aprovado e uma para reprovado.
A partir criar um bloco usando cursores para processar o cálculo da média
somando o maior valor entre A1 e A2 às notas A3 e A4 e dividindo o valor
obtido por três (achando a média).
Se a média for menor que 6 (seis) o aluno estará REPROVADO e se a média
for igual ou superior a 6 (seis) o aluno estará APROVADO.
O bloco deverá inserir os valores acima numa tabela denominada ALUNO com
as seguintes colunas RA,NOME,A1,A2,A3,A4,MEDIA,RESULTADO.
*/
--Excluindo tabela se existir e verificando
drop table aluno_cp;
delete from aluno_cp;
select * from aluno_cp;

create table aluno_cp (ra number(2) primary key, nome varchar(20),
A1 number(4,2), A2 number(4,2), A3 number(4,2), A4 number(4,2),
media number(4,2), resultado varchar(15));

declare
    v_media number(4,2);
    v_resultado varchar(15);
    
    cursor c_exibe is select * from aluno_cp;
begin
    -- Inserindo os alunos na tabela
    INSERT INTO aluno_cp VALUES (1,'Andre Colombo',8,9,10,5,null,null);
    INSERT INTO aluno_cp VALUES (2,'José Diogo',6,5,4,3,null,null);
    INSERT INTO aluno_cp VALUES (3,'Arthur Cabral',9,5,7,6,null,null);
    INSERT INTO aluno_cp VALUES (4,'Vitor Fria',4,7,4,3,null,null);

    for v_exibe in c_exibe loop
        -- Calculo da media
        if (v_exibe.A1 > v_exibe.A2) then
            v_media := (v_exibe.A1 + v_exibe.A3 + v_exibe.A4)/3;
            update aluno_cp set media = v_media where ra = v_exibe.ra;
        else
            v_media := (v_exibe.A2 + v_exibe.A3 + v_exibe.A4)/3;
            update aluno_cp set media = v_media where ra = v_exibe.ra;
        end if;
        
        -- Resultado Aprovado/Reprovado
        if (v_media >= 6) then
            v_resultado := 'Aprovado';
            update aluno_cp set resultado = v_resultado where ra = v_exibe.ra;
            dbms_output.put_line('Nome: '||v_exibe.nome||' - Media: '||v_media||' - Resultado: '||v_resultado);
        else
            v_resultado := 'Reprovado';
            update aluno_cp set resultado = v_resultado where ra = v_exibe.ra;
            dbms_output.put_line('Nome: '||v_exibe.nome||' - Media: '||v_media||' - Resultado: '||v_resultado);
        end if;
    end loop;
end;


/*
Exercício 3
Dadas as tabelas criar um bloco PL com cursores que transfira
apenas os dados diferentes entre as tabelas A e B para a tabela C

Tabela A                Tabela B                      Tabela C
1 - Água                1 - Café
2 - Refrigerante       2 - Refrigerante
3 - Arroz               3 - Macarrão
4 - Sal                 4 - Vinagre
5 - Vinagre             5 - Arroz
6 - Pão Integral       6 - Pão de Forma
7 - Danone              7 - Energético
8 - Energético          8 - Molho de Tomate
*/
--Excluindo tabela se existir e verificando
drop table tabela_a;
delete from tabela_a;
select * from tabela_a;

drop table tabela_b;
delete from tabela_b;
select * from tabela_b;

drop table tabela_c;
delete from tabela_c;
select * from tabela_c;


-- Criando as tabelas
CREATE TABLE tabela_a (
    id NUMBER PRIMARY KEY,
    nome VARCHAR2(50)
);
CREATE TABLE tabela_b (
    id NUMBER PRIMARY KEY,
    nome VARCHAR2(50)
);
CREATE TABLE tabela_c (
    id NUMBER PRIMARY KEY,
    nome VARCHAR2(50)
);
 
 
-- Inserindo os dados nas tabelas
begin
    INSERT INTO tabela_a (id, nome) VALUES (1, 'Água');
    INSERT INTO tabela_a (id, nome) VALUES (2, 'Refrigerante');
    INSERT INTO tabela_a (id, nome) VALUES (3, 'Arroz');
    INSERT INTO tabela_a (id, nome) VALUES (4, 'Sal');
    INSERT INTO tabela_a (id, nome) VALUES (5, 'Vinagre');
    INSERT INTO tabela_a (id, nome) VALUES (6, 'Pão Integral');
    INSERT INTO tabela_a (id, nome) VALUES (7, 'Danone');
    INSERT INTO tabela_a (id, nome) VALUES (8, 'Energético');
     
    INSERT INTO tabela_b (id, nome) VALUES (1, 'Café');
    INSERT INTO tabela_b (id, nome) VALUES (2, 'Refrigerante');
    INSERT INTO tabela_b (id, nome) VALUES (3, 'Macarrão');
    INSERT INTO tabela_b (id, nome) VALUES (4, 'Vinagre');
    INSERT INTO tabela_b (id, nome) VALUES (5, 'Arroz');
    INSERT INTO tabela_b (id, nome) VALUES (6, 'Pão de Forma');
    INSERT INTO tabela_b (id, nome) VALUES (7, 'Energético');
    INSERT INTO tabela_b (id, nome) VALUES (8, 'Molho de Tomate');
end;

-- Agora fazendo o codigo
declare
    v_id number := 0;

    cursor c_exibe_tabela_a is select id, nome from tabela_a;
    cursor c_exibe_tabela_b is select id, nome from tabela_b;
    
    v_nome_a tabela_a.nome%type;
    v_nome_b tabela_b.nome%type;
begin
    -- Inserir dados da tabela_a que não existem na tabela_b
    for v_exibe_tabela_a in c_exibe_tabela_a loop
        v_nome_a := v_exibe_tabela_a.nome;
        
        -- Comparar se o nome de tabela_a não existe na tabela_b
        declare
            v_encontrado boolean := false;
        begin
            for v_exibe_tabela_b in c_exibe_tabela_b loop
                if v_exibe_tabela_b.nome = v_nome_a then
                    v_encontrado := true;
                    exit;
                end if;
            end loop;

            -- Se não encontrou, insere o dado de tabela_a
            if not v_encontrado then
                v_id := v_id + 1;
                INSERT INTO tabela_c (id, nome) VALUES (v_id, v_nome_a);
            end if;
        end;
    end loop;
    
    -- Inserir dados da tabela_b que não existem na tabela_a
    for v_exibe_tabela_b in c_exibe_tabela_b loop
        v_nome_b := v_exibe_tabela_b.nome;
        
        -- Comparar se o nome de tabela_b não existe na tabela_a
        declare
            v_encontrado boolean := false;
        begin
            for v_exibe_tabela_a in c_exibe_tabela_a loop
                if v_exibe_tabela_a.nome = v_nome_b then
                    v_encontrado := true;
                    exit;
                end if;
            end loop;
            
            -- Se não encontrou, insere o dado de tabela_b
            if not v_encontrado then
                v_id := v_id + 1;
                INSERT INTO tabela_c (id, nome) VALUES (v_id, v_nome_b);
            end if;
        end;
    end loop;
end;