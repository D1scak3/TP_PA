package miguel.pa.p1.logica.estados;

import miguel.pa.p1.logica.dados.DataGame;

import java.util.Scanner;

public class AwaitShipSelect extends EstadoAdapter{

    public AwaitShipSelect(DataGame jogo){super(jogo);}

    @Override
    public IStates escolheNave(int escolha){

        if(escolha == 1)
            getDataGame().setMining();
        else if(escolha == 2)
            getDataGame().setMilitar();

        return new AwaitMove(getDataGame());

    }
}
