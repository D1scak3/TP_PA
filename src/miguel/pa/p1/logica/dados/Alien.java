package miguel.pa.p1.logica.dados;

import java.io.Serializable;
import java.util.Random;

public class Alien implements Serializable {

    private String tipo;//cor
    private int x , y;

    public Alien(){
        int val = new Random().nextInt(4);
        switch(val){
            case 0:
                tipo = "Black";
                break;
            case 1:
                tipo = "Red";
                break;
            case 2:
                tipo = "Green";
                break;
            case 3:
                tipo = "Blue";
                break;
        }

    }

    public String getTipo(){
        return tipo;
    }

    public void setCoords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}
