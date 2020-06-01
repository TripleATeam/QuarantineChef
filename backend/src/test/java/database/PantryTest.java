package database;

import database.Ingredient;
import database.IngredientGroup;
import database.Pantry;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PantryTest {

    /**
     * Creates an instance of a Pantry using the String
     * constructor and checks if it is not null.
     */
    @Test
    public void testConstructorString() {
        Ingredient ing1 = new Ingredient("Salt", IngredientGroup.ETC);
        Ingredient ing2 = new Ingredient("Pepper", IngredientGroup.BAKING);
        Ingredient[] ings = {ing1, ing2};
        String[] exps = {"1-1-2020", "5-1-1900"};
        int[] quants = {29, 13};
        Pantry p = new Pantry(ings, exps, quants);

        assertNotEquals(null, p);
    }

    /**
     * Creates an instance of a Pantry using the Date
     * constructor and checks if it is not null.
     */
    @Test
    public void testConstructorDate() {
        Ingredient ing1 = new Ingredient("Salt", IngredientGroup.ETC);
        Ingredient ing2 = new Ingredient("Pepper", IngredientGroup.BAKING);
        Ingredient[] ings = {ing1, ing2};
        Date[] exps = {new Date(120, 1, 1), new Date(0, 1, 5)};
        int[] quants = {29, 13};
        Pantry p = new Pantry(ings, exps, quants);

        assertNotEquals(null, p);
    }

    /**
     * Creates an instance of the Pantry, then checks if the ingredient field
     * was properly initialized and properly returned by the getIngredient() method.
     */
    @Test
    public void testGetIngredients() {
        Ingredient ing1 = new Ingredient("Salt", IngredientGroup.ETC);
        Ingredient ing2 = new Ingredient("Pepper", IngredientGroup.BAKING);
        Ingredient[] ings = {ing1, ing2};
        String[] exps = {"1-1-2020", "5-1-1900"};
        int[] quants = {29, 13};
        Pantry p = new Pantry(ings, exps, quants);

        assertEquals(ings, p.getIngredients());
    }

    /**
     * Creates an instance of the Pantry, then checks if the expirations field
     * was properly initialized and properly returned by the getExpirations() method.
     */
    @Test
    public void testGetExpirations() {
        Ingredient ing1 = new Ingredient("Salt", IngredientGroup.ETC);
        Ingredient ing2 = new Ingredient("Pepper", IngredientGroup.BAKING);
        Ingredient[] ings = {ing1, ing2};
        String[] exps = {"1-1-2020", "5-1-1900"};
        Date[] expirations = {new Date(120, 0, 1), new Date(0, 0, 5)};
        int[] quants = {29, 13};
        Pantry p = new Pantry(ings, exps, quants);

        assertEquals(expirations, p.getExpirations());
    }

    /**
     * Creates an instance of the Pantry, then checks if the expirations field
     * was properly initialized and properly returned by the getExpirationsAsStrings() method.
     */
    @Test
    public void testGetExpirationsAsStrings() {
        Ingredient ing1 = new Ingredient("Salt", IngredientGroup.ETC);
        Ingredient ing2 = new Ingredient("Pepper", IngredientGroup.BAKING);
        Ingredient[] ings = {ing1, ing2};
        String[] exps = {"1-1-2020", "5-1-1900"};
        int[] quants = {29, 13};
        Pantry p = new Pantry(ings, exps, quants);

        assertEquals(exps, p.getExpirationsAsStrings());
    }

    /**
     * Creates an instance of the Pantry, then checks if the quantities field
     * was properly initialized and properly returned by the getQuantities() method.
     */
    @Test
    public void testGetQuantities() {
        Ingredient ing1 = new Ingredient("Salt", IngredientGroup.ETC);
        Ingredient ing2 = new Ingredient("Pepper", IngredientGroup.BAKING);
        Ingredient[] ings = {ing1, ing2};
        String[] exps = {"1-1-2020", "5-1-1900"};
        int[] quants = {29, 13};
        Pantry p = new Pantry(ings, exps, quants);

        assertEquals(quants, p.getQuantities());
    }

    /**
     * Creates two equal instances of a Pantry, then
     * checks if equals() method properly returns.
     */
    @Test
    public void testEqualsIdentical() {
        Ingredient ing1 = new Ingredient("Salt", IngredientGroup.ETC);
        Ingredient ing2 = new Ingredient("Pepper", IngredientGroup.BAKING);
        Ingredient[] ings = {ing1, ing2};
        String[] exps = {"1-1-2020", "5-1-1900"};
        int[] quants = {29, 13};
        Pantry p1 = new Pantry(ings, exps, quants);

        ing1 = new Ingredient("Salt", IngredientGroup.ETC);
        ing2 = new Ingredient("Pepper", IngredientGroup.BAKING);
        Ingredient[] ings2 = {ing1, ing2};
        Date[] exps2 = {new Date(120, 0, 1), new Date(0, 0, 5)};
        int[] quants2 = {29, 13};
        Pantry p2 = new Pantry(ings2, exps2, quants2);

        assertEquals(p1, p2);
    }

    /**
     * Creates two unequal instances of a Pantry, then
     * checks if equals() method properly returns.
     */
    @Test
    public void testEqualsNonIdentical() {
        Ingredient ing1 = new Ingredient("Silt", IngredientGroup.ETC);
        Ingredient ing2 = new Ingredient("Pepper", IngredientGroup.BAKING);
        Ingredient[] ings = {ing1, ing2};
        String[] exps = {"1-1-2020", "5-1-1900"};
        int[] quants = {29, 13};
        Pantry p1 = new Pantry(ings, exps, quants);

        ing1 = new Ingredient("Salt", IngredientGroup.ETC);
        ing2 = new Ingredient("Pepper", IngredientGroup.BAKING);
        Ingredient[] ings2 = {ing1, ing2};
        Date[] exps2 = {new Date(2020, 0, 1), new Date(1900, 0, 5)};
        int[] quants2 = {29, 13};
        Pantry p2 = new Pantry(ings2, exps2, quants2);

        assertNotEquals(p1, p2);
    }

    /**
     * Creates an instance of the Ingredient, then checks if the group field
     * was properly initialized and properly returned by the getGroup() method.
     */
    @Test
    public void testToString() {
        Ingredient ing1 = new Ingredient("Salt", IngredientGroup.ETC);
        Ingredient ing2 = new Ingredient("Pepper", IngredientGroup.BAKING);
        Ingredient[] ings = {ing1, ing2};
        String[] exps = {"1-1-2020", "5-1-1900"};
        int[] quants = {29, 13};
        Pantry p = new Pantry(ings, exps, quants);

        assertEquals("[[(Salt, ETC), (Pepper, BAKING)], [1-1-2020, 5-1-1900], [29, 13]]",
                p.toString());
    }
}
