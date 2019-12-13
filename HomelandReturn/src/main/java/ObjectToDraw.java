
import java.awt.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evgen
 */
public class ObjectToDraw {
    float x;
    float y;
    int width;
    int height;
    int distFromMiddleY;
    int distFromMiddleX;
    
    public ObjectToDraw(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        distFromMiddleY = (int) y;
        distFromMiddleX = (int) x;
    }
    
    public Rectangle getRectangle() {
        return new Rectangle((int) this.x, (int) this.y, this.width, this.height);
    }
}
