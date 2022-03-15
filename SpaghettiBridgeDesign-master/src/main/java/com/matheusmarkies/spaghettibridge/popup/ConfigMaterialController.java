/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.popup;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.matheusmarkies.spaghettibridge.main.MainFrameController;
import com.matheusmarkies.spaghettibridge.main.SpaghettiBridgeMain;

/**
 * FXML Controller class
 *
 * @author Matheus Markies
 */
public class ConfigMaterialController implements Initializable {
    
    @FXML
    private TextField tensilestrengthmaximumload_inputfield;
    
    @FXML
    private TextField elasticitymodulus_inputfield;
    
    @FXML
    private TextField diameter_inputfield;
    
    @FXML
    private TextField safetycoefficient_inputfield;
    
    @FXML
    private Button save_button;
    
    @FXML
    void saveButtonAction(ActionEvent event) {
        bridgeMain.bridgeManager.getMaterial().setTensileStrengthMaximumLoad(Double.parseDouble(tensilestrengthmaximumload_inputfield.getText()));
        bridgeMain.bridgeManager.getMaterial().setElasticityModulus(Double.parseDouble(elasticitymodulus_inputfield.getText()));
        bridgeMain.bridgeManager.getMaterial().setDiameter(Double.parseDouble(diameter_inputfield.getText()));
        bridgeMain.bridgeManager.getMaterial().setSafetyCoefficient(Double.parseDouble(safetycoefficient_inputfield.getText()));
    }

    com.matheusmarkies.spaghettibridge.main.SpaghettiBridgeMain bridgeMain;
    MainFrameController mainFrameController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tensilestrengthmaximumload_inputfield.setText(bridgeMain.bridgeManager.getMaterial().getTensileStrengthMaximumLoad() + "");
        elasticitymodulus_inputfield.setText(bridgeMain.bridgeManager.getMaterial().getElasticityModulus() + "");
        diameter_inputfield.setText(bridgeMain.bridgeManager.getMaterial().getDiameter() + "");
        safetycoefficient_inputfield.setText(bridgeMain.bridgeManager.getMaterial().getSafetyCoefficient() + "");
    }    
    
    public SpaghettiBridgeMain getBridgeMain() {
        return bridgeMain;
    }
    
    public void setBridgeMain(SpaghettiBridgeMain bridgeMain) {
        this.bridgeMain = bridgeMain;
    }
    
    public MainFrameController getMainFrameController() {
        return mainFrameController;
    }
    
    public void setMainFrameController(MainFrameController mainFrameController) {
        this.mainFrameController = mainFrameController;
    }
    
}
