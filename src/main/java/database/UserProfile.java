package database;

import java.util.Arrays;

public class UserProfile {
    public int userID;
    public String googleUserID;
    public int[] preferences;  // American, Asian, Caribbean, Chinese, French, Indian, Italian, Japanese, Mediterranean, Mexican, Middle Eastern
    public int[] health;  // Dairy-free, Gluten-free, Keto, Kosher, Low Sugar, Paleo, Peanut-free, Vegan, Vegetarian
    public int[] diet;  // High Fiber, High Protein, Low Carb, Low Fat, Low Sodium
    public int[] meal;  // Breakfast, Lunch, Dinner

    /**
     * Returns a UserProfile with the given userID, cuisine preferences,
     * health restrictions, and diet types, respective to the passed parameters.
     *
     * @param userID        an int representing the ID for a user
     * @param preferences   a non-null array of integers representing
     *                      the cuisine preferences of the user
     *                      (Every element is between 0 and 10).
     * @param health        a non-null array of integers representing
     *                      the health requirements of the user
     *                      (0 = not a requirement, 1 = a requirement).
     * @param diet          a non-null array of integers representing the
     *                      diets the user is on (0 = not on diet, 1 = on diet).
     */
    public UserProfile(int userID, int[] preferences, int[] health, int[] diet, int[] meal) {
        this.userID = userID;
        this.preferences = preferences;
        this.health = health;
        this.diet = diet;
        this.meal = meal;
    }
    public UserProfile(int userID, int[] preferences, int[] health, int[] diet, int[] meal, String googleUserID) {
        this(userID, preferences, health, diet, meal);
        this.googleUserID = googleUserID;

    }

    /**
     * Returns a boolean denoting if another Object is equal to this one.
     * If the other Object is anything other than a Pantry with the exact
     * same values in the same fields, this will be false. If it is such a
     * Pantry, then this method will return true.
     *
     * @param other     Another object being tested for equality.
     * @return          a boolean representing if other is equal to this object.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UserProfile)) {
            return false;
        }
        UserProfile otherPan = (UserProfile) other;
        boolean bool1 = Arrays.equals(preferences, otherPan.preferences);
        boolean bool2 = Arrays.equals(health, otherPan.health);
        boolean bool3 = Arrays.equals(diet, otherPan.diet);
        boolean bool4 = Arrays.equals(meal, otherPan.meal);
        return bool1 && bool2 && bool3 && bool4;
    }
}