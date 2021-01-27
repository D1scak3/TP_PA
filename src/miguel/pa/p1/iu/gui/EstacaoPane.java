package miguel.pa.p1.iu.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import miguel.pa.p1.logica.estados.Estacao;
import miguel.pa.p1.logica.estados.ObservableSMG;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EstacaoPane extends VBox implements Constants, PropertyChangeListener {
    private ObservableSMG obsSMG;
    Label strOfficer;

    public EstacaoPane(ObservableSMG obsSMG){
        this.obsSMG = obsSMG;
        this.obsSMG.addPropertyChangeListener(this);

        Button op1 = new Button("Attest");
        Button op2 = new Button("Recharge");
        Button op3 = new Button("Reload");
        Button op4 = new Button("Cargo Upgrade");
        Button op5 = new Button("Buy Drone");


        ChoiceBox<Integer> officers = new ChoiceBox<>();
        strOfficer = new Label("Officers:");
        officers.getItems().addAll(1, 2, 3, 4, 5, 6);
        officers.setValue(1);
        Button op6 = new Button("Contract");

        HBox botoes = new HBox();
        botoes.getChildren().add(op1);
        botoes.getChildren().add(op2);
        botoes.getChildren().add(op3);
        botoes.getChildren().add(op4);
        botoes.getChildren().add(op5);
        botoes.getChildren().add(officers);
        botoes.getChildren().add(op6);

        Button sair = new Button("Sair");

        ImageView img = new ImageView(new Image(PATH_TO_STATION));
        img.setFitWidth(250);
        img.setFitHeight(250);

        getChildren().addAll(img, botoes, sair);

        op1.setOnAction(new Attest());
        op2.setOnAction(new Recharge());
        op3.setOnAction(new Reload());
        op4.setOnAction(new CargoUp());
        op5.setOnAction(new BuyDrone());
        op6.setOnAction(new Contract(officers));
        sair.setOnAction(new Sair());

        propertyChange(null);
    }

    class Attest implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent evt){
            obsSMG.fazUpgrade(1);
        }
    }

    class Recharge implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent evt){
            obsSMG.fazUpgrade(2);
        }
    }

    class Reload implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent evt){
            obsSMG.fazUpgrade(3);
        }
    }

    class CargoUp implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent evt){
            obsSMG.fazUpgrade(5);
        }
    }

    class BuyDrone implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent evt){
            obsSMG.fazUpgrade(6);
        }
    }

    class Contract implements EventHandler<ActionEvent>{

        private ChoiceBox<Integer> officers;

        public Contract(ChoiceBox<Integer> off){
            officers = off;
        }

        @Override
        public void handle(ActionEvent evt){
            obsSMG.contrata(officers.getValue());
        }
    }

    class Sair implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent evt){
            obsSMG.saiEstacao();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        setVisible(obsSMG.getState() instanceof Estacao);
    }

}
