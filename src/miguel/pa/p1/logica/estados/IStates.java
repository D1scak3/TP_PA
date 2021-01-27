package miguel.pa.p1.logica.estados;

import miguel.pa.p1.logica.dados.DataGame;

import java.io.Serializable;

public interface IStates extends Serializable{
    //AwaitShipSelect
    IStates escolheNave(int escolha);

    //AwaitMove
    IStates entraEvento();

    //ApllyEvent
    IStates aplicaEventoCerto(int i);
    IStates aplicaEvento();

    //OnOrbit
    IStates entraPlaneta();
    IStates entraTroca();
    IStates entraEstacao();
    IStates saiOrbita();

    //Estacao
    IStates fazUpgrade(int num);
    IStates contrata(int num);
    IStates saiEstacao();

    //ResourceConvert
    IStates troca(int x, int y);//entra no estado troca
    IStates saiTroca();

    //OnPlanet
    IStates emMovimento(char move);//retorno à nave são condições implementadas neste método

    //GameOver
    IStates restart();

}
