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
        int[] pref = {1, 2};
        int[] health = {3, 4};
        int[] diet = {5, 6};
        up = new UserProfile(userID, pref, health, diet);
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
        int[] pref = {1, 2};
        int[] health = {3, 4};
        int[] diet = {5, 6};
        UserProfile up2 = new UserProfile(userID, pref, health, diet);
        assertEquals(up, up2);
    }

    /**
     * Creates two unequal instances of a UserProfile, then
     * checks if equals() method properly returns.
     */
    @Test
    public void testEqualsNonIdentical() {
        int userID = 0;
        int[] pref = {5, 4};
        int[] health = {3, 4};
        int[] diet = {5, 6};
        UserProfile up2 = new UserProfile(userID, pref, health, diet);
        assertNotEquals(up, up2);
        int[] health2 = {4, 3};
        up2 = new UserProfile(userID, pref, health2, diet);
        assertNotEquals(up, up2);
        int[] diet2 = {3, 2};
        up2 = new UserProfile(userID, pref, health2, diet2);
        assertNotEquals(up, up2);
    }
}
