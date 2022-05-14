/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.calculator;

import com.matheusmarkies.spaghettibridge.material.Material;
import com.matheusmarkies.spaghettibridge.objects.bar.Bar;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class Deformation {

    public static double getBarDeformation(Material material, Bar bar) {
        double radius = material.getDiameter() / 2;
        double A = radius * radius * Math.PI * bar.getNumberOfWires();

        double deformation = (Vector2D.distance(bar.getNodeStart().getPosition(), bar.getNodeEnd().getPosition()) * bar.getBarForce()) / (material.getElasticityModulus() * A);
        //                    LN/EA
        return deformation;
    }

    public static double exp(double value, int times) {
        for (int i = 0; i < times; i++)
            value *= value;
        return value;
    }

    public static double getElasticLineIntegral(double x, double force, Bar bar) {
        double barLength = Vector2D.distance(bar.getNodeStart().getPosition(), bar.getNodeEnd().getPosition());
        System.out.println("x: "+x);
        System.out.println("L: "+ barLength);
        System.out.println("F: "+ force);
        double elasticLine = (1 / 24) * (2 *force* barLength * x*x*x - force * x*x*x*x - force * barLength*barLength*barLength);
        //                    1/24(2PLx^3 - Px^4 - PL^3)
        return elasticLine;
    }

}
