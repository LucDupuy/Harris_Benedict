package harris.benedict;

import javax.swing.JFrame;
import java.io.File;

/**
 * Main class that loads the program
 */
public class Viewer {

    public static JFrame frame = new HomePage();

    public static void main(String[] args) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TEE Calculator");
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        String pathToSave = System.getProperty("user.dir") + File.separator + "Saved Profiles";
        System.out.println(pathToSave);

    }
}


//Work on stolen code
//work on restarting
//condense code
//creation of object is cancer to look at
//home button doesnt work
//change name from HB
//cite OG Harris Benedict for values
//cancelling boxes error(no close allowed?)