package miguel.pa.p1.logica.estados;

import miguel.pa.p1.logica.dados.DataGame;
import miguel.pa.p1.logica.dados.Planet;

import java.util.concurrent.ThreadLocalRandom;

public class AwaitMove extends EstadoAdapter{

    public AwaitMove(DataGame jogo) {
        super(jogo);
    }

    @Override
    public IStates entraEvento(){
        boolean result = getDataGame().whormhole();
        if(result == true)
            return new GameOver(getDataGame());
        return new ApplyEvent(getDataGame());
    }

}
