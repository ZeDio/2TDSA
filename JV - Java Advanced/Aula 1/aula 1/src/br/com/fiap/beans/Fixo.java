package br.com.fiap.beans;

public class Fixo extends Telefone {
    public Fixo(){
        super("Telefone Fixo");
    }

    @Override
    public void disca(String numero) {
        System.out.println("Numero de telefone fixo é "+numero+".");
    }

    @Override
    public void toca(int numToques) {
        for(int i=0; i < numToques;i++){
            System.out.println("trimmmmmmmm");
        }
    }
}
