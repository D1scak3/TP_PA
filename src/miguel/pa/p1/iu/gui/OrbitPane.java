package miguel.pa.p1.iu.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import miguel.pa.p1.logica.dados.Planet;
import miguel.pa.p1.logica.estados.ApplyEvent;
import miguel.pa.p1.logica.estados.ObservableSMG;
import miguel.pa.p1.logica.estados.OnOrbit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OrbitPane extends VBox implements Constants, PropertyChangeListener {
    private ObservableSMG obsSMG;

    public OrbitPane(ObservableSMG obsSMG){
        this.obsSMG = obsSMG;
        this.obsSMG.addPropertyChangeListener(this);

        Button sair = new Button("Sair da orbita.");
        Button estacao = new Button("Entrar na estação");
        Button troca = new Button("Trocar recursos.");
        Button aterrar = new Button("Enviar drone ao planeta");
        HBox botoes = new HBox();
        botoes.getChildren().addAll(sair, estacao, troca, aterrar);

        ImageView orbita = new ImageView(new Image(PATH_TO_ORBIT));
        orbita.setFitHeight(250);
        orbita.setFitWidth(250);

        getChildren().addAll(orbita, botoes);

        sair.setOnAction(new Sair());
        estacao.setOnAction(new Compra());
        troca.setOnAction(new Troca());
        aterrar.setOnAction(new Aterrar());

        Planet planeta = obsSMG.getStateMachineGame().getDadosJogo().getPlanet();
        System.out.println(planeta);

        propertyChange(null);
    }

    class Sair implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent evt){
            obsSMG.saiOrbita();
        }
    }

    class Compra implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            obsSMG.entraEstacao();
        }
    }

    class Troca implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            obsSMG.entraTroca();
        }
    }

    class Aterrar implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            obsSMG.entraPlaneta();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        setVisible(obsSMG.getState() instanceof OnOrbit);
    }

}
