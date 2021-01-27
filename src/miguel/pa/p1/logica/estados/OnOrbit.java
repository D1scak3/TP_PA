package miguel.pa.p1.logica.estados;

import miguel.pa.p1.logica.dados.DataGame;
import miguel.pa.p1.logica.dados.Planet;
import miguel.pa.p1.logica.dados.Ship;
import miguel.pa.p1.logica.dados.Terrain;

import java.util.Scanner;

public class OnOrbit extends EstadoAdapter{
    public OnOrbit(DataGame jogo) {
        super(jogo);
    }

    @Override
    public IStates entraPlaneta(){
        Ship nave = getDataGame().getShip();
        boolean[] officers = getDataGame().getShip().getOfficers();

        if(officers[2] == true && getDataGame().getShip().getDrone() == true) {
            nave.setFuel(nave.getFuel() - 1);
            if(nave.getFuel() == 0) {
                getDataGame().addMSG("Ficaste sem combustível.");
                return new GameOver(getDataGame());
            }
            return new OnPlanet(getDataGame());
        }
        getDataGame().addMSG("Não possuis o Officer de Landing ou o drone para entrares no planeta.");
        return this;
    }

    @Override
    public IStates entraTroca(){
        boolean[] officers = getDataGame().getShip().getOfficers();
        if(officers[5] == true)
            return new ResourceConvert(getDataGame());
        getDataGame().addMSG("Não possuis o Officer da Cargo para poderes converter recursos.");
        return this;
    }

    @Override
    public IStates entraEstacao(){
        getDataGame().criaListaUpgradesFeitos();
        return new Estacao(getDataGame());
    }

    @Override
    public IStates saiOrbita(){
        return new AwaitMove(getDataGame());
    }

}
