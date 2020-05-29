package harris.benedict;



import javax.swing.*;

public class DataCalculations {

    /**
     * Constants for the equations
     */
    //private static final double proteinDivide = 4;
    private static final double carbDivide = 4;
    private static final double fatDivide = 9;

    /**
     * Average amounts needed
     */
    private static double protein;
    private static double fats;
    private static double carbs;
    /**
     * Hold the values used
     */
    static double PAL;
    static double BMI;
    static double REE;
    static double goal;
    static double SF;
    static double TEE;


    /**
     * Setting the factor of activity for the user
     *
     * @param person the current user
     */
    public static void setPAL(Person person) {
        String val = person.activity;

        if (val == "Light") {
            PAL = 1.53;
        } else if (val == "Moderate") {
            PAL = 1.76;
        } else if (val == "Vigorous") {
            PAL = 2.25;
        }
    }

    /**
     * Setting the factor of activity for the user
     *
     * @param person the current user
     */
    public static void setPALPro(Person person) {
        String val = person.activity;
            PAL = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter a value for patient's activity factor", "1.0"));
    }


    protected static void setSF(Person person) {
        SF = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter a value for patient's stress factor", "1.0"));

    }

    /**
     * Setting the BMI for the current user
     *
     * @param person the current user
     */
    public static void setBMI(Person person) {
        BMI = (person.weight) / (Math.pow((person.height / 100), 2));
    }

    /**
     * Setting the REE for the user
     *
     * @param person the current user
     */
    public static void setREE(Person person) {
        if (person.gender == "Male") {
            REE = (66.5 + (13.75 * (person.weight)) + (5.003 * person.height) - (6.755 * person.age));
        } else {
            REE = (655.1 + (9.563 * (person.weight)) + (1.850 * person.height) - (4.676 * person.age));
        }
    }


    protected static void setTEE() {
        TEE = REE * PAL * SF;
    }


    /**
     * Setting the weight goal
     */
    protected static void setGoal(Person person) {

        if (person.goal.equals("Lose Weight (0.5 lbs)")) {
            goal = TEE - (1750 / 7.0);
        } else if (person.goal.equals("Lose Weight (1.0 lbs)")) {
            goal = TEE - (3500 / 7.0);
        } else if (person.goal.equals("Lose Weight (1.5 lbs)")) {
            goal = TEE - (5250 / 7.0);
        } else if (person.goal.equals("Lose Weight (2.0 lbs)")) {
            goal = TEE - (7000 / 7.0);
        } else if (person.goal.equals("Maintain Weight")) {
            goal = TEE;
        } else if (person.goal.equals("Gain Weight (0.5 lbs)")) {
            goal = TEE + (1750 / 7.0);
        } else if (person.goal.equals("Gain Weight (1.0 lbs)")) {
            goal = TEE + (3500 / 7.0);
        } else if (person.goal.equals("Gain Weight (1.5 lbs)")) {
            goal = TEE + (5250 / 7.0);
        } else if (person.goal.equals("Gain Weight (2.0 lbs)")) {
            goal = TEE + (7000 / 7.0);
        }
    }


    /**
     * This will create the average amount of protein, carbs, fats needed as well as calories needed to maintain your weight
     * (add something about how to gain / lose)
     *
     * @return the string representation value of the macros
     */

    static void settingMacros(double REE, Person person) {

        double min_protein;
        double max_protein;
        double min_carbs;
        double max_carbs;
        double min_fats;
        double max_fats;

        //Min and maxes are the cals

        //0.8 - 1 g per kilo

        min_protein = person.weight * 0.8;
        max_protein = person.weight * 1;
        min_fats = (goal * 0.2) / fatDivide;
        max_fats = (goal * 0.35) / fatDivide;
        min_carbs = (goal * 0.45) / carbDivide;
        max_carbs = (goal * 0.65) / carbDivide;

//THIS IS AVERAGE NEEDED
        protein = (min_protein + max_protein) / 2;
        carbs = (min_carbs + max_carbs) / 2;
        fats = (min_fats + max_fats) / 2;
    }


    /**
     * This method creates a string representation of the protein needed
     *
     * @return the string
     */
    static String proteinToString() {
        return "Protein Needed: " + String.format("%.1f", protein) + " grams";
    }

    /**
     * This method creates a string representation of the fats needed
     *
     * @return the string
     */
    static String fatsToString() {
        return "Fats Needed: " + String.format("%.1f", fats) + " grams";
    }

    /**
     * This method creates a string representation of the carbs needed
     *
     * @return the string
     */
    static String carbsToString() {
        return "Carbs Needed: " + String.format("%.1f", carbs) + " grams";
    }

    /**
     * Getter method for the user's PAL
     *
     * @return the user's PAL
     */
     static double getPAL() {
        return PAL;
    }

    /**
     * Getter method for the user's BMI
     *
     * @return the user's BMI
     */
    static double getBMI() {
        return BMI;
    }

    /**
     * Getter method for the user's REE
     *
     * @return the user's REE
     */
    static double getREE() {
        return REE;
    }

    static double getTEE() {
        return TEE;
    }


    /**
     * Getter for weight goal
     *
     * @return the goal in calories
     */
    static double getGoal() { return goal; }
}
