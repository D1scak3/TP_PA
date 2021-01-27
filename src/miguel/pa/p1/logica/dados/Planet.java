package miguel.pa.p1.logica.dados;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Planet implements Serializable {
    private String cor;
    private int corID;
    private Cargo recursos;//preto/vermelho/verde/azul
    private boolean artifact;//só o azul é que tem artefacto
    private boolean spaceStation;
    private Terrain terreno;
    private Ship nave;



    public Planet(Ship nave){
        //int rnd = (int)Math.random()*4;//[min, max(val+1)] == [1,4]
        Random rand = new Random();
//        int rnd = rand.nextInt(4);
//        rnd = rnd + 1;
//        System.out.println("VALOR RND PRO PLANETA " + rnd);
        int rnd = 1;
        this.nave = nave;

        int val = rand.nextInt(11);
        val = val + 1;
        //System.out.println("VALOR VAL PRA STATION " + val);
        if(val <= 3)
            spaceStation = true;


        //preto/vermelho/verde/azul  ordem/indices das cores
        switch(rnd){
            case 1:
                cor =  "azul";//preto, verde e azul
                recursos = new Cargo();
                recursos.initAzul();
                artifact =true;
                corID = 1;
                this.terreno = new Terrain(recursos, corID, nave);
                break;
            case 2:
                cor = "verde";// vermelho e verde
                recursos = new Cargo();
                recursos.initVerde();
                artifact =false;
                corID = 2;
                this.terreno = new Terrain(recursos, corID, nave);
                break;
            case 3:
                cor =  "vermelho";//vermelho e azul
                recursos = new Cargo();
                recursos.initVermelho();
                artifact =false;
                corID = 3;
                this.terreno = new Terrain(recursos, corID, nave);
                break;
            case 4:
                cor =  "preto";//preto e azul
                recursos = new Cargo();
                recursos.initPreto();
                artifact =false;
                corID = 4;
                this.terreno = new Terrain(recursos, corID, nave);
                break;


        }

        int rndD = rand.nextInt(2);
        if(rndD == 1)
            spaceStation = true;
        else
            spaceStation = false;
    }

    @Override
    public String toString(){
        return "Cor:" + cor + "\nRecursos:" + recursos;
    }

    public Terrain getTerrain(){
        return terreno;
    }

    public boolean getSpaceStation(){
        return spaceStation;
    }

}
