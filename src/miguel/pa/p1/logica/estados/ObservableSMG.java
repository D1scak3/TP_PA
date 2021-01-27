package miguel.pa.p1.logica.estados;

import java.beans.PropertyChangeSupport;

public class ObservableSMG  extends PropertyChangeSupport {

    private StateMachineGame stateMachineGame;

    public ObservableSMG(StateMachineGame stateMachineGame) {
        super(stateMachineGame);
        this.stateMachineGame = stateMachineGame;
    }

    public StateMachineGame getStateMachineGame(){
        return stateMachineGame;
    }

    public IStates getState(){
        return stateMachineGame.getState();
    }


    //-------------Métodos que permitem acessar à informação/estado do jogo

    public void setStateMachineGame(StateMachineGame novo){
        this.stateMachineGame = novo;

        firePropertyChange(null, null, null);
    }

    public void escolheNave(int escolha){
        stateMachineGame.escolheNave(escolha);

        firePropertyChange(null, null, null);
    }

    //AwaitMove
    public void entraEvento(){
        stateMachineGame.entraEvento();

        firePropertyChange(null, null, null);
    }

    //ApllyEvent
    public void aplicaEventoCerto(int i){
        stateMachineGame.aplicaEventoCerto(i);

        firePropertyChange(null, null, null);
    }
    public void aplicaEvento(){
        stateMachineGame.aplicaEvento();

        firePropertyChange(null, null, null);
    }

    //OnOrbit
    public void entraPlaneta(){
        stateMachineGame.entraPlaneta();

        firePropertyChange(null, null, null);
    }
    public void entraTroca(){
        stateMachineGame.entraTroca();

        firePropertyChange(null, null, null);
    }
    public void entraEstacao(){
        stateMachineGame.entraEstacao();

        firePropertyChange(null, null, null);
    }
    public void saiOrbita(){
        stateMachineGame.saiOrbita();

        firePropertyChange(null, null, null);
    }

    //Estacao
    public void fazUpgrade(int num){
        stateMachineGame.fazUpgrade(num);

        firePropertyChange(null, null, null);
    }
    public void contrata(int num){
        stateMachineGame.contrata(num);

        firePropertyChange(null, null, null);
    }
    public void saiEstacao(){
        stateMachineGame.saiEstacao();

        firePropertyChange(null, null, null);
    }

    //ResourceConvert
    public void troca(int x, int y){//entra no estado troca
        stateMachineGame.troca(x, y);

        firePropertyChange(null, null, null);
    }
    public void saiTroca(){
        stateMachineGame.saiTroca();

        firePropertyChange(null, null, null);
    }

    //OnPlanet
    public void emMovimento(char move){//retorno à nave são condições implementadas neste método
        stateMachineGame.correTerreno(move);

        firePropertyChange(null, null, null);
    }

    //GameOver
    public void restart(){
        stateMachineGame.restart();

        firePropertyChange(null, null, null);
    }

}
