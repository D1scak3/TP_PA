package miguel.pa.p1.logica.estados;

import miguel.pa.p1.logica.dados.Cargo;
import miguel.pa.p1.logica.dados.DataGame;

public class ResourceConvert extends EstadoAdapter{
    public ResourceConvert(DataGame jogo) {
        super(jogo);
    }

    @Override
    public IStates troca(int x, int y){
        int maxQuant = getDataGame().getShip().getMaxQuant();
        Cargo resource = getDataGame().getShip().getCargo();
        if(resource.getQuant(x-1) > 0 && resource.getQuant(y-1) < maxQuant) {
            resource.setQuant(resource.getQuant(x-1) - 1, x-1);//diminui o que quero trocar
            resource.setQuant(resource.getQuant(y-1) + 1, y-1);//aumenta pelo que troquei
            getDataGame().addMSG("Troca efetuada com sucesso.");
        }
        else
            getDataGame().addMSG("Troca não efetuada.");
        return this;
    }

    @Override
    public IStates saiTroca(){
        getDataGame().addMSG("Trocas finalizadas.");
        return new OnOrbit(getDataGame());
    }

}
