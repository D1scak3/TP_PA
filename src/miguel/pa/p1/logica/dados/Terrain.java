package miguel.pa.p1.logica.dados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class Terrain implements Serializable {
    private char [][] mapa;
    private int idCorPlaneta;
    private Cargo recursos;//preto/vermelho/verde/azul
    private Alien alien;
    private String tipoAlien;
    private Ship nave;
    private int x, y, Sx, Sy;//posição inicial do drone, onde pode mandar recursos de volta para a nave
    private int vidaDrone;
    private char [] bag;//onde guarda as coisas que mina do planeta
    private int pesoBag;
    private boolean aptoSaida;
    ArrayList<Coords> items;

    public Terrain(Cargo recursosPlaneta, int corID, Ship nave) {
        mapa = new char[6][6];
        this.idCorPlaneta = corID;
        alien = new Alien();
        tipoAlien = alien.getTipo();
        recursos = recursosPlaneta;// B/R/G/B
        bag = new char[4];
        pesoBag = 0;
        vidaDrone = 6;
        this.nave = nave;
        aptoSaida = false;
        items = new ArrayList<>();//coisas no terreno
        initializeTerrain(mapa, tipoAlien, recursosPlaneta);
    }

    //GETTERS E SETTERS----------------------------------------------------------
    public char [][] getMapa(){
        return mapa;
    }

    public int getIdCorPlaneta(){
        return idCorPlaneta;
    }

    public Ship getNave(){
        return nave;
    }

    public Cargo getRecursos(){
        return recursos;
    }

    public int getVidaDrone(){
            return vidaDrone;
    }

    public char getTipo(String tipoAlien){
        switch(tipoAlien){
            case "Black"://preto
                return 'B';

            case "Red":
                return 'R';

            case "Green":
                return 'G';

            case "Blue"://blue é b, não confundir com B que é black
                return 'b';

        }
        return 'X';//retorna X caso dê erro
    }

    public int getDX(){
        return x;
    }

    public int getDY(){
        return y;
    }

    public Alien getAlien(){
        return alien;
    }

    public boolean getAptoSaida(){
        return aptoSaida;
    }

    public char[] getBag(){
        return bag;
    }

    public void setAptoSaida(boolean state){
        aptoSaida = state;
    }

    public void resetPesoBag(){
        pesoBag = 0;
        bag = new char[4];
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }



//distribui alien, recursos e drone
    public void initializeTerrain(char [][] mapa, String tipoAlien, Cargo recursos){

        Random rand = new Random();

        for(int i = 0; i < 6; i++)//mete underscores no mapa pra saber como é o mapa
            for(int j = 0; j < 6; j++)
                mapa[i][j] = '_';


        alien.setCoords(rand.nextInt(6), rand.nextInt(6));//mete alien
        mapa[alien.getX()][alien.getY()] = 'A';


        for(int i = 0; i < 4; i++){//adiciona recursos à lista do terreno
            if(recursos.getQuant(i) > 0){
                if(i == 0)
                    items.add(new Coords('B'));
                else if(i == 1)
                    items.add(new Coords('R'));
                else if(i == 2)
                    items.add(new Coords('G'));
                else
                    items.add(new Coords('b'));
            }

        }

        //System.out.println("VOU ADICIONAR RECURSOS");
        for(Coords xy : items){//distribui os recursos pelo mapa
            do{
                x = rand.nextInt(6);
                y = rand.nextInt(6);
            }while(mapa[x][y] != '_');
            xy.setCoords(x, y);
            //System.out.println("Recurso " + xy.getDGT() + "adicionado");
            mapa[x][y] = xy.getDGT();
            //System.out.println(mapa[x][y]);
        }

        items.add(new Coords('S'));//mete saída
        for(int i = 0; i < items.size(); i++){//mete saída
            if(i == items.size()-1) {
                do {
                    x = rand.nextInt(6);
                    y = rand.nextInt(6);
                } while (mapa[x][y] != '_');
                items.get(i).setCoords(x, y);
                mapa[x][y] = 'S';
            }
        }

        if(idCorPlaneta == 1) {//mete artefacto
            items.add(new Coords('X'));
            do {
                x = rand.nextInt(6);
                y = rand.nextInt(6);
            } while (mapa[x][y] != '_');
            items.get(items.size()-1).setCoords(x, y);
            mapa[x][y] = 'X';//X marks the spot(artefacto)
        }

        for(Coords xy : items){//mete coords do drone a coincidir com a saída
            if(xy.getDGT() == 'S'){
                x = xy.getX();
                y = xy.getY();
            }
        }

        //System.out.println("COORDS DO DRONE:" +x + y);
        //System.out.println("O QUE ESTÁ NO MAPA: " + mapa[x][y]);

    }


//FUNÇÕES DE MOVIMENTO--------------------------------------------------
    public void moveDrone(char move){//só aceita WASD
        if(move == 'W' && y > 0)
            y--;
        else if(move == 'A' && x > 0)
            x--;
        else if(move == 'S' && y < 5)
            y++;
        else if(move == 'D' && x < 5)
            x++;

        for(int  i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if(mapa[i][j] == 'D')
                    mapa[i][j] = '_';
            }
        }
        mapa[x][y] = 'D';
//        System.out.println("POSI DO DRONE" + x + y);
//        System.out.println("O QUE ESTÁ DEPOIS DE MOVER: " + mapa[x][y]);

        if(alien != null)
            if(x == alien.getX() && y == alien.getY())
                luta();

    }

    public void moveAlien(){//só se move se o caminho estiver disponível(por outras palavras, a IA é burra
        int distX = x -alien.getX();
        int distY = y - alien.getY();
        int absX = 0, absY = 0;

        //valores absolutos(pk o math.abs decidiu n funcionar)
        if(distX < 0)
            absX = distX * (-1);
        if(distY < 0)
            absY = distY * (-1);

        //System.out.println("COORDS ALIEN ANTES:" + alien.getX() + alien.getY());

        if(absX > absY) {//compara horizontal com vertical
            if (distY > 0 && mapa[alien.getX()][alien.getY()+1] == '_') {//vê quem está em baixo
                alien.setCoords(alien.getX(), alien.getY()+1);
            }
            else if(distY < 0 && mapa[alien.getX()][alien.getY()-1] == '_'){//vê quem está em cima
                alien.setCoords(alien.getX(), alien.getY()-1);
            }
        }else
            if(distX > 0 && mapa[alien.getX()+1][alien.getY()] == '_'){//vê quem está à direita
                alien.setCoords(alien.getX()+1, alien.getY());
            }
            else if(distX < 0 && mapa[alien.getX()-1][y] == '_'){//vê quem está à esquerda
                alien.setCoords(alien.getX()-1, alien.getY());
            }


        for(int  i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if(mapa[i][j] == 'A')
                    mapa[i][j] = '_';
            }
        }

        mapa[alien.getX()][alien.getY()] = 'A';
        System.out.println("COORDS ALIEN DEPOIS:" + alien.getX() + alien.getY());

        //System.out.println("DRONE NO MAPA:" + mapa[x][y]);

    }

    public void atualizeTerrain(){

        for(int  i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if(mapa[i][j] == 'A')
                    mapa[i][j] = '_';
            }
        }
        mapa[alien.getX()][alien.getY()] = 'A';

        for(int  i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if(mapa[i][j] == 'D')
                    mapa[i][j] = '_';
            }
        }
        mapa[x][y] = 'D';
    }



//DESENCADEIAMENTO DE AÇÕES---------------------------------------------
    public void checkSides(){

        //array de items, e se as coords forem iguais, adiciona recurso na bag
        for(Coords xy : items){
            if(x == xy.getX() && y == xy.getY()){
                bag[pesoBag++] = xy.getDGT();
            }
        }

        //luta se as coords do alien foram iguais às do drone e alien != null
        if(alien != null) {
            if (x == alien.getX() && y == alien.getY()) {
                luta();
                if (alien == null) {//depois da luta atualiza o local onde a luta ocorreu(só há 1 sobrevivente)
                    mapa[x][y] = 'D';
                } else
                    mapa[x][y] = 'A';
            }
        }
    }


//AÇÕES EXECUTADAS
    public void novoAlien(){
        Random rand = new Random();
        int ax, ay;

        do {//arranja coords disponíveis e cria um novo alien
            ax = rand.nextInt(6);
            ay = rand.nextInt(6);
        }while(mapa[ax][ay] != '_');

        alien = new Alien();
        alien.setCoords(ax, ay);
        mapa[ax][ay] = 'A';
    }

    void luta() {
        Random rand = new Random();
        int roll = 0;

        //System.out.println("VOU COMECAR A LUTA");

        while (alien != null && vidaDrone != 0) {
            roll = rand.nextInt(6);//0 a 5
            roll = roll + 1;//1 a 6
            //System.out.println("Valor rolado pra fight " + roll + "Tipo de alien" + tipoAlien);
            //System.out.println("VIDA DO DRONE:" + vidaDrone);

            //comparar string com o equals
            if (tipoAlien == "Black") {
                if (roll == 1)
                    vidaDrone--;
                else if (roll == 5 || roll == 6) {
                    //mapa[alien.getX()][alien.getY()] = '_';
                    alien = null;
                }
            } else if (tipoAlien == "Red") {
                if (roll == 1)
                    vidaDrone--;
                else if (roll == 5 || roll == 6) {
                    //mapa[alien.getX()][alien.getY()] = '_';
                    alien = null;
                }
            } else if (tipoAlien == "Green") {
                if (roll == 1 || roll == 2)
                    vidaDrone--;
                else if (roll >= 4 && roll <= 6) {
                    //mapa[alien.getX()][alien.getY()] = '_';
                    alien = null;
                }
            } else if (tipoAlien == "Blue") {
                if (roll >= 3 && roll <= 5) {
                    vidaDrone--;
                    //mapa[alien.getX()][alien.getY()] = '_';
                    alien = null;
                }
            }

            //System.out.println("ACABEI UMA FASE DA LUTA");

            if (vidaDrone == 0) {
                nave.setDrone(false);
                return;
            }
        }

    }

    public void checkSair(){

        boolean flag = false;

        for(Coords xy : items){
            if(xy.getDGT() == 'S'){
                if(x == xy.getX() && y == xy.getY()){
                    mapa[x][y] = 'S';
                    aptoSaida = true;
                }
            }
        }


    }
}


