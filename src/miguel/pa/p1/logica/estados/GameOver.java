package miguel.pa.p1.logica.estados;

import miguel.pa.p1.logica.dados.DataGame;

public class GameOver extends EstadoAdapter{
    public GameOver(DataGame jogo) {
        super(jogo);
    }

    @Override
    public IStates restart(){
        return new AwaitShipSelect(new DataGame());
    }
}
