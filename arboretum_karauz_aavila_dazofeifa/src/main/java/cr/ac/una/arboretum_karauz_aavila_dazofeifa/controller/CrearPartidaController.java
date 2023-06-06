/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.arboretum_karauz_aavila_dazofeifa.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import conexion.Conexion;
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
    private JFXButton btnListo;
    @FXML
    private JFXButton btnCrearPartida1;
    
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
            case 1:
                System.out.println("Opción 1 seleccionada");
                break;
            case 2:
                System.out.println("Opción 2 seleccionada");
                break;
            case 3:
                System.out.println("Opción 3 seleccionada");
                break;
            case 4:
                System.out.println("Opción 4 seleccionada");
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }

    @FXML
    private void onActionBtnCrearPartida(ActionEvent event) {
    }

    @FXML
    private void onActionBtnListo(ActionEvent event) {
    }
    
}
