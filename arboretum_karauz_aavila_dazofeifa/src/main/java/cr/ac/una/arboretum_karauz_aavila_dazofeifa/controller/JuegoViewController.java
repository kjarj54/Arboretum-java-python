/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.arboretum_karauz_aavila_dazofeifa.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class JuegoViewController extends Controller implements Initializable {

    @FXML
    private Label lbPlayer1;
    @FXML
    private HBox contCartasPlayer1;
    @FXML
    private StackPane stpPlayer1;
    @FXML
    private GridPane gpTablaPlayer1;
    
    @FXML
    private Label lbPlayer2;
    @FXML
    private VBox contCartasPlayer2;
    @FXML
    private StackPane stpPlayer2;
    @FXML
    private GridPane gpTablaPlayer2;
    
    @FXML
    private Label lbPlayer3;
    @FXML
    private HBox contCartasPlayer3;
    @FXML
    private StackPane stpPlayer3;
    @FXML
    private GridPane gpTablaPlayer3;
    
    @FXML
    private Label lbPlayer4;
    @FXML
    private VBox contCartasPlayer4;
    @FXML
    private StackPane stpPlayer4;
    @FXML
    private GridPane gpTablaPlayer4;
    
    @FXML
    private Label lbTemporizador;
    @FXML
    private JFXButton btnTerminarTurno;
    @FXML
    private JFXButton btnTerminarJuego;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnTerminarTurno(ActionEvent event) {
    }

    @FXML
    private void onActionBtnTerminarJuego(ActionEvent event) {
    }
    
}
