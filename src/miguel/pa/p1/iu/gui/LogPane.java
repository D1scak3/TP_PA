package miguel.pa.p1.iu.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import miguel.pa.p1.logica.estados.ObservableSMG;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class LogPane extends VBox implements Constants, PropertyChangeListener {
    private ObservableSMG obsSMG;
    TextArea log;

    public LogPane(ObservableSMG obsSMG){
        this.obsSMG = obsSMG;
        this.obsSMG.addPropertyChangeListener(this);

        Label label = new Label("Log:");

        log = new TextArea();
        log.setMaxSize(LOG_X, LOG_Y);
        log.setPrefSize(LOG_X, LOG_Y);
        log.setMinSize(LOG_X/4, LOG_Y/4);

        this.setAlignment(Pos.CENTER);

        this.getChildren().addAll(label, log);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){

        ArrayList<String> logRecebido = obsSMG.getStateMachineGame().getDadosJogo().getMsgLog();

        for(int i = 0; i < logRecebido.size(); i++){
            if(i == (logRecebido.size()-1)) {
                log.appendText(logRecebido.get(i));
                log.appendText("\n");
            }
        }

        log.setScrollLeft(Double.MAX_VALUE);
    }

}
