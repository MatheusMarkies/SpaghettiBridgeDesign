/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.material;

import java.io.Serializable;

/**
 *
 * @author Matheus Markies
 */
public class Material implements Serializable {

    //Barrila 7
    double tensileStrengthMaximumLoad = 42.5d;//N
    double ElasticityModulus = (3600);//MPa
    double Diameter = 1.8;//mm
    double SafetyCoefficient = 4;

    public Material(double tensileStrengthMaximumLoad, long ElasticityModulus, double Diameter, double SafetyCoefficient) {
        this.tensileStrengthMaximumLoad = tensileStrengthMaximumLoad;
        this.ElasticityModulus = ElasticityModulus;
        this.Diameter = Diameter;
        this.SafetyCoefficient = SafetyCoefficient;
    }

    public Material() {
    }

    public double getTensileStrengthMaximumLoad() {
        return tensileStrengthMaximumLoad;
    }

    public void setTensileStrengthMaximumLoad(double tensileStrengthMaximumLoad) {
        this.tensileStrengthMaximumLoad = tensileStrengthMaximumLoad;
    }

    public double getElasticityModulus() {
        return ElasticityModulus;
    }

    public void setElasticityModulus(double ElasticityModulus) {
        this.ElasticityModulus = ElasticityModulus;
    }

    public double getDiameter() {
        return Diameter;
    }

    public void setDiameter(double Diameter) {
        this.Diameter = Diameter;
    }

    public double getSafetyCoefficient() {
        return SafetyCoefficient;
    }

    public void setSafetyCoefficient(double SafetyCoefficient) {
        this.SafetyCoefficient = SafetyCoefficient;
    }
 
}
