/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.objects.node;

import java.io.Serializable;
import java.util.ArrayList;
import com.matheusmarkies.spaghettibridge.objects.bar.Bar;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class NodeSerializable implements Serializable {

    Vector2D position;
    String nodeName;

    //ArrayList<ReactionForces> externalForces = new ArrayList<>();
    ArrayList<String> connectedBars = new ArrayList<>();

    public NodeSerializable(Vector2D position, String nodeName, ArrayList<String> connectedBars, ArrayList<ReactionForces> externalForces) {
        this.position = position;
        this.nodeName = nodeName;
        this.connectedBars = connectedBars;
        //this.externalForces = externalForces;
    }

    public NodeSerializable() {
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    //public ArrayList<ReactionForces> getExternalForces() {
    //    return externalForces;
    //}
    
    // public void setExternalForces(ArrayList<ReactionForces> externalForces) {
    //   this.externalForces = externalForces;
    //}
    
    public ArrayList<String> getConnectedBars() {
        return connectedBars;
    }

    public void setConnectedBars(ArrayList<String> connectedBars) {
        this.connectedBars = connectedBars;
    }

    public void nodeToNodeSerializable(Node node) {
        this.position = node.getPosition();
        this.nodeName = node.getNodeName();
        for (Bar bar : node.getConnectedBars())
            this.connectedBars.add(bar.getBarName());
        //this.externalForces = node.getExternalForces();
    }

    public Node nodeSerializableToNode(ArrayList<Bar> bars) {
        Node node = new Node();

        node.setPosition(position);
        node.setNodeName(nodeName);
        //node.setExternalForces(externalForces);

        ArrayList<Bar> connectedBars = new ArrayList<>();

        for (Bar bar : bars)
            for (String barSerializable : getConnectedBars())
                if (barSerializable.equals(bar.getBarName())) {
                    connectedBars.add(bar);
                    break;
                }

        node.setConnectedBars(connectedBars);

        return node;
    }

}
