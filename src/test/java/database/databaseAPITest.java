package database;

import database.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.Arrays;

import static database.databaseAPI.*;
import static org.junit.Assert.*;

public class databaseAPITest {
    UserProfile up;
    Pantry pantry;

    @Before
    public void initialize() {
        int userID = 0;
        int[] pref = {1, 2};
        int[] health = {3, 4};
        int[] diet = {5, 6};
        up = new UserProfile(userID, pref, health, diet);

        Ingredient ing1 = new Ingredient("Salt", IngredientGroup.SPICES);
        Ingredient ing2 = new Ingredient("Pepper", IngredientGroup.SPICES);
        Ingredient[] ings = {ing1, ing2};
        String[] exps = {"1-1-2020", "5-1-1900"};
        int[] quants = {29, 13};
        pantry = new Pantry(ings, exps, quants);
    }

    @Test
    public void testPlaceInDatabase() {
        assertNotEquals(null, up);
        assertNotEquals(null, pantry);

        assertFalse(placeInDatabase(null, pantry));
        assertTrue(placeInDatabase(up, pantry));
    }

    @Test
    public void testRemoveFromDatabase() {
        placeInDatabase(up, pantry);
        UserProfile up2 = getUserProfile(up.userID);
        assertEquals(up, up2);
        assertEquals(pantry, getPantry(up));
        removeFromDatabase(up);
        assertNull(getUserProfile(up.userID));
        assertNull(getPantry(up));
    }

    @Test
    public void testGetPantry() {
        placeInDatabase(up, pantry);
        assertEquals(pantry, getPantry(up));
    }

    @Test
    public void testGetUserProfile() {
        placeInDatabase(up, pantry);
        assertEquals(up, getUserProfile(up.userID));
    }

    @Test
    public void testUpdateUser() {
        placeInDatabase(up, pantry);
        int userID = 0;
        int[] pref = {-1, -2};
        int[] health = {-3, -4};
        int[] diet = {-5, -6};
        UserProfile up2 = new UserProfile(userID, pref, health, diet);
        assertEquals(up, getUserProfile(up2.userID));
        updateUser(up2);
        assertEquals(up2, getUserProfile(up2.userID));
    }

    @Test
    public void testUpdatePantry() {
        placeInDatabase(up, pantry);
        Ingredient ing1 = new Ingredient("Salt", IngredientGroup.SPICES);
        Ingredient ing2 = new Ingredient("Pepper", IngredientGroup.SPICES);
        Ingredient[] ings = {ing1, ing2};
        String[] exps = {"1-1-2020", "5-1-1900"};
        int[] quants = {29, 13};
        Pantry p2 = new Pantry(ings, exps, quants);
        assertEquals(pantry, getPantry(up));
        updatePantry(up, p2);
        assertEquals(p2, getPantry(up));
    }

    @Test
    public void testGetIngredientGroup() {
        assertEquals(IngredientGroup.BEVERAGES, getIngredientGroup("gin"));
        assertEquals(IngredientGroup.BAKING, getIngredientGroup("bisquick"));
        assertEquals(IngredientGroup.SPICES, getIngredientGroup("salt"));
        assertEquals(IngredientGroup.DAIRY, getIngredientGroup("manchego"));
        assertNull(getIngredientGroup("notAnIngredient"));
    }

    @Test
    public void testGetIngredient() {
        assertEquals(new Ingredient("Beef Broth", getIngredientGroup("beef broth")),
                getIngredient("Beef Broth"));
    }

    @Test
    public void testGetAllIngredients() {
        assertNotNull(getAllIngredients());
    }

    @Test
    public void testGetIngredientsByGroup() {
        Ingredient[] ingArr = getIngredientsByGroup(IngredientGroup.SOUPS);
        String[] ingArr2 = {"Beef Broth", "Canned Beets", "Canned Carrots", "Canned Corn", "Canned Green Beans",
        "Canned Peas", "Canned Tomato", "Canned Vegetables", "Celery Soup", "Chicken Broth", "Chicken Soup",
        "Dashi", "Lamb Stock", "Mushroom Soup", "Onion Soup", "Pork Stock", "Tomato Soup", "Veal Stock",
        "Vegetable Bouillon", "Vegetable Soup", "Vegetable Stock"};
        String[] ingArr3 = new String[ingArr.length];
        for (int i = 0; i < ingArr.length; i++) {
            ingArr3[i] = ingArr[i].getName();
        }
        Arrays.sort(ingArr3);
        for (int i = 0; i < ingArr2.length; i++) {
            assertEquals(ingArr2[i], ingArr3[i]);
        }
    }

    @Test
    public void testFindIngredientGroupByName() {
        assertEquals(IngredientGroup.POULTRY, findIngredientGroupByName("Poultry"));
        assertEquals(IngredientGroup.SEAFOOD, findIngredientGroupByName("sEafood"));
        assertEquals(IngredientGroup.MEAT, findIngredientGroupByName("mEAT"));
        assertEquals(IngredientGroup.VEGETABLES, findIngredientGroupByName("VEGETABLES"));
        assertEquals(IngredientGroup.FRUIT, findIngredientGroupByName("FruIT"));
        assertEquals(IngredientGroup.DAIRY, findIngredientGroupByName("DairY"));
        assertEquals(IngredientGroup.GRAINS, findIngredientGroupByName("gRaInS"));
        assertEquals(IngredientGroup.SPICES, findIngredientGroupByName("SPICEs"));
        assertEquals(IngredientGroup.CONDIMENTS, findIngredientGroupByName("CondiMENTs"));
        assertEquals(IngredientGroup.SWEETENERS, findIngredientGroupByName("SWEETeners"));
        assertEquals(IngredientGroup.NUTS, findIngredientGroupByName("NUTS"));
        assertEquals(IngredientGroup.BEVERAGES, findIngredientGroupByName("beverages"));
        assertEquals(IngredientGroup.BAKING, findIngredientGroupByName("Baking"));
        assertEquals(IngredientGroup.SOUPS, findIngredientGroupByName("SOUps"));
        assertEquals(IngredientGroup.ETC, findIngredientGroupByName("cheese"));
    }
}
