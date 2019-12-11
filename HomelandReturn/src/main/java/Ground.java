/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evgen
 */
public class Ground {
    
    int x;
    int y;
    int width;
    int height;
    int distFromMiddleX;
    int distFromMiddleY;
    
    public Ground(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.distFromMiddleX = x;
        this.distFromMiddleY = y;
    }
}
