package br.com.fiap.main;

import br.com.fiap.beans.Celular;
import br.com.fiap.beans.Fixo;
import br.com.fiap.beans.Publico;
import br.com.fiap.beans.Telefone;

public class TesteTelefone {
    public static void main(String[] args) {
        Celular celular = new Celular();
        Fixo fixo = new Fixo();
        Publico publico = new Publico();
        Telefone telefone = null;

        int n = (int)(Math.random()*3.0);
        System.out.println("Foi escolhido o número: "+n);

        switch (n){
            case 0: telefone = celular;break;
            case 1: telefone = fixo;break;
            case 2: telefone = publico;break;
            default: System.out.println("Erro inesperado");
        }

        if(telefone != null){
          telefone.disca("11975372267");
          telefone.toca(2);
        }

    }
}
