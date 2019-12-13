import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evgen
 */
public class Player {
    
    static FloatPoint pos;
    static Point size;
    static boolean isFalling = false;
    static boolean desireToJump = false;
    static Obstacle curObstacleGround;
    static BufferedImage character;
    static double speed = 0.04;
    
    //Movement
    static int dir;
    
    //Gravity
    static float velY;
    
    static boolean loaded = false;
    
    public static void setup() {
        size = new Point(GameFrame.WIDTH/25, 0);
        size.y = (int) ((double) (size.x));
        pos = new FloatPoint(GameFrame.WIDTH/2 - size.x/2, 700);
        velY = 0;
        loaded = true;
        isFalling = false;
        
        try {
            character = ImageIO.read(new File("C:/Users/evgen/Documents/NetBeansProjects/HomelandReturn/src/main/java/bookshelve_character.png"));
        } catch (IOException ex) {
        }
        
        while(!World.loaded) {
            System.out.print("");
        }
        
        curObstacleGround = World.obstacles.get(0);
    }
    
    public static Rectangle getRectangle() {
        return new Rectangle((int) pos.x, (int) pos.y, size.x, size.y);
    }
    
    public static void paintComponent(Graphics g) {
        //g.drawRect((int) pos.x, (int) pos.y, size.x, size.y); //Hitbox
        if (World.dir != 0) {
            dir = World.dir;
        }
        if (dir < 0) {
            g.drawImage(character, (int) pos.x + size.x, (int) pos.y, -size.x, size.y, null);
        } else {
            g.drawImage(character, (int) pos.x, (int) pos.y, size.x, size.y, null);
        }
    }
}