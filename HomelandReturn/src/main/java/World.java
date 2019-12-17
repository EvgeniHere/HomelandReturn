
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evgen
 */
public class World {
    
    static ArrayList<Obstacle> obstacles = new ArrayList<>();
    static ArrayList<Jumppad> jumppads = new ArrayList<>();
    static int dir;
    static double gravity;
    static double velY;
    static double velX;
    
    static boolean loaded;
    
    static Point movePoint;
    
    public static void setup() {
        //obstacles.add(new Obstacle(0, GameFrame.HEIGHT - GameFrame.HEIGHT / 15, GameFrame.WIDTH, 500));
        
        while (!Player.loaded) {
            System.out.print("");
        }
        
        /*for (int i = 0; i < 10; i++) {
            obstacles.add(new Obstacle(i * 100, middlePoint.y + 100, 50, 50));
        }*/
        
        obstacles = Generator.getLoadedObstacles(2);
        jumppads = Generator.getLoadedJumppads(2);
        
        movePoint = new Point(Generator.getLoadedPlayerPos(2));
        movePoint.x *= -1;
        movePoint.y *= -1;
        movePoint.x += Player.pos.x;
        movePoint.y += Player.pos.y;
        
        dir = 0;
        gravity = 0.09;
        loaded = true;
        
        startTimer();
    }
    
    public static void move() {
        if (!isSideObstacleAt((int) velX + dir)) {
            if (Math.abs(velX) < 3) {
                velX += dir * Player.speed;
            }
            movePoint.x -= velX;
        }
        if (movePoint.y < -2000) {
            movePoint.y = 2000;
        }
    }
    
    public static void jump() {
        if (isGroundCeilingObstacleAt(5)) {
            Player.desireToJump = true;
            velY -= 6.0;
            Timer timer = new Timer();
            timer.schedule(new TimerTask(){
                @Override
                public void run() {
                    Player.desireToJump = false;
                }
            }, 100);
        }
    }
    
    public static void paintComponent(Graphics g) {
        for (int i = 0; i < obstacles.size(); i++) {
            g.fillRect((int) obstacles.get(i).x, (int) obstacles.get(i).y, obstacles.get(i).width, obstacles.get(i).height);
        }
        for (int i = 0; i < jumppads.size(); i++) {
            g.fillRect((int) jumppads.get(i).x, (int) jumppads.get(i).y, jumppads.get(i).width, jumppads.get(i).height);
        }
        g.setColor(Color.RED);
        g.fillRect(movePoint.x-5, movePoint.y-5, 10, 10);
        g.setColor(Color.BLACK);
    }
    
    public static void addGravity() {
        if (isGroundJumppadAt(2) || isGroundJumppadAt(5)) {
            velY = Jumppad.force;
            movePoint.y += 2;
        }
        if (isGroundCeilingObstacleAt(-1)) {
            velY = 0;
            movePoint.y -= 1;
        }
        
        if (isGroundCeilingObstacleAt(1)) {
            if (!Player.desireToJump) {
                velY = 0;
                movePoint.y += 1;
            }
        } 
        if (!isGroundCeilingObstacleAt(2)) {
            if (velY < 5) {
                velY += gravity;
            }
        }
        movePoint.y -= velY;
    }
    
    public static void startTimer() {
        for (int i = 1; i < 6; i++) {
            new Timer().schedule(new TimerTask(){
                @Override
                public void run() {
                    new Timer().scheduleAtFixedRate(new TimerTask(){
                        @Override
                        public void run() {
                            updatePositions();
                        }
                    }, 3, 3);
                }
            }, i);
        }
            
        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                addGravity();
            }
        }, 9, 6);
        
        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                move();
            }
        }, 9, 4);
    }
    
    public static void updatePositions() {
        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).y = movePoint.y + obstacles.get(i).distFromMiddleY;
            obstacles.get(i).x = movePoint.x + obstacles.get(i).distFromMiddleX;
        }
        for (int i = 0; i < jumppads.size(); i++) {
            jumppads.get(i).y = movePoint.y + jumppads.get(i).distFromMiddleY;
            jumppads.get(i).x = movePoint.x + jumppads.get(i).distFromMiddleX;
        }
    }
    
    
    
    public static boolean isSideObstacleAt(int xPos) {
        xPos += Player.pos.x;
        
        if (xPos > Player.pos.x) {
            xPos += Player.size.x;
        }
        
        int minIndexY = (int) Player.pos.y;
        int maxIndexY = (int) Player.pos.y + Player.size.y;

        for (int j = minIndexY; j < maxIndexY; j += 10) {
            for (int k = 0; k < obstacles.size(); k++) {
                int x = (int) obstacles.get(k).x;
                int y = (int) obstacles.get(k).y;
                if (xPos > x && xPos < x + obstacles.get(k).width && j > y && j < y + obstacles.get(k).height) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isGroundCeilingObstacleAt(int yPos) {
        yPos += Player.pos.y;
        
        if (yPos > Player.pos.y) {
            yPos += Player.size.y;
        }
        
        int minIndexX = (int) Player.pos.x;
        int maxIndexX = (int) Player.pos.x + Player.size.x;

        for (int j = minIndexX; j < maxIndexX; j += 10) {
            for (int k = 0; k < obstacles.size(); k++) {
                int x = (int) obstacles.get(k).x;
                int y = (int) obstacles.get(k).y;
                if (j > x && j < x + obstacles.get(k).width && yPos > y && yPos < y + obstacles.get(k).height) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isGroundJumppadAt(int yPos) {
        yPos += Player.pos.y;
        
        if (yPos > Player.pos.y) {
            yPos += Player.size.y;
        }
        
        int minIndexX = (int) Player.pos.x;
        int maxIndexX = (int) Player.pos.x + Player.size.x;

        for (int j = minIndexX; j < maxIndexX; j += 10) {
            for (int k = 0; k < jumppads.size(); k++) {
                int x = (int) jumppads.get(k).x;
                int y = (int) jumppads.get(k).y;
                if (j > x && j < x + jumppads.get(k).width && yPos > y && yPos < y + jumppads.get(k).height) {
                    return true;
                }
            }
        }
        return false;
    }
}
