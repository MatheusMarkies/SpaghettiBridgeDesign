/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.view;

import java.util.ArrayList;

import com.matheusmarkies.spaghettibridge.main.tables.AngleTable;
import com.matheusmarkies.spaghettibridge.utilities.BarColorController;
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
public class ShowBridge{

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
        Standard, Forces, Vectors, FreeBody, ExplodedView
    }

    BarView barView = BarView.Standard;

    public ShowBridge(AnchorPane canvas_plane, SpaghettiBridgeMain bridgeMain, MainFrameController mainFrameController) {
        this.canvas_plane = canvas_plane;
        this.bridgeMain = bridgeMain;
        this.mainFrameController = mainFrameController;
        canvas_plane.setOnMouseClicked(this::onMouseClickInNode);
    }

    public void showCartesianSystem(float pointsOffset, float labelOffset, Line cartesian_system_X, Line cartesian_system_Y) {
        removeCartesianSystem();

        int deltaX = (int) ((Math.abs(cartesian_system_X.getEndX()) + Math.abs(cartesian_system_X.getStartX())) / pointsOffset);

        for (int i = 0; i <= deltaX / 2; i++) {
            Circle circleP = new Circle();
            Circle circleN = new Circle();

            circleP.setCenterX(canvas_plane.getWidth() / 2 - i * pointsOffset);
            circleP.setCenterY(canvas_plane.getHeight() / 2);

            if (i > 0) {
                circleN.setCenterX(canvas_plane.getWidth() / 2 + i * pointsOffset);
                circleN.setCenterY(canvas_plane.getHeight() / 2);

                circleN.setRadius(1.0f);
                cartesianPointsInPlane.add(circleN);

                Text cartesianLabel = new Text();

                cartesianLabel.setText((i * pointsOffset)
                        + "");
                cartesianLabel.setX(canvas_plane.getWidth() / 2 + i * pointsOffset);
                cartesianLabel.setY(canvas_plane.getHeight() / 2 + labelOffset);

                cartesianLabel.setStyle("-fx-font: 8 System;");

                cartesianLabelsInPlane.add(cartesianLabel);

                canvas_plane.getChildren().add(cartesianLabel);
                canvas_plane.getChildren().add(circleN);
            }

            if (i != 0) {
                Text cartesianLabel = new Text();

                cartesianLabel.setText((i * -pointsOffset)
                        + "");
                cartesianLabel.setX(canvas_plane.getWidth() / 2 - i * pointsOffset);
                cartesianLabel.setY(canvas_plane.getHeight() / 2 + labelOffset);

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

            circleP.setCenterX(canvas_plane.getWidth() / 2);
            circleP.setCenterY(canvas_plane.getHeight() / 2 - i * pointsOffset);
            if (i > 0) {
                circleN.setCenterX(canvas_plane.getWidth() / 2);
                circleN.setCenterY(canvas_plane.getHeight() / 2 + i * pointsOffset);
                circleN.setRadius(1.0f);
                cartesianPointsInPlane.add(circleN);

                Text cartesianLabel = new Text();

                cartesianLabel.setText((i * -pointsOffset)
                        + "");
                cartesianLabel.setX(canvas_plane.getWidth() / 2 + labelOffset);
                cartesianLabel.setY(canvas_plane.getHeight() / 2 + i * pointsOffset);

                cartesianLabel.setStyle("-fx-font: 8 System;");

                cartesianLabelsInPlane.add(cartesianLabel);

                canvas_plane.getChildren().add(cartesianLabel);
                canvas_plane.getChildren().add(circleN);
            }

            Text cartesianLabel = new Text();

            cartesianLabel.setText((i * pointsOffset)
                    + "");
            cartesianLabel.setX(canvas_plane.getWidth() / 2 + labelOffset);
            cartesianLabel.setY(canvas_plane.getHeight() / 2 - i * pointsOffset);

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
        updateAngleTable();

        for (Node entry : SpaghettiBridgeMain.bridgeManager.getNodes()) {
            Circle circle = new Circle();

            Vector2D circleCenter = canvasTranslate(entry.getPosition());

            circle.setCenterX(circleCenter.x());
            circle.setCenterY(circleCenter.y());

            Text nodeLabel = new Text();

            nodeLabel.setText(entry.getNodeName());
            nodeLabel.setX(circleCenter.x());
            nodeLabel.setY(circleCenter.y() - 1f * SpaghettiBridgeMain.bridgeManager.getZoomCoefficient());

            circle.setRadius(4.0f);

            if (entry.isCargoReciver())
                circle.setFill(Color.DARKMAGENTA);
            else
                circle.setFill(Color.DARKBLUE);

            circle.setOnMouseDragged(this::onMouseDragNode);
            circle.setOnMouseClicked(this::onMouseClickInNode);

            nodesInPlane.add(circle);
            nodesLabelsInPlane.add(nodeLabel);

            canvas_plane.getChildren().add(circle);
            canvas_plane.getChildren().add(nodeLabel);
        }
    }

    public void onMouseClickInNode(MouseEvent event) {
        javafx.scene.Node n = (javafx.scene.Node) event.getSource();

        boolean change = false;

        Vector2D cursorPosition = new Vector2D(
                n.getTranslateX() + event.getX() - mainFrameController.getCanvasPlane().getWidth() / 2, -(n.getTranslateY() + event.getY() - mainFrameController.getCanvasPlane().getHeight() / 2)
        );

        if (SpaghettiBridgeMain.bridgeManager.getNodes().size() > 0) {
            Node nodeSelected = SpaghettiBridgeMain.bridgeManager.getNodes().get(0);
            for (Node node : SpaghettiBridgeMain.bridgeManager.getNodes())
                if (Vector2D.distance(nodeSelected.getPosition(), cursorPosition) > Vector2D.distance(node.getPosition(), cursorPosition))
                    nodeSelected = node;

            if (Vector2D.distance(nodeSelected.getPosition(), cursorPosition) < 10) {
                mainFrameController.getDown_status().setText(nodeSelected.getNodeName() + " (" + nodeSelected.getPosition().x() + "," + nodeSelected.getPosition().y() + ")");

            } else if (SpaghettiBridgeMain.bridgeManager.getBars().size() > 0) {

                Bar barSelected = SpaghettiBridgeMain.bridgeManager.getBars().get(0);

                for (Bar bar : SpaghettiBridgeMain.bridgeManager.getBars()) {
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
//                event.getX() - mainFrameController.getCanvasPlane().getWidth() / 2,
//                event.getY() - mainFrameController.getCanvasPlane().getHeight() / 2
//        );
//        
//        Vector2D position = new Vector2D(
//                n.getTranslateX() + event.getX() - mainFrameController.getCanvasPlane().getWidth() / 2,
//                n.getTranslateY() + event.getY() - mainFrameController.getCanvasPlane().getHeight() / 2
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
        setBarColor();
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
            case ExplodedView:
                explodedViewBarsView();
                break;
        }

        updateAngleTable();
        mainFrameController.getBarTableView().setItems(getBarTableValues());
    }

        private ObservableList<BarTable> getBarTableValues() {
        ObservableList<BarTable> obsList = FXCollections.observableArrayList();
            for (Bar entry : SpaghettiBridgeMain.bridgeManager.getBars()) {
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
        removeReations();
        showReactions();
        for (Bar entry : SpaghettiBridgeMain.bridgeManager.getBars()) {
            Line line = new Line();

            Vector2D nodeStartPositionCanvas = canvasTranslate(entry.getNodeStart().getPosition());
            Vector2D nodeEndPositionCanvas = canvasTranslate(entry.getNodeEnd().getPosition());

            line.setStartX(nodeStartPositionCanvas.x());
            line.setStartY(nodeStartPositionCanvas.y());
            line.setEndX(nodeEndPositionCanvas.x());
            line.setEndY(nodeEndPositionCanvas.y());

            line.setStroke(entry.getBarColor());

            barsLabelsInPlane.add(setTextOrthogonal(nodeStartPositionCanvas,
                    nodeEndPositionCanvas,
                    entry.getBarName()));

            barsInPlane.add(line);
            canvas_plane.getChildren().add(line);
            //showArcs();
        }

        showArcs();
    }

    public void forcesBarsView() {
        removeReations();
        removeArcs();
        showReactions();
        for (Bar entry : SpaghettiBridgeMain.bridgeManager.getBars()) {
            Line line = new Line();

            Vector2D nodeStartPositionCanvas = canvasTranslate(entry.getNodeStart().getPosition());
            Vector2D nodeEndPositionCanvas = canvasTranslate(entry.getNodeEnd().getPosition());

            line.setStartX(nodeStartPositionCanvas.x());
            line.setStartY(nodeStartPositionCanvas.y());
            line.setEndX(nodeEndPositionCanvas.x());
            line.setEndY(nodeEndPositionCanvas.y());

            line.setStroke(entry.getBarColor());

            barsLabelsInPlane.add(setTextOrthogonal(nodeStartPositionCanvas,
                    nodeEndPositionCanvas,
                    entry.getBarName() + " " + (int) entry.getBarForce() + "N"));

            barsInPlane.add(line);

            canvas_plane.getChildren().add(line);
            //showArcs();
        }
    }

    ArrayList<Arrow> arrowInPlane = new ArrayList<>();

    public void vectorsBarsView() {
        removeReations();
        removeArcs();
        showReactions();
        for (Bar entry : SpaghettiBridgeMain.bridgeManager.getBars()) {

            Vector2D nodeStartPosition = canvasTranslate(entry.getNodeStart().getPosition());
            Vector2D nodeEndPosition = canvasTranslate(entry.getNodeEnd().getPosition());

            Arrow arrowA;
            Arrow arrowB = new Arrow(nodeEndPosition, nodeStartPosition, 20, entry.getBarColor(), true);

            if (entry.getBarForce() < 0) {
                arrowA = new Arrow(nodeStartPosition, nodeEndPosition, 20, entry.getBarColor(), true);
                arrowB = new Arrow(nodeEndPosition, nodeStartPosition, 20, entry.getBarColor(), true);
            } else if (entry.getBarForce() > 0) {
                arrowA = new Arrow(nodeStartPosition, nodeEndPosition, 20,entry.getBarColor(), false);
                arrowB = new Arrow(nodeEndPosition, nodeStartPosition, 20, entry.getBarColor(), false);
            } else
                arrowA = new Arrow(nodeEndPosition, nodeStartPosition, 20, entry.getBarColor(), false);

            arrowA.setArrowInPlane(canvas_plane);
            arrowB.setArrowInPlane(canvas_plane);

            barsLabelsInPlane.add(setTextOrthogonal(nodeStartPosition,
                    nodeEndPosition,
                    (int) entry.getBarForce() + "N"));

            arrowInPlane.add(arrowA);
            arrowInPlane.add(arrowB);
            //showArcs();
        }
    }

    public void freeBodyDiagramBarsView() {
        removeReations();
        removeArcs();
        showReactionsVectors();
        for (Bar entry : SpaghettiBridgeMain.bridgeManager.getBars()) {

            Vector2D nodeStartPosition = canvasTranslate(entry.getNodeStart().getPosition());
            Vector2D nodeEndPosition = canvasTranslate(entry.getNodeEnd().getPosition());

            Arrow arrowA;
            Arrow arrowB;

            Vector2D dir = Vector2D.subtract(nodeEndPosition, nodeStartPosition);
            Vector2D.normalize(dir);

            float size = 0.35f;
            
            if (entry.getBarForce() < 0) {
                Color color = Color.color(Math.random(), Math.random(), Math.random());
                arrowA = new Arrow(Vector2D.add(Vector2D.multiply(new Vector2D(-size, -size), dir), nodeEndPosition), nodeEndPosition, 20, entry.getBarColor(), false);
                arrowB = new Arrow(Vector2D.add(Vector2D.multiply(new Vector2D(size, size), dir), nodeStartPosition), nodeStartPosition, 20, entry.getBarColor(), false);
            } else {
                Color color = Color.color(Math.random(), Math.random(), Math.random());
                arrowA = new Arrow(nodeEndPosition, Vector2D.add(Vector2D.multiply(new Vector2D(-size, -size), dir), nodeEndPosition), 20, entry.getBarColor(), false);
                arrowB = new Arrow(nodeStartPosition, Vector2D.add(Vector2D.multiply(new Vector2D(size, size), dir), nodeStartPosition), 20, entry.getBarColor(), false);
            }

            float mag = 0.5f;

            arrowA.setArrowInPlane(canvas_plane);
            arrowB.setArrowInPlane(canvas_plane);

            arrowInPlane.add(arrowA);
            arrowInPlane.add(arrowB);

            //showArcs();
        }
    }

    public void explodedViewBarsView() {
        removeReations();
        showReactionsVectors();
        removeArcs();
        for (Bar entry : SpaghettiBridgeMain.bridgeManager.getBars()) {
            Line line = new Line();

            Vector2D nodeStartPositionCanvas = canvasTranslate(entry.getNodeStart().getPosition());
            Vector2D nodeEndPositionCanvas = canvasTranslate(entry.getNodeEnd().getPosition());

            Vector2D lineVector = Vector2D.normalize(Vector2D.subtract(nodeEndPositionCanvas, nodeStartPositionCanvas));

            Vector2D barCenter = Vector2D.getCenter(nodeStartPositionCanvas, nodeEndPositionCanvas);

            float lineSize = (float)Vector2D.distance(nodeStartPositionCanvas,nodeEndPositionCanvas);

            line.setStartX(Vector2D.add(barCenter,
                    Vector2D.multiply(lineVector, lineSize * 0.25f)).x());
            line.setStartY(Vector2D.add(barCenter,
                    Vector2D.multiply(lineVector, lineSize * 0.25f)).y());
            line.setEndX(Vector2D.add(barCenter,
                    Vector2D.multiply(lineVector, -lineSize * 0.25f)).x());
            line.setEndY(Vector2D.add(barCenter,
                    Vector2D.multiply(lineVector, -lineSize * 0.25f)).y());

            line.setStroke(entry.getBarColor());
            line.setStrokeWidth(5);

            showExplodedForceVectors(nodeStartPositionCanvas,
                    nodeEndPositionCanvas, entry, false);
            showExplodedForceVectors(Vector2D.add(barCenter,
                            Vector2D.multiply(lineVector, lineSize * 0.35f
                                    )),
                    Vector2D.add(barCenter,
                            Vector2D.multiply(lineVector, -lineSize * 0.35f
                            )), entry , true);

            if(entry.getBarForce() > 0)
            barsLabelsInPlane.add(setTextOrthogonal(nodeStartPositionCanvas,
                    nodeEndPositionCanvas,
                    entry.getBarName() + " " + (int) entry.getBarForce() + "N" + " Tensao"));
            else
                barsLabelsInPlane.add(setTextOrthogonal(nodeStartPositionCanvas,
                    nodeEndPositionCanvas,
                    entry.getBarName() + " " + (int) entry.getBarForce() + "N" + " Compressao"));

            barsInPlane.add(line);

            canvas_plane.getChildren().add(line);
            //showArcs();
        }
    }

    void showExplodedForceVectors(Vector2D nodeStartPosition, Vector2D nodeEndPosition, Bar entry, boolean invert){
        Arrow arrowA;
        Arrow arrowB;

        Vector2D dir = Vector2D.subtract(nodeEndPosition, nodeStartPosition);
        Vector2D.normalize(dir);

        float size = 0.1f;

        if(!invert)
        if (entry.getBarForce() < 0) {
            arrowA = new Arrow(Vector2D.add(Vector2D.multiply(new Vector2D(-size, -size), dir), nodeEndPosition),
                    nodeEndPosition, 20, entry.getBarColor(), false);
            arrowB = new Arrow(Vector2D.add(Vector2D.multiply(new Vector2D(size, size), dir), nodeStartPosition),
                    nodeStartPosition, 20, entry.getBarColor(), false);
        } else {
            arrowA = new Arrow(nodeEndPosition, Vector2D.add(Vector2D.multiply(new Vector2D(-size, -size), dir), nodeEndPosition), 20, entry.getBarColor(), false);
            arrowB = new Arrow(nodeStartPosition, Vector2D.add(Vector2D.multiply(new Vector2D(size, size), dir), nodeStartPosition), 20, entry.getBarColor(), false);
        }
        else {
            if (entry.getBarForce() > 0) {
                arrowA = new Arrow(Vector2D.add(Vector2D.multiply(new Vector2D(-size, -size), dir), nodeEndPosition),
                        nodeEndPosition, 20, entry.getBarColor(), false);
                arrowB = new Arrow(Vector2D.add(Vector2D.multiply(new Vector2D(size, size), dir), nodeStartPosition),
                        nodeStartPosition, 20, entry.getBarColor(), false);
            } else {
                arrowA = new Arrow(nodeEndPosition, Vector2D.add(Vector2D.multiply(new Vector2D(-size, -size), dir), nodeEndPosition), 20, entry.getBarColor(), false);
                arrowB = new Arrow(nodeStartPosition, Vector2D.add(Vector2D.multiply(new Vector2D(size, size), dir), nodeStartPosition), 20, entry.getBarColor(), false);
            }
        }

        float mag = 0.5f;

        arrowA.setArrowInPlane(canvas_plane, 1);
        arrowB.setArrowInPlane(canvas_plane, 1);

        arrowInPlane.add(arrowA);
        arrowInPlane.add(arrowB);
    }

    public void showReactionsVectors() {
        removeReations();
float arraowSize = Math.min(20f
        * (float) SpaghettiBridgeMain.bridgeManager.getZoomCoefficient(),100f);
        for (Node entry : SpaghettiBridgeMain.bridgeManager.getNodes()) {

            if (entry.getExternalForces().size() > 0) {
                for (ReactionForces reation : entry.getExternalForces()) {
                    Vector2D nodeStartPosition = canvasTranslate(entry.getPosition());
                    Vector2D nodeEndPosition = canvasTranslate(entry.getPosition());

                    if (reation.getForceDirection().y() != 0) {
                        nodeEndPosition = Vector2D.add(nodeEndPosition, Vector2D.multiply(reation.getForceDirection(), new Vector2D(arraowSize, -arraowSize)));

                        Arrow arrowA = new Arrow(nodeStartPosition,
                                nodeEndPosition, 20, Color.CRIMSON, false);

                        reationsLabelInPlane.add(setTextOrthogonal(nodeStartPosition,
                                nodeEndPosition,
                                reation.getReactionName()));

                        arrowA.setArrowInPlane(canvas_plane);
                        arrowInPlane.add(arrowA);
                    }
                }
            }
            if (entry.isCargoReciver()) {
                Vector2D nodeStartPosition = canvasTranslate(entry.getPosition());
                Vector2D nodeEndPosition = canvasTranslate(entry.getPosition());
                Arrow arrowA = new Arrow(nodeStartPosition,
                        Vector2D.add(nodeStartPosition, new Vector2D(0, arraowSize)), 20, Color.CRIMSON, false);
                arrowA.setArrowInPlane(canvas_plane);
                arrowInPlane.add(arrowA);
            }
        }
    }

    public void showReactions() {
        removeReations();

        for (Node entry : SpaghettiBridgeMain.bridgeManager.getNodes()) {

            if (entry.getExternalForces().size() > 0) {
                for (ReactionForces reation : entry.getExternalForces()) {
                    Line line = new Line();

                    Vector2D nodeStartPosition = canvasTranslate(entry.getPosition());
                    Vector2D nodeEndPosition = canvasTranslate(entry.getPosition());

                    nodeEndPosition = Vector2D.add(nodeEndPosition,Vector2D.multiply(reation.getForceDirection(), new Vector2D(25f,-25f)));

                    line.setStartX(nodeStartPosition.x());
                    line.setStartY(nodeStartPosition.y());
                    line.setEndX(nodeEndPosition.x());
                    line.setEndY(nodeEndPosition.y());

                    reationsLabelInPlane.add(setTextOrthogonal(nodeStartPosition,
                            nodeEndPosition,
                            reation.getReactionName()));

                    line.setStroke(Color.CRIMSON);

                    reationsInPlane.add(line);

                    canvas_plane.getChildren().add(line);
                }
            }

        }
    }
    ObservableList<AngleTable> obsList = FXCollections.observableArrayList();
    public void showArcs() {
        removeArcs();

        ArrayList<Truss> nodeTruss = new ArrayList<>();

        for (Node node : SpaghettiBridgeMain.bridgeManager.getNodes()) {
            ArrayList<Truss> nodeTrussFinder = Trussing.TrussFinder(node);
            for (Truss truss : nodeTrussFinder)
                Truss.addTrussInList(nodeTruss, truss);
        }

    }

    void updateAngleTable(){
        ArrayList<Truss> nodeTruss = new ArrayList<>();

        for (Node node : SpaghettiBridgeMain.bridgeManager.getNodes()) {
            ArrayList<Truss> nodeTrussFinder = Trussing.TrussFinder(node);
            for (Truss truss : nodeTrussFinder)
                Truss.addTrussInList(nodeTruss, truss);
        }
        obsList = FXCollections.observableArrayList();
        for (Truss truss : nodeTruss) {
            ArrayList<Bar> bars = new ArrayList<>();

            double angleLengthA = Angle.getTrussAngles(truss.getNodeA(), truss.getNodeB(), truss.getNodeC());
            double angleLengthB = Angle.getTrussAngles(truss.getNodeB(), truss.getNodeA(), truss.getNodeC());
            double angleLengthC = Angle.getTrussAngles(truss.getNodeC(), truss.getNodeA(), truss.getNodeB());

            AngleTable angleTableA = new AngleTable(getBarByNodes(truss.getNodeA().getNodeName(), truss.getNodeB().getNodeName()),
                    getBarByNodes(truss.getNodeA().getNodeName(), truss.getNodeC().getNodeName()),
                    Math.floor(angleLengthA*100)/100);

            AngleTable angleTableB = new AngleTable(getBarByNodes(truss.getNodeA().getNodeName(), truss.getNodeB().getNodeName()),
                    getBarByNodes(truss.getNodeB().getNodeName(), truss.getNodeC().getNodeName()),
                    Math.floor(angleLengthB*100)/100);

            AngleTable angleTableC = new AngleTable(getBarByNodes(truss.getNodeA().getNodeName(), truss.getNodeC().getNodeName()),
                    getBarByNodes(truss.getNodeB().getNodeName(), truss.getNodeC().getNodeName()),
                    Math.floor(angleLengthC*100)/100);

            obsList.add(angleTableA);
            obsList.add(angleTableB);
            obsList.add(angleTableC);
        }
        mainFrameController.getBridgeTableAngleView().setItems(obsList);
    }

    String getBarByNodes(String nodeA, String nodeB){
        String name = "";
        for (Bar bar: SpaghettiBridgeMain.bridgeManager.getBars()
             ) {
            if(bar.getNodeStart().getNodeName().equals(nodeA) || bar.getNodeEnd().getNodeName().equals(nodeA))
                if(bar.getNodeStart().getNodeName().equals(nodeB) || bar.getNodeEnd().getNodeName().equals(nodeB))
                {
                    name = bar.getBarName();
                    break;
                }
        }
        return name;
    }

    void setTrussArc(Node center, Node TargetA, Node TargetB) {
        Arc arc = new Arc();

        //Vector2D nodeStartPosition = new Vector2D(center.getPosition().x() + canvas_plane.getWidth() / 2,
                //-center.getPosition().y() + canvas_plane.getHeight() / 2);
        Vector2D nodeStartPosition = canvasTranslate(center.getPosition());

        Vector2D dirA = Vector2D.subtract(TargetA.getPosition(), center.getPosition());
        Vector2D dirB = Vector2D.subtract(TargetB.getPosition(), center.getPosition());

        double angleLength = Angle.getTrussAngles(center, TargetA, TargetB);

        double angle = Angle.getTrussStartAngle(center, TargetA, TargetB);

        arc.setCenterX(nodeStartPosition.x());
        arc.setCenterY(nodeStartPosition.y());

        double radius = 15d + (50 - 15) * SpaghettiBridgeMain.bridgeManager.getZoomCoefficient()/20;

        if(SpaghettiBridgeMain.bridgeManager.getZoomCoefficient() < 1)
            radius = 5d + (15 - 5) * SpaghettiBridgeMain.bridgeManager.getZoomCoefficient()/20;

        arc.setRadiusX(radius);
        arc.setRadiusY(radius);

        arc.setStartAngle(angle);
        //arc.setStartAngle(0);

        arc.setType(ArcType.ROUND);
        arc.setLength(angleLength);

        arc.setStroke(Color.BLUEVIOLET);
        arc.setFill(Color.BLUEVIOLET);

        canvas_plane.getChildren().add(arc);
        arcsInPlane.add(arc);
    }

    public void showMeasure() {
        removeMeasures();

        for (Measure measure : SpaghettiBridgeMain.bridgeManager.getMeasures()) {
            Line line = new Line();

            Vector2D canvasMeasureStartPosition = canvasTranslate(measure.getStartPosition());
            Vector2D canvasMeasureEndPosition = canvasTranslate(measure.getEndPosition());

            line.setStartX(canvasMeasureStartPosition.x());
            line.setStartY(canvasMeasureStartPosition.y());
            line.setEndX(canvasMeasureEndPosition.x());
            line.setEndY(canvasMeasureEndPosition.y());

            measuresValueLabelsInPlane.add(setTextOrthogonal(canvasMeasureStartPosition,
                    canvasMeasureEndPosition,
                    (Math.round(
                            Vector2D.distance(measure.getStartPosition(),
                                    measure.getEndPosition())
                                    * 1000) / 1000) + "cm"));

            canvas_plane.getChildren().add(line);
            measuresInPlane.add(line);
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

    public void setBarColor(){
        for (Bar bar: SpaghettiBridgeMain.bridgeManager.getBars()) {
            boolean selected = false;
            for (Bar selectedBar: SpaghettiBridgeMain.bridgeManager.getSelectedBar())
                if (bar.equals(selectedBar)) {
                    selected = true;
                    break;
                }

            if(!selected){
                if (bar.getBarForce() < 0)
                    bar.setBarColor(BarColorController.getCompressionColor(barView));
                else if (bar.getBarForce() > 0)
                    bar.setBarColor(BarColorController.getTensionColor(barView));
                else
                    bar.setBarColor(BarColorController.getNeutralColor(barView));
                }else
                    bar.setBarColor(BarColorController.SELECTED);
        }
    }

    public Vector2D canvasTranslate(Vector2D a){
        return Vector2D.add(
                Vector2D.multiply(
                        Vector2D.multiply(
                            a, SpaghettiBridgeMain.bridgeManager.getZoomCoefficient()
                        ),
                        new Vector2D(1,-1)
                ),
                new Vector2D(
                        canvas_plane.getWidth() / 2 + SpaghettiBridgeMain.bridgeManager.getTranslateVector().x(),
                        canvas_plane.getHeight() / 2 + SpaghettiBridgeMain.bridgeManager.getTranslateVector().y())
        );
    }

    Text setTextOrthogonal(Vector2D start, Vector2D end, String text){
        Vector2D vector = Vector2D.subtract(end, start);
        Vector2D orthogonalVector = new Vector2D(vector.y(), -vector.x());

        orthogonalVector = Vector2D.normalize(orthogonalVector);

        double angle = Math.toDegrees(Math.acos(Vector2D.dot(start, end)));

        //if(start.y() > end.y())
            //angle = Math.max(Math.toDegrees(Math.acos(Vector2D.dot(start, end))),
                    //Math.toDegrees(Math.acos(Vector2D.dot(end, start))));

        Text label = new Text();

        label.setText(text);

        Vector2D center = Vector2D.getCenter(start, end);

        //label.setRotate(-angle);

        double sin = Math.floor(
                Math.sqrt(1- Vector2D.dot(start, end)*Vector2D.dot(start, end))*10)/10;

        Vector2D offset;
        if(center.x() < 0)
            offset = new Vector2D(60 * sin,30 * Vector2D.dot(start, end));
        else
            offset = new Vector2D(-60 * sin,30 * Vector2D.dot(start, end));

        //center = Vector2D.add(center,offset);

        label.setX(center.x());
        label.setY(center.y());

        canvas_plane.getChildren().add(label);
        return label;
    }

}
