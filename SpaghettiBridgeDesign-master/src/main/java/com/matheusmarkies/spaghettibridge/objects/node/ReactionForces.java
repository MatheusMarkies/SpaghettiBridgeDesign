/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.objects.node;

import java.io.Serializable;
import java.util.Objects;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class ReactionForces implements Serializable{
    
    private Node ReactionNode;
    private Vector2D forceDirection;
    private String reactionName;

    public ReactionForces(Node ReactionNode, Vector2D forceDirection, String reactionName) {
        this.ReactionNode = ReactionNode;
        this.forceDirection = forceDirection;
        this.reactionName = reactionName;
    }

    public Node getReactionNode() {
        return ReactionNode;
    }

    public void setReactionNode(Node ReactionNode) {
        this.ReactionNode = ReactionNode;
    }

    public Vector2D getForceDirection() {
        return forceDirection;
    }

    public void setForceDirection(Vector2D forceDirection) {
        this.forceDirection = forceDirection;
    }

    public String getReactionName() {
        return reactionName;
    }

    public void setReactionName(String reactionName) {
        this.reactionName = reactionName;
    }

//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 97 * hash + Objects.hashCode(this.ReactionNode);
//        hash = 97 * hash + Objects.hashCode(this.forceDirection);
//        hash = 97 * hash + Objects.hashCode(this.reactionName);
//        return hash;
//    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        final ReactionForces other = (ReactionForces) obj;
//        if (!Objects.equals(this.reactionName, other.reactionName))
//            return false;
//        if (!Objects.equals(this.ReactionNode, other.ReactionNode))
//            return false;
//        if (!Objects.equals(this.forceDirection, other.forceDirection))
//            return false;
//        return true;
//    }

}
