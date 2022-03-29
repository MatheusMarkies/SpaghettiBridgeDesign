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
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import com.matheusmarkies.spaghettibridge.main.MainFrameController;
import com.matheusmarkies.spaghettibridge.main.SpaghettiBridgeMain;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 * FXML Controller class
 *
 * @author Matheus Markies
 */
public class CreateNodeController implements Initializable {

    @FXML
    private TextField node_name;

    @FXML
    private TextField node_coordinate_x_inputfield;

    @FXML
    private TextField node_coordinate_y_inputfield;

    Circle point = new Circle();

    @FXML
    void coordenateInputFieldAction(ActionEvent event) {

    }

    @FXML
    void createNodeButtonAction(ActionEvent event) {
        try {
            double X = Double.parseDouble(node_coordinate_x_inputfield.getText());
            double Y = Double.parseDouble(node_coordinate_y_inputfield.getText());

            String nodeName = node_name.getText().toUpperCase();

            com.matheusmarkies.spaghettibridge.objects.node.Node node = new com.matheusmarkies.spaghettibridge.objects.node.Node(
                    new Vector2D(X, Y),
                    nodeName,
                    spaghettiBridgeMain.bridgeManager.getNodes().size());

            if (nodeName != "" && spaghettiBridgeMain.bridgeManager.checkNodeNameVality(nodeName)) {
                spaghettiBridgeMain.bridgeManager.addNode(node);

                mainFrameController.removeObjectToCanvas(point);

                mainFrameController.getConsoleManager().printLog("[Criar No] No " + node.getNodeName() + " foi criado");
            } else if (!spaghettiBridgeMain.bridgeManager.checkNodeNameVality(nodeName))
                mainFrameController.getConsoleManager().printLog("[Criar No] Nome nao valido");

            mainFrameController.getShowBridge().showNodes();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    boolean isDrag = false;

    void changePointPosition() {
        if (!isDrag) {

            mainFrameController.removeObjectToCanvas(point);

            double X = 0;
            double Y = 0;

            try {
                X = Double.parseDouble(node_coordinate_x_inputfield.getText());
            } catch (Exception e) {
            }
            try {
                Y = Double.parseDouble(node_coordinate_y_inputfield.getText());
            } catch (Exception e) {
            }

            Vector2D nodeStartPosition = mainFrameController.getShowBridge().canvasTranslate(new Vector2D(X,Y));

            point.setCenterX(nodeStartPosition.x());
            point.setCenterY(nodeStartPosition.y());
            point.setRadius(5);

            mainFrameController.addObjectToCanvas(point);
        }
    }

    public void drag(MouseEvent event) {
        isDrag = true;
        Node n = (Node) event.getSource();

        Vector2D position = new Vector2D(
                n.getTranslateX() + event.getX() - mainFrameController.getCanvasPlane().getWidth() / 2,
                n.getTranslateY() + event.getY() - mainFrameController.getCanvasPlane().getHeight() / 2
        );

        n.setTranslateX(mainFrameController.getGrid().clampMouse(position).x());
        n.setTranslateY(mainFrameController.getGrid().clampMouse(position).y());

        node_coordinate_x_inputfield.setText(mainFrameController.getGrid().clampMouse(position).x() + "");
        node_coordinate_y_inputfield.setText(-mainFrameController.getGrid().clampMouse(position).y() + "");
        //node_coordinate_x_inputfield.setDisable(true);
        //node_coordinate_y_inputfield.setDisable(true);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        node_coordinate_x_inputfield.textProperty().addListener((observable, oldValue, newValue) -> {
            changePointPosition();
        });
        node_coordinate_y_inputfield.textProperty().addListener((observable, oldValue, newValue) -> {
            changePointPosition();
        });

        point = new Circle();
//        mainFrameController.setNodePointer(point);
        
        point.setOnMouseDragged(event -> drag(event));
        point.setOnDragExited(event -> {
            isDrag = false;
            //node_coordinate_x_inputfield.setDisable(false);
            //node_coordinate_y_inputfield.setDisable(false);
        });
    }

    MainFrameController mainFrameController;
    com.matheusmarkies.spaghettibridge.main.SpaghettiBridgeMain spaghettiBridgeMain;

    public MainFrameController getMainFrameController() {
        return mainFrameController;
    }

    public void setMainFrameController(MainFrameController mainFrameController) {
        this.mainFrameController = mainFrameController;
    }

    public SpaghettiBridgeMain getSpaghettiBridgeMain() {
        return spaghettiBridgeMain;
    }

    public void setSpaghettiBridgeMain(SpaghettiBridgeMain spaghettiBridgeMain) {
        this.spaghettiBridgeMain = spaghettiBridgeMain;
    }

}
