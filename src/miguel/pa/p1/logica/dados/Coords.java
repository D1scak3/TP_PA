package miguel.pa.p1.logica.dados;

import java.io.Serializable;

public class Coords implements Serializable {
    private int x;
    private int y;
    private char digito;

    public Coords(char dgt){
        digito = dgt;
    }

    public void setCoords(int a, int b){
        x = a;
        y = b;
    }

    public char getDGT(){
        return digito;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}
