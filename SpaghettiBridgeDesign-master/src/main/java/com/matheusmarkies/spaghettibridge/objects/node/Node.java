/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.objects.node;

import java.util.ArrayList;
import java.util.Objects;
import com.matheusmarkies.spaghettibridge.objects.bar.Bar;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class Node {

    Vector2D position;
    String nodeName;

    boolean isCargoReciver = false;

    int index = 0;

    ArrayList<ReactionForces> externalForces = new ArrayList<>();

    ArrayList<Bar> connectedBars = new ArrayList<>();

    public Node(Vector2D position, String nodeName, int index) {
        this.position = position;
        this.nodeName = nodeName;
        this.index = index;
    }

    public Node() {
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<ReactionForces> getExternalForces() {
        return externalForces;
    }

    public void setExternalForces(ArrayList<ReactionForces> externalForces) {
        this.externalForces = externalForces;
    }

    public void addExternalForce(ReactionForces e) {
        this.externalForces.add(e);
    }

    public ArrayList<Bar> getConnectedBars() {
        return connectedBars;
    }

    public void setConnectedBars(ArrayList<Bar> connectedBars) {
        this.connectedBars = connectedBars;
    }

    public void addConnectedBar(Bar bar) {
        connectedBars.add(bar);
    }

    public boolean isCargoReciver() {
        return isCargoReciver;
    }

    public void setIsCargoReciver(boolean isCargoReciver) {
        this.isCargoReciver = isCargoReciver;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.position);
        hash = 41 * hash + Objects.hashCode(this.nodeName);
        hash = 41 * hash + (this.isCargoReciver ? 1 : 0);
        hash = 41 * hash + this.index;
        hash = 41 * hash + Objects.hashCode(this.externalForces);
        hash = 41 * hash + Objects.hashCode(this.connectedBars);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Node other = (Node) obj;
        if (this.isCargoReciver != other.isCargoReciver)
            return false;
        if (!Objects.equals(this.nodeName, other.nodeName))
            return false;
        return Objects.equals(this.position, other.position);
    }

}
