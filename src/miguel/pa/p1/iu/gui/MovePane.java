package miguel.pa.p1.iu.gui;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import miguel.pa.p1.logica.estados.AwaitMove;
import miguel.pa.p1.logica.estados.AwaitShipSelect;
import miguel.pa.p1.logica.estados.ObservableSMG;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MovePane extends VBox implements Constants, PropertyChangeListener {
    private ObservableSMG obsSMG;

    public MovePane(ObservableSMG obsSMG){
        this.obsSMG = obsSMG;
        this.obsSMG.addPropertyChangeListener(this);

        ImageView movimento = new ImageView(new Image(PATH_TO_MOVIMENTO));

        Button avancar = new Button("Avançar");

        setAlignment(Pos.CENTER);
        getChildren().addAll(movimento, avancar);
        avancar.setOnAction(new Avanca());

        propertyChange(null);
    }


    class Avanca implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            obsSMG.entraEvento();
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt){
        setVisible(obsSMG.getState() instanceof AwaitMove);
    }

}
