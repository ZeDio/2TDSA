package br.com.fiap.beans;

public class Celular extends Telefone{
    public Celular(){
        super("Telefone Celular");
    }

    @Override
    public void disca(String numero) {
        System.out.println("Numero de telefone celular é "+numero+".");
    }

    @Override
    public void toca(int numToques) {
        switch (numToques){
            case 1:
                System.out.println("buuuuuuuuuu");
                break;
            case 2:
                System.out.println("blimmmmmmmm");
                break;
            default:
                System.out.println("trimmmmmmmm");
        }
    }
}
