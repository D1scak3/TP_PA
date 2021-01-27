package miguel.pa.p1.iu.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import miguel.pa.p1.logica.estados.ObservableSMG;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SMGPane extends BorderPane implements Constants, PropertyChangeListener {
    private final ObservableSMG obsSMG;

    //panes constantes(info nave/log/)
    private LogPane logPane;//mensagens do log
    private InfoPane infoPane;//info da nave

    //panes dos estados
    private ShipSelectPane shipSelectPane;
    private EventPane eventPane;
    private MovePane movePane;
    private OrbitPane orbitPane;
    private PlanetPane planetPane;
    private EstacaoPane estacaoPane;
    private ConvertPane convertPane;
    private GameOverPane gameOverPane;

    private Label logLabel;
    private Label infoLabel;

    VBox leftVBox;
    StackPane rightSP;

    public SMGPane(ObservableSMG obsSMG){
        this.obsSMG = obsSMG;
        this.obsSMG.addPropertyChangeListener(this);

        setupComponents();
        setupLayout();

        propertyChange(null);
    }

    private void setupComponents(){
        setPrefSize(DIM_X_FRAME, DIM_Y_FRAME);
        setMinSize(DIM_X_FRAME, DIM_Y_FRAME);

        logLabel = new Label("Log de Viagem");
        logLabel.setFont(letra);
        logLabel.setPadding(new Insets(10, 10, 10, 10));
        //este pane é permanente
        logPane = new LogPane(obsSMG);

        infoLabel = new Label("Info da Nave");
        infoLabel.setFont(letra);
        infoLabel.setPadding(new Insets(10, 10, 10, 10));
        //este pane é permanente
        infoPane = new InfoPane(obsSMG);

        shipSelectPane = new ShipSelectPane(obsSMG);
        eventPane = new EventPane(obsSMG);
        movePane = new MovePane(obsSMG);
        orbitPane = new OrbitPane(obsSMG);
        planetPane = new PlanetPane(obsSMG);
        estacaoPane = new EstacaoPane(obsSMG);
        convertPane = new ConvertPane(obsSMG);
        gameOverPane = new GameOverPane(obsSMG);

    }

    private void setupLayout(){

        VBox topVBox = new VBox(10);
        topVBox.setMaxSize(LOG_X, LOG_Y);
        topVBox.setPrefSize(LOG_X, LOG_Y);
        topVBox.setMinSize(LOG_X/4, LOG_Y/4);
        topVBox.setPadding(new Insets(10, 10, 10, 10));
        topVBox.getChildren().addAll(logLabel, logPane);
        logLabel.setAlignment(Pos.TOP_LEFT);
        logPane.setAlignment(Pos.CENTER);

        VBox bottomVBox = new VBox(10);
        bottomVBox.setMaxSize(INFO_X, INFO_Y);
        bottomVBox.setPrefSize(INFO_X, INFO_Y);
        bottomVBox.setMinSize(INFO_X/4, INFO_Y/4);
        bottomVBox.setPadding(new Insets(10, 10, 10, 10));
        bottomVBox.getChildren().addAll(infoLabel, infoPane);
        infoLabel.setAlignment(Pos.TOP_LEFT);
        infoPane.setAlignment(Pos.CENTER);


        leftVBox = new VBox(1);
        leftVBox.setMaxSize(LEFT_VBOX_X, LEFT_VBOX_Y);
        leftVBox.setPrefSize(LEFT_VBOX_X, LEFT_VBOX_Y);
        leftVBox.setMinSize(LEFT_VBOX_X/2, LEFT_VBOX_Y/2);
        leftVBox.setPadding(new Insets(10, 10, 10, 10));
        leftVBox.getChildren().addAll(topVBox, bottomVBox);
        leftVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

        rightSP = new StackPane(shipSelectPane, eventPane, movePane, orbitPane, planetPane, estacaoPane, convertPane, gameOverPane);
        rightSP.setMaxSize(RIGHT_STACK_X, RIGHT_STACK_Y);
        rightSP.setPrefSize(RIGHT_STACK_X, RIGHT_STACK_Y);
        rightSP.setMinSize(RIGHT_STACK_X/10, RIGHT_STACK_Y/10);
        rightSP.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

        HBox center = new HBox();//meter aqui um valor se necessário
        center.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        center.getChildren().addAll(leftVBox, rightSP);

        leftVBox.setAlignment(Pos.CENTER_LEFT);
        rightSP.setAlignment(Pos.CENTER_RIGHT);

        setCenter(center);

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt){
        
    }

}
