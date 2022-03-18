/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.manager;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import com.matheusmarkies.spaghettibridge.main.calculator.WireCalculator.CalculationComponents;
import com.matheusmarkies.spaghettibridge.material.Material;
import com.matheusmarkies.spaghettibridge.objects.bar.Bar;
import com.matheusmarkies.spaghettibridge.objects.fx.Measure;
import com.matheusmarkies.spaghettibridge.objects.node.Node;
import com.matheusmarkies.spaghettibridge.objects.node.ReactionForces;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class BridgeManager {

    ArrayList<Node> nodes = new ArrayList<Node>();
    ArrayList<Bar> bars = new ArrayList<Bar>();
    ArrayList<ReactionForces> reactions = new ArrayList<ReactionForces>();

    ArrayList<String> equations = new ArrayList<>();

    ArrayList<Measure> measures = new ArrayList<>();
    
    CalculationComponents calculationComponents;

    Material material = new Material();
    double testLoadForce = 100;
    double testLoadInAction = 100;

    double zoomCoefficient = 1;
    Vector2D translateVector = new Vector2D(0,0);

    public void reset(){
        nodes = new ArrayList<Node>();
        bars = new ArrayList<Bar>();
        reactions = new ArrayList<ReactionForces>();

        equations = new ArrayList<>();

        measures = new ArrayList<>();
    }
    
    public void addNode(Node node) {
        int i = 0;
        for (Node nodeCompare : nodes)
            if (nodeCompare == node) {
                i++;
                break;
            }
        if (i == 0) {
            nodes.add(node);
            updateMainForces();
        }
    }

    public void addBar(Bar bar) {
        int i = 0;
        for (Bar barCompare : bars)
            if (barCompare == bar) {
                i++;
                break;
            }
        if (i == 0)
            bars.add(bar);
    }

    public boolean checkNodeNameVality(String name) {
        int i = 0;

        for (Node nodeCompare : nodes)
            if (nodeCompare.getNodeName().equals(name)) {
                i++;
                break;
            }

        return i == 0;
    }

    public boolean checkBarVality(Node nodeStart, Node nodeEnd) {
        int i = 0;

        for (Bar barCompare : bars)
            if (barCompare.getNodeStart().equals(nodeStart) && barCompare.getNodeEnd().equals(nodeEnd)) {
                i++;
                break;
            } else if (barCompare.getNodeEnd().equals(nodeStart) && barCompare.getNodeStart().equals(nodeEnd)) {
                i++;
                break;
            }

        return i == 0;
    }

    public void updateMainForces() {
        Node cargoNode = getMiddleNode();

        for (Node node : nodes) {
            node.setExternalForces(new ArrayList<ReactionForces>());

            if (cargoNode.equals(node))
                node.setIsCargoReciver(true);
            else
                node.setIsCargoReciver(false);
        }

        reactions = calculateResultPoints();
        System.out.println(reactions.size());
    }

    ArrayList<ReactionForces> calculateResultPoints() {
        ArrayList<ReactionForces> results = new ArrayList<>();

        Node MaxX = new Node(new Vector2D(0, 0), "", 0);
        Node MinX = new Node(new Vector2D(0, 0), "", 0);

        int i = 0;
        for (Node node : nodes) {
            if (i == 0) {
                MaxX = node;
                MinX = node;
                i++;
            } else {
                if (node.getPosition().x() > MaxX.getPosition().x()) {
                    MaxX = node;
                }
                if (node.getPosition().x() < MinX.getPosition().x()) {
                    MinX = node;
                }
            }
        }

        ReactionForces a = new ReactionForces(MinX, new Vector2D(-1, 0), MinX.getNodeName() + "X");
        ReactionForces b = new ReactionForces(MinX, new Vector2D(0, 1), MinX.getNodeName() + "Y");
        ReactionForces c = new ReactionForces(MaxX, new Vector2D(0, 1), MaxX.getNodeName() + "Y");

        MinX.addExternalForce(a);
        MinX.addExternalForce(b);
        MaxX.addExternalForce(c);

        results.add(a);
        results.add(b);
        results.add(c);

        return results;
    }

    public Node getMiddleNode() {

        Node middle = new Node(new Vector2D(0, 0), "", 0);

        Node MaxX = new Node(new Vector2D(0, 0), "", 0);
        Node MinX = new Node(new Vector2D(0, 0), "", 0);

        int i = 0;
        for (Node node : nodes) {
            if (i == 0) {
                MaxX = node;
                middle = node;
                MinX = node;
                i++;
            } else {
                if (node.getPosition().x() > MaxX.getPosition().x()) {
                    MaxX = node;
                }
                if (node.getPosition().x() < MinX.getPosition().x()) {
                    MinX = node;
                }
            }
        }

        Vector2D middlePoint = Vector2D.getCenter(MaxX.getPosition(), MinX.getPosition());

        for (Node node : nodes) {
            if (Vector2D.distance(middle.getPosition(), middlePoint) > Vector2D.distance(node.getPosition(), middlePoint))
                middle = node;
        }

        return middle;
    }

    public void updateBarsColors() {
        for (Bar entry : this.getBars()) {
            if (entry.getBarForce() == 0)
                entry.setBarColor(Color.DARKCYAN);
            else if (entry.getBarForce() > 0)
                entry.setBarColor(Color.YELLOWGREEN);
            else
                entry.setBarColor(Color.DARKMAGENTA);
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Bar> getBars() {
        return bars;
    }

    public ArrayList<ReactionForces> getReactions() {
        return reactions;
    }

    public void addEquation(String eq) {
        this.equations.add(eq);
    }

    public ArrayList<String> getEquations() {
        return equations;
    }

    public void setEquations(ArrayList<String> equations) {
        this.equations = equations;
    }

    public CalculationComponents getCalculationComponents() {
        return calculationComponents;
    }

    public void setCalculationComponents(CalculationComponents calculationComponents) {
        this.calculationComponents = calculationComponents;
    }

    public ArrayList<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(ArrayList<Measure> measures) {
        this.measures = measures;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getTestLoadForce() {
        return testLoadForce;
    }

    public void setTestLoadForce(double testLoadForce) {
        this.testLoadForce = testLoadForce;
    }

    public double getTestLoadInAction() {
        return testLoadInAction;
    }

    public void setTestLoadInAction(double testLoadInAction) {
        this.testLoadInAction = testLoadInAction;
    }

    public double getZoomCoefficient() {
        return zoomCoefficient;
    }

    public void setZoomCoefficient(double zoomCoefficient) {
        this.zoomCoefficient = Math.max(0.25d, Math.min(10, zoomCoefficient));
    }

    public Vector2D getTranslateVector() {
        return translateVector;
    }

    public void setTranslateVector(Vector2D translateVector) {
        this.translateVector = translateVector;
    }
}
