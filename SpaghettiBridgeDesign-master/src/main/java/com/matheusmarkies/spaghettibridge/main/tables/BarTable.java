/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.tables;

/**
 *
 * @author Matheus Markies
 */
public class BarTable {
    private String name;
    private double size;
    private double force;
    private int wires;

    public BarTable(String name, double size, double force, int wires) {
        this.name = name;
        this.size = size;
        this.force = force;
        this.wires = wires;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getForce() {
        return force;
    }

    public void setForce(double force) {
        this.force = force;
    }

    public int getWires() {
        return wires;
    }

    public void setWires(int wires) {
        this.wires = wires;
    }

}
