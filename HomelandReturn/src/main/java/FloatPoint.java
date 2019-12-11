/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evgen
 */
public class FloatPoint {
    
    float x;
    float y;
    
    public FloatPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void setLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void addX(float x) {
        this.x += x;
    }
    
    public void addY(float y) {
        this.y += y;
    }
    
    public void setX(float x) {
        this.x = x;
    }
    
    public void setY(float y) {
        this.y = y;
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
}
