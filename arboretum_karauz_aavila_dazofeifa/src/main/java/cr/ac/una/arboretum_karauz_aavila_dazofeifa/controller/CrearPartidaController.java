/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.arboretum_karauz_aavila_dazofeifa.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import conexion.Conexion;
import cr.ac.una.arboretum_karauz_aavila_dazofeifa.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class CrearPartidaController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXCheckBox cbxPlayer1;
    @FXML
    private JFXCheckBox cbxPlayer2;
    @FXML
    private JFXCheckBox cbxPlayer3;
    @FXML
    private JFXCheckBox cbxPlayer4;
    @FXML
    private JFXButton btnCrearPartida1;
    @FXML
    private JFXButton btnSalir;

    Conexion conexion;
    int jugadorIndex;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void initialize() {
        conexion = new Conexion();
        jugadorIndex = Integer.parseInt(conexion.jugadorIndex());
        
        switch (jugadorIndex) {
            case 0:
                btnCrearPartida1.setDisable(false);
                break;
            case 1:
                
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
        
        conexion.statusJugadores(cbxPlayer1, cbxPlayer2, cbxPlayer3, cbxPlayer4);
        conexion.esperar(cbxPlayer1, cbxPlayer2, cbxPlayer3, cbxPlayer4);
    }

    @FXML
    private void onActionBtnCrearPartida(ActionEvent event) {
        conexion.iniciarPartida();
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        conexion.continuarHilo = false;
        conexion.desconeccion();
        if (conexion.hiloEspera != null) {
            conexion.hiloEspera.interrupt();
            System.out.println("Salir");
            FlowController.getInstance().goMain();
            getStage().close();
        }
    }

}
