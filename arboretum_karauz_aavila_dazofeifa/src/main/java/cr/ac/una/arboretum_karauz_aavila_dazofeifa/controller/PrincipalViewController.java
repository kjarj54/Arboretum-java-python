/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.arboretum_karauz_aavila_dazofeifa.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.arboretum_karauz_aavila_dazofeifa.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class PrincipalViewController extends Controller implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private JFXButton btnBuscarPartida;
    @FXML
    private JFXButton btnCrearPartida;
    @FXML
    private JFXButton btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnBuscarPartida(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCrearPartida(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("CrearPartida");
        FlowController.getInstance().salir();
    }

    @FXML
    private void onActionSalir(ActionEvent event) {
    }
    
}
