
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
public class Main {
    
    static MainFrame mf;
    static boolean loading = true;
    
    public static void main(String[] args) {
        Timer timer3 = new Timer();
        timer3.schedule(new TimerTask(){
            @Override
            public void run() {
                loading = false;
            }
        }, 6000);
        
        mf = new MainFrame();
        mf.main(args);
    }
}
