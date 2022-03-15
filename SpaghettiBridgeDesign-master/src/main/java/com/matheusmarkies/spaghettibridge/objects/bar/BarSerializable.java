/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.objects.bar;

import java.io.Serializable;
import com.matheusmarkies.spaghettibridge.objects.node.Node;
import com.matheusmarkies.spaghettibridge.objects.node.NodeSerializable;

/**
 *
 * @author Matheus Markies
 */
public class BarSerializable implements Serializable{
    
    String nodeStart;
    String nodeEnd;
    
    String barName;

    public BarSerializable(String nodeStart, String nodeEnd, String barName) {
        this.nodeStart = nodeStart;
        this.nodeEnd = nodeEnd;
        this.barName = barName;
    }

    public String getNodeStart() {
        return nodeStart;
    }

    public void setNodeStart(String nodeStart) {
        this.nodeStart = nodeStart;
    }

    public String getNodeEnd() {
        return nodeEnd;
    }

    public void setNodeEnd(String nodeEnd) {
        this.nodeEnd = nodeEnd;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }
    
}
