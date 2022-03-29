/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.calculator;

import java.util.ArrayList;
import com.matheusmarkies.spaghettibridge.objects.node.Node;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class Angle {

//    public static void getTrussAngles(Node node) {
//        ArrayList<Trussing.Truss> nodeTruss = Trussing.TrussFinder(node);
//        Vector2D directionVectorA = Vector2D.subtract(nodeTruss.get(0).getNodeB().getPosition(), nodeTruss.get(0).getNodeA().getPosition());
//        Vector2D directionVectorB = Vector2D.subtract(nodeTruss.get(0).getNodeC().getPosition(), nodeTruss.get(0).getNodeA().getPosition());
//
//        System.out.println(directionVectorA.toString());
//        System.out.println(directionVectorB.toString());
//        System.out.println(Vector2D.dot(directionVectorA, directionVectorB));
//    }

    public static double getTrussAngles(Node center, Node nodeA, Node nodeB) {
                Vector2D dirA = Vector2D.subtract(nodeA.getPosition(), center.getPosition());
                Vector2D dirB = Vector2D.subtract(nodeB.getPosition(), center.getPosition());
                Vector2D closeVectorC = Vector2D.subtract(nodeB.getPosition(), nodeA.getPosition());

                double A = Vector2D.magnetude(closeVectorC);
                double B = Vector2D.magnetude(dirB);
                double C = Vector2D.magnetude(dirA);

                double cos = (C * C + B * B - A * A) / (2 * C * B);

                return Math.toDegrees(Math.acos(cos));
    }

    public static double getTrussStartAngle(Node nodeFix, Node nodeAngleTargetA, Node nodeAngleTargetB) {
        double angle = 0;

        Vector2D dirA = Vector2D.subtract(nodeAngleTargetA.getPosition(), nodeFix.getPosition());
        Vector2D dirB = Vector2D.subtract(nodeAngleTargetB.getPosition(), nodeFix.getPosition());

        double angleA = Math.toDegrees(Math.acos(Vector2D.dot(dirA,new Vector2D(1,0))));
        double angleB = Math.toDegrees(Math.acos(Vector2D.dot(dirB,new Vector2D(1,0))));

        double startAngle = Math.min(angleA,angleB);

        Vector2D C = Vector2D.add(Vector2D.normalize(nodeAngleTargetA.getPosition()),Vector2D.normalize(nodeAngleTargetB.getPosition()));

        System.out.println();
        System.out.println(C.toString());
        if(C.x() < 0 && -C.y() > 0)
        {
            //Terceiro Quadrante
            System.out.println("Terceiro Quadrante");
            angle=180;
        }
        if(C.x() < 0 && -C.y() < 0)
        {
            //Segundo Quadrante
            System.out.println("Segundo Quadrante");
            angle=180;
        }
        if(C.x() > 0 && -C.y() > 0)
        {
            //Quarto Quadrante
            System.out.println("Quarto Quadrante");
        }
        if(C.x() > 0 && -C.y() < 0)
        {
            //Primeiro Quadrante
            System.out.println("Primeiro Quadrante");
        }

        return angle;
    }

}
