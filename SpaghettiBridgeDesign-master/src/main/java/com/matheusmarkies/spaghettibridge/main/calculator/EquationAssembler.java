/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.calculator;

import java.util.ArrayList;
import com.matheusmarkies.spaghettibridge.objects.bar.Bar;
import com.matheusmarkies.spaghettibridge.objects.node.Node;
import com.matheusmarkies.spaghettibridge.objects.node.ReactionForces;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class EquationAssembler {

    public static String[] getForcePerNode(Node node, boolean isCargoReceiver) {
        String[] equation = new String[]{"", ""};

        for (Bar bar : node.getConnectedBars()) {

            Node NodeA = bar.getNodeStart();
            Node NodeB = bar.getNodeEnd();

            if (!NodeA.equals(node)) {
                NodeA = bar.getNodeEnd();
                NodeB = bar.getNodeStart();
            }

            String forceX = (bar.getBarName()) + " cos(" + NodeA.getNodeName() + "," + NodeB.getNodeName() + ")";
            if (equation[0].length() > 0) {
                equation[0] += " + ";
            }

            equation[0] += forceX;

            String forceY = (bar.getBarName()) + " sin(" + NodeA.getNodeName() + "," + NodeB.getNodeName() + ")";
            if (equation[1].length() > 0) {
                equation[1] += " + ";
            }
            equation[1] += forceY;
        }

        for (ReactionForces reation : node.getExternalForces()) {
            if (reation.getForceDirection().x() != 0)
                equation[0] += reation.getForceDirection().x() + reation.getReactionName();

            if (reation.getForceDirection().y() != 0)
                equation[1] += reation.getForceDirection().y() + reation.getReactionName();
        }

        if (isCargoReceiver) {
            equation[1] += " = P / 2";
        } else {
            equation[1] += " = 0";
        }
        equation[0] += " = 0";

        return equation;
    }

    //Calcular equacoes e os sen e cos
    public static String[] getForcePerNodeWithAngles(Node node, boolean isCargoReceiver) {
        String[] equation = new String[]{"", ""};

        Vector2D vectorNormal = new Vector2D(1, 1);
        
        for (Bar bar : node.getConnectedBars()) {

            Node NodeA = bar.getNodeStart();
            Node NodeB = bar.getNodeEnd();

            if (!NodeA.equals(node)) {
                NodeA = bar.getNodeEnd();
                NodeB = bar.getNodeStart();
            }
            //System.out.println("Angle between "+ NodeA.getNodeName() +","+ NodeB.getNodeName()+": "+ Angle.getTrussAngles(NodeA, NodeB));
            
            //double angle = Angle.getTrussAngles(NodeA, NodeB);
            
            Vector2D directionVector = Vector2D.subtract(NodeB.getPosition(), NodeA.getPosition());
            
            if(directionVector.x() != 0)
            directionVector.x(directionVector.x()/Math.abs(directionVector.x()));
            if(directionVector.y() != 0)
            directionVector.y(directionVector.y()/Math.abs(directionVector.y()));
            
            double cos = (NodeA.getPosition().x() - NodeB.getPosition().x()) / Vector2D.distance(NodeA.getPosition(), NodeB.getPosition());
             cos = Math.abs(cos) * directionVector.x();
             
//            if(angle > 0 && angle <= 90)
//                cos = Math.abs(cos);
//            else if(angle > 90 && angle < 180)
//                cos = Math.abs(cos) * -1;
//            else if(angle > 180 && angle < 270)
//                cos = Math.abs(cos) * -1;
//            else if(angle > 270 && angle < 360)
//                cos = Math.abs(cos);
            
            String forceX = (bar.getBarName()) + " cos(" + cos + ")";

            if (equation[0].length() > 0) {
                equation[0] += " + ";
            } 
            equation[0] += forceX;

            double sin = (NodeA.getPosition().y() - NodeB.getPosition().y()) / Vector2D.distance(NodeA.getPosition(), NodeB.getPosition());
            sin = Math.abs(sin) * directionVector.y();
//             if(angle > 0 && angle <= 180)
//                sin = Math.abs(sin);
//            else if(angle > 180 && angle < 360)
//                sin = Math.abs(sin) * -1;
            
            String forceY = (bar.getBarName()) + " sin(" + sin + ")";
            //String forceY = (bar.getBarName()) + " sin(" + Math.sin(Math.toRadians(Angle.getTrussAngles(NodeA, NodeB))) + ")";
            
            if (equation[1].length() > 0) {
                equation[1] += " + ";
            }
            equation[1] += forceY;
        }
        for (ReactionForces reation : node.getExternalForces()) {
            if (reation.getForceDirection().x() != 0)
                equation[0] += " + " + reation.getForceDirection().x() + reation.getReactionName();

            if (reation.getForceDirection().y() != 0)
                equation[1] +=  " + " + reation.getForceDirection().y() + reation.getReactionName();
        }

        if (isCargoReceiver) {
            equation[1] += " = P / 2";
        } else {
            equation[1] += " = 0";
        }
        equation[0] += " = 0";

        return equation;
    }
}
