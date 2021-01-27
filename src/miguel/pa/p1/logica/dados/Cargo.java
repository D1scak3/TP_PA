package miguel.pa.p1.logica.dados;

import java.io.Serializable;

public class Cargo implements Serializable {//recursos
    private int[] quantidade;

    public Cargo() {
        quantidade = new int[4];//preto/vermelho/verde/azul
    }

    //preto/vermelho/verde/azul
    public void setQuant(int i, int tipo) {
        quantidade[tipo] = i;
    }

    //preto/vermelho/verde/azul
    public int getQuant(int tipo) {
        return quantidade[tipo];
    }

    //funções para inicializar os planetas
    public void initAzul() {
        quantidade[0] = 1;
        quantidade[1] = 0;
        quantidade[2] = 1;
        quantidade[3] = 1;
    }

    public void initVerde() {
        quantidade[0] = 0;
        quantidade[1] = 1;
        quantidade[2] = 1;
        quantidade[3] = 0;
    }

    public void initVermelho() {
        quantidade[0] = 0;
        quantidade[1] = 1;
        quantidade[2] = 0;
        quantidade[3] = 1;
    }

    public void initPreto() {
        quantidade[0] = 1;
        quantidade[1] = 0;
        quantidade[2] = 0;
        quantidade[3] = 1;
    }

    @Override
    public String toString() {
        return "1-Black: " + quantidade[0] + "\t2-Red:" + quantidade[1] + "\t3-Green:" + quantidade[2] + "\t4-Blue:" + quantidade[3];
    }

    public char getChar(int i) {
        if (i == 0)
            return 'B';
        else if (i == 1)
            return 'R';
        else if (i == 2)
            return 'G';
        else
            return 'b';//pk black tb começa com b
    }

}