package miguel.pa.p1.iu.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import miguel.pa.p1.logica.estados.ObservableSMG;
import miguel.pa.p1.logica.estados.ResourceConvert;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ConvertPane extends VBox implements Constants, PropertyChangeListener {
    private ObservableSMG obsSMG;

    public ConvertPane(ObservableSMG obsSMG){
        this.obsSMG = obsSMG;
        this.obsSMG.addPropertyChangeListener(this);

        ChoiceBox<Integer> troca = new ChoiceBox<>();
        ChoiceBox<Integer> trocado = new ChoiceBox<>();
        troca.getItems().addAll(1, 2, 3, 4);
        trocado.getItems().addAll(1, 2, 3, 4);
        troca.setValue(1);
        trocado.setValue(1);

        float dimMin = Math.min(RIGHT_STACK_X, RIGHT_STACK_Y);
        BackgroundSize backgroundSize = new BackgroundSize(dimMin, dimMin, false, false, false, false);
        BackgroundImage bckImg = new BackgroundImage(new Image(PATH_TO_CONVERT), BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        setBackground(new Background(bckImg));

        Button executar = new Button("Trocar");
        Button sair = new Button("Sair");

        HBox box = new HBox();
        box.getChildren().addAll(troca, trocado, executar, sair);//pra mante-los todos juntos

        getChildren().addAll(box);
        setAlignment(Pos.CENTER);


        executar.setOnAction(new EfetuaTroca(troca, trocado));
        sair.setOnAction(new Sair());


        propertyChange(null);
    }

    class EfetuaTroca implements EventHandler<ActionEvent>{

        private ChoiceBox<Integer> troca1;
        private ChoiceBox<Integer> troca2;

        public EfetuaTroca(ChoiceBox<Integer> t1, ChoiceBox<Integer> t2){
            this.troca1 = t1;
            this.troca2 = t2;
        }

        @Override
        public void handle(ActionEvent evt){
            System.out.println(troca1.getValue());
            System.out.println(troca2.getValue());
            obsSMG.troca(troca1.getValue(), troca2.getValue());
        }
    }

    class Sair implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent evt){
            obsSMG.saiTroca();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setVisible(obsSMG.getState() instanceof ResourceConvert);
    }

}
