package miguel.pa.p1;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import miguel.pa.p1.iu.gui.SMGRoot;
import miguel.pa.p1.logica.estados.ObservableSMG;
import miguel.pa.p1.logica.estados.StateMachineGame;

public class main extends Application {

//    public static void main(String args[]){
//        TextUserInterface ui = new TextUserInterface(new StateMachineGame());
//        ui.corre();
//    }

    @Override
    public void start(Stage primaryStage){

        StateMachineGame model = new StateMachineGame();
        ObservableSMG obsSMG = new ObservableSMG(model);

        SMGRoot root = new SMGRoot(obsSMG);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Planet Bound");
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String args[]){
        launch(args);
    }

}
