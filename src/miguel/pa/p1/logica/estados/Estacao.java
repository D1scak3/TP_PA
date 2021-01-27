package miguel.pa.p1.logica.estados;

import miguel.pa.p1.logica.dados.Cargo;
import miguel.pa.p1.logica.dados.DataGame;
import miguel.pa.p1.logica.dados.Ship;

public class Estacao  extends EstadoAdapter{
    public Estacao(DataGame jogo) {
        super(jogo);
    }

    @Override
    public IStates fazUpgrade(int num){
        int tipo = getDataGame().getShip().getTipo();
        Ship nave = getDataGame().getShip();
        Cargo recursos = getDataGame().getShip().getCargo();
        boolean[] listaUpgradesFeitos = getDataGame().getListaUpgradesFeitos();
        switch(num){
            case 0://sai da estação
                break;
            case 1://reabastecer

                if(nave.getOfficer(1) != false) {
                    if (listaUpgradesFeitos[0] == false){
                        if (recursos.getQuant(0) > 0 && recursos.getQuant(1) > 0 && recursos.getQuant(2) > 0 && recursos.getQuant(3) > 0) {
                            if (tipo == 1)
                                nave.setFuel(53);
                            else
                                nave.setFuel(35);
                            recursos.setQuant(recursos.getQuant(0) - 1, 0);
                            recursos.setQuant(recursos.getQuant(1) - 1, 1);
                            recursos.setQuant(recursos.getQuant(2) - 1, 2);
                            recursos.setQuant(recursos.getQuant(3) - 1, 3);
                            listaUpgradesFeitos[0] = true;
                            getDataGame().addMSG("Abastecimento feito com sucesso.");
                        } else
                            getDataGame().addMSG("Não tens recursos suficientes para abastecer.");
                    }
                    else
                        getDataGame().addMSG("Já fizeste este upgrade/compra nesta visita.");
                }else
                    getDataGame().addMSG("Não tens o Navigation officer(1) para abastecer, arranja um primeiro.");
                break;
            case 2://recarregar
                if(nave.getOfficer(3) != false) {
                    if(listaUpgradesFeitos[1] == false) {
                        if (recursos.getQuant(0) > 0 && recursos.getQuant(1) > 0 && recursos.getQuant(2) > 0 && recursos.getQuant(3) > 0) {
                            if (tipo == 1)
                                nave.setShields(18);
                            else
                                nave.setShields(9);
                            recursos.setQuant(recursos.getQuant(0) - 1, 0);
                            recursos.setQuant(recursos.getQuant(1) - 1, 1);
                            recursos.setQuant(recursos.getQuant(2) - 1, 2);
                            recursos.setQuant(recursos.getQuant(3) - 1, 3);
                            listaUpgradesFeitos[1] = true;
                            getDataGame().addMSG("Recarregamento de escudos feito com sucesso.");
                        } else
                            getDataGame().addMSG("Não tens recursos pra recarregar escudos.");
                    }
                    else
                        getDataGame().addMSG("Já fizeste este upgrade/compra nesta visita.");
                }else
                    getDataGame().addMSG("Não tens o Shield Officer(3) para recarregar escudos, arranja um primeiro.");

                break;
            case 3://reload
                if(nave.getOfficer(4) != false) {
                    if(listaUpgradesFeitos[2] == false) {
                        if (recursos.getQuant(0) > 0 && recursos.getQuant(1) > 0 && recursos.getQuant(2) > 0 && recursos.getQuant(3) > 0) {
                            if (tipo == 1)
                                nave.setWeapon1(9);
                            else {
                                nave.setWeapon2(0, 9);
                                nave.setWeapon2(1, 9);
                            }
                            recursos.setQuant(recursos.getQuant(0) - 2, 0);
                            recursos.setQuant(recursos.getQuant(1) - 2, 1);
                            recursos.setQuant(recursos.getQuant(2) - 2, 2);
                            recursos.setQuant(recursos.getQuant(3) - 2, 3);
                            listaUpgradesFeitos[2] = true;
                            getDataGame().addMSG("Reload das armas efetuado com sucesso.");
                        } else
                            getDataGame().addMSG("Não tens recursos suficientes para comprar munição.");
                    }
                    else
                        getDataGame().addMSG("Já fizeste esse upgrade/compra nesta visita à estação.");
                }else
                    getDataGame().addMSG("Não tens o Weapon Officer(4) para comprar munição, arranja um primeiro.");
                break;
            case 5://lv up
                if(nave.getOfficer(5) != false) {
                    if(listaUpgradesFeitos[4] == false) {
                        if (recursos.getQuant(0) >= 1 && recursos.getQuant(1) >= 1 && recursos.getQuant(2) >= 1 && recursos.getQuant(3) >= 1) {
                            nave.upgradeCargo();
                            recursos.setQuant(recursos.getQuant(0) - 1, 0);
                            recursos.setQuant(recursos.getQuant(1) - 1, 1);
                            recursos.setQuant(recursos.getQuant(2) - 1, 2);
                            recursos.setQuant(recursos.getQuant(3) - 1, 3);
                            listaUpgradesFeitos[4] = true;
                            getDataGame().addMSG("Aprimoramento do Cargo efetuado com sucesso.");
                        } else
                            getDataGame().addMSG("Não tens recursos suficientes para melhorar a cargo.");
                    }
                    else
                        getDataGame().addMSG("Já fizeste esse upgrade/compra nesta visita à estação.");
                }else
                    getDataGame().addMSG("Não tens o Cargo Officer(5) para melhorar a cargo, arranja um primeiro.");
                break;
            case 6://compra drone
                if(listaUpgradesFeitos[5] == false) {
                    if (recursos.getQuant(0) >= 2 && recursos.getQuant(1) >= 2 && recursos.getQuant(2) >= 2 && recursos.getQuant(3) >= 2) {
                        nave.setDrone(true);
                        recursos.setQuant(recursos.getQuant(0) - 2, 0);
                        recursos.setQuant(recursos.getQuant(1) - 2, 1);
                        recursos.setQuant(recursos.getQuant(2) - 2, 2);
                        recursos.setQuant(recursos.getQuant(3) - 2, 3);
                        getDataGame().addMSG("Drone comprado com sucesso.");
                    } else
                        getDataGame().addMSG("Não tens recursos suficientes para comprar um drone.");
                }
                else
                    getDataGame().addMSG("Já fizeste esse upgrade/compra nesta visita à estação.");
                break;
        }

        return this;
    }

    @Override
    public IStates contrata(int num){

        boolean[] listaUpgradesFeitos = getDataGame().getListaUpgradesFeitos();

        if(listaUpgradesFeitos[3] == false) {
            if(getDataGame().getShip().getOfficer(num) == false) {
                getDataGame().getShip().setOfficer(num, true);
                listaUpgradesFeitos[3] = true;
                getDataGame().addMSG("Officer contratado com sucesso.");
            }
            else
                getDataGame().addMSG("Seleciona um officer que não tenhas.");
        }
        else
            getDataGame().addMSG("Já contrataste um Officer nesta visita à estação.");

        return this;
    }

    @Override
    public IStates saiEstacao(){
        return new OnOrbit(getDataGame());
    }
}
