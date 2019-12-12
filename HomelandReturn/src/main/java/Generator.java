
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
    
    public static ArrayList<Obstacle> dataToList(String data, int modifier) {
        ArrayList<Obstacle> loadingObstacles = new ArrayList<>();
        String code = Generator.LOADCODE();
        for (int i = 0; i + 1 < code.length(); i++) {
            if (code.charAt(i) == ';') {
                i++;
            }
            int[] info = new int[4];
            for (int j = 0; j < info.length; j++) {
                String num = "";
                while (code.charAt(i) != ',' && code.charAt(i) != ';') {
                    num += code.charAt(i);
                    i++;
                }
                if (code.charAt(i) == ',') {
                    i++;
                }
                info[j] = (int) Double.parseDouble(num) * modifier;
            }
            loadingObstacles.add(new Obstacle(info[0], info[1], info[2], info[3]));
        }
        return loadingObstacles;
    }
    
    public static String listToData(ArrayList<Obstacle> obs) {
        String info = "";
        for (int i = 0; i < obs.size(); i++) {
            Obstacle curObs = obs.get(i);
            info += ";" + curObs.x + "," + curObs.y + "," + curObs.width + "," + curObs.height;
        }
        info += ";";
        return info;
    }
}
