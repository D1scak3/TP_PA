package miguel.pa.p1.logica.estados;

import miguel.pa.p1.logica.dados.*;

import java.util.Random;

public class OnPlanet extends EstadoAdapter{
    public OnPlanet(DataGame jogo) {
        super(jogo);
    }

    @Override
    public IStates emMovimento(char move){

        int j = 0, espera = 0;
        Random rand = new Random();
        Terrain terreno = getDataGame().getPlanet().getTerrain();
        Ship nave = getDataGame().getShip();


        terreno.moveDrone(move);// move/luta se necessário(altera coords drone)
        if(terreno.getAlien() == null && j == 0){
            j++;
            espera = rand.nextInt(6);
        }
        if(j == (espera+1)){
            terreno.novoAlien();
        }
        if(terreno.getAlien() != null)
            terreno.moveAlien();

        terreno.checkSides();//verifica lados(auto fight/mine), atualiza mapa tb

        System.out.println(getDataGame().getPlanet().getTerrain().getAptoSaida());
        terreno.checkSair();//verifica se a saída está ao lado, só funciona com a bag cheia(ativa bool aptoSaida sair)
        System.out.println(getDataGame().getPlanet().getTerrain().getAptoSaida());

        if(terreno.getVidaDrone() == 0){
            getDataGame().getShip().setDrone(false);
            getDataGame().addMSG("O alien destriui o drone, não ganhaste nada.");
            return new OnOrbit(getDataGame());
        }

        if(terreno.getAptoSaida() == true) {
            Cargo recNave = nave.getCargo();
            int maxQuant = nave.getMaxQuant();
            for(int i = 0; i < 4; i++){
                int val = rand.nextInt(6);
                val = val + 1;//1 a 6

                //confere o tipo do que está no bag
                if(terreno.getBag()[i] == 'B'){
                    if(recNave.getQuant(0) + val <= maxQuant)
                        recNave.setQuant(recNave.getQuant(0)+val, 0);
                    else
                        recNave.setQuant(maxQuant, 0);

                }else if(terreno.getBag()[i] == 'R'){
                    if(recNave.getQuant(1) + val <= maxQuant)
                        recNave.setQuant(recNave.getQuant(1)+val, 1);
                    else
                        recNave.setQuant(maxQuant, 1);

                }else if(terreno.getBag()[i] == 'G'){
                    if(recNave.getQuant(2) + val <= maxQuant)
                        recNave.setQuant(recNave.getQuant(2)+val, 2);
                    else
                        recNave.setQuant(maxQuant, 2);

                }else if(terreno.getBag()[i] == 'b'){
                    if(recNave.getQuant(3) + val <= maxQuant)
                        recNave.setQuant(recNave.getQuant(3)+val, 3);
                    else
                        recNave.setQuant(maxQuant, 3);

                }else if(terreno.getBag()[i] == 'X')
                    nave.gotArtefacto();
            }
            getDataGame().addMSG("O drone voltou à nave com sucesso, juntamente com o que minou.");
            if(nave.getArtefactos() == 5)
                return new GameOver(getDataGame());
            return new OnOrbit(getDataGame());
        }

            return new OnPlanet(getDataGame());
    }



}
