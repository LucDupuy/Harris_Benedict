package harris.benedict;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class saves the data into a readable text format
 */
public class SaveData {

    /**
     * This method saves the data into a text file for the user to refer to
     *
     * @param name the name of the user
     */
    protected static void writeData(String name) {


        String pathToSave = System.getProperty("user.dir") + File.separator + "Saved Profiles";


        File file = new File(pathToSave);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR SD01:  Please contact dev team with error code");
            }
        }

        /*
        This block of code is used to create a text file in which to save the information
         */
        //WORKS FOR WINDOWS IDK ABOUT MAC


        JFileChooser jcf = new JFileChooser(new File(pathToSave));

        jcf.setDialogTitle("Save File");
        jcf.setFileFilter(new FileNameExtensionFilter("Text File", "txt"));

        if (name.equals("")) {
            jcf.setSelectedFile(new File("TEE.txt"));
        } else {
            jcf.setSelectedFile(new File(name + " - TEE.txt"));
        }

            /*
            This block of code writes the data into the newly created text file

             */

        ArrayList<String> output = ProfessionalPage.getResults();
        int result = jcf.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter out = new PrintWriter(jcf.getSelectedFile().getAbsolutePath())) {

                if (name.equals("")) {
                    out.println("Hello! This is your recommended, average daily nutrition intake.");
                } else {
                    out.println("Hello " + name + "! This is your recommended, average daily nutrition intake.");
                }
                out.println();
                for (String str : output) {
                    out.println(str);
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "ERROR WD01:  Please contact dev team with error code");
            }
        }
    }
}