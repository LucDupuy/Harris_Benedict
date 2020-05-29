package harris.benedict;

//import javafx.scene.effect.DropShadow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;




public class HomePage extends JFrame {

    /**
     *
     */
  //  private static final long serialVersionUID = 1L;
    private static JButton newButtonBasic = new JButton("NEW (BASIC)");
    private static JButton newButtonPro = new JButton("NEW (PROFESSIONAL)");
    private BufferedImage image;


    /**
     * The constructor for the main page object
     */
    public HomePage() {
        setSize(800, 800);
        setResizable(false);

        add(createMain(), BorderLayout.NORTH);
        add(createImagePanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);


    }

    /**
     * Creates the main panel of the program
     *
     * @return the panel
     */
    private JPanel createMain() {

        JPanel panel = new JPanel();
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Harris Benedict Tool");
        label.setFont(new Font(Font.SERIF, 1, 40));
        panel.add(label);

        //panel.setBackground( Color.decode("#e8dee7"));
        panel.setBackground( Color.decode("#d7d5d5"));
        setSize(650, 650);

        return panel;
    }

    /**
     * This method creates the panel to hold the new information button
     *
     * @return the panel to add to the main frame
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        newButtonBasic.setPreferredSize(new Dimension(200, 50));
        newButtonBasic.setFont(new Font("Airal", 1, 15));
        panel.add(newButtonBasic);
        newButtonBasic.addActionListener(new Listen());

        newButtonPro.setPreferredSize(new Dimension(200, 50));
        newButtonPro.setFont(new Font("Airal", 1, 15));
        panel.add(newButtonPro);
        newButtonPro.addActionListener(new Listen());

        panel.setBackground( Color.decode("#d7d5d5"));

        return panel;
    }


    /**
     * This creates the panel to be added that holds the image for the program
     *
     * @return the panel to hold the image
     */

    private JPanel createImagePanel() {
        JPanel panel = new JPanel();




        try {

            image = ImageIO.read(new File("HomePic.jpg"));
            Image scaledImg = image.getScaledInstance(400,450, Image.SCALE_SMOOTH);
            ImageIcon img = new ImageIcon(scaledImg);
            JLabel imgLbl = new JLabel();
            imgLbl.setIcon(img);
            panel.add(imgLbl);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error IL01: Image Cannot Be Loaded, please contact dev team", "Error", JOptionPane.ERROR_MESSAGE);
        }

        panel.setBackground( Color.decode("#d7d5d5"));

        return panel;
    }


    /**
     * This class deals with button clicks
     */
    class Listen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == newButtonPro) {
                dispose();
                //minimizes
                //setState ( Frame.ICONIFIED );
                HarrisFrame.setUserName();
            } else {
                System.out.println("BASIC VERSION NOT MADE YET");
                System.exit(0);
            }
        }
    }
}