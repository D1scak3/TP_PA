package miguel.pa.p1.logica.dados;

/*
* gerenciamento da quantidade de recursos é feito sempre com o mesmo Cargo object
* a quant max é definida com a var maxQuant e com o nível da cargo(lvCargo)
* */


import java.io.Serializable;
import java.util.Random;

public class Ship implements Serializable {
    private int tipo;
    private int shields;
    private int weapon1;
    private int weapon2;
    private int fuel;
    private boolean [] officers;//tam 6, captain, navigation, landing, shield, weapon, cargo
    private int lvCargo;//lv max é 3
    private int maxQuant;
    private int artefactos;
    private Cargo armazenamento;
    private boolean drone;

    public Ship(int type){
        tipo = type;
        if(type == 1){//MINING
            shields = 18;
            weapon1 = 9;
            weapon2 = 0;
            fuel = 53;
            officers = new boolean[6];
            for(int i = 0; i < 6; i++)
                officers[i] = true;
        }else{//MILITAR
            shields = 9;
            weapon1 = 9;
            weapon2 = 9;
            fuel = 35;
            officers = new boolean[6];
            for(int i = 0; i < 6; i++)
                officers[i] = true;
        }
        lvCargo = 0;
        maxQuant = 6;
        artefactos = 0;
        armazenamento = new Cargo();
        drone = true;
    }


    public int getShields(){
        return shields;
    }

    public void setShields(int i){
        shields = i;
    }

    public int getWeapon1(){
        return weapon1;
    }

    public int getWeapon2(){
        return weapon2;
    }

    public void setWeapon1(int i){//MINING
        weapon1 = i;
    }

    public void setWeapon2(int index, int i){//MILITAR
        if(index == 1)
            weapon1 = i;
        else
            weapon2 = i;
    }

    public int getFuel(){
        return fuel;
    }

    public void setFuel(int i){
        fuel = i;
    }

    //fazer verificação do index antes
    public boolean getOfficer(int index){
        return officers[index];
    }

    public void setOfficer(int index, boolean state){
        officers[index] = state;
    }

    public int getLvCargo(){
        return lvCargo;
    }

    public Cargo getCargo(){
        return armazenamento;
    }

    public int getMaxQuant(){
        return maxQuant;
    }

    public boolean upgradeCargo(){
        if(tipo == 1){//MINING
            if(lvCargo >= 4)
                return false;
            lvCargo++;
            maxQuant = 6 * (lvCargo+1);
            return true;
        }else{//MILITAR
            if(lvCargo >= 2)
                return false;
            lvCargo++;
            maxQuant = 6 * (lvCargo+1);
            return true;
        }
    }

    public int getArtefactos(){
        return artefactos;
    }

    public void gotArtefacto(){
        artefactos++;
    }

    public boolean getDrone(){
        return drone;
    }

    public void setDrone(boolean estado){
        drone = estado;
    }

    @Override
    public String toString(){//captain, navigation, landing, shield, weapon, cargo
        if(tipo == 1)//MINING
            return "Tipo: Mineira\t Shields:" + shields + "\tWeapon1:" + weapon1 + "\tFuel:" + fuel + "\tLv.Cargo:" + lvCargo + "\nCargo" + armazenamento +
                    "\nOfficers:\n" + "Captain: " + officers[0] + "  Navigation: " + officers[1] + "  Landing: " + officers[2] + "  Shield: " + officers[3] + "  Weapon: " + officers[4] + "  Cargo: " + officers[5];
        else//MILITAR
            return "Tipo: Militar\t Shields:" + shields + "\tWeapon1:" + weapon1 + "\tWeapon2:" + weapon2 + "\tFuel:" + fuel + "\tLv.Cargo:" + lvCargo + "\nCargo" + armazenamento +
                    "\n Officers:" + "Captain:" + officers[0] + "Navigation:" + officers[1] + "Landing:" + officers[2] + "Shield:" + officers[3] + "Weapon:" + officers[4] + "Cargo:" + officers[5];
    }

    public int getTipo(){
        return tipo;
    }

    public boolean[] getOfficers(){
        return officers;
    }

    public void killRandomOfficer(){
        Random rnd = new Random();
        int val = 0;

        do{
            val = rnd.nextInt();
        }while(officers[val] == false);

        officers[val] = false;
    }

}
