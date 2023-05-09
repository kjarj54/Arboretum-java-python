/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.arboretum_karauz_aavila_dazofeifa.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
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
    private JFXTextField txtPlayer1;
    @FXML
    private JFXCheckBox cbxPlayer1;
    @FXML
    private JFXTextField txtPlayer2;
    @FXML
    private JFXCheckBox cbxPlayer2;
    @FXML
    private JFXTextField txtPlayer3;
    @FXML
    private JFXCheckBox cbxPlayer3;
    @FXML
    private JFXTextField txtPlayer4;
    @FXML
    private JFXCheckBox cbxPlayer4;
    @FXML
    private JFXButton btnCrearPartida;

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
    private void onActionBtnCrearPartida(ActionEvent event) {
    }
    
}
