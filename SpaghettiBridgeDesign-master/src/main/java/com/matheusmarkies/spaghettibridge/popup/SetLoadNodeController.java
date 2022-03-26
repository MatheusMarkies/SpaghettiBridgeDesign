package com.matheusmarkies.spaghettibridge.popup;

import com.matheusmarkies.spaghettibridge.main.MainFrameController;
import com.matheusmarkies.spaghettibridge.main.SpaghettiBridgeMain;
import com.matheusmarkies.spaghettibridge.objects.node.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SetLoadNodeController implements Initializable {

    @FXML
    private Button select_node;

    @FXML
    private ChoiceBox<String> select_node_choice;

    @FXML
    void selectNodeButtonAction(ActionEvent event) {
        if(select_node_choice.getValue() != null){
            for (Node node : bridgeMain.bridgeManager.getNodes())
                if (node.getNodeName().equals(select_node_choice.getValue()))
                    node.setIsCargoReciver(true);
            else
                    node.setIsCargoReciver(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Node node : bridgeMain.bridgeManager.getNodes())
            select_node_choice.getItems().add(node.getNodeName());
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
