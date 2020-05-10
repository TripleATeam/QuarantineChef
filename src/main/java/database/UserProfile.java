package database;

import java.util.Arrays;

public class UserProfile {
    public int userID;
    public int[] preferences;
    public int[] health;
    public int[] diet;

    // Precondition: Username, preferences, health, diet != null
    // Every element in preferences is <=10 and >=0.
    // Every element in health and diet is either 0 or 1.
    public UserProfile(int userID, int[] preferences, int[] health, int[] diet) {
        this.userID = userID;
        this.preferences = preferences;
        this.health = health;
        this.diet = diet;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UserProfile)) {
            return false;
        }
        UserProfile otherPan = (UserProfile) other;
        boolean bool1 = Arrays.equals(preferences, otherPan.preferences);
        boolean bool2 = Arrays.equals(health, otherPan.health);
        boolean bool3 = Arrays.equals(diet, otherPan.diet);
        return bool1 && bool2 && bool3;
    }
}