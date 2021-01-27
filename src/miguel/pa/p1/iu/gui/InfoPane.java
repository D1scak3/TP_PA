package miguel.pa.p1.iu.gui;

import javafx.css.Size;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import miguel.pa.p1.logica.dados.Ship;
import miguel.pa.p1.logica.estados.ObservableSMG;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class InfoPane extends VBox implements Constants, PropertyChangeListener {
    private ObservableSMG obsSMG;
    TextArea info;

    public InfoPane(ObservableSMG obsSMG){
        this.obsSMG = obsSMG;
        this.obsSMG.addPropertyChangeListener(this);

        Label label = new Label("Estado da nave:");
        info = new TextArea();
        info.setMaxSize(INFO_X, INFO_Y);
        info.setPrefSize(INFO_X, INFO_Y );
        info.setMinSize(INFO_X/4, INFO_Y/4);
        this.getChildren().addAll(label, info);

        this.setAlignment(Pos.CENTER);

        propertyChange(null);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt){
        Ship nave = obsSMG.getStateMachineGame().getDadosJogo().getShip();
        info.clear();
        if(nave != null)
            info.appendText("" + nave);
    }




}
