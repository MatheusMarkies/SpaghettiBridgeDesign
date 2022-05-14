/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.objects.bar;

import javafx.scene.paint.Color;
import com.matheusmarkies.spaghettibridge.objects.node.Node;

/**
 *
 * @author Matheus Markies
 */
public class Bar {

    Node nodeStart;
    Node nodeEnd;

    String barName;

    Color barColor = Color.DARKCYAN;

    double barForce;
    int numberOfWires;

    public Bar(Node nodeStart, Node nodeEnd, String barName) {
        this.nodeStart = nodeStart;
        this.nodeEnd = nodeEnd;
        this.barName = barName;
    }

    public Bar() {
    }

    public Node getNodeStart() {
        return nodeStart;
    }

    public void setNodeStart(Node nodeStart) {
        this.nodeStart = nodeStart;
    }

    public Node getNodeEnd() {
        return nodeEnd;
    }

    public void setNodeEnd(Node nodeEnd) {
        this.nodeEnd = nodeEnd;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public double getBarForce() {
        return barForce;
    }

    public void setBarForce(double barForce) {
        this.barForce = barForce;
    }

    public int getNumberOfWires() {
        return numberOfWires;
    }

    public void setNumberOfWires(int numberOfWires) {
        this.numberOfWires = numberOfWires;
    }

    public Color getBarColor() {
        return barColor;
    }

    public void setBarColor(Color barColor) {
        this.barColor = barColor;
    }

    public static Bar barSerializableToBar(BarSerializable barSerializable) {
        Node nodeStart = new Node();
        nodeStart.setNodeName(barSerializable.getNodeStart());
        Node nodeEnd = new Node();
        nodeEnd.setNodeName(barSerializable.getNodeEnd());
        
        return new Bar(
                nodeStart,
                nodeEnd,
                barSerializable.getBarName()
        );
    }

}
