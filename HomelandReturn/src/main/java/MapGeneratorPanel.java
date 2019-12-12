
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evgen
 */
public class MapGeneratorPanel extends javax.swing.JPanel implements MouseMotionListener, MouseListener {

    /**
     * Creates new form MainPanel
     */
    
    static int mx;
    static int my;
    static int start_mx;
    static int start_my;
    static Obstacle selectedObstacle;
    static ArrayList<Obstacle> obstaclesOnMap;
    
    public MapGeneratorPanel() {
        initComponents();
        setBackground(Color.GRAY);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        mx = 0;
        my = 0;
        selectedObstacle = null;
        obstaclesOnMap = new ArrayList<>();
    }
    
    public static void setObstacles(ArrayList<Obstacle> loadedObstacles) {
        obstaclesOnMap = loadedObstacles;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        repaint();
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        
        for (int i = 0; i < obstaclesOnMap.size(); i++) {
            g.fillRect((int) obstaclesOnMap.get(i).x, (int) obstaclesOnMap.get(i).y, obstaclesOnMap.get(i).width, obstaclesOnMap.get(i).height);
        }
        if (selectedObstacle != null) {
            g.fillRect((int) selectedObstacle.x, (int) selectedObstacle.y, selectedObstacle.width, selectedObstacle.height);
        }
    }
    
    public static void setSelectedObstacle(Obstacle obstacle) {
        selectedObstacle = obstacle;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void mouseDragged(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        if (selectedObstacle != null) {
            selectedObstacle.width = mx - start_mx;
            selectedObstacle.height = my - start_my;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        start_mx = e.getX();
        start_my = e.getY();
        if (selectedObstacle != null) {
            selectedObstacle.x = start_mx;
            selectedObstacle.y = start_my;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        if (selectedObstacle != null) {
            selectedObstacle.width = mx - start_mx;
            selectedObstacle.height = my - start_my;
            obstaclesOnMap.add(new Obstacle(selectedObstacle.x, selectedObstacle.y, selectedObstacle.width, selectedObstacle.height));
            selectedObstacle.width = 0;
            selectedObstacle.height = 0;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}