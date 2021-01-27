package miguel.pa.p1.logica.estados;

import miguel.pa.p1.logica.dados.Cargo;
import miguel.pa.p1.logica.dados.DataGame;
import miguel.pa.p1.logica.dados.Planet;
import miguel.pa.p1.logica.dados.Ship;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ApplyEvent extends EstadoAdapter{
    public ApplyEvent(DataGame jogo) {
        super(jogo);
    }

    @Override
    public IStates aplicaEvento(){
        Random rand = new Random();
        int val = rand.nextInt(6);//escolhe evento
        Ship nave = getDataGame().getShip();
        boolean[] officers = nave.getOfficers();
        boolean result;



        switch(val){
            case 0://officer morre, sorte a tua que não pagas o funeral
                int rnd = rand.nextInt(6);
                if(officers[rnd] == true){
                    getDataGame().mataOfficer(rnd);
                    getDataGame().addMSG("Um dos quartos dos Officers sofreu uma explosão e alguem morreu.");//Se o Officer correspondente já estava morto, nada aconteceu
                }
                else
                    getDataGame().addMSG("Um dos quartos dos Officers sofreu uma explosão. Felizmente ninguém morreu.");

                //switch com rnd pra enviar msg pro log
                break;
            case 1://you get free stuff, yesssssssss

                result = getDataGame().ganhaRecursos();
                if(result == true)
                    getDataGame().addMSG("Ganhaste recursos que estavam à deriva no espaço. Que sorte!!!");
                else
                    getDataGame().addMSG("Encontraste recursos no espaço, mas não tinhas espaço para eles. Que pena...");

                break;
            case 2://you lose stuff, ¯\_(ツ)_/¯

                result = getDataGame().perdeRecursos();
                if(result == true)
                    getDataGame().addMSG("Houve uma explosão num dos compartimentos da nave e perdeste recursos.");
                else
                    getDataGame().addMSG("Houva uma explosão num dos compartimentos da nave. Felizmente o compartimento estava vazio.");
                break;
            case 3://respeitasses o limite de velocidade
                getDataGame().queimaCombustivel();
                getDataGame().addMSG("Não havia trânsito e a polícia tinha mais que fazer. Queimaste mais combustível.");
                break;
            case 4://go on, nothing to see here
                getDataGame().addMSG("Não aconteceu nada afinal, nem todas as viagens espaciais precisam de uma catástrofe né?");
                break;
            case 5://fortune smiles upon you, free officer
                result = getDataGame().officerGratis();
                if(result == true)
                    getDataGame().addMSG("Encontraste o que resta de uma nave e para tua surpresa tinha um sobrevivente. Ele ficou tão feliz que decidiu trabalhar pra ti.");
                else
                    getDataGame().addMSG("Encontraste um sobrevivente no que resta de uma nave. Ele queria ajudar-te mas como não te falta pessoal, deixa-lo na estação mais próxima.");
                break;
        }

        if(getDataGame().verificaPerdeu())
            return new GameOver(getDataGame());

        //avança para um novo planeta
        getDataGame().setPlanet(new Planet(getDataGame().getShip()));
        getDataGame().queimaCombustivel();
        return new OnOrbit(getDataGame());

    }

    @Override
    public IStates aplicaEventoCerto(int i){

        Random rand = new Random();
        int val = i - 1;
        boolean result;
        boolean[] officers = getDataGame().getShip().getOfficers();


        switch(val){
            case 0://officer morre, sorte a tua que não pagas o funeral
                int rnd = rand.nextInt(6);
                if(officers[rnd] == true){
                    getDataGame().mataOfficer(rnd);
                    getDataGame().addMSG("Um dos quartos dos Officers sofreu uma explosão e alguem morreu.");//Se o Officer correspondente já estava morto, nada aconteceu
                }
                else
                    getDataGame().addMSG("Um dos quartos dos Officers sofreu uma explosão. Felizmente ninguém morreu.");

                //switch com rnd pra enviar msg pro log
                break;
            case 1://you get free stuff, yesssssssss

                result = getDataGame().ganhaRecursos();
                if(result == true)
                    getDataGame().addMSG("Ganhaste recursos que estavam à deriva no espaço. Que sorte!!!");
                else
                    getDataGame().addMSG("Encontraste recursos no espaço, mas não tinhas espaço para eles. Que pena...");

                break;
            case 2://you lose stuff, ¯\_(ツ)_/¯

                result = getDataGame().perdeRecursos();
                if(result == true)
                    getDataGame().addMSG("Houve uma explosão num dos compartimentos da nave e perdeste recursos.");
                else
                    getDataGame().addMSG("Houva uma explosão num dos compartimentos da nave. Felizmente o compartimento estava vazio.");
                break;
            case 3://respeitasses o limite de velocidade
                getDataGame().queimaCombustivel();
                getDataGame().addMSG("Não havia trânsito e a polícia tinha mais que fazer. Queimaste mais combustível.");
                break;
            case 4://go on, nothing to see here
                getDataGame().addMSG("Não aconteceu nada afinal, nem todas as viagens espaciais precisam de uma catástrofe né?");
                break;
            case 5://fortune smiles upon you, free officer
                result = getDataGame().officerGratis();
                if(result == true)
                    getDataGame().addMSG("Encontraste o que resta de uma nave e para tua surpresa tinha um sobrevivente. Ele ficou tão feliz que decidiu trabalhar pra ti.");
                else
                    getDataGame().addMSG("Encontraste um sobrevivente no que resta de uma nave. Ele queria ajudar-te mas como não te falta pessoal, deixa-lo na estação mais próxima.");
                break;
        }

        if(getDataGame().verificaPerdeu())
            return new GameOver(getDataGame());

        //avança para um novo planeta
        getDataGame().setPlanet(new Planet(getDataGame().getShip()));
        getDataGame().queimaCombustivel();
        return new OnOrbit(getDataGame());
    }




}
