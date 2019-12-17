
import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evgen
 */
public class Generator {
    
    static ArrayList<Obstacle> loadingObstacles;
    static ArrayList<Jumppad> loadingJumppads;
    static Point loadingPlayerPos;
    
    public static void GENCODE(String code) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("savedMap.txt"));
            writer.write(code);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static String LOADCODE() {
        String content = "";
        try {
            content = new String ( Files.readAllBytes( Paths.get("savedMap.txt") ) );
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    
    public static void processData() {
        String code = Generator.LOADCODE();
        
        loadingObstacles = new ArrayList<>();
        loadingJumppads = new ArrayList<>();
        
        String curObject = "";
        for (int i = 0; i + 1 < code.length(); i++) {
            if ((((int) code.charAt(i) >= (int) 'a' && (int) code.charAt(i) <= (int) 'z')
                    || ((int) code.charAt(i) >= (int) 'A' && (int) code.charAt(i) <= (int) 'Z'))) {
                curObject = "";
                while ((((int) code.charAt(i) >= (int) 'a' && (int) code.charAt(i) <= (int) 'z')
                        || ((int) code.charAt(i) >= (int) 'A' && (int) code.charAt(i) <= (int) 'Z'))) {
                    curObject += code.charAt(i);
                    i++;
                }
            }
            if (code.charAt(i) == ';') {
                i++;
            }
            int length = 0;
            if (curObject.equals("obstacles")) {
                length = 4;
            } else if (curObject.equals("playerPos")) {
                length = 2;
            } else if (curObject.equals("jumppads")) {
                length = 3;
            }
            int[] info = new int[length];
            for (int j = 0; j < info.length; j++) {
                String num = "";
                while ((int) code.charAt(i) >= (int) '0' && (int) code.charAt(i) <= (int) '9') {
                    num += code.charAt(i);
                    i++;
                }
                if (code.charAt(i) == ',') {
                    i++;
                }
                if (!num.equals("")) {
                    info[j] = (int) Integer.parseInt(num);
                }
            }
            if (curObject.equals("obstacles")) {
                loadingObstacles.add(new Obstacle(info[0], info[1], info[2], info[3]));
            } else if (curObject.equals("playerPos")) {
                loadingPlayerPos = new Point(info[0], info[1]);
            } else if (curObject.equals("jumppads")) {
                loadingJumppads.add(new Jumppad(info[0], info[1], info[2]));
            }
        }
    }
    
    public static Point getLoadedPlayerPos(int modifier) {
        loadingPlayerPos.x *= modifier;
        loadingPlayerPos.y *= modifier;
        return loadingPlayerPos;
    }
    
    public static ArrayList<Obstacle> getLoadedObstacles(int modifier) {
        if (loadingObstacles == null) {
            processData();
        }
        for (int i = 0; i < loadingObstacles.size(); i++) {
            loadingObstacles.set(i, new Obstacle(loadingObstacles.get(i).x * modifier, loadingObstacles.get(i).y * modifier, loadingObstacles.get(i).width * modifier, loadingObstacles.get(i).height * modifier));
        }
        return loadingObstacles;
    }
    
    public static ArrayList<Jumppad> getLoadedJumppads(int modifier) {
        if (loadingJumppads == null) {
            processData();
        }
        for (int i = 0; i < loadingJumppads.size(); i++) {
            loadingJumppads.set(i, new Jumppad((int) loadingJumppads.get(i).x * modifier, (int) loadingJumppads.get(i).y * modifier, loadingJumppads.get(i).width * modifier));
            loadingJumppads.get(i).height *= modifier;
        }
        return loadingJumppads;
    }
    
    public static String mapToData(ArrayList<Obstacle> obs, Point playerPos, ArrayList<Jumppad> jumppads) {
        String info = "obstacles";
        for (int i = 0; i < obs.size(); i++) {
            Obstacle curObs = obs.get(i);
            info += ";" + (int) curObs.x + "," + (int) curObs.y + "," + (int) curObs.width + "," + (int) curObs.height;
        }
        info += ";";
        if (playerPos != null) {
            info += "playerPos;" + (int) playerPos.x + "," + (int) playerPos.y + ";";
        } else {
            info += "playerPos;" + 0 + "," + 0 + ";";
        }
        info += "jumppads";
        for (int i = 0; i < jumppads.size(); i++) {
            Jumppad curJumppad = jumppads.get(i);
            info += ";" + (int) curJumppad.x + "," + (int) curJumppad.y + "," + (int) curJumppad.width + "," + (int) curJumppad.height;
        }
        return info;
    }
}