/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.objects.fx;

import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class Measure {
    
    Vector2D startPosition;
    Vector2D endPosition;

    public Measure(Vector2D startPosition, Vector2D endPosition, double offset) {
        Vector2D vector = Vector2D.subtract(endPosition, startPosition);
        Vector2D orthogonalVector = new Vector2D(vector.y(), -vector.x());
        
        orthogonalVector = Vector2D.normalize(orthogonalVector);
        
        this.startPosition = Vector2D.add(startPosition, Vector2D.multiply(orthogonalVector, new Vector2D(offset, offset)));
        this.endPosition = Vector2D.add(endPosition, Vector2D.multiply(orthogonalVector, new Vector2D(offset, offset)));
    }

    public Vector2D getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Vector2D startPosition) {
        this.startPosition = startPosition;
    }

    public Vector2D getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Vector2D endPosition) {
        this.endPosition = endPosition;
    }

}
