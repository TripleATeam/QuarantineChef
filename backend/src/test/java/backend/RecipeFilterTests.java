package backend;

import backend.RecipeFilter;
import backend.RecipeParser;
import database.Ingredient;
import database.IngredientGroup;
import database.Pantry;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

public class RecipeFilterTests {

    @Test
    public void testGetNewRecipes() {

    }

    @Test
    public void testNegatePantryIngredients() {
        
    }

    @Test
    public void testIngredientsToNameSet() {

    }

    @Test
    public void testGetAllIngredients() {

    }
/*
    @Test
    public void testGetPriorityIngredient() {
        // set up test pantry
        Ingredient[] ingredients = new Ingredient[5];
        ingredients[0] = new Ingredient("aaa", IngredientGroup.ETC);
        ingredients[1] = new Ingredient("1of3", IngredientGroup.ETC);
        ingredients[2] = new Ingredient("2of3", IngredientGroup.ETC);
        ingredients[3] = new Ingredient("ddd", IngredientGroup.ETC);
        ingredients[4] = new Ingredient("3of3", IngredientGroup.ETC);
        Date[] expiration = new Date[5];
        expiration[0] = new Date(3,3,3);
        expiration[1] = new Date(1,1,1);
        expiration[2] = new Date(1,1,1);
        expiration[3] = new Date(2,2,2);
        expiration[4] = new Date(1,1,1);
        int[] quantities = new int[5];
        Pantry pantry = new Pantry(ingredients, expiration, quantities);
        RecipeFilter rf = new RecipeFilter(null, null, pantry);
        Ingredient chosenKeyIngredient = rf.getPriorityIngredient();
        System.out.println(chosenKeyIngredient.getName());
    }

    @Test
    public void testExclusionsWithUselessPantry() {
        // set up test pantry
        Ingredient[] ingredients = new Ingredient[5];
        ingredients[0] = new Ingredient("aaa", IngredientGroup.ETC);
        ingredients[1] = new Ingredient("bbb", IngredientGroup.ETC);
        ingredients[2] = new Ingredient("ccc", IngredientGroup.ETC);
        ingredients[3] = new Ingredient("ddd", IngredientGroup.ETC);
        ingredients[4] = new Ingredient("eee", IngredientGroup.ETC);
        String[] expiration = new String[5];
        int[] quantities = new int[5];
        Pantry pantry = new Pantry(ingredients, expiration, quantities);
        RecipeFilter rf = new RecipeFilter(null, null, pantry);
        Set<String> excludeSet = rf.excludeTop15Ingredients();
        System.out.println("Should show all exclusions");
        for (String i : excludeSet) {
            System.out.println(i);
        }
    }

    @Test
    public void testExclusionsWithChickenPantry() {
        // set up test pantry
        Ingredient[] ingredients = new Ingredient[5];
        ingredients[0] = new Ingredient("chicken breast", IngredientGroup.ETC);
        ingredients[1] = new Ingredient("beef", IngredientGroup.ETC);
        ingredients[2] = new Ingredient("chicken 2", IngredientGroup.ETC);
        ingredients[3] = new Ingredient("ddd", IngredientGroup.ETC);
        ingredients[4] = new Ingredient("eee", IngredientGroup.ETC);
        String[] expiration = new String[5];
        int[] quantities = new int[5];
        Pantry pantry = new Pantry(ingredients, expiration, quantities);
        RecipeFilter rf = new RecipeFilter(null, null, pantry);
        Set<String> excludeSet = rf.excludeTop15Ingredients();
        System.out.println("Should show all exclusions except chicken and beef");
        for (String i : excludeSet) {
            System.out.println(i);
        }
    }

    // too many exclusions and the api cannot handle it...
    @Test
    public void testManyExclusions() {
        String httpRequest =
                "https://api.edamam.com/search?q=chicken&app_id=56d7887a&app_key=4740dac00a0df8a5f23c6f81ad502e26"
                        + "&excluded=vinegar&excluded=pretzel"
                        + "&excluded=potato&excluded=garlic"
                        + "&excluded=onion&excluded=oregano"
                        + "&excluded=butter&excluded=olive"
                        + "&excluded=sugar&excluded=alcohol"
                        + "&excluded=shallot&excluded=wine"
                        + "&excluded=bread&excluded=mustard"
                        + "&excluded=herb&excluded=thyme"
                        + "&excluded=salt&excluded=pepper"
                        + "&excluded=oil&excluded=sauce"
                        + "&excluded=mushroom&excluded=salad"
                        + "&excluded=rice&excluded=kosher"
                        + "&excluded=milk&excluded=cheese"
                        + "&excluded=cream&excluded=broth";
        RecipeParser rp = new RecipeParser();
        String response = "failed";
        try {
            response = rp.request(httpRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(httpRequest);
        System.out.println("unknown");
        System.out.println("Actual: " + response);
    }
*/
}
