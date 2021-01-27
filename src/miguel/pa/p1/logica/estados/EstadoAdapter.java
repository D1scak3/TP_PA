package miguel.pa.p1.logica.estados;

import miguel.pa.p1.logica.dados.DataGame;

public class EstadoAdapter implements IStates {

    //DataGame pode ser protected para as classes derivadas poderem aceder diretamente

    private DataGame jogo;

    public EstadoAdapter(DataGame jogo){this.jogo = jogo;}

    public DataGame getDataGame(){
        return jogo;
    }

    public void setDataGame(DataGame jogo){
        this.jogo = jogo;
    }


    @Override
    public IStates escolheNave(int escolha) {
        return this;
    }

    @Override
    public IStates entraEvento() {
        return this;
    }

    @Override
    public IStates aplicaEventoCerto(int i) {
        return this;
    }

    @Override
    public IStates aplicaEvento() {
        return this;
    }

    @Override
    public IStates entraPlaneta() {
        return this;
    }

    @Override
    public IStates entraTroca() {
        return this;
    }

    @Override
    public IStates entraEstacao() {
        return this;
    }

    @Override
    public IStates saiOrbita() {
        return this;
    }

    @Override
    public IStates fazUpgrade(int num) {
        return this;
    }

    @Override
    public IStates contrata(int num) {
        return this;
    }

    @Override
    public IStates saiEstacao() {
        return this;
    }

    @Override
    public IStates troca(int x, int y) {
        return this;
    }

    @Override
    public IStates saiTroca() {
        return this;
    }

    @Override
    public IStates emMovimento(char move) {
        return this;
    }

    @Override
    public IStates restart() {
        return this;
    }

}
