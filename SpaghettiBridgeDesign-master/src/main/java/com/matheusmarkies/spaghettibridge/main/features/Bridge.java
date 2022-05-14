/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.features;

import java.io.Serializable;
import java.util.ArrayList;

import com.matheusmarkies.spaghettibridge.objects.bar.BarSerializable;
import com.matheusmarkies.spaghettibridge.objects.node.NodeSerializable;

/**
 *
 * @author Matheus Markies
 */
public class Bridge implements Serializable{
    private ArrayList<NodeSerializable> nodes = new ArrayList<NodeSerializable>();
    private ArrayList<BarSerializable> bars = new ArrayList<BarSerializable>();

    public Bridge(ArrayList<BarSerializable> bars, ArrayList<NodeSerializable> nodes) {
        this.bars = bars;
        this.nodes = nodes;
    }

    public ArrayList<NodeSerializable> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<NodeSerializable> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<BarSerializable> getBars() {
        return bars;
    }

    public void setBars(ArrayList<BarSerializable> bars) {
        this.bars = bars;
    }
 
}
