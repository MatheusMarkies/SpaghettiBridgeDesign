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
public class SetTestLoadController implements Initializable {
    
    @FXML
    private TextField testload_inputfield;
    
    @FXML
    private Button save_button;
    
    @FXML
    void saveLoadButtonAction(ActionEvent event) {
        if (!testload_inputfield.getText().equals(""))
            bridgeMain.bridgeManager.setTestLoadForce(Double.parseDouble(testload_inputfield.getText()));
    }
    
    com.matheusmarkies.spaghettibridge.main.SpaghettiBridgeMain bridgeMain;
    MainFrameController mainFrameController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        testload_inputfield.setText(bridgeMain.bridgeManager.getTestLoadForce() + "");
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
