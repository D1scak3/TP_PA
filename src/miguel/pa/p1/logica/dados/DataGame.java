package miguel.pa.p1.logica.dados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGame implements Serializable {
    private Ship nave;
    private Planet planeta;
    private ArrayList<String> MsgLog;
    private boolean[] upgradesFeitos;

    public DataGame(){
        MsgLog = new ArrayList<>();
    }
//como o planeta recebe a nave
    public void setMining(){
        nave = new Ship(1);
    }

    public void setMilitar(){
        nave = new Ship(2);
    }

    public Ship getShip(){
        return nave;
    }

    public Planet getPlanet(){
        return planeta;
    }

    public void setPlanet(Planet planeta){
        this.planeta = planeta;
    }

    public ArrayList<String> getMsgLog(){
        return MsgLog;
    } //Usado no jfx pro logPane

    public void addMSG(String msg){
        MsgLog.add(msg);
    }

    public void mostraLog(){
        if(MsgLog.size() == 0){
            System.out.println("Não há mensagens no Log.");
            return;
        }

        for(int i = 0; i < MsgLog.size(); i++){
            if(i == (MsgLog.size() - 1))
                System.out.println(MsgLog.get(i));
        }
        return;
    }

    public void criaListaUpgradesFeitos(){
        upgradesFeitos = new boolean[6];
        for(int i = 0; i < 6; i++)
            upgradesFeitos[i] = false;
    }

    public boolean[] getListaUpgradesFeitos(){
        return upgradesFeitos;
    }

    //-------------------VERIFICAÇÕES----------------------
    public boolean verificaPerdeu() {//true pra perder/false pra continuar
        if(nave.getFuel() == 0)//sem fuel
            return true;

        boolean flag = false;
        for(int i = 0; i < 6; i++){//sem officers
            if(nave.getOfficer(i) == true)
                flag = true;
        }
        if(flag == false)
            return true;

        return false;
    }

    public boolean verificaGanhou(){
        //falta implementar
        return true;
    }


    //-------------------FUNÇÕES CHAMADAS NA LÓGICA DOS DADOS

    public void mataOfficer(int rnd) {
        boolean[] officers = nave.getOfficers();
        officers[rnd] = false;
    }

    public boolean ganhaRecursos() {
        Cargo recursos = nave.getCargo();
        Random rnd = new Random();
        int type = rnd.nextInt(4);
        int quant = rnd.nextInt(6);
        int lv = nave.getLvCargo();
        int quantMax = (lv+1) * 6;

        if(recursos.getQuant(type) == quantMax)//está no max
            return false;
        else if(recursos.getQuant(type) + quant < quantMax)//tem espaço pra aceitar tudo
            recursos.setQuant(recursos.getQuant(type) + quant, type);
        else//tem espaço pra aceitar parcial
            recursos.setQuant(quantMax, type);

        return true;
    }

    public boolean perdeRecursos() {
        Cargo recursos = nave.getCargo();
        Random rnd = new Random();
        int type = rnd.nextInt(4);
        int quant = rnd.nextInt(6);

        if(recursos.getQuant(type) == 0)
            return false;
        else if(recursos.getQuant(type) - quant > 0)
            recursos.setQuant(recursos.getQuant(type) - quant, type);
        else
            recursos.setQuant(0, type);

        return true;
    }

    public void queimaCombustivel() {
        nave.setFuel(nave.getFuel() - 1);
    }

    public boolean officerGratis() {

        int i, quant = 0;

        for(i = 0; i < 6; i++){
            if(nave.getOfficer(i) == true) {
                nave.setOfficer(i, false);
                quant++;
            }
        }

        if(quant == 6) {//tem os officers todos
            for (i = 0; i < 6; i++)
                nave.setOfficer(i, true);
            return false;
        }
        else if(quant < 6){//não tem os officers todos
            quant++;//apanha um officer extra
            for(i = 0; i < quant; i++)
                nave.setOfficer(i, true);
        }

        return true;
    }


    public boolean whormhole() {//true pra morre, false pra sobrevive
        Random rnd = new Random(8);
        int prob = rnd.nextInt();
        boolean[] officers = getShip().getOfficers();

        if(prob == 0) {//1 em 8 valores

            if(officers[3] == true){//tem shield officer

                if(nave.getShields() >= 2 && nave.getFuel() > 3){//gasta recursos
                    nave.setFuel(nave.getFuel() - 3);
                    nave.setShields(nave.getShields() - 2);
                }
                else if(nave.getShields() < 2 && nave.getFuel() > 3){//gasta recursos e morre officer
                    nave.setFuel(nave.getFuel() - 3);
                    nave.setShields(0);
                    nave.killRandomOfficer();
                }
                else if(nave.getFuel() < 3) {//game over
                    addMSG("Morreste para o whormhole");
                    return true;
                }

            }else{//n tem shield officer

                if(nave.getShields() >= 4 && nave.getFuel() > 6) {//gasta recursos
                    nave.setFuel(nave.getFuel() - 6);
                    nave.setShields(nave.getShields() - 2);
                }
                else if(nave.getShields() < 4 && nave.getFuel() > 6){//gasta recursos e morre officer
                    nave.setFuel(nave.getFuel() - 6);
                    nave.setShields(0);
                    nave.killRandomOfficer();
                }
                else if(nave.getFuel() < 6) {//game over
                    addMSG("Morreste para o whormhole.");
                    return true;
                }

            }

        }

        return false;

    }


}
