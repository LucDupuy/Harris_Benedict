package harris.benedict;

/**
 * This class creates the user object and contains getter and setter methods for the information stored
 */
public class Person {

    String name;
    String activity;
    String gender;
    String goal;
    String stress;
    double weight;
    double height;
    int age;


    /**
     * Creates a user object for data to be stored in (PRO)
     *
     * @param name     name of the user
     * @param weight   weight of the user
     * @param height   height of the user
     * @param activity the activity level of the user
     * @param gender   the gender of the user
     * @param age      the age of the user
     * @param goal     the goal calories for the user
     * @param stress   the stress level of the user
     */
    protected Person(String name, double weight, double height, String activity, String gender, int age, String goal, String stress) {
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
     * Creates a user object for data to be stored in (BASIC)
     *
     * @param name     name of the user
     * @param weight   weight of the user
     * @param height   height of the user
     * @param activity the activity level of the user
     * @param gender   the gender of the user
     * @param age      the age of the user
     * @param goal     the goal calories for the user
     */
    protected Person(String name, double weight, double height, String activity, String gender, int age, String goal) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.activity = activity;
        this.gender = gender;
        this.age = age;
        this.goal = goal;
    }

    /**
     * Returns the name of the current user
     *
     * @return the user's name
     */
    protected String getName() {
        return this.name;
    }

    /**
     * Getter method for goal calories
     * @return the user's goal calories
     */
    protected String getGoal() {
        return this.goal;
    }


    /**
     * Returns the user's weight
     * @return the user's weight
     */
    protected double getWeight() {
        return this.weight;
    }

    /**
     * Returns the user's height
     * @return the user's height
     */
    protected double getHeight() {
        return this.height;
    }

    /**
     * Returns the user's age
     * @return the user's age
     */
    protected int getAge() {
        return this.age;
    }

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