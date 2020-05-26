package database;

import database.Ingredient;
import database.IngredientGroup;
import database.UserProfile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserProfileTest {
    UserProfile up;

    /**
     * Initializes the UserProfile
     */
    @Before
    public void initialize() {
        int userID = 0;
        int[] pref = new int[11];
        int[] health = new int[9];
        int[] diet = new int[5];
        int[] meal = new int[3];
        up = new UserProfile(userID, pref, health, diet, meal);
    }

    /**
     * Creates an instance of the UserProfile and
     * if the creation is not null, then it works.
     */
    @Test
    public void testConstructor() {
        assertNotEquals(null, up);
    }

    /**
     * Creates two equal instances of a UserProfile, then
     * checks if equals() method properly returns.
     */
    @Test
    public void testEqualsIdentical() {
        int userID = 0;
        int[] pref = new int[11];
        int[] health = new int[9];
        int[] diet = new int[5];
        int[] meal = new int[3];
        UserProfile up2 = new UserProfile(userID, pref, health, diet, meal);
        assertEquals(up, up2);
    }

    /**
     * Creates two unequal instances of a UserProfile, then
     * checks if equals() method properly returns.
     */
    @Test
    public void testEqualsNonIdentical() {
        int userID = 0;
        int[] pref = new int[11];
        int[] health = new int[9];
        int[] diet = new int[5];
        int[] meal = new int[3];
        UserProfile up2 = new UserProfile(userID, pref, health, diet, meal);
        assertEquals(up, up2);

        int[] pref2 = {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        up2 = new UserProfile(userID, pref2, health, diet, meal);
        assertNotEquals(up, up2);

        int[] health2 = {-2, 0, 0, 0, 0, 0, 0, 0, 0};
        up2 = new UserProfile(userID, pref2, health2, diet, meal);
        assertNotEquals(up, up2);

        int[] diet2 = {-3, 0, 0, 0, 0};
        up2 = new UserProfile(userID, pref, health2, diet2, meal);
        assertNotEquals(up, up2);

        int[] meal2 = {-4, 0, 0};
        up2 = new UserProfile(userID, pref, health2, diet2, meal2);
        assertNotEquals(up, up2);
    }
}
