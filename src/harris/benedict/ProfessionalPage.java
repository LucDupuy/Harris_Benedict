package harris.benedict;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class creating the main page of the program and bringing everything together
 */
public class ProfessionalPage extends JFrame {


    /**
     * Holds the name of the person
     */
    private static String name = "";

    /**
     * Person object to store information
     */
    private Person user;

    /**
     * Arrays containing choices
     */
    private static final String[] genders = {"Male", "Female"};
    private static final String[] activitiesPRO = {"Resting", "Asleep/Sedated", "Lying Still", "Spinal cord injury", "Bedrest", "Mobilizing Occasionally", "Sedentary/Light Activity", "Mobilizing Frequently", "Regular Physiotherapy", "Moderate Activity"};
    private static final String[] weightGoals = {"Lose Weight (0.5 lbs)","Lose Weight (1.0 lbs)","Lose Weight (1.5 lbs)","Lose Weight (2.0 lbs)", "Maintain Weight", "Gain Weight (0.5 lbs)","Gain Weight (1.0 lbs)","Gain Weight (1.5 lbs)","Gain Weight (2.0 lbs)"};
    private static final String[] stressLevel = {"No Stress", "Cancer", "Elective Surgery", "Peritonitis", "Multiple bone Fractures", "Fever", "Spinal Cord Injury", "Sepsis", "Severe Infection", "Burns", "Infection with trauma", "Multiple Trauma/Traumatic Brain Injury"};

    /**
     * Boxes visualising those choices
     */
    private static final JComboBox<String> genderBox   = new JComboBox<>(genders);
    private static final JComboBox<String> activityBoxPro = new JComboBox<>(activitiesPRO);
    private static final JComboBox<String> stressBox   = new JComboBox<>(stressLevel);
    private static final JComboBox<String> goalsBox    = new JComboBox<>(weightGoals);


    /**
     * Button used to submit the information
     */
    private static final JButton submit = new JButton("SUBMIT");
    private static final JButton save = new JButton("SAVE");
    private static final JButton home = new JButton("  HOME ");

    /**
     * Labels to explain each part of the page
     */
    private static final JLabel nameLbl     = new JLabel("Please enter patient's name: ");
    private static final JLabel genderLbl   = new JLabel("       Please select patient's sex             ");
    private static final JLabel weightLbl   = new JLabel("\n     Please enter weight in pounds        ");
    private static final JLabel heightLbl   = new JLabel("\n     Please enter height in centimetres ");
    private static final JLabel activityLbl = new JLabel("\n     Please select patient's level of typical daily activity");
    private static final JLabel ageLbl      = new JLabel("\n     Please enter patient's age                        ");
    private static final JLabel goalsLbl    = new JLabel("\n     Please select patient's goal (in a week)");
    private static final JLabel stressLbl   = new JLabel("\n     Please select patient's stress level");
    JLabel personalInfo = new JLabel("<HTML><U>PERSONAL INFORMATION</U></HTML>", SwingConstants.CENTER);
    JLabel resultsLbl = new JLabel("<HTML><U>RESULTS</U></HTML>", SwingConstants.CENTER);

    /**
     * Text fields to input info
     */
    private static final JTextField weightTxt = new JTextField("0");
    private static final JTextField heightTxt = new JTextField("0");
    private static final JTextField nameTxt   = new JTextField(10);
    private static final JTextField ageBox    = new JTextField("0");

    /**
     * Variables to hold the values of the user's PAL, BMI and REE
     */
    private static double REE;
    private static double TEE;
    private static double BMI;
    private static double goal;

    /**
     * String representation of the macros needed
     */
    private static String protein;
    private static String fats;
    private static String carbs;

    /**
     * Creating the two panels of the main screen
     */
    JPanel mainPanel = new JPanel();
    JPanel sidePanel = new JPanel();
    JPanel centerPanel = new JPanel();

    /**
     * Constructor method for the frame
     */
    ProfessionalPage() {
        setSize(1000, 600);
        add(createMainPanel(), BorderLayout.LINE_START);
        add(createSidePanel(), BorderLayout.EAST);
        getRootPane().setDefaultButton(submit);
    }

    /**
     * This method sets the name of the user
     */
    protected static void setUserName() {

        String[] buttons = {"Create Profile", "Leave Name Blank"};

        JPanel panel = new JPanel();
        panel.add(nameLbl);
        panel.add(nameTxt);

        int result = JOptionPane.showOptionDialog(null, panel, "Create Profile", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null, buttons, null);
        if (result == JOptionPane.YES_OPTION && nameTxt.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter the missing information.");
            setUserName();
        } else if (result == JOptionPane.YES_OPTION) {
            name = nameTxt.getText();
            openMain();
        } if (result == JOptionPane.NO_OPTION) {
        name = "";
        openMain();
        } else if (result == JOptionPane.CLOSED_OPTION) {
            openHome();
        }
    }

    /**
     * This method creates the main panel to display the information on the left hand side
     *
     * @return the panel that gets added to the frame
     */
    private JPanel createMainPanel() {


        GridBagConstraints gbc = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;


        customFontSize(personalInfo);
        customFontSize(genderLbl);
        customFontSize(ageLbl);
        customFontSize(weightLbl);
        customFontSize(heightLbl);
        customFontSize(goalsLbl);
        customFontSize(activityLbl);
        customFontSize(stressLbl);


        genderBox.setRenderer(new ComboBoxFontSize());
        goalsBox.setRenderer(new ComboBoxFontSize());
        activityBoxPro.setRenderer(new ComboBoxFontSize());
        stressBox.setRenderer(new ComboBoxFontSize());

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        mainPanel.add(personalInfo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(genderLbl, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        mainPanel.add(genderBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(ageLbl, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(ageBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(weightLbl, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(weightTxt, gbc);


        weightTxt.setColumns(5);

        gbc.gridx = 0;
        gbc.gridy = 7;
        mainPanel.add(heightLbl, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        mainPanel.add(heightTxt, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        mainPanel.add(goalsLbl, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        mainPanel.add(goalsBox, gbc);


        heightTxt.setColumns(5);

        gbc.gridx = 0;
        gbc.gridy = 11;
        mainPanel.add(activityLbl, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        mainPanel.add(activityBoxPro, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        mainPanel.add(stressLbl, gbc);

        gbc.gridx = 0;
        gbc.gridy = 14;
        mainPanel.add(stressBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 15;
        mainPanel.add(new JLabel(""), gbc);

        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.fill = GridBagConstraints.LAST_LINE_START;
        mainPanel.add(submit, gbc);
        submit.addActionListener(new Listen());

        gbc.gridx = 0;
        gbc.gridy = 17;

        mainPanel.add(home, gbc);
        home.addActionListener(new Listen());

        mainPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));
        mainPanel.setPreferredSize(new Dimension(450, 5));
        mainPanel.setBackground( Color.decode("#d7d5d5"));

        return mainPanel;
    }


    /**
     * This method creates the panel on the right hand side that will contain all of the calculated information
     *
     * @return the panel that gets added to the right hand side of the frame
     */
    private JPanel createSidePanel() {

        sidePanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));
        sidePanel.setPreferredSize(new Dimension(450, 5));
        sidePanel.setVisible(false);
        sidePanel.setBackground( Color.decode("#d7d5d5"));
        addSaveListener();


        return sidePanel;
    }


    /**
     * This method is needed to add the actionListener to the save button only once, avoiding
     * multiple save dialog pop ups
     */
    private void addSaveListener() {
        save.addActionListener(new Listen());
    }


    /**
     * This method populates the side panel of the window with the completed calculations
     */


    private void fillSidePanel(String goalType) {

    GridBagConstraints gbc = new GridBagConstraints();
    sidePanel.setLayout(new GridBagLayout());

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTHWEST;

        sidePanel.setVisible(true);
        centerPanel.setVisible(true);

        customFontSize(resultsLbl);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        sidePanel.add(resultsLbl, gbc);

        JLabel goalTypeLbl = new JLabel("     Patient's goal is to: " + goalType);
        customFontSize(goalTypeLbl);
        gbc.gridx = 0;
        gbc.gridy = 1;
        sidePanel.add(goalTypeLbl, gbc);

        JLabel REELbl = new JLabel("     Patient's *REE is: " + String.format("%.1f", REE) + " calories\n");
        customFontSize(REELbl);
        gbc.gridx = 0;
        gbc.gridy = 2;
        sidePanel.add(REELbl, gbc);

        JLabel TEELbl = new JLabel("     Patient's *TEE is: " + String.format("%.1f", TEE) + " calories\n");
        customFontSize(TEELbl);
        gbc.gridx = 0;
        gbc.gridy = 3;
        sidePanel.add(TEELbl, gbc);

        JLabel BMILbl = new JLabel("\n     Patient's *BMI is: " + String.format("%.1f", BMI));
        customFontSize(BMILbl);
        gbc.gridx = 0;
        gbc.gridy = 4;
        sidePanel.add(BMILbl, gbc);

        JLabel caloriesNeeded = new JLabel("     Patient needs to consume " + String.format("%.1f", goal) + " calories a day.");
        customFontSize(caloriesNeeded);
        gbc.gridx = 0;
        gbc.gridy = 5;
        sidePanel.add(caloriesNeeded, gbc);

        JLabel proteinLbl = new JLabel("     " + protein);
        customFontSize(proteinLbl);
        gbc.gridx = 0;
        gbc.gridy = 6;
        sidePanel.add(proteinLbl, gbc);

        JLabel fatsLbl = new JLabel("     " + fats);
        customFontSize(fatsLbl);
        gbc.gridx = 0;
        gbc.gridy = 7;
        sidePanel.add(fatsLbl, gbc);

        JLabel carbsLbl = new JLabel("     " + carbs);
        customFontSize(carbsLbl);
        gbc.gridx = 0;
        gbc.gridy = 8;
        sidePanel.add(carbsLbl, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        sidePanel.add(new JLabel(""), gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 0;
        gbc.fill = GridBagConstraints.CENTER;
        sidePanel.add(save, gbc);



        JLabel definitionLbl = new JLabel("*Please click REE, TEE, or BMI for a definition");
        customFontSize(definitionLbl);
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.WEST;
        sidePanel.add(definitionLbl, gbc);

        String strREE = "REE (or Resting Energy Expenditure), is the amount of calories one expends while at rest.";
        LblListen(strREE, REELbl);
        String strTEE = "Total Energy Expenditure";
        LblListen(strTEE, TEELbl);
        String strBMI = "BMI (or Body Mass Index), is a rule of thumb used to categorize one's weight as healthy or not.\nPlease note, that BMI is not one hundred percent accurate, as it is based on factors such as body weight percentage.";
        LblListen(strBMI, BMILbl);
    }

    /**
     * Returns a string representation of the output to use for the save file
     *
     * @return the string
     */
    protected static ArrayList<String> getResults() {

        ArrayList<String> list = new ArrayList<>();


       list.add("Patient's REE is:         " + String.format("%.1f", REE) + "\n");
       list.add("Patient's BMI is:         " + String.format("%.1f", BMI) + "\n");
       list.add("Patient's TEE is:         " + String.format("%.1f", TEE) + "\n");
       list.add("Calories needed :         " + String.format("%.1f", goal)+ "\n\n");
       list.add(protein + "\n");
       list.add(fats + "\n");
       list.add( carbs + "\n" + "\n" + "\n");
       list.add("*Please remember that these figures are not set in stone, and before making any\n");
       list.add("major changes to patient's diet, you should consult a dietitian or physician first");
       list.add("\n\n------------------------------------------\n");
       list.add(java.time.LocalDate.now() + "");

       return list;
    }

    /**
     * Method to separate the adding of mouse listeners to the REE and BMI JLabels
     */
    private static void LblListen(String msg, JLabel lbl) {
        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, msg);
            }
        });

    }

    /**
     * Change the font of the given label
     */
        private static void customFontSize(JLabel lbl){
            lbl.setFont(lbl.getFont().deriveFont(16.0f));
        }


    /**
     * This class enables the user to click buttons and have certain actions performed.
     */
    class Listen implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submit && weightTxt.getText().equals("") || heightTxt.getText().equals("") || ageBox.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the missing information.");
            } else if (!weightTxt.getText().matches("^[0-9]+$") ||  !heightTxt.getText().matches("^[0-9]+$") ||  !ageBox.getText().matches("^[0-9]+$")) {
                JOptionPane.showMessageDialog(null, "Please enter the numeric values only for height, weight and age.");
            } else if (e.getSource() == submit) {
                user = new Person(name, (Double.parseDouble(weightTxt.getText()) / 2.2), Double.parseDouble(heightTxt.getText()), Objects.requireNonNull(activityBoxPro.getSelectedItem()).toString(), Objects.requireNonNull(genderBox.getSelectedItem()).toString(), Integer.parseInt(ageBox.getText()), Objects.requireNonNull(goalsBox.getSelectedItem()).toString(), Objects.requireNonNull(stressBox.getSelectedItem()).toString());
                DataCalculations.setPALPro();
                DataCalculations.setSF(true);
                DataCalculations.setVals(user);
                DataCalculations.setGoal(user);
                BMI = DataCalculations.getBMI();
                REE = DataCalculations.getREE();
                TEE = DataCalculations.getTEE();
                goal = DataCalculations.getGoal();
                DataCalculations.settingMacros(user);
                protein = DataCalculations.proteinToString();
                fats    = DataCalculations.fatsToString();
                carbs   = DataCalculations.carbsToString() ;


                sidePanel.removeAll();
                fillSidePanel(goalsBox.getSelectedItem().toString());
                revalidate();


            } else if (e.getSource() == save) {
                SaveData.writeData(user.getName());
            } else if (e.getSource() == home) {
                try {
                    restartApp();
                } catch (IOException error) {
                    JOptionPane.showMessageDialog(null, "Error RE01: Application cannot restart. Please contact dev team", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }


    /*********************************************************************************************
     * Title: Set text sie of JComboBox in Swing
     * Author: Thompson, A
     * Date: September 9 2013
     * Availability: https://stackoverflow.com/questions/18704022/set-text-size-of-jcombobox-in-swing
     *********************************************************************************************/
    static class ComboBoxFontSize extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList list, Object obj, int i, boolean isSelected, boolean bool2) {

            JLabel lbl = (JLabel) super.getListCellRendererComponent(list, obj, i, isSelected, bool2);
            Font font = new Font((String) obj, Font.BOLD, 16);
            lbl.setFont(font);
            return lbl;
        }
    }



    /**
     * Opens the main page of the application
     */
    protected static void openMain() {

        JFrame mainFrame = new ProfessionalPage();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setTitle("TEE Calculator");
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);

    }


    /**
     * Opens the home page
     */
    protected static void openHome() {
        JFrame frame = new HomePage();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TEE Calculator");
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }


    /**
     * Restarts when we go back to home page, in order to deal with pop up errors
     */
    protected static void restartApp() throws IOException {
        Runtime.getRuntime().exec("java -jar Harris_Benedict_Calculator.jar");
        System.exit(0);
    }
}