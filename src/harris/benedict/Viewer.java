package harris.benedict;

import javax.swing.JFrame;
import java.io.File;

public class Viewer {

    public static JFrame frame = new HomePage();

    public static void main(String[] args) {

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("REE Calculator");
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
//specify weight gain/loss for aweek
//public to pravite methods
//add getter methods for object person
    //will have to change how we get those values in data calc
//creation of object is cancer to look at
//check set goal 3000 numbers
//fix javadoc
//remove blacked out code

//display REE, TEE and Goal

//make interface different based on who is using it (general user or RD)
    //Your should be changed to patient


//fix activities basic