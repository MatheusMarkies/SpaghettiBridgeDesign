/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.manager;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Matheus Markies
 */
public class ConsoleManager {
   @FXML
    private AnchorPane consolePlane;
    ArrayList<Label> logs = new ArrayList<>();
   
    public ConsoleManager(AnchorPane consolePlane) {
        this.consolePlane = consolePlane;
    }

    public AnchorPane getConsolePlane() {
        return consolePlane;
    }

    public void setConsolePlane(AnchorPane consolePlane) {
        this.consolePlane = consolePlane;
    }
   
    public void printLog(String log){
        Label logLabel = new Label();
        
        logLabel.setText(log);
        
        logLabel.setLayoutX(6);
        logLabel.setLayoutY(6 + (logs.size()) * 14);
        logs.add(logLabel);
    
        consolePlane.getChildren().add(logLabel);
    }
    
    public void clear(){
        for(Label label : logs)
         consolePlane.getChildren().remove(label);
        logs = new ArrayList<>();
    }
   
}
