package miguel.pa.p1.iu.gui;

import com.sun.javafx.embed.HostDragStartListener;
import com.sun.javafx.tk.ImageLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import miguel.pa.p1.logica.estados.AwaitShipSelect;
import miguel.pa.p1.logica.estados.ObservableSMG;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShipSelectPane extends VBox implements Constants, PropertyChangeListener {
    private ObservableSMG obsSMG;


    public ShipSelectPane(ObservableSMG obsSMG){
        this.obsSMG = obsSMG;
        this.obsSMG.addPropertyChangeListener(this);

        ImageView img = new ImageView(new Image(PATH_TO_SWORD_PICK));

        Button mineira = new Button("Mineira");
        Button militar = new Button("Militar");
        HBox botoes = new HBox();
        botoes.getChildren().addAll(mineira, militar);
        botoes.setMaxSize(RIGHT_STACK_X, RIGHT_STACK_Y/4);
        botoes.setPrefSize(RIGHT_STACK_X, RIGHT_STACK_Y/4);
        botoes.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

        getChildren().addAll(img, botoes);

        mineira.setOnAction(new Escolhe1());
        militar.setOnAction(new Escolhe2());

        propertyChange(null);
    }


    //eventListeners
    class Escolhe1 implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            obsSMG.escolheNave(1);
        }
    }

    class Escolhe2 implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent){
            obsSMG.escolheNave(2);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        setVisible(obsSMG.getState() instanceof AwaitShipSelect);
    }

}
