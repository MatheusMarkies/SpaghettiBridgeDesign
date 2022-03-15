/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.material;

/**
 *
 * @author Matheus Markies
 */
public class Material {

    //Barrila 7
    double tensileStrengthMaximumLoad = 42.5d;
    double ElasticityModulus = (3600);
    double Diameter = 0.018;
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
