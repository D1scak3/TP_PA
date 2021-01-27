package miguel.pa.p1.logica.estados;

import miguel.pa.p1.logica.dados.DataGame;

import java.io.Serializable;

public class StateMachineGame implements Serializable {

    private DataGame dadosJogo;
    private IStates estado;


    public StateMachineGame(){
        dadosJogo = new DataGame();
        estado = new AwaitShipSelect(dadosJogo);
    }

    public IStates getState(){
        return estado;
    }

    public void setState(IStates estado){
        this.estado = estado;
    }

    //esta função n deve existir
    public DataGame getDadosJogo(){
        return dadosJogo;
    }

    //-------------Methods that change the state of the machine------------------

    //recebe input do user(escolha) e manda para o estado
    public void escolheNave(int escolha){//passa de AwaitShipSelect para AwaitMove
        setState(getState().escolheNave(escolha));
    }

    public void entraEvento(){
        setState(getState().entraEvento());
    }

    public void aplicaEventoCerto(int i){
        setState(getState().aplicaEventoCerto(i));
    }

    public void aplicaEvento(){
        setState(getState().aplicaEvento());
    }

    public void entraPlaneta(){
        setState(getState().entraPlaneta());
    }

    public void entraTroca(){
        setState(getState().entraTroca());
    }

    public void saiOrbita(){
        setState(getState().saiOrbita());
    }

    public void entraEstacao(){
        setState(getState().entraEstacao());
    }

    public void contrata(int i){
        setState(getState().contrata(i));
    }

    public void fazUpgrade(int i){
        setState(getState().fazUpgrade(i));
    }

    public void saiEstacao(){
        setState(getState().saiEstacao());
    }

    public void correTerreno(char move){
        setState(getState().emMovimento(move));
    }

    public void troca(int x, int y){
        setState(getState().troca(x, y));
    }

    public void saiTroca(){
        setState(getState().saiTroca());
    }

    public void restart(){
        setState(getState().restart());
    }

}
