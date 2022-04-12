/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.utilities;

/**
 *
 * @author Matheus Markies
 */
public class Line {
  double a;
  double b;
  double c;

    public Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
  
    public static Line getLineEquation(Vector2D a, Vector2D b) {
        Line line = new Line(0, 0, 0);
        
        line.a(-b.y() + a.y());
        line.b(b.x() - a.x());
        line.c(a.x()*b.y() - a.y() * b.x());
        
        return line;
    }

    public double getM(){
        return -(this.a/this.b);
    }
    
    public double getN(){
        return -(this.c/this.b);
    }
    
    public double a() {
        return a;
    }

    public void a(double a) {
        this.a = a;
    }

    public double b() {
        return b;
    }

    public void b(double b) {
        this.b = b;
    }

    public double c() {
        return c;
    }

    public void c(double c) {
        this.c = c;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.a) ^ (Double.doubleToLongBits(this.a) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.b) ^ (Double.doubleToLongBits(this.b) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.c) ^ (Double.doubleToLongBits(this.c) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Line other = (Line) obj;
        if (Double.doubleToLongBits(this.a) != Double.doubleToLongBits(other.a))
            return false;
        if (Double.doubleToLongBits(this.b) != Double.doubleToLongBits(other.b))
            return false;
        return Double.doubleToLongBits(this.c) == Double.doubleToLongBits(other.c);
    }

    @Override
    public String toString() {
        return "Line: "+ b+"y + "+a+"x +"+c+" = 0";
    }

}
