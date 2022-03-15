/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.objects.fx;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class Arrow {

    Line arrowGuide;
    Line arrowHeadA;
    Line arrowHeadB;

    public Arrow(Vector2D start, Vector2D end, double angleOfHead, Color color, boolean invert) {
        Line arrowGuide = new Line();

        double angleOfArrow = Math.toDegrees(
                Math.acos(
                        Vector2D.dot(Vector2D.subtract(end, start), new Vector2D(1, 0))
                )
        );
        
        double angleOfYAxie = Math.toDegrees(
                Math.acos(
                        Vector2D.dot(Vector2D.subtract(end, start), new Vector2D(0, -1))
                )
        );
        
        if(angleOfYAxie > 90)
           angleOfArrow = 360 -angleOfArrow;
        
        System.out.println("Y: "+angleOfYAxie);
        System.out.println("Start: "+start.toString());
        System.out.println("End: "+end.toString());
        System.out.println(Vector2D.subtract(end, start).toString());
        System.out.println(Vector2D.dot(Vector2D.subtract(end, start), new Vector2D(1, 0)));
        
        Vector2D directionVector = Vector2D.subtract(end, start);
        double m = Vector2D.magnetude(directionVector);

        double tg = Math.tan(Math.toRadians(angleOfHead));

        double arrowLength = Math.max(5, Math.min(10, (m * 0.2f)));

        Vector2D P, Q, Q2, arrowHeadAStartPosition, arrowHeadBStartPosition, arrowHeadEndPosition = new Vector2D(0, 0);

        //if (!invert) {
            P = Vector2D.subtract(end, Vector2D.multiply(Vector2D.subtract(end, start), new Vector2D(0.2f, 0.2f)));

            Q = Vector2D.add(
                    P, Vector2D.multiply(
                            new Vector2D(
                                    Math.cos(Math.toRadians(90 - angleOfArrow)),
                                    Math.sin(Math.toRadians(90 - angleOfArrow))
                            ),
                            new Vector2D(arrowLength * tg, arrowLength * tg)
                    )
            );

            Q2 = Vector2D.add(P,
                    Vector2D.multiply(
                            new Vector2D(
                                    Math.cos(Math.toRadians(90 - angleOfArrow)),
                                    Math.sin(Math.toRadians(90 - angleOfArrow))
                            ),
                            new Vector2D(-arrowLength * tg, -arrowLength * tg)
                    )
            );

            System.out.println(Q.toString());
            System.out.println(Q2.toString());
            
            arrowHeadAStartPosition = Q;
            arrowHeadBStartPosition = Q2;

            arrowHeadEndPosition = end;
        //} else {
            //P = Vector2D.subtract(end, Vector2D.multiply(Vector2D.subtract(end, start), new Vector2D(0.2f, 0.2f)));

            //Q = Vector2D.add(end, Vector2D.multiply(new Vector2D(Math.cos(Math.toRadians(90 - angleOfArrow)),
                    //Math.sin(Math.toRadians(90 - angleOfArrow))), new Vector2D(arrowLength * tg, arrowLength * tg)));
            //Q2 = Vector2D.add(end, Vector2D.multiply(new Vector2D(Math.cos(Math.toRadians(90 - angleOfArrow)),
                    //Math.sin(Math.toRadians(90 - angleOfArrow))), new Vector2D(-arrowLength * tg, -arrowLength * tg)));

            //arrowHeadAStartPosition = Q;
            //arrowHeadBStartPosition = Q2;

            //arrowHeadEndPosition = P;
        //}

        arrowGuide.setStartX(start.x());
        arrowGuide.setStartY(start.y());
        arrowGuide.setEndX(arrowHeadEndPosition.x());
        arrowGuide.setEndY(arrowHeadEndPosition.y());

        Line arrowHeadA = new Line();

        arrowHeadA.setStartX(arrowHeadAStartPosition.x());
        arrowHeadA.setStartY(arrowHeadAStartPosition.y());
        arrowHeadA.setEndX(arrowHeadEndPosition.x());
        arrowHeadA.setEndY(arrowHeadEndPosition.y());

        Line arrowHeadB = new Line();

        arrowHeadB.setStartX(arrowHeadBStartPosition.x());
        arrowHeadB.setStartY(arrowHeadBStartPosition.y());
        arrowHeadB.setEndX(arrowHeadEndPosition.x());
        arrowHeadB.setEndY(arrowHeadEndPosition.y());

        arrowGuide.setStroke(color);
        arrowHeadA.setStroke(color);
        arrowHeadB.setStroke(color);

        this.arrowGuide = arrowGuide;
        this.arrowHeadA = arrowHeadA;
        this.arrowHeadB = arrowHeadB;
    }

    public void setArrowInPlane(AnchorPane pane) {
        pane.getChildren().add(arrowGuide);
        pane.getChildren().add(arrowHeadA);
        pane.getChildren().add(arrowHeadB);
    }

    public void removeArrow(AnchorPane pane) {
        pane.getChildren().remove(arrowGuide);
        pane.getChildren().remove(arrowHeadA);
        pane.getChildren().remove(arrowHeadB);
    }
}
