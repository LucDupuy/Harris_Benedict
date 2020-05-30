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
//change name from HB
//Check that restarting the app works properly
//Web & Mac functionality
//Fix cancelling things

//fix javadoc