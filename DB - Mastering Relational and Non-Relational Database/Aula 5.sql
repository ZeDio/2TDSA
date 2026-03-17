--Aula 5 17/03/2026

--Ativar a saída de dados
set serveroutput on
--desabilitar saida de variaveis
set verify off


-- Instrução DQL, exemplo
drop table Boletim;
delete from Boletim;
select * from Boletim;

-- Deletando a tabela
-- Exercicio
create table Boletim( 
cd_disciplina number(3) not null,
nm_disciplina varchar(30) not null,
carga_hora number(3) not null,
cp_1 number(4,2),
cp_2 number(4,2),
cp_3 number(4,2),
media number(4,2),
faltas number(3),
situacao varchar(50)
);


-- Inserindo os valores
begin
    insert into Boletim values (1, 'BD', 100, 10, 8, 9, null, 5, null);
    insert into Boletim values (2, 'PY', 100, 6, 8, 9, null, 5, null);
    insert into Boletim values (3, 'FT', 100, 3, 8, 5, null, 5, null);
    insert into Boletim values (4, 'JV', 100, 7, 8, 3, null, 5, null);
    insert into Boletim values (5, 'IA', 100, 5, 8, 4, null, 5, null);
    insert into Boletim values (6, 'IA', 100, 6, 3, 4, null, 5, null);
end;



declare
    v_cd_disciplina number(3) := &disciplina;
    v_carga_hora  v_cd_disciplina%type;
    v_faltas v_cd_disciplina%type;
    v_cp_1 v_cd_disciplina%type;
    v_cp_2 v_cd_disciplina%type;
    v_cp_3 v_cd_disciplina%type;
    v_media v_cd_disciplina%type;
    v_situacao varchar(50);
begin
    select carga_hora, cp_1, cp_2, cp_3, faltas 
    into v_carga_hora, v_cp_1, v_cp_2, v_cp_3, v_faltas
    from Boletim where cd_disciplina = v_cd_disciplina;


    if (v_cp_1 = v_cp_2 or v_cp_1 = v_cp_3) then
        v_media := (v_cp_2 + v_cp_3)/2;
        update Boletim set media = v_media where cd_disciplina = v_cd_disciplina;
        dbms_output.put_line('Sua media é de :'|| v_media);
    elsif (v_cp_1 < v_cp_2 and v_cp_1 < v_cp_3) then
        v_media := (v_cp_2 + v_cp_3)/2;
        update Boletim set media = v_media where cd_disciplina = v_cd_disciplina;
        dbms_output.put_line('Sua media é de :'|| v_media);
    elsif (v_cp_2 < v_cp_1 and v_cp_2 < v_cp_3) then
        v_media := (v_cp_1 + v_cp_3)/2;
        update Boletim set media = v_media where cd_disciplina = v_cd_disciplina;
        dbms_output.put_line('Sua media é de :'|| v_media);
    else
        v_media := (v_cp_1 + v_cp_2)/2;
        update Boletim set media = v_media where cd_disciplina = v_cd_disciplina;
        dbms_output.put_line('Sua media é de :'|| v_media);
    end if;
    
    
    v_carga_hora := v_carga_hora/4;
    
    
    if (v_media >= 6 and v_carga_hora > v_faltas) then
        v_situacao := 'Aprovado';
        update Boletim set situacao = v_situacao where cd_disciplina = v_cd_disciplina;
        dbms_output.put_line('Sua situação :'|| v_situacao);
        
    elsif (v_media <= 6 and v_carga_hora <= v_faltas) then
        v_situacao := 'Reprovado por faltas';
        update Boletim set situacao = v_situacao where cd_disciplina = v_cd_disciplina;
        dbms_output.put_line('Sua situação :'|| v_situacao);
        
    elsif (v_media >= 4 and v_media < 6 or v_carga_hora > v_faltas) then
        v_situacao := 'Exame';
        update Boletim set situacao = v_situacao where cd_disciplina = v_cd_disciplina;
        dbms_output.put_line('Sua situação :'|| v_situacao);
        
    elsif (v_media < 4 and v_carga_hora > v_faltas) then
        v_situacao := 'Reprovado por nota';
        update Boletim set situacao = v_situacao where cd_disciplina = v_cd_disciplina;
        dbms_output.put_line('Sua situação :'|| v_situacao);
        
    elsif (v_media < 4 and v_carga_hora <= v_faltas) then
        v_situacao := 'Reprovado por nota e faltas';
        update Boletim set situacao = v_situacao where cd_disciplina = v_cd_disciplina;
        dbms_output.put_line('Sua situação :'|| v_situacao);
        
    end if;
end;