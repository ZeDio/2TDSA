-- Aula 11 - Cp 2 - 05/05/2026
set serveroutput on
set verify off

/*
Avaliação Prática 2 – Funcionários e Departamentos

Considere as tabelas:
FUNCIONARIOS (id_func, nome, salario, id_departamento, bonus)
DEPARTAMENTOS (id_departamento, nome_departamento)

Desenvolva um bloco PL/SQL que:
1. Utilize um cursor explícito para percorrer os funcionários
2. Para cada funcionário:
      Leia o nome do departamento
3. Realize o seguinte processamento:
      Salário ≥ 5000 → bônus = 10% do salário
      Salário entre 3000 e 4999 → bônus = 5%
      Caso contrário → bônus = 2%
4. Faça uma atualização do bônus na tabela FUNCIONARIOS
5. Ao final, exiba:
      Total de funcionários processados
      Soma total dos bônus pagos
*/

DROP TABLE FUNCIONARIOS CASCADE CONSTRAINTS;
DROP TABLE DEPARTAMENTOS CASCADE CONSTRAINTS;

-- Criando as tabelas
Create Table FUNCIONARIOS (
    id_func number(4) primary key,
    salario number(8,2),
    id_departamento number(4),
    bonus number(4,2)
);

Create Table DEPARTAMENTOS (
    id_departamento number(4) primary key,
    nome_departamento varchar(40)
);

-- Inserindo os valores
INSERT INTO FUNCIONARIOS VALUES (1, 5100.00, 1, 0);
INSERT INTO FUNCIONARIOS VALUES (2, 3500.00, 1, 0);
INSERT INTO FUNCIONARIOS VALUES (3, 2000.00, 2, 0);
INSERT INTO FUNCIONARIOS VALUES (4, 1000.00, 2, 0);

INSERT INTO DEPARTAMENTOS VALUES (1, 'Area TI');
INSERT INTO DEPARTAMENTOS VALUES (2, 'Area Atendimento');

select * from FUNCIONARIOS

declare
    v_nome_departamento varchar(40);
    v_salario number(8,2) := 0;
    v_bonus number(4,2) := 0;
    total_funcionarios number(1) := 0;
    total_bonus number(8,2) := 0;
    
    Cursor c_exibe_funcionarios is select * from FUNCIONARIOS;
    Cursor c_exibe_departamentos is select * from DEPARTAMENTOS;
begin    
    for v_exibe_funcionarios in c_exibe_funcionarios loop
        total_funcionarios := 1 + total_funcionarios;
        if (v_exibe_funcionarios.salario > 5000) then
            v_bonus := 0.1;

            UPDATE FUNCIONARIOS SET bonus = v_bonus WHERE id_func = v_exibe_funcionarios.id_func;
                
            v_salario := v_exibe_funcionarios.salario+(v_bonus * v_exibe_funcionarios.salario);
            total_bonus := (v_bonus * v_exibe_funcionarios.salario) + total_bonus;

            UPDATE FUNCIONARIOS SET salario = v_salario WHERE id_func = v_exibe_funcionarios.id_func;
                
            dbms_output.put_line('ID Funcionario: ' || v_exibe_funcionarios.id_func || ' - Nome Departamento:' || v_nome_departamento || ' - Salario + Bonus: ' || v_exibe_funcionarios.salario);
        elsif (v_exibe_funcionarios.salario > 3000 and v_exibe_funcionarios.salario > 4999) then
            v_bonus := 0.05;

            UPDATE FUNCIONARIOS SET bonus = v_bonus WHERE id_func = v_exibe_funcionarios.id_func;
                
            v_salario := v_exibe_funcionarios.salario+(v_bonus * v_exibe_funcionarios.salario);
            total_bonus := (v_bonus * v_exibe_funcionarios.salario) + total_bonus;

            UPDATE FUNCIONARIOS SET salario = v_salario WHERE id_func = v_exibe_funcionarios.id_func;
                
            dbms_output.put_line('ID Funcionario: ' || v_exibe_funcionarios.id_func || ' - Nome Departamento:' || v_nome_departamento || ' - Salario + Bonus: ' || v_exibe_funcionarios.salario);
        else
            v_bonus := 0.02;

            UPDATE FUNCIONARIOS SET bonus = v_bonus WHERE id_func = v_exibe_funcionarios.id_func;
                
            v_salario := v_exibe_funcionarios.salario+(v_bonus * v_exibe_funcionarios.salario);
            total_bonus := (v_bonus * v_exibe_funcionarios.salario) + total_bonus;
            
            UPDATE FUNCIONARIOS SET salario = v_salario WHERE id_func = v_exibe_funcionarios.id_func;
                
            dbms_output.put_line('ID Funcionario: ' || v_exibe_funcionarios.id_func || ' - Nome Departamento:' || v_nome_departamento || ' - Salario + Bonus: ' || v_exibe_funcionarios.salario);
        end if;
    end loop;
    
    dbms_output.put_line('Total de Funcionarios processados: '|| total_funcionarios ||' - Bonus totais pagos:'||total_bonus);
end;
