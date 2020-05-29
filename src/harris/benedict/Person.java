package harris.benedict;

//returns REE, bmi, wieght, water needed, water left, protein/fats/carbs/cals needed (was a string, maybe we can do an object?)

import javax.swing.*;

public class Person {

    String name;
    String activity;
    String gender;
    String goal;
    String stress;
    double weight;
    double height;
    int age;



    public Person(String name, double weight, double height, String activity, String gender, int age, String goal, String stress) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.activity = activity;
        this.gender = gender;
        this.age = age;
        this.goal = goal;
        this.stress = stress;
    }

    /**
     * Returns the name of the current user
     *
     * @return the user's name
     */
    public String getName() {
        return this.name;
    }


    /**
     * Returns the stress level of the person
     * @return the stress level
     */
    public String getStress() {return this.stress;}

    /**
     * Provides a string representation of the user's information
     *
     * @return the string
     */
    @Override
    public String toString() {

        String name = this.name;
        String weight = "" + this.weight;
        String height = "" + this.height;
        String activity = this.activity;
        String gender = this.gender;
        String age = "" + this.age;

        return name + ", Weight: " + weight + ", Height " + height + ", Activity Level: " + activity + ", Gender: " + gender + ", Age: " + age + "Goal: " + goal;
    }
}