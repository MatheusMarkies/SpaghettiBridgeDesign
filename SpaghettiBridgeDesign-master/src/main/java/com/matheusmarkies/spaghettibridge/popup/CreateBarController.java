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
import javafx.scene.control.ChoiceBox;
import com.matheusmarkies.spaghettibridge.main.MainFrameController;
import com.matheusmarkies.spaghettibridge.main.SpaghettiBridgeMain;
import com.matheusmarkies.spaghettibridge.objects.bar.Bar;
import com.matheusmarkies.spaghettibridge.objects.node.Node;

/**
 * FXML Controller class
 *
 * @author Matheus Markies
 */
public class CreateBarController implements Initializable {

    @FXML
    private ChoiceBox<String> chooser_node_start;

    @FXML
    private ChoiceBox<String> chooser_node_end;

    @FXML
    private Button create_button;

    @FXML
    void createBarButtonAction(ActionEvent event) {
        if(chooser_node_start.getValue() != null && chooser_node_end.getValue() != null){
        if (!chooser_node_start.getValue().equals(chooser_node_end.getValue())) {
            Node nodeStart = null;
            Node nodeEnd = null;
            for (Node node : bridgeMain.bridgeManager.getNodes())
                if (node.getNodeName().equals(chooser_node_start.getValue()))
                    nodeStart = node;
                else if (node.getNodeName().equals(chooser_node_end.getValue()))
                    nodeEnd = node;

            Bar bar = new Bar(nodeStart, nodeEnd,nodeStart.getNodeName() + nodeEnd.getNodeName());
            
            if(bridgeMain.bridgeManager.checkBarVality(nodeStart, nodeEnd)){
                
            nodeStart.addConnectedBar(bar);
            nodeEnd.addConnectedBar(bar);
             mainFrameController.getConsoleManager().printLog("[Criar Barra] Barra "+bar.getBarName()+" foi criada");
                bridgeMain.bridgeManager.addBar(bar);
                
            }else{
                mainFrameController.getConsoleManager().printLog("[Criar Barra] Barra invalida");
            }
            
            mainFrameController.getShowBridge().showBars();
        }else
                mainFrameController.getConsoleManager().printLog("[Criar Barra] Barra invalida");
        }else
                mainFrameController.getConsoleManager().printLog("[Criar Barra] Barra invalida");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Node node : bridgeMain.bridgeManager.getNodes())
            chooser_node_start.getItems().add(node.getNodeName());
        for (Node node : bridgeMain.bridgeManager.getNodes())
            chooser_node_end.getItems().add(node.getNodeName());
    }

    MainFrameController mainFrameController;
    com.matheusmarkies.spaghettibridge.main.SpaghettiBridgeMain bridgeMain;

    public MainFrameController getMainFrameController() {
        return mainFrameController;
    }

    public void setMainFrameController(MainFrameController mainFrameController) {
        this.mainFrameController = mainFrameController;
    }

    public SpaghettiBridgeMain getBridgeMain() {
        return bridgeMain;
    }

    public void setBridgeMain(SpaghettiBridgeMain bridgeMain) {
        this.bridgeMain = bridgeMain;
    }

}
