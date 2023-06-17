/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.arboretum_karauz_aavila_dazofeifa.controller;

import com.jfoenix.controls.JFXButton;
import conexion.Conexion;
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
    private StackPane stpPlayer1;
    @FXML
    private GridPane gpTablaPlayer1;
    
    @FXML
    private StackPane stpPlayer2;
    @FXML
    private GridPane gpTablaPlayer2;
    
    @FXML
    private HBox contCartasPlayer3;
    @FXML
    private StackPane stpPlayer3;
    @FXML
    private GridPane gpTablaPlayer3;
    
    @FXML
    private StackPane stpPlayer4;
    @FXML
    private GridPane gpTablaPlayer4;  
    
    @FXML
    private Label lbTurno;
    @FXML
    private Label lbPlayer;
    @FXML
    private Label lbTemporizador;
    @FXML
    private JFXButton btnTerminarTurno;
    @FXML
    private JFXButton btnTerminarJuego;
    
    Conexion conexion;
    boolean miTurno;
    int jugadorIndex;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @Override
    public void initialize() {
    }
    
    public void cargarDatosIniciales(Conexion conexion, int jugadorIndex) {
        this.conexion = conexion;
        this.jugadorIndex = jugadorIndex;
        
        if(jugadorIndex == 0) {
            miTurno = true;
        } else {
            miTurno = false;
        }
        
        lbTurno.setText("Player 1");
        lbPlayer.setText("Player " + (jugadorIndex+1));
        btnTerminarTurno.setDisable(!miTurno);
        conexion.jugando();
    }

    @FXML
    private void onActionBtnTerminarTurno(ActionEvent event) {
    }

    @FXML
    private void onActionBtnTerminarJuego(ActionEvent event) {
    }
    
}
