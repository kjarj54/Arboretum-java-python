/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.arboretum_karauz_aavila_dazofeifa.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.arboretum_karauz_aavila_dazofeifa.util.AppContext;
import cr.ac.una.arboretum_karauz_aavila_dazofeifa.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class ConexionViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtIP;
    @FXML
    private JFXTextField txtPuerto;
    @FXML
    private JFXButton btnIniciar;

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
    private void onActionBtnIniciar(ActionEvent event) {
        AppContext.getInstance().set("IP", txtIP.getText());
        AppContext.getInstance().set("Puerto", txtPuerto.getText());
        getStage().close();
        FlowController.getInstance().goMain();
        
        
    }
    
    
    
}
