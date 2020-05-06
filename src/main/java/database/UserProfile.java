package database;

public class UserProfile {
    public String username;
    public int[] preferences;
    public int[] health;
    public int[] diet;

    // Precondition: Username, preferences, health, diet != null
    // Every element in preferences is <=10 and >=0.
    // Every element in health and diet is either 0 or 1.
    public UserProfile(String username, int[] preferences, int[] health, int[] diet) {
        this.username = username;
        this.preferences = preferences;
        this.health = health;
        this.diet = diet;
    }
}