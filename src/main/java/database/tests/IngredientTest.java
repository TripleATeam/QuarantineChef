package database.tests;

import database.Ingredient;
import database.IngredientGroup;
import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTest {

    /**
     * Creates an instance of the Ingredient and
     * if the creation is not null, then it works.
     */
    @Test
    public void testConstructor() {
        Ingredient ing = new Ingredient("Salt", IngredientGroup.ETC);
        assertNotEquals(null, ing);
    }

    /**
     * Creates an instance of the Ingredient, then checks if the name field
     * was properly initialized and properly returned by the getName() method.
     */
    @Test
    public void testGetName() {
        Ingredient ing = new Ingredient("Salt", IngredientGroup.ETC);
        assertEquals("Salt", ing.getName());
    }

    /**
     * Creates an instance of the Ingredient, then checks if the group field
     * was properly initialized and properly returned by the getGroup() method.
     */
    @Test
    public void testToString() {
        Ingredient ing = new Ingredient("Salt", IngredientGroup.ETC);
        assertEquals("(Salt, ETC)", ing.toString());
    }

    /**
     * Creates two equal instances of a Ingredient, then
     * checks if equals() method properly returns.
     */
    @Test
    public void testEqualsIdentical() {
        Ingredient ing1 = new Ingredient("Salt", IngredientGroup.ETC);
        Ingredient ing2 = new Ingredient("Salt", IngredientGroup.ETC);
        assertEquals(ing1, ing2);
    }

    /**
     * Creates two unequal instances of a Ingredient, then
     * checks if equals() method properly returns.
     */
    @Test
    public void testEqualsNonIdentical() {
        Ingredient ing1 = new Ingredient("Salt", IngredientGroup.ETC);
        Ingredient ing2 = new Ingredient("Pepper", IngredientGroup.ETC);
        Ingredient ing3 = new Ingredient("Salt", IngredientGroup.BAKING);
        assertNotEquals(ing1, ing2);
        assertNotEquals(ing1, ing3);
    }
}
