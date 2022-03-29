package com.matheusmarkies.spaghettibridge.main.tables;

public class AngleTable {
    private String nodes;
    private double arc;

    public AngleTable(String nodes, double arc) {
        this.nodes = nodes;
        this.arc = arc;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public double getArc() {
        return arc;
    }

    public void setArc(double arc) {
        this.arc = arc;
    }
}
