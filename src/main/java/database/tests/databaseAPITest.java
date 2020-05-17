package database.tests;

import database.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static database.databaseAPI.*;
import static org.junit.Assert.*;

public class databaseAPITest {
    UserProfile up;
    Pantry pantry;

    @BeforeAll
    public void initialize() {
        int userID = 0;
        int[] pref = {1, 2};
        int[] health = {3, 4};
        int[] diet = {5, 6};
        up = new UserProfile(userID, pref, health, diet);

        Ingredient ing1 = new Ingredient("Salt", IngredientGroup.ETC);
        Ingredient ing2 = new Ingredient("Pepper", IngredientGroup.BAKING);
        Ingredient[] ings = {ing1, ing2};
        String[] exps = {"1-1-2020", "5-1-1900"};
        int[] quants = {29, 13};
        pantry = new Pantry(ings, exps, quants);
    }

    @Before
    @Test
    public void testPlaceInDatabase() {
        assertNotEquals(null, up);
        assertNotEquals(null, pantry);

        assertFalse(placeInDatabase(null, pantry));
        assertTrue(placeInDatabase(up, pantry));
    }


}
