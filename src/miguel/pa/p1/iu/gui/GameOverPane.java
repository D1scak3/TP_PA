package miguel.pa.p1.iu.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import miguel.pa.p1.logica.estados.GameOver;
import miguel.pa.p1.logica.estados.ObservableSMG;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameOverPane extends HBox implements Constants, PropertyChangeListener {
    private ObservableSMG obsSMG;

    public GameOverPane(ObservableSMG obsSMG){
        this.obsSMG = obsSMG;
        this.obsSMG.addPropertyChangeListener(this);

        Button restart = new Button("Restart");
        Button exit = new Button("Exit");

        HBox box = new HBox();
        box.getChildren().addAll(restart, exit);

        ImageView gameover = new ImageView(new Image(PATH_TO_GAMEOVER));

        getChildren().addAll(gameover, box);
        box.setAlignment(Pos.BASELINE_CENTER);

        restart.setOnAction(new Restart());
        exit.setOnAction(new Exit());

        propertyChange(null);
    }

    class Restart implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            obsSMG.restart();
        }
    }

    //VER ESTA FUNÇÃO
    class Exit implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent){
            Platform.exit();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        setVisible(obsSMG.getState() instanceof GameOver);
    }

}
