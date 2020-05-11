package backend.tests;

import backend.RecipeParser;
import org.junit.Test;

import java.io.IOException;

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

}
