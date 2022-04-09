package com.matheusmarkies.spaghettibridge.main.tables;

public class AngleTable {
    private String firstBar;
    private String secondBar;
    private double arc;

    public AngleTable(String firstBar,String secondBar, double arc) {
        this.firstBar = firstBar;
        this.secondBar = secondBar;
        this.arc = arc;
    }

    public String getFirstBar() {
        return firstBar;
    }

    public void setFirstBar(String firstBar) {
        this.firstBar = firstBar;
    }

    public String getSecondBar() {
        return secondBar;
    }

    public void setSecondBar(String secondBar) {
        this.secondBar = secondBar;
    }

    public double getArc() {
        return arc;
    }

    public void setArc(double arc) {
        this.arc = arc;
    }
}
