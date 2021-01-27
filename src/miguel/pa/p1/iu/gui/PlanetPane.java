package miguel.pa.p1.iu.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import miguel.pa.p1.logica.estados.ObservableSMG;
import miguel.pa.p1.logica.estados.OnPlanet;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.PipedOutputStream;

public class PlanetPane extends VBox implements Constants, PropertyChangeListener {
    private ObservableSMG obsSMG;

    public PlanetPane(ObservableSMG obsSMG){
        this.obsSMG = obsSMG;
        this.obsSMG.addPropertyChangeListener(this);

        propertyChange(null);

    }

    class Up implements EventHandler<ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent actionEvent) {
            obsSMG.emMovimento('W');
        }
    }

    class Down implements EventHandler<ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent actionEvent) {
            obsSMG.emMovimento('S');
        }
    }

    class Right implements EventHandler<ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent actionEvent) {
            obsSMG.emMovimento('D');
        }
    }

    class Left implements EventHandler<ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent actionEvent) {
            obsSMG.emMovimento('A');
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){

        getChildren().clear();

        Button up = new Button("Up");
        Button down = new Button("Down");
        Button right = new Button("Right");
        Button left = new Button("Left");


        HBox botoes = new HBox();
        botoes.getChildren().addAll(up, left, down, right);

        GridPane terreno = new GridPane();
        terreno.setAlignment(Pos.CENTER);
        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints(70);
            terreno.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 5; i++) {
            RowConstraints row = new RowConstraints(70);
            terreno.getRowConstraints().add(row);
        }
        terreno.setGridLinesVisible(true);

        float dimMin = Math.min(RIGHT_STACK_X, RIGHT_STACK_Y);
        BackgroundSize backgroundSize = new BackgroundSize(dimMin, dimMin, false, false, false, false);
        BackgroundImage bckImg = new BackgroundImage(new Image(PATH_TO_TERRAIN), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        setBackground(new Background(bckImg));



        if(obsSMG.getStateMachineGame().getDadosJogo().getPlanet() != null) {
            //System.out.println("OLA");
            char [][] mapa = obsSMG.getStateMachineGame().getDadosJogo().getPlanet().getTerrain().getMapa();
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    Image img = null;
                    ImageView terrenoView = new ImageView();
                    terrenoView.setFitHeight(30);
                    terrenoView.setFitWidth(30);

                    if (mapa[i][j] == 'X') {
                        img = new Image(PATH_TO_ARTIFACT);
                    } else if (mapa[i][j] == 'R') {
                        img = new Image(PATH_TO_RED);
                    } else if (mapa[i][j] == 'B') {
                        img = new Image(PATH_TO_BLACK);
                    } else if (mapa[i][j] == 'G') {
                        img = new Image(PATH_TO_GREEN);
                    } else if (mapa[i][j] == 'b') {
                        img = new Image(PATH_TO_BLUE);
                    } else if (mapa[i][j] == 'S') {
                        img = new Image(PATH_TO_EXIT);
                    } else if (mapa[i][j] == 'A') {
                        img = new Image(PATH_TO_ALIEN);
                    } else if (mapa[i][j] == 'D') {
                        img = new Image(PATH_TO_DRONE);
                    }

                    terrenoView.setImage(img);

                    terreno.add(terrenoView, i, j);

                }
            }
        }

        up.setOnAction(new Up());
        down.setOnAction(new Down());
        right.setOnAction(new Right());
        left.setOnAction(new Left());

        getChildren().addAll(terreno, botoes);
        terreno.setAlignment(Pos.CENTER);
        botoes.setAlignment(Pos.BASELINE_CENTER);


        setVisible(obsSMG.getState() instanceof OnPlanet);

    }

}
