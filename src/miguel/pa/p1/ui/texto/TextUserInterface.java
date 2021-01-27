package miguel.pa.p1.ui.texto;

import javafx.application.Platform;
import miguel.pa.p1.logica.dados.Cargo;
import miguel.pa.p1.logica.dados.Planet;
import miguel.pa.p1.logica.estados.*;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class TextUserInterface {

    private StateMachineGame maqEstados;
    private boolean sair;

    public TextUserInterface(StateMachineGame  maqEstados){
        this.maqEstados = maqEstados;
        sair = false;
    }


    //------------------------funções que aplicam os métodos que mudam os estados------------------------
    void uiAwaitShipSelect(){
        System.out.println("Indique a nave que quer escolher:");
        System.out.println("Mineira(1) ou Militar(2)");
        Scanner sc = new Scanner(System.in);
        int val = sc.nextInt();
        maqEstados.escolheNave(val);
    }


    void uiAwaitMove() {

        System.out.println("Tem 2 opções de escolha.");
        System.out.println("1-Abordar um planeta\t2-Mostrar última mensagem no log");
        Scanner sc = new Scanner(System.in);
        int val = 0;

        while (val < 1 || val > 2) {
            System.out.println("Indique a escolha:");
            val = sc.nextInt();
            if (val == 1) {//aborda planeta, mas antes passa por evento
                maqEstados.entraEvento();//whormhole é feito aqui
            } else if (val == 2) {
                maqEstados.getDadosJogo().mostraLog();//mostrar última mensagem do log
            }
        }
    }


    void uiApplyEvent(){
        int i = 0;

        while(i < 1 || i > 2){
            System.out.println("Certo(1) ou Random(2)?");
            i = new Scanner(System.in).nextInt();
        }

        if(i == 1){
            System.out.println("Indique o número do evento(1 a 6)");
            i = new Scanner(System.in).nextInt();
            maqEstados.aplicaEventoCerto(i);
        }
        else{
            System.out.println("Está a acontecer alguma coisa... Prepara-te para o pior...");
            maqEstados.aplicaEvento();
        }

        System.out.println("Chegaste a um planeta novo.");//
        maqEstados.getDadosJogo().mostraLog();

    }


    void uiOnOrbit() {

        Scanner sc = new Scanner(System.in);
        int val = 0;
        Planet planeta = maqEstados.getDadosJogo().getPlanet();
        System.out.println("Estás em órbita de um planeta.");
        //maqEstados.verifGanhou();
        System.out.println(planeta);
        System.out.println("Opções:");
        if (planeta.getSpaceStation() == true) {

            do {

                System.out.println("1-Enviar drone para o planeta\t2-Trocar recursos\t3-Sair da orbita do planeta\t4-Mostrar última mensagem no log\t5-Entrar na estação espacial");
                val = sc.nextInt();

                switch (val) {
                    case 1://envia drone para o planeta
                        maqEstados.entraPlaneta();
                        break;
                    case 2://troca recursos
                        maqEstados.entraTroca();
                        break;
                    case 3://novo planeta
                        maqEstados.saiOrbita();
                        break;
                    case 4://mostra log
                        maqEstados.getDadosJogo().mostraLog();
                        break;
                }

            } while (val > 4 || val < 1);

        } else {
            System.out.println("1-Enviar drone para o planeta\t2-Trocar recursos\t3-Sair da orbita do planeta\t 4-Mostrar última mensagem no log");
            val = sc.nextInt();

            do {

                switch (val) {
                    case 1://envia drone para o planeta
                        maqEstados.entraPlaneta();
                        System.out.println("Drone enviado pro planeta.");
                        break;
                    case 2://troca recursos
                        maqEstados.entraTroca();
                        break;
                    case 3://novo planeta
                        maqEstados.saiOrbita();
                        break;
                    case 4://mostra log
                        maqEstados.getDadosJogo().mostraLog();
                        break;
                    case 5://entra na estação
                        maqEstados.entraEstacao();
                        break;
                }

            } while (val > 5 || val < 1);

        }

    }


    void uiEstacao(){
        boolean [] officers = maqEstados.getDadosJogo().getShip().getOfficers();
        Scanner sc = new Scanner(System.in);
        int val = 0;
        boolean flag = false;

        System.out.println("Bem vindo à estação espacial. O que deseja fazer?(não há devoluções)");

        while(!flag) {

            while (val > 7 || val < 0) {

                System.out.println("1-Atestar o depósito 2-Recarregar escudos 3-Comprar foguetes(munição)\n4-Contratar officers 5-Upgrade do Cargo 6-Comprar drone");
                System.out.println("0-Sair");

                val = sc.nextInt();

                if (val == 4) {
                    do {
                        System.out.println("Estado dos officers:");
                        System.out.println("1-Captain:" + officers[0] + "2-Navigation:" + officers[1] + "3-Landing:" + officers[2] + "\n4-Shield:" + officers[3] + "5-Weapon:" + officers[4] + "6-Cargo:" + officers[5]);
                        System.out.println("Indique o número do officer, cujo estado é falso, que deseja contratar.");
                        val = sc.nextInt();
                    } while (val > 6 || val < 1);
                    maqEstados.contrata(val);
                    maqEstados.getDadosJogo().mostraLog();
                }
                else {
                    maqEstados.fazUpgrade(val);
                    maqEstados.getDadosJogo().mostraLog();
                }

                if (val == 0)
                    flag = true;

            }

            maqEstados.saiEstacao();

        }

    }


    void uiOnPlanet(){
        System.out.println("Começa no 'S'.");
        char [][] mapa = maqEstados.getDadosJogo().getPlanet().getTerrain().getMapa();

        //mostra matriz


            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 6; j++){
                    System.out.printf("%c  ", mapa[j][i]);
                }
                System.out.printf("\n");
            }

            //apanha input
            System.out.println("Movimento: W(cima) A(esquerda) S(baixo) D(direita)");
            char move = 0;

            while(move != 'W' && move != 'A' && move != 'S' && move != 'D') {
                try {
                    move = (char) System.in.read();
                } catch (IOException e) {

                }
            }
            //envia input
            maqEstados.correTerreno(move);



    }


    void uiResourceConvert(){

        Cargo resources = maqEstados.getDadosJogo().getShip().getCargo();
        Scanner sc = new Scanner(System.in);

        int val1 = 0;
        int val2 = 0;

        int answer = 0;



            while(true) {

                System.out.println(resources);

                while (val1 < 0 || val1 > 4) {
                    System.out.println("Que recurso deseja trocar?(Indique o nº/0 para cancelar troca)");
                    val1 = sc.nextInt();
                    if(val1 != 0)
                        while (val2 < 0 || val2 > 4) {
                            System.out.println("Por que recurso deseja trocar?(Indique o nº/0 para cancelar troca)");
                            val2 = sc.nextInt();
                            if(val2 != 0)
                                maqEstados.troca(val1-1, val2-1);
                        }
                }

                maqEstados.getDadosJogo().mostraLog();

                do{
                    System.out.println("Deseja fazer mais trocas?(1-sim/2-nao)");
                    answer = sc.nextInt();
                }while(answer != 1 && answer != 2);

                if(answer == 2)
                    break;

            }

            maqEstados.saiTroca();

    }


    void uiGameOver(){
        //mostra ultima msg do log, esta msg mostrar o ultimo acontecimento que levou ao gameover
        System.out.println("Chegaste ao final do jogo.");
        System.out.println("Novo jogo?(s/n)");
        if(new Scanner(System.in).nextLine() == "s")
            maqEstados.restart();
    }

    //mesmo que continue o estado é alterado na primeira iteração
    //cada iteração muda o estado(mesmo que mude para um estado novo(mas igual))
    //ex: awaitmove -> awaitmove
    //porem é o que permite que a maq de estados saiba onde está(awaitmove ou onorbit)
    //quando acabar, vai sair da função com o estado adequado


    public void corre(){
        while(!sair){
            IStates estado = maqEstados.getState();
            System.out.printf("\n\n");

            if(maqEstados.getDadosJogo().getShip() != null)
                System.out.println(maqEstados.getDadosJogo().getShip());

            if(estado instanceof AwaitShipSelect)
                uiAwaitShipSelect();
            else if(estado instanceof AwaitMove)
                uiAwaitMove();
            else if(estado instanceof ApplyEvent)
                uiApplyEvent();
            else if(estado instanceof OnOrbit)
                uiOnOrbit();
            else if(estado instanceof Estacao)
                uiEstacao();
            else if(estado instanceof OnPlanet)
                uiOnPlanet();
            else if(estado instanceof ResourceConvert)
                uiResourceConvert();
            else if(estado instanceof GameOver)
                uiGameOver();

        }
    }


}
