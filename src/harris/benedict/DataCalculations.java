package harris.benedict;


import javax.swing.*;


/**
 * All of the calculations done by the program
 */
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
    static double goalCalories;
    static double SF;
    static double TEE;

    /**
     * Setting the factor of activity for the user (BASIC)
     *
     * @param person the current user
     */
    protected static void setPALBasic(Person person) {
        switch (person.activity) {
            case "Light":
                PAL = 1.53;
                break;
            case "Moderate":
                PAL = 1.76;
                break;
            case "Vigorous":
                PAL = 2.25;
                break;
        }
    }


    /**
     * Setting the factor of activity for the user (PRO)
     */
    protected static void setPALPro(String activity) {
        boolean correct = false;

        while (!correct) {
            try {
                PAL = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter a value for patient's activity factor: " + activity, "1.0"));
                correct = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter numeric values only", "Warning", JOptionPane.WARNING_MESSAGE);
            } catch (NullPointerException ex) {
                System.out.println("Closed Dialog");
            }
        }
    }


    /**
     * Setting the stress factor for the user (PRO)
     *
     */
    protected static void setSF(Boolean bool, String stress) {

        //For basic users
        if (!bool) {
            SF = 1.0;
            return;
        }

        boolean correct = false;

        while (!correct) {
            try {
                SF = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter a value for patient's stress factor: " + stress, "1.0"));
                correct = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter numeric values only", "Warning", JOptionPane.WARNING_MESSAGE);
            } catch (NullPointerException ex) {
                System.out.println("Closed Dialog");
            }
        }
    }

    /**
     * Setting the REE, TEE and BMI for the user
     *
     * @param person the current user
     */
    protected static void setVals(Person person) {

        BMI = (person.getWeight()) / (Math.pow((person.getHeight() / 100), 2));

        if (person.gender.equals("Male")) {
            REE = (66.5 + (13.75 * (person.getWeight())) + (5.003 * person.getHeight()) - (6.755 * person.getAge()));
        } else {
            REE = (655.1 + (9.563 * (person.getWeight())) + (1.850 * person.getHeight()) - (4.676 * person.getAge()));
        }
        TEE = REE * PAL * SF;
    }


    /**
     * Setting the weight goal
     */
    protected static void setGoal(Person person) {

        String strGoal = person.getGoal();

        switch (strGoal) {
            case "Lose Weight (0.5 lbs)":
                goalCalories = TEE - (1750 / 7.0);
                break;
            case "Lose Weight (1.0 lbs)":
                goalCalories = TEE - (3500 / 7.0);
                break;
            case "Lose Weight (1.5 lbs)":
                goalCalories = TEE - (5250 / 7.0);
                break;
            case "Lose Weight (2.0 lbs)":
                goalCalories = TEE - (7000 / 7.0);
                break;
            case "Maintain Weight":
                goalCalories = TEE;
                break;
            case "Gain Weight (0.5 lbs)":
                goalCalories = TEE + (1750 / 7.0);
                break;
            case "Gain Weight (1.0 lbs)":
                goalCalories = TEE + (3500 / 7.0);
                break;
            case "Gain Weight (1.5 lbs)":
                goalCalories = TEE + (5250 / 7.0);
                break;
            case "Gain Weight (2.0 lbs)":
                goalCalories = TEE + (7000 / 7.0);
                break;
        }
    }


    /**
     * This will create the average amount of protein, carbs, fats needed as well as calories needed to maintain your weight
     * (add something about how to gain / lose)
     *
     * @return the string representation value of the macros
     */

    protected static void settingMacros(Person person) {

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
        min_fats = (goalCalories * 0.2) / fatDivide;
        max_fats = (goalCalories * 0.35) / fatDivide;
        min_carbs = (goalCalories * 0.45) / carbDivide;
        max_carbs = (goalCalories * 0.65) / carbDivide;

        //THIS IS AVERAGE NEEDED
        protein = (min_protein + max_protein) / 2;
        carbs = (min_carbs + max_carbs) / 2;
        fats = (min_fats + max_fats) / 2;
    }

    protected static String proteinToString() {
        return "Protein Needed: " + String.format("%.1f", protein) + " grams";
    }

    protected static String fatsToString() {
        return "Fats Needed: " + String.format("%.1f", fats) + " grams";
    }

    protected static String carbsToString() {
        return "Carbs Needed: " + String.format("%.1f", carbs) + " grams";
    }

    protected static double getBMI() {
        return BMI;
    }

    protected static double getREE() {
        return REE;
    }

    protected static double getTEE() {
        return TEE;
    }

    protected static double getGoal() {
        return goalCalories;
    }
}
