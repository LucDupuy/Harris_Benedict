package harris.benedict;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Common {

    /**
     * Arrays containing choices
     */
    private static final String[] genders = {"Male", "Female"};
    private static final String[] activitiesBasic = {"Light", "Moderate", "Vigorous"};
    private static final String[] activitiesPRO = {"Resting (1.0-1.4)", "Asleep/Sedated (0.9-1.1)", "Lying Still (1.0-1.1)", "Spinal cord injury (1.1)", "Bedrest (1.15-1.2)", "Mobilizing Occasionally (1.15-1.4)", "Sedentary/Light Activity (1.5-1.6)", "Mobilizing Frequently (1.4-1.5)", "Regular Physiotherapy (1.5-1.6)", "Moderate Activity (1.6-1.8)"};
    private static final String[] weightGoals = {"Lose Weight (0.5 lbs)", "Lose Weight (1.0 lbs)", "Lose Weight (1.5 lbs)", "Lose Weight (2.0 lbs)", "Maintain Weight", "Gain Weight (0.5 lbs)", "Gain Weight (1.0 lbs)", "Gain Weight (1.5 lbs)", "Gain Weight (2.0 lbs)"};
    private static final String[] stressLevel = {"No Stress (1.0)", "Cancer (0.8-1.5)", "Elective Surgery (1.0-1.1)", "Peritonitis (1.05-1.25)", "Multiple Bone Fractures (1.1-1.3)", "Fever (1.2 per 1\u00B0C > 37\u00B0C)", "Spinal Cord Injury (1.2)", "Sepsis (1.2-1.4)", "Severe Infection (1.2-1.6)", "Burns (1.2-2.0)", "Infection with trauma (1.3-1.55)", "Multiple Trauma/Traumatic Brain Injury (1.4))"};


    /**
     * Drop down boxes for the above choices
     */
    private static final JComboBox<String> genderBox = new JComboBox<>(genders);
    private static final JComboBox<String> activityBoxBasic = new JComboBox<>(activitiesBasic);
    private static final JComboBox<String> activityBoxPRO = new JComboBox<>(activitiesPRO);
    private static final JComboBox<String> goalsBox = new JComboBox<>(weightGoals);
    private static final JComboBox<String> stressLevelBox = new JComboBox<>(stressLevel);

    private static final JButton submit = new JButton("SUBMIT");
    private static final JButton save = new JButton("SAVE");


    /**
     * Getter Methods
     *
     */

    protected static JLabel getNameLbl() {
        JLabel lbl = new JLabel("Please enter your name: ");
        customFontSize(lbl);
        return lbl;
    }

    protected static JLabel getGenderLbl() {
        JLabel lbl = new JLabel("      Please select your sex             ");
        customFontSize(lbl);
        return lbl;
    }

    protected static JLabel getWeightLbl() {
        JLabel lbl =  new JLabel("\n     Please enter weight in pounds        ");
        customFontSize(lbl);
        return lbl;
    }

    protected static JLabel getHeightLbl() {
        JLabel lbl =  new JLabel("\n     Please enter height in centimetres        ");
        customFontSize(lbl);
        return lbl;
    }

    protected static JLabel getActivityLbl() {
        JLabel lbl =  new JLabel("\n     Please select Your level of typical daily activity");
        customFontSize(lbl);
        return lbl;
    }

    protected static JLabel getAgeLbl() {
        JLabel lbl =  new JLabel("\n     Please enter your age                        ");
        customFontSize(lbl);
        return lbl;
    }

    protected static JLabel getGoalsLbl() {
        JLabel lbl =  new JLabel("\n     Please select your goal (in a week)");
        customFontSize(lbl);
        return lbl;
    }

    protected static JLabel getPersonalLbl() {
        JLabel lbl = new JLabel("<HTML><U>PERSONAL INFORMATION</U></HTML>", SwingConstants.CENTER);
        customFontSize(lbl);
        return lbl;
    }

    protected static JLabel getResultsLbl() {
        JLabel lbl =  new JLabel("<HTML><U>RESULTS</U></HTML>", SwingConstants.CENTER);
        customFontSize(lbl);
        return lbl;
    }

    protected static JLabel getStressLbl() {
       JLabel lbl   = new JLabel("\n     Please select patient's stress level");
       customFontSize(lbl);
       return lbl;
    }


    protected static JComboBox<String>getGenders() {
        genderBox.setRenderer(new ComboBoxFontSize());
        return genderBox;
    }

    protected static JComboBox<String>getActivityBoxBasic() {
        activityBoxBasic.setRenderer(new ComboBoxFontSize());
        return activityBoxBasic;
    }

    protected static JComboBox<String>getGoalsBox() {
        goalsBox.setRenderer(new ComboBoxFontSize());
        return goalsBox;
    }

    protected static JComboBox<String>getActivityBoxPro() {
        activityBoxPRO.setRenderer(new ComboBoxFontSize());
        return activityBoxPRO;
    }

    protected static JComboBox<String>getStressLevelBox(){
        stressLevelBox.setRenderer(new ComboBoxFontSize());
        return stressLevelBox;
    }

    protected static JButton getSubmit(){
        return submit;
    }

    protected static JButton getSave(){
        return save;
    }

    protected static ArrayList<String> getResults(double REE, double BMI, double TEE, double goal, String protein, String fats, String carbs) {

        ArrayList<String> list = new ArrayList<>();


        list.add("Your REE is:         " + String.format("%.1f", REE) + "\n");
        list.add("Your BMI is:         " + String.format("%.1f", BMI) + "\n");
        list.add("Your TEE is:         " + String.format("%.1f", TEE) + "\n");
        list.add("Calories needed :         " + String.format("%.1f", goal) + "\n\n");
        list.add(protein + "\n");
        list.add(fats + "\n");
        list.add(carbs + "\n" + "\n" + "\n");
        list.add("*Please remember that these figures are not set in stone, and before making any\n");
        list.add("major changes to Your diet, you should consult a dietitian or physician first");
        list.add("\n\n------------------------------------------\n");
        list.add(java.time.LocalDate.now() + "");

        return list;
    }


    /*********************************************************************************************
     * Title: Set text size of JComboBox in Swing
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
     * Change the font of the given label
     */
    protected static void customFontSize(JLabel lbl) {
        lbl.setFont(lbl.getFont().deriveFont(16.0f));
    }
}