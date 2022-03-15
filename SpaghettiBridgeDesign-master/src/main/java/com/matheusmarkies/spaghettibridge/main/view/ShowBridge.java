/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.view;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import com.matheusmarkies.spaghettibridge.main.MainFrameController;
import com.matheusmarkies.spaghettibridge.main.SpaghettiBridgeMain;
import com.matheusmarkies.spaghettibridge.main.calculator.Angle;
import com.matheusmarkies.spaghettibridge.main.calculator.Trussing;
import com.matheusmarkies.spaghettibridge.main.calculator.Trussing.Truss;
import com.matheusmarkies.spaghettibridge.main.tables.BarTable;
import com.matheusmarkies.spaghettibridge.objects.bar.Bar;
import com.matheusmarkies.spaghettibridge.objects.fx.Arrow;
import com.matheusmarkies.spaghettibridge.objects.fx.Measure;
import com.matheusmarkies.spaghettibridge.objects.node.Node;
import com.matheusmarkies.spaghettibridge.objects.node.ReactionForces;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class ShowBridge {

    AnchorPane canvas_plane;
    SpaghettiBridgeMain bridgeMain;

    ArrayList<Circle> nodesInPlane = new ArrayList<>();
    ArrayList<Text> nodesLabelsInPlane = new ArrayList<>();

    ArrayList<Line> barsInPlane = new ArrayList<>();
    ArrayList<Text> barsLabelsInPlane = new ArrayList<>();

    ArrayList<Line> reationsInPlane = new ArrayList<>();
    ArrayList<Text> reationsLabelInPlane = new ArrayList<>();

    ArrayList<Circle> cartesianPointsInPlane = new ArrayList<>();
    ArrayList<Text> cartesianLabelsInPlane = new ArrayList<>();

    ArrayList<Arc> arcsInPlane = new ArrayList<>();

    ArrayList<Line> measuresInPlane = new ArrayList<>();
    ArrayList<Text> measuresValueLabelsInPlane = new ArrayList<>();

    MainFrameController mainFrameController;

    public enum BarView {
        Standard, Forces, Vectors, FreeBody
    }

    BarView barView = BarView.Standard;

    public ShowBridge(AnchorPane canvas_plane, SpaghettiBridgeMain bridgeMain, MainFrameController mainFrameController) {
        this.canvas_plane = canvas_plane;
        this.bridgeMain = bridgeMain;
        this.mainFrameController = mainFrameController;
        canvas_plane.setOnMouseClicked(event -> onMouseClickInNode(event));
    }

    public void showCartesianSystem(float pointsOffset, float labelOffset, Line cartesian_system_X, Line cartesian_system_Y) {
        removeCartesianSystem();

        int deltaX = (int) ((Math.abs(cartesian_system_X.getEndX()) + Math.abs(cartesian_system_X.getStartX())) / pointsOffset);

        for (int i = 0; i <= deltaX / 2; i++) {
            Circle circleP = new Circle();
            Circle circleN = new Circle();

            circleP.setCenterX(canvas_plane.getPrefWidth() / 2 - i * pointsOffset);
            circleP.setCenterY(canvas_plane.getPrefHeight() / 2);

            if (i > 0) {
                circleN.setCenterX(canvas_plane.getPrefWidth() / 2 + i * pointsOffset);
                circleN.setCenterY(canvas_plane.getPrefHeight() / 2);

                circleN.setRadius(1.0f);
                cartesianPointsInPlane.add(circleN);

                Text cartesianLabel = new Text();

                cartesianLabel.setText((i * pointsOffset)
                        + "");
                cartesianLabel.setX(canvas_plane.getPrefWidth() / 2 + i * pointsOffset);
                cartesianLabel.setY(canvas_plane.getPrefHeight() / 2 + labelOffset);

                cartesianLabel.setStyle("-fx-font: 8 System;");

                cartesianLabelsInPlane.add(cartesianLabel);

                canvas_plane.getChildren().add(cartesianLabel);
                canvas_plane.getChildren().add(circleN);
            }

            if (i != 0) {
                Text cartesianLabel = new Text();

                cartesianLabel.setText((i * -pointsOffset)
                        + "");
                cartesianLabel.setX(canvas_plane.getPrefWidth() / 2 - i * pointsOffset);
                cartesianLabel.setY(canvas_plane.getPrefHeight() / 2 + labelOffset);

                cartesianLabel.setStyle("-fx-font: 8 System;");
                canvas_plane.getChildren().add(cartesianLabel);
                cartesianLabelsInPlane.add(cartesianLabel);
            }
            circleP.setRadius(1.0f);
            cartesianPointsInPlane.add(circleP);

            canvas_plane.getChildren().add(circleP);

        }

        int deltaY = (int) ((Math.abs(cartesian_system_Y.getEndY()) + Math.abs(cartesian_system_Y.getStartY())) / pointsOffset);

        for (int i = 0; i <= deltaY / 2; i++) {
            Circle circleP = new Circle();
            Circle circleN = new Circle();

            circleP.setCenterX(canvas_plane.getPrefWidth() / 2);
            circleP.setCenterY(canvas_plane.getPrefHeight() / 2 - i * pointsOffset);
            if (i > 0) {
                circleN.setCenterX(canvas_plane.getPrefWidth() / 2);
                circleN.setCenterY(canvas_plane.getPrefHeight() / 2 + i * pointsOffset);
                circleN.setRadius(1.0f);
                cartesianPointsInPlane.add(circleN);

                Text cartesianLabel = new Text();

                cartesianLabel.setText((i * -pointsOffset)
                        + "");
                cartesianLabel.setX(canvas_plane.getPrefWidth() / 2 + labelOffset);
                cartesianLabel.setY(canvas_plane.getPrefHeight() / 2 + i * pointsOffset);

                cartesianLabel.setStyle("-fx-font: 8 System;");

                cartesianLabelsInPlane.add(cartesianLabel);

                canvas_plane.getChildren().add(cartesianLabel);
                canvas_plane.getChildren().add(circleN);
            }

            Text cartesianLabel = new Text();

            cartesianLabel.setText((i * pointsOffset)
                    + "");
            cartesianLabel.setX(canvas_plane.getPrefWidth() / 2 + labelOffset);
            cartesianLabel.setY(canvas_plane.getPrefHeight() / 2 - i * pointsOffset);

            cartesianLabel.setStyle("-fx-font: 8 System;");

            circleP.setRadius(1.0f);

            cartesianPointsInPlane.add(circleP);
            cartesianLabelsInPlane.add(cartesianLabel);

            canvas_plane.getChildren().add(circleP);
            canvas_plane.getChildren().add(cartesianLabel);
        }
    }

    public void showNodes() {
        removeNodes();

        for (Node entry : bridgeMain.bridgeManager.getNodes()) {
            Circle circle = new Circle();

            circle.setCenterX(entry.getPosition().x() + canvas_plane.getPrefWidth() / 2);
            circle.setCenterY(-entry.getPosition().y() + canvas_plane.getPrefHeight() / 2);

            Text nodeLabel = new Text();

            nodeLabel.setText(entry.getNodeName());
            nodeLabel.setX(entry.getPosition().x() + canvas_plane.getPrefWidth() / 2);
            nodeLabel.setY((-entry.getPosition().y() + canvas_plane.getPrefHeight() / 2) - 1f);

            circle.setRadius(4.0f);

            if (entry.isCargoReciver())
                circle.setFill(Color.DARKMAGENTA);
            else
                circle.setFill(Color.DARKBLUE);

            circle.setOnMouseDragged(event -> onMouseDragNode(event));
            circle.setOnMouseClicked(event -> onMouseClickInNode(event));

            nodesInPlane.add(circle);
            nodesLabelsInPlane.add(nodeLabel);

            canvas_plane.getChildren().add(circle);
            canvas_plane.getChildren().add(nodeLabel);
        }
        removeReations();
        showReactions();
    }

    public void onMouseClickInNode(MouseEvent event) {
        javafx.scene.Node n = (javafx.scene.Node) event.getSource();

        boolean change = false;

        Vector2D cursorPosition = new Vector2D(
                n.getTranslateX() + event.getX() - mainFrameController.getCanvasPlane().getPrefWidth() / 2, -(n.getTranslateY() + event.getY() - mainFrameController.getCanvasPlane().getPrefHeight() / 2)
        );

        if (bridgeMain.bridgeManager.getNodes().size() > 0) {
            Node nodeSelected = bridgeMain.bridgeManager.getNodes().get(0);
            for (Node node : bridgeMain.bridgeManager.getNodes())
                if (Vector2D.distance(nodeSelected.getPosition(), cursorPosition) > Vector2D.distance(node.getPosition(), cursorPosition))
                    nodeSelected = node;

            if (Vector2D.distance(nodeSelected.getPosition(), cursorPosition) < 10) {
                mainFrameController.getDown_status().setText(nodeSelected.getNodeName() + " (" + nodeSelected.getPosition().x() + "," + nodeSelected.getPosition().y() + ")");
                change = true;

            } else if (bridgeMain.bridgeManager.getBars().size() > 0) {

                Bar barSelected = bridgeMain.bridgeManager.getBars().get(0);

                for (Bar bar : bridgeMain.bridgeManager.getBars()) {
                    com.matheusmarkies.spaghettibridge.utilities.Line lineSelected = com.matheusmarkies.spaghettibridge.utilities.Line.getLineEquation(barSelected.getNodeStart().getPosition(), barSelected.getNodeEnd().getPosition());
                    com.matheusmarkies.spaghettibridge.utilities.Line lineCompare = com.matheusmarkies.spaghettibridge.utilities.Line.getLineEquation(bar.getNodeStart().getPosition(), bar.getNodeEnd().getPosition());

                    if (Vector2D.distanceVectorToLine(lineSelected, cursorPosition) > Vector2D.distanceVectorToLine(lineCompare, cursorPosition)) {
                        barSelected = bar;
                    }
                }

                com.matheusmarkies.spaghettibridge.utilities.Line lineSelected = com.matheusmarkies.spaghettibridge.utilities.Line.getLineEquation(barSelected.getNodeStart().getPosition(), barSelected.getNodeEnd().getPosition());

                double barSize = (Math.round(Vector2D.distance(barSelected.getNodeStart().getPosition(), barSelected.getNodeEnd().getPosition()) * 100.0) / 100.0);

                if (Vector2D.distanceVectorToLine(lineSelected, cursorPosition) < 10
                        && Math.max(barSelected.getNodeStart().getPosition().y(), barSelected.getNodeEnd().getPosition().y()) > cursorPosition.y()
                        && Math.min(barSelected.getNodeStart().getPosition().y(), barSelected.getNodeEnd().getPosition().y()) < cursorPosition.y()) {
                    mainFrameController.getDown_status().setText(barSelected.getBarName() + " (" + barSelected.getNodeStart().getPosition().x() + "," + barSelected.getNodeStart().getPosition().y() + ")"
                            + " (" + barSelected.getNodeEnd().getPosition().x() + "," + barSelected.getNodeEnd().getPosition().y() + ") | Tamanho: "
                            + barSize
                            + "cm | "
                            + "Numero de fios: " + barSelected.getNumberOfWires());
                    change = true;
                }
            }
        }
//        if(change == false)
//            mainFrameController.getDown_status().setText("");
    }

    public void onMouseDragNode(MouseEvent event) {
//        javafx.scene.Node n = (javafx.scene.Node) event.getSource();
//
//        Vector2D cursorPosition = new Vector2D(
//                event.getX() - mainFrameController.getCanvasPlane().getPrefWidth() / 2,
//                event.getY() - mainFrameController.getCanvasPlane().getPrefHeight() / 2
//        );
//        
//        Vector2D position = new Vector2D(
//                n.getTranslateX() + event.getX() - mainFrameController.getCanvasPlane().getPrefWidth() / 2,
//                n.getTranslateY() + event.getY() - mainFrameController.getCanvasPlane().getPrefHeight() / 2
//        );
//        
//        Node nodeSelected = bridgeMain.bridgeManager.getNodes().get(0);
//        for(Node node : bridgeMain.bridgeManager.getNodes())
//            if(Vector2D.distance(nodeSelected.getPosition(), cursorPosition) > Vector2D.distance(node.getPosition(), cursorPosition))
//            nodeSelected = node;
//
//        position = new Vector2D(mainFrameController.getGrid().clampMouse(position).x(), mainFrameController.getGrid().clampMouse(position).y());
//        
//        nodeSelected.setPosition(position);
//        
//        n.setTranslateX(position.x());
//        n.setTranslateY(position.y());
    }

    public void showBars() {
        removeBars();

        switch (barView) {
            case Standard:
                standardBarsView();
                break;
            case Forces:
                forcesBarsView();
                break;
            case Vectors:
                vectorsBarsView();
                break;
            case FreeBody:
                freeBodyDiagramBarsView();
                break;
        }

        mainFrameController.getBarTableView().setItems(getBarTableValues());
    }

        private ObservableList<BarTable> getBarTableValues() {
        ObservableList<BarTable> obsList = FXCollections.observableArrayList();
            for (Bar entry : bridgeMain.bridgeManager.getBars()) {
                BarTable barTable = new BarTable(entry.getBarName(), 
                        Math.round(
                                Vector2D.distance(entry.getNodeStart().getPosition(), entry.getNodeEnd().getPosition())
                                        *100)/100,
                        entry.getBarForce(),
                        entry.getNumberOfWires());
                
                obsList.add(barTable);
            }
        return obsList;
    }
    
    public void standardBarsView() {
        for (Bar entry : bridgeMain.bridgeManager.getBars()) {
            Line line = new Line();

            Vector2D nodeStartPosition = new Vector2D(entry.getNodeStart().getPosition().x() + canvas_plane.getPrefWidth() / 2,
                    -entry.getNodeStart().getPosition().y() + canvas_plane.getPrefHeight() / 2);

            Vector2D nodeEndPosition = new Vector2D(entry.getNodeEnd().getPosition().x() + canvas_plane.getPrefWidth() / 2,
                    -entry.getNodeEnd().getPosition().y() + canvas_plane.getPrefHeight() / 2);

            line.setStartX(nodeStartPosition.x());
            line.setStartY(nodeStartPosition.y());
            line.setEndX(nodeEndPosition.x());
            line.setEndY(nodeEndPosition.y());

            line.setStroke(Color.DARKSLATEBLUE);

            Text barLabel = new Text();

            barLabel.setText(entry.getBarName());

            double widthPosition = (Vector2D.getCenter(entry.getNodeStart().getPosition(), entry.getNodeEnd().getPosition()).x() + canvas_plane.getPrefWidth() / 2);

            if ((entry.getNodeEnd().getPosition().x() - entry.getNodeStart().getPosition().x()) < 0) {
                barLabel.setX(widthPosition - 10f);
                barLabel.setY((-Vector2D.getCenter(entry.getNodeStart().getPosition(), entry.getNodeEnd().getPosition()).y() + canvas_plane.getPrefHeight() / 2) + 0.5f);
            } else {
                barLabel.setX(widthPosition + 10f);
                barLabel.setY((-Vector2D.getCenter(entry.getNodeStart().getPosition(), entry.getNodeEnd().getPosition()).y() + canvas_plane.getPrefHeight() / 2) - 0.5f);
            }

            barsLabelsInPlane.add(barLabel);
            barsInPlane.add(line);

            canvas_plane.getChildren().add(line);
            canvas_plane.getChildren().add(barLabel);
            //showArcs();
        }

        //showArcs();
    }

    public void forcesBarsView() {
        for (Bar entry : bridgeMain.bridgeManager.getBars()) {
            Line line = new Line();

            Vector2D nodeStartPosition = new Vector2D(entry.getNodeStart().getPosition().x() + canvas_plane.getPrefWidth() / 2,
                    -entry.getNodeStart().getPosition().y() + canvas_plane.getPrefHeight() / 2);

            Vector2D nodeEndPosition = new Vector2D(entry.getNodeEnd().getPosition().x() + canvas_plane.getPrefWidth() / 2,
                    -entry.getNodeEnd().getPosition().y() + canvas_plane.getPrefHeight() / 2);

            line.setStartX(nodeStartPosition.x());
            line.setStartY(nodeStartPosition.y());
            line.setEndX(nodeEndPosition.x());
            line.setEndY(nodeEndPosition.y());

            line.setStroke(entry.getBarColor());

            Text barLabel = new Text();

            barLabel.setText(entry.getBarName() + " " + (int) entry.getBarForce() + "N");

            double widthPosition = (Vector2D.getCenter(entry.getNodeStart().getPosition(), entry.getNodeEnd().getPosition()).x() + canvas_plane.getPrefWidth() / 2);

            if ((entry.getNodeEnd().getPosition().x() - entry.getNodeStart().getPosition().x()) < 0) {
                barLabel.setX(widthPosition - 10f);
                barLabel.setY((-Vector2D.getCenter(entry.getNodeStart().getPosition(), entry.getNodeEnd().getPosition()).y() + canvas_plane.getPrefHeight() / 2) + 0.5f);
            } else {
                barLabel.setX(widthPosition + 10f);
                barLabel.setY((-Vector2D.getCenter(entry.getNodeStart().getPosition(), entry.getNodeEnd().getPosition()).y() + canvas_plane.getPrefHeight() / 2) - 0.5f);
            }

            barsLabelsInPlane.add(barLabel);
            barsInPlane.add(line);

            canvas_plane.getChildren().add(line);
            canvas_plane.getChildren().add(barLabel);
            //showArcs();
        }
    }

    ArrayList<Arrow> arrowInPlane = new ArrayList<>();

    public void vectorsBarsView() {
        for (Bar entry : bridgeMain.bridgeManager.getBars()) {

            Vector2D nodeStartPosition = new Vector2D(entry.getNodeStart().getPosition().x() + canvas_plane.getPrefWidth() / 2,
                    -entry.getNodeStart().getPosition().y() + canvas_plane.getPrefHeight() / 2);

            Vector2D nodeEndPosition = new Vector2D(entry.getNodeEnd().getPosition().x() + canvas_plane.getPrefWidth() / 2,
                    -entry.getNodeEnd().getPosition().y() + canvas_plane.getPrefHeight() / 2);

            Arrow arrowA = new Arrow(nodeStartPosition, nodeEndPosition, 20, Color.CORAL, true);
            Arrow arrowB = new Arrow(nodeEndPosition, nodeStartPosition, 20, Color.CORAL, true);

//            if (entry.getBarForce() < 0) {
//                arrowA = new Arrow(nodeStartPosition, nodeEndPosition, 20, Color.CORAL, true);
//                arrowB = new Arrow(nodeEndPosition, nodeStartPosition, 20, Color.CORAL, true);
//            } else if (entry.getBarForce() > 0) {
//                arrowA = new Arrow(nodeStartPosition, nodeEndPosition, 20, Color.DARKBLUE, false);
//                arrowB = new Arrow(nodeEndPosition, nodeStartPosition, 20, Color.DARKBLUE, false);
//            } else
//                arrowA = new Arrow(nodeEndPosition, nodeStartPosition, 20, Color.BLACK, false);
            arrowA.setArrowInPlane(canvas_plane);
            arrowB.setArrowInPlane(canvas_plane);

            Text barLabel = new Text();

            barLabel.setText((int) entry.getBarForce() + "N");

            double widthPosition = (Vector2D.getCenter(entry.getNodeStart().getPosition(), entry.getNodeEnd().getPosition()).x() + canvas_plane.getPrefWidth() / 2);

            if ((entry.getNodeEnd().getPosition().x() - entry.getNodeStart().getPosition().x()) < 0) {
                barLabel.setX(widthPosition - 10f);
                barLabel.setY((-Vector2D.getCenter(entry.getNodeStart().getPosition(), entry.getNodeEnd().getPosition()).y() + canvas_plane.getPrefHeight() / 2) + 0.5f);
            } else {
                barLabel.setX(widthPosition + 10f);
                barLabel.setY((-Vector2D.getCenter(entry.getNodeStart().getPosition(), entry.getNodeEnd().getPosition()).y() + canvas_plane.getPrefHeight() / 2) - 0.5f);
            }

            arrowInPlane.add(arrowA);
            arrowInPlane.add(arrowB);

            barsLabelsInPlane.add(barLabel);
            canvas_plane.getChildren().add(barLabel);
            //showArcs();
        }

        Vector2D nodeStartPosition = new Vector2D(bridgeMain.bridgeManager.getMiddleNode().getPosition().x() + canvas_plane.getPrefWidth() / 2,
                -bridgeMain.bridgeManager.getMiddleNode().getPosition().y() + canvas_plane.getPrefHeight() / 2);

        Text barLabel = new Text();

        barLabel.setText("100" + "N");

        barLabel.setX(Vector2D.getCenter(nodeStartPosition, Vector2D.add(nodeStartPosition, new Vector2D(0, 40))).x() + 10f + canvas_plane.getPrefWidth() / 2);
        barLabel.setY((-Vector2D.getCenter(nodeStartPosition, Vector2D.add(nodeStartPosition, new Vector2D(0, 40))).y() + canvas_plane.getPrefHeight() / 2) - 0.5f);

        Arrow arrowP = new Arrow(nodeStartPosition,
                Vector2D.add(nodeStartPosition, new Vector2D(0, 40)),
                20, Color.CRIMSON, false);

        arrowP.setArrowInPlane(canvas_plane);

        arrowInPlane.add(arrowP);
        barsLabelsInPlane.add(barLabel);
        canvas_plane.getChildren().add(barLabel);
    }

    public void freeBodyDiagramBarsView() {
        for (Bar entry : bridgeMain.bridgeManager.getBars()) {

            Vector2D nodeStartPosition = new Vector2D(entry.getNodeStart().getPosition().x() + canvas_plane.getPrefWidth() / 2,
                    -entry.getNodeStart().getPosition().y() + canvas_plane.getPrefHeight() / 2);

            Vector2D nodeEndPosition = new Vector2D(entry.getNodeEnd().getPosition().x() + canvas_plane.getPrefWidth() / 2,
                    -entry.getNodeEnd().getPosition().y() + canvas_plane.getPrefHeight() / 2);

            Arrow arrowA = new Arrow(new Vector2D(0, 0), new Vector2D(0, 0), 0, Color.CORAL, false);
            Arrow arrowB = new Arrow(new Vector2D(0, 0), new Vector2D(0, 0), 0, Color.CORAL, false);

            Vector2D dir = Vector2D.subtract(nodeEndPosition, nodeStartPosition);
            dir.normalize(dir);

            float size = 0.35f;
            
            if (entry.getBarForce() < 0) {
                Color color = Color.color(Math.random(), Math.random(), Math.random());
                arrowA = new Arrow(Vector2D.add(Vector2D.multiply(new Vector2D(-size, -size), dir), nodeEndPosition), nodeEndPosition, 20, color, false);
                arrowB = new Arrow(Vector2D.add(Vector2D.multiply(new Vector2D(size, size), dir), nodeStartPosition), nodeStartPosition, 20, color, false);
            } else {
                Color color = Color.color(Math.random(), Math.random(), Math.random());
                arrowA = new Arrow(nodeEndPosition, Vector2D.add(Vector2D.multiply(new Vector2D(-size, -size), dir), nodeEndPosition), 20, color, false);
                arrowB = new Arrow(nodeStartPosition, Vector2D.add(Vector2D.multiply(new Vector2D(size, size), dir), nodeStartPosition), 20, color, false);
            }

            float mag = 0.5f;

            //arrowA = new Arrow(nodeEndPosition, Vector2D.add(Vector2D.multiply(new Vector2D(mag, mag), dir), nodeEndPosition), 20, Color.DARKMAGENTA, false);
            //arrowB = new Arrow(nodeStartPosition, Vector2D.add(Vector2D.multiply(new Vector2D(-mag, -mag), dir), nodeStartPosition), 20, Color.DARKMAGENTA, false);
            arrowA.setArrowInPlane(canvas_plane);
            arrowB.setArrowInPlane(canvas_plane);

//            Text barLabel = new Text();
//
//            barLabel.setText((int) entry.getBarForce() + "N");
//
//            double widthPosition = (Vector2D.getCenter(entry.getNodeStart().getPosition(), entry.getNodeEnd().getPosition()).x() + canvas_plane.getPrefWidth() / 2);
//
//            if ((entry.getNodeEnd().getPosition().x() - entry.getNodeStart().getPosition().x()) < 0) {
//                barLabel.setX(widthPosition - 10f);
//                barLabel.setY((-Vector2D.getCenter(entry.getNodeStart().getPosition(), entry.getNodeEnd().getPosition()).y() + canvas_plane.getPrefHeight() / 2) + 0.5f);
//            } else {
//                barLabel.setX(widthPosition + 10f);
//                barLabel.setY((-Vector2D.getCenter(entry.getNodeStart().getPosition(), entry.getNodeEnd().getPosition()).y() + canvas_plane.getPrefHeight() / 2) - 0.5f);
//            }
            arrowInPlane.add(arrowA);
            arrowInPlane.add(arrowB);

//            barsLabelsInPlane.add(barLabel);
//            canvas_plane.getChildren().add(barLabel);
            //showArcs();
        }

        Vector2D nodeStartPosition = new Vector2D(bridgeMain.bridgeManager.getMiddleNode().getPosition().x() + canvas_plane.getPrefWidth() / 2,
                -bridgeMain.bridgeManager.getMiddleNode().getPosition().y() + canvas_plane.getPrefHeight() / 2);

        Text barLabel = new Text();

        barLabel.setText("100" + "N");

        barLabel.setX(Vector2D.getCenter(nodeStartPosition, Vector2D.add(nodeStartPosition, new Vector2D(0, 40))).x() + 10f + canvas_plane.getPrefWidth() / 2);
        barLabel.setY((-Vector2D.getCenter(nodeStartPosition, Vector2D.add(nodeStartPosition, new Vector2D(0, 40))).y() + canvas_plane.getPrefHeight() / 2) - 0.5f);

        Arrow arrowP = new Arrow(nodeStartPosition,
                Vector2D.add(nodeStartPosition, new Vector2D(0, 40)),
                20, Color.CRIMSON, false);

        arrowP.setArrowInPlane(canvas_plane);

        arrowInPlane.add(arrowP);
        barsLabelsInPlane.add(barLabel);
        canvas_plane.getChildren().add(barLabel);
    }

    public void showReactions() {
        removeReations();

        for (Node entry : bridgeMain.bridgeManager.getNodes()) {

            if (entry.getExternalForces().size() > 0) {
                for (ReactionForces reation : entry.getExternalForces()) {
                    Line line = new Line();

                    Vector2D nodeStartPosition = new Vector2D(entry.getPosition().x() + canvas_plane.getPrefWidth() / 2,
                            -entry.getPosition().y() + canvas_plane.getPrefHeight() / 2);

                    Vector2D nodeEndPosition = new Vector2D(entry.getPosition().x() + canvas_plane.getPrefWidth() / 2 + 30 * reation.getForceDirection().x(),
                            -entry.getPosition().y() + canvas_plane.getPrefHeight() / 2 - 30 * reation.getForceDirection().y());

                    line.setStartX(nodeStartPosition.x());
                    line.setStartY(nodeStartPosition.y());
                    line.setEndX(nodeEndPosition.x());
                    line.setEndY(nodeEndPosition.y());

                    Text reationLabel = new Text();

                    reationLabel.setText(reation.getReactionName());

                    reationLabel.setX((Vector2D.getCenter(nodeStartPosition, nodeEndPosition).x()));
                    reationLabel.setY((Vector2D.getCenter(nodeStartPosition, nodeEndPosition).y()) + 0.5f);

                    line.setStroke(Color.CRIMSON);

                    reationsInPlane.add(line);
                    reationsLabelInPlane.add(reationLabel);

                    canvas_plane.getChildren().add(line);
                    canvas_plane.getChildren().add(reationLabel);
                }
            }

        }
    }

    public void showArcs() {
        removeArcs();

        ArrayList<Truss> nodeTruss = new ArrayList<>();

        for (Node node : bridgeMain.bridgeManager.getNodes()) {
            ArrayList<Truss> nodeTrussFinder = Trussing.TrussFinder(node);
            for (Truss truss : nodeTrussFinder)
                Truss.addTrussInList(nodeTruss, truss);
        }

        for (Truss truss : nodeTruss) {
            setTrussArc(truss.getNodeA(), truss.getNodeB(), truss.getNodeC());
            setTrussArc(truss.getNodeB(), truss.getNodeA(), truss.getNodeC());
            setTrussArc(truss.getNodeC(), truss.getNodeA(), truss.getNodeB());
        }
    }

    void setTrussArc(Node center, Node TargetA, Node TargetB) {
        Arc arc = new Arc();

        Vector2D nodeStartPosition = new Vector2D(center.getPosition().x() + canvas_plane.getPrefWidth() / 2,
                -center.getPosition().y() + canvas_plane.getPrefHeight() / 2);

        Vector2D dirA = Vector2D.subtract(TargetA.getPosition(), center.getPosition());
        Vector2D dirB = Vector2D.subtract(TargetB.getPosition(), center.getPosition());

        double angleLength = Angle.getTrussAngles(center, TargetA, TargetB);
        double angle = Angle.getTrussStartAngle(center, TargetA, TargetB);

        arc.setCenterX(nodeStartPosition.x());
        arc.setCenterY(nodeStartPosition.y());

        arc.setRadiusX(10.0f);
        arc.setRadiusY(10.0f);

        arc.setStartAngle(angle);

        arc.setType(ArcType.ROUND);
        arc.setLength(angleLength);

        arc.setStroke(Color.DIMGRAY);
        arc.setFill(Color.DIMGRAY);

        canvas_plane.getChildren().add(arc);
        arcsInPlane.add(arc);
    }

    public void showMeasure() {
        removeMeasures();

        for (Measure measure : bridgeMain.bridgeManager.getMeasures()) {
            Line line = new Line();

            line.setStartX(measure.getStartPosition().x());
            line.setStartY(measure.getStartPosition().y());
            line.setEndX(measure.getEndPosition().x());
            line.setEndY(measure.getEndPosition().y());

            Text measureValue = new Text();

            measureValue.setText((Math.round(Vector2D.distance(measure.getStartPosition(), measure.getEndPosition()) * 1000) / 1000) + "cm");

            measureValue.setX((Vector2D.getCenter(measure.getStartPosition(), measure.getEndPosition()).x()));
            measureValue.setY((Vector2D.getCenter(measure.getStartPosition(), measure.getEndPosition()).y()) + 0.5f);

            canvas_plane.getChildren().add(measureValue);
            canvas_plane.getChildren().add(line);
            measuresInPlane.add(line);
            measuresValueLabelsInPlane.add(measureValue);
        }
    }

    public void removeNodes() {
        for (Circle circle : nodesInPlane)
            canvas_plane.getChildren().remove(circle);
        for (Text text : nodesLabelsInPlane)
            canvas_plane.getChildren().remove(text);

        nodesInPlane = new ArrayList<>();
        nodesLabelsInPlane = new ArrayList<>();
    }

    public void removeReations() {
        for (Line line : reationsInPlane)
            canvas_plane.getChildren().remove(line);
        for (Text text : reationsLabelInPlane)
            canvas_plane.getChildren().remove(text);

        reationsInPlane = new ArrayList<>();
        reationsLabelInPlane = new ArrayList<>();
    }

    public void removeBars() {
        for (Line line : barsInPlane)
            canvas_plane.getChildren().remove(line);
        for (Text text : barsLabelsInPlane)
            canvas_plane.getChildren().remove(text);
        for (Arrow arrow : arrowInPlane)
            arrow.removeArrow(canvas_plane);

        barsInPlane = new ArrayList<>();
        barsLabelsInPlane = new ArrayList<>();
    }

    public void removeCartesianSystem() {
        for (Circle circle : cartesianPointsInPlane)
            canvas_plane.getChildren().remove(circle);
        for (Text text : cartesianLabelsInPlane)
            canvas_plane.getChildren().remove(text);

        cartesianPointsInPlane = new ArrayList<>();
        cartesianLabelsInPlane = new ArrayList<>();
    }

    public void removeArcs() {
        for (Arc arc : arcsInPlane)
            canvas_plane.getChildren().remove(arc);

        arcsInPlane = new ArrayList<>();
    }

    public void removeMeasures() {
        for (Line line : measuresInPlane)
            canvas_plane.getChildren().remove(line);
        for (Text text : measuresValueLabelsInPlane)
            canvas_plane.getChildren().remove(text);

        measuresInPlane = new ArrayList<>();
    }

    public BarView getBarView() {
        return barView;
    }

    public void setBarView(BarView barView) {
        this.barView = barView;
        showBars();
    }

}
