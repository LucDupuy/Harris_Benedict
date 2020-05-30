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
public class BasicPage extends JFrame {

    private static String name = "";
    private Person user;
    private static final double WEIGHTCONVERSION = 2.2;

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
    BasicPage() {
        setSize(900, 600);
        add(createMainPanel(), BorderLayout.LINE_START);
        add(createSidePanel(), BorderLayout.EAST);
        getRootPane().setDefaultButton(Common.getSubmit());
    }

    /**
     * This method sets the name of the user
     */
    protected static void setUserName() {

        String[] buttons = {"Create Profile", "Leave Name Blank"};

        JPanel panel = new JPanel();
        panel.add(Common.getNameLbl());
        panel.add(nameTxt);

        int result = JOptionPane.showOptionDialog(null, panel, "Create Profile", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, null);
        if (result == JOptionPane.YES_OPTION && nameTxt.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the missing information.");
            setUserName();
        } else if (result == JOptionPane.YES_OPTION) {
            name = nameTxt.getText();
            openMain();
        }
        if (result == JOptionPane.NO_OPTION) {
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

        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        mainPanel.add(Common.getPersonalLbl(), gbc);

        gbc.gridy = 1;
        mainPanel.add(Common.getGenderLbl(), gbc);

        gbc.gridy = 2;

        mainPanel.add(Common.getGenders(), gbc);

        gbc.gridy = 3;
        mainPanel.add(Common.getAgeLbl(), gbc);

        gbc.gridy = 4;
        mainPanel.add(ageBox, gbc);

        gbc.gridy = 5;
        mainPanel.add(Common.getWeightLbl(), gbc);

        gbc.gridy = 6;
        mainPanel.add(weightTxt, gbc);

        weightTxt.setColumns(5);

        gbc.gridy = 7;
        mainPanel.add(Common.getHeightLbl(), gbc);

        gbc.gridy = 8;
        mainPanel.add(heightTxt, gbc);

        gbc.gridy = 9;
        mainPanel.add(Common.getGoalsLbl(), gbc);

        gbc.gridy = 10;
        mainPanel.add(Common.getGoalsBox(), gbc);

        heightTxt.setColumns(5);

        gbc.gridy = 11;
        mainPanel.add(Common.getActivityLbl(), gbc);

        gbc.gridy = 12;
        mainPanel.add(Common.getActivityBoxBasic(), gbc);

        gbc.gridy = 13;

        gbc.gridy = 14;
        mainPanel.add(new JLabel(""), gbc);

        gbc.gridy = 15;
        gbc.fill = GridBagConstraints.LAST_LINE_START;
        mainPanel.add(Common.getSubmit(), gbc);
        Common.getSubmit().addActionListener(new Listen());

        gbc.gridy = 16;

        mainPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));
        mainPanel.setPreferredSize(new Dimension(400, 5));
        mainPanel.setBackground(Color.decode("#d7d5d5"));

        return mainPanel;
    }


    /**
     * This method creates the panel on the right hand side that will contain all of the calculated information
     *
     * @return the panel that gets added to the right hand side of the frame
     */
    private JPanel createSidePanel() {
        Common.getSidePanel(sidePanel);
        addSaveListener();
        return sidePanel;
    }

    /**
     * This method is needed to add the actionListener to the save button only once, avoiding
     * multiple save dialog pop ups
     */
    private void addSaveListener() {
        Common.getSave().addActionListener(new Listen());
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

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        sidePanel.add(Common.getResultsLbl(), gbc);

        JLabel goalTypeLbl = new JLabel("    Your goal is to: " + goalType);
        Common.customFontSize(goalTypeLbl);
        gbc.gridx = 0;
        gbc.gridy = 1;
        sidePanel.add(goalTypeLbl, gbc);

        JLabel REELbl = new JLabel("     Your *REE is: " + String.format("%.1f", REE) + " calories\n");
        Common.customFontSize(REELbl);
        gbc.gridx = 0;
        gbc.gridy = 2;
        sidePanel.add(REELbl, gbc);

        JLabel TEELbl = new JLabel("     Your *TEE is: " + String.format("%.1f", TEE) + " calories\n");
        Common.customFontSize(TEELbl);
        gbc.gridx = 0;
        gbc.gridy = 3;
        sidePanel.add(TEELbl, gbc);

        JLabel BMILbl = new JLabel("\n     Your *BMI is: " + String.format("%.1f", BMI));
        Common.customFontSize(BMILbl);
        gbc.gridx = 0;
        gbc.gridy = 4;
        sidePanel.add(BMILbl, gbc);

        JLabel caloriesNeeded = new JLabel("     You need to consume " + String.format("%.1f", goal) + " calories a day.");
        Common.customFontSize(caloriesNeeded);
        gbc.gridx = 0;
        gbc.gridy = 5;
        sidePanel.add(caloriesNeeded, gbc);

        JLabel proteinLbl = new JLabel("     " + protein);
        Common.customFontSize(proteinLbl);
        gbc.gridx = 0;
        gbc.gridy = 6;
        sidePanel.add(proteinLbl, gbc);

        JLabel fatsLbl = new JLabel("     " + fats);
        Common.customFontSize(fatsLbl);
        gbc.gridx = 0;
        gbc.gridy = 7;
        sidePanel.add(fatsLbl, gbc);

        JLabel carbsLbl = new JLabel("     " + carbs);
        Common.customFontSize(carbsLbl);
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
        sidePanel.add(Common.getSave(), gbc);


        JLabel definitionLbl = new JLabel("*Please click REE, TEE, or BMI for a definition");
        Common.customFontSize(definitionLbl);
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.WEST;
        sidePanel.add(definitionLbl, gbc);

        String strREE = "REE (or Resting Energy Expenditure), is the amount of calories one expends while at rest.";
        Common.LblListen(strREE, REELbl);
        String strTEE = "TEE (or Total Energy Expenditure), is the amount of calories burned when taking exercise into account.";
        Common.LblListen(strTEE, TEELbl);
        String strBMI = "BMI (or Body Mass Index), is a rule of thumb used to categorize one's weight as healthy or not.\nPlease note, that BMI is not one hundred percent accurate, as it is based on factors such as body weight percentage.";
        Common.LblListen(strBMI, BMILbl);
    }

    /**
     * Returns a string representation of the output to use for the save file
     *
     * @return the string
     */
    protected static ArrayList<String> getResults() {
        return Common.getResults(REE, BMI, TEE, goal, protein, fats, carbs);

    }

    /**
     * This class enables the user to click buttons and have certain actions performed.
     */
    class Listen implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

             if (!weightTxt.getText().matches("^[0-9]+$") || !heightTxt.getText().matches("^[0-9]+$") || !ageBox.getText().matches("^[0-9]+$")) {
                JOptionPane.showMessageDialog(null, "Please enter the numeric values only for height, weight and age.");
            } else if (e.getSource() == Common.getSubmit()) {
                user = new Person(name, (Double.parseDouble(weightTxt.getText()) / WEIGHTCONVERSION), Double.parseDouble(heightTxt.getText()), Objects.requireNonNull(Common.getActivityBoxBasic().getSelectedItem()).toString(), Objects.requireNonNull(Common.getGenders().getSelectedItem()).toString(), Integer.parseInt(ageBox.getText()), Objects.requireNonNull(Common.getGoalsBox().getSelectedItem()).toString());
                DataCalculations.setPALBasic(user);
                DataCalculations.setSF(false, "");
                DataCalculations.setVals(user);
                DataCalculations.setGoal(user);
                BMI = DataCalculations.getBMI();
                REE = DataCalculations.getREE();
                TEE = DataCalculations.getTEE();
                goal = DataCalculations.getGoal();
                DataCalculations.settingMacros(user);
                protein = DataCalculations.proteinToString();
                fats = DataCalculations.fatsToString();
                carbs = DataCalculations.carbsToString();

                sidePanel.removeAll();
                fillSidePanel(Common.getGoalsBox().getSelectedItem().toString());
                revalidate();


            } else if (e.getSource() == Common.getSave()) {
                SaveData.writeData(user.getName(), "Basic");
            }
        }
    }

    /**
     * Opens the main page of the application
     */
    protected static void openMain() {

        JFrame mainFrame = new BasicPage();
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
}