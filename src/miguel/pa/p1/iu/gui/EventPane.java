package miguel.pa.p1.iu.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import miguel.pa.p1.logica.estados.ApplyEvent;
import miguel.pa.p1.logica.estados.ObservableSMG;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

public class EventPane extends VBox implements Constants, PropertyChangeListener {
    private ObservableSMG obsSMG;

    public EventPane(ObservableSMG obsSMG){
        this.obsSMG = obsSMG;
        this.obsSMG.addPropertyChangeListener(this);


        ImageView random = new ImageView(new Image(PATH_TO_RANDOM));
        random.setFitHeight(250);
        random.setFitWidth(250);

        Button certo = new Button("Certo");
        ChoiceBox<Integer> val = new ChoiceBox<>();
        val.getItems().addAll(1, 2, 3, 4, 5, 6);
        val.setValue(1);
        Button aleatorio = new Button("Aleatorio");

        HBox botoes = new HBox();
        botoes.getChildren().addAll(aleatorio, val, certo);

        getChildren().addAll(random, botoes);
        botoes.setAlignment(Pos.BASELINE_CENTER);

        certo.setOnAction(new Certo(val));
        aleatorio.setOnAction(new Aleatorio());

        propertyChange(null);
    }


    class Certo implements EventHandler<ActionEvent>{
        private ChoiceBox<Integer> choice;

        public Certo(ChoiceBox<Integer> choice){
            this.choice = choice;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            obsSMG.aplicaEventoCerto(choice.getValue());
        }
    }

    class Aleatorio implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            obsSMG.aplicaEvento();
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt){
        setVisible(obsSMG.getState() instanceof ApplyEvent);
    }
}
