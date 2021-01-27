package miguel.pa.p1.iu.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import miguel.pa.p1.logica.estados.IStates;
import miguel.pa.p1.logica.estados.ObservableSMG;
import miguel.pa.p1.logica.estados.StateMachineGame;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;

public class SMGRoot extends VBox implements Constants, PropertyChangeListener {
    private final ObservableSMG obsSMG;
    private final SMGPane smgPane;

    public SMGRoot(ObservableSMG obsSMG){
        this.obsSMG = obsSMG;
        this.obsSMG.addPropertyChangeListener(this);


        smgPane = new SMGPane(obsSMG);
        MenuBar menuBar = getMenuBar();

        getChildren().addAll(menuBar, smgPane);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        IStates state = obsSMG.getState();
    }


    private MenuBar getMenuBar(){
        MenuBar menuBar = new MenuBar();

        Menu gameMenu = new Menu("_Menu");

        MenuItem readMenuItem = new MenuItem("_Load");
        readMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));

        MenuItem saveMenuItem = new MenuItem("_Save");
        saveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        gameMenu.getItems().addAll(readMenuItem, saveMenuItem);

        readMenuItem.setOnAction(new LoadObjMenuBarListener());
        saveMenuItem.setOnAction(new SaveObjMenuBarListener());

        menuBar.getMenus().addAll(gameMenu);


        return menuBar;
    }


    //eventHandlers para o menu
    class LoadObjMenuBarListener implements EventHandler<ActionEvent>  {
        @Override
        public void handle(ActionEvent e) {
            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(getScene().getWindow());
            if(f == null)
                return;

            try {

                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                StateMachineGame maqEstados = (StateMachineGame) ois.readObject();
                obsSMG.setStateMachineGame(maqEstados);
                ois.close();

            }catch(Exception ex) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Não foi possível dar load.");
                alerta.setTitle("Error");
                alerta.show();
            }

        }
    }

    class SaveObjMenuBarListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            FileChooser fc = new FileChooser();
            File f = fc.showSaveDialog(getScene().getWindow());
            if(f == null)
                return;

            try {

                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                oos.writeObject(obsSMG.getStateMachineGame());
                oos.close();

            }catch(Exception ex) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Não foi possível guardar o jogo.");
                alerta.setTitle("Error");
                alerta.show();
            }
        }

    }

}
