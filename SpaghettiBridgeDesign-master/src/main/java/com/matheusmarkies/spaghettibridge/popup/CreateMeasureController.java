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
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import com.matheusmarkies.spaghettibridge.main.MainFrameController;
import com.matheusmarkies.spaghettibridge.main.SpaghettiBridgeMain;
import com.matheusmarkies.spaghettibridge.objects.fx.Measure;
import com.matheusmarkies.spaghettibridge.objects.node.Node;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 * FXML Controller class
 *
 * @author Matheus Markies
 */
public class CreateMeasureController implements Initializable {

    @FXML
    private ChoiceBox<String> measure_start_node;

    @FXML
    private ChoiceBox<String> measure_end_node;

    @FXML
    private TextField measure_offset_inputfield;

    @FXML
    private Button create_measure_button;

    @FXML
    void createMeasureButtonAction(ActionEvent event) {

        if (measure_start_node.getValue() != null && measure_end_node.getValue() != null) {
            if (!measure_start_node.getValue().equals(measure_end_node.getValue())) {
                Node nodeStart = null;
                Node nodeEnd = null;
                for (Node node : bridgeMain.bridgeManager.getNodes())
                    if (node.getNodeName().equals(measure_start_node.getValue()))
                        nodeStart = node;
                    else if (node.getNodeName().equals(measure_end_node.getValue()))
                        nodeEnd = node;

                Vector2D nodeStartPosition = new Vector2D(
                        nodeStart.getPosition().x(),
                        nodeStart.getPosition().y()
                );

                Vector2D nodeEndPosition = new Vector2D(
                        nodeEnd.getPosition().x(),
                        nodeEnd.getPosition().y()
                );

                com.matheusmarkies.spaghettibridge.objects.fx.Measure measure = new Measure(nodeStartPosition,
                        nodeEndPosition,
                        Double.parseDouble(measure_offset_inputfield.getText()));

                mainFrameController.removeObjectToCanvas(preview);

                bridgeMain.bridgeManager.getMeasures().add(measure);
                mainFrameController.getShowBridge().showMeasure();
            }
        }
    }

    com.matheusmarkies.spaghettibridge.main.SpaghettiBridgeMain bridgeMain;
    com.matheusmarkies.spaghettibridge.main.MainFrameController mainFrameController;

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
    Line preview = new Line();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Node node : bridgeMain.bridgeManager.getNodes())
            measure_start_node.getItems().add(node.getNodeName());
        for (Node node : bridgeMain.bridgeManager.getNodes())
            measure_end_node.getItems().add(node.getNodeName());

        measure_offset_inputfield.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                createPreview();
            }catch (Exception e){}
        });
    }

    public void createPreview() {
        mainFrameController.removeObjectToCanvas(preview);

        if (measure_start_node.getValue() != null && measure_end_node.getValue() != null) {
            if (!measure_start_node.getValue().equals(measure_end_node.getValue())) {
                Node nodeStart = null;
                Node nodeEnd = null;

                for (Node node : bridgeMain.bridgeManager.getNodes())
                    if (node.getNodeName().equals(measure_start_node.getValue()))
                        nodeStart = node;
                    else if (node.getNodeName().equals(measure_end_node.getValue()))
                        nodeEnd = node;

                Vector2D nodeStartPosition = new Vector2D(
                        nodeStart.getPosition().x(),
                        nodeStart.getPosition().y());

                Vector2D nodeEndPosition = new Vector2D(
                        nodeEnd.getPosition().x(),
                        nodeEnd.getPosition().y());

                nodeStartPosition = mainFrameController.getShowBridge().canvasTranslate(nodeStartPosition);
                nodeEndPosition = mainFrameController.getShowBridge().canvasTranslate(nodeEndPosition);

                com.matheusmarkies.spaghettibridge.objects.fx.Measure measure = new Measure(nodeStartPosition,
                        nodeEndPosition,
                        -Double.parseDouble(measure_offset_inputfield.getText())
                                * bridgeMain.bridgeManager.getZoomCoefficient());

                preview.setStartX(measure.getStartPosition().x());
                preview.setStartY(measure.getStartPosition().y());
                preview.setEndX(measure.getEndPosition().x());
                preview.setEndY(measure.getEndPosition().y());

                mainFrameController.addObjectToCanvas(preview);
            }
        }
    }

    public Line getPreview() {
        return preview;
    }

}
