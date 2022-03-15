/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.grid;

import com.matheusmarkies.spaghettibridge.main.MainFrameController;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class Grid {

    MainFrameController mainFrameController;

    public Grid(MainFrameController mainFrameController) {
        this.mainFrameController = mainFrameController;
    }
    
    
    public Vector2D clampMouse(Vector2D position) {
        Vector2D mousePosition = position;

        double angleX = Math.toDegrees(Math.acos(Vector2D.dot(position, new Vector2D(1, 0))));
        double angleY = Math.toDegrees(Math.acos(Vector2D.dot(position, new Vector2D(0, 1))));

        if (angleX <= 10 || (180 - angleX) <= 10)
            mousePosition.y(0);
        if (angleY <= 10 || (180 - angleY) <= 10)
            mousePosition.x(0);
        if(Vector2D.distance(new Vector2D(Math.round(mousePosition.x()/30) * 30,0),position) < 10)
            mousePosition = new Vector2D(Math.round(mousePosition.x()/30) * 30,0);
        if(Vector2D.distance(new Vector2D(0, Math.round(mousePosition.y()/30) * 30),position) < 10)
            mousePosition = new Vector2D(0, Math.round(mousePosition.y()/30) * 30);
        
        return mousePosition;
    }

}
