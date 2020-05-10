package backend.tests;

import backend.Recipe;
import backend.RecipeParser;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class RecipeParserTests {

    // tests that the parser can retrieve information from edamam, and gets the recipe information we care about
    @Test
    public void testChickenFull() {
        String httpRequest =
                "https://api.edamam.com/search?q=chicken&app_id=56d7887a&app_key=4740dac00a0df8a5f23c6f81ad502e26";
        RecipeParser rp = new RecipeParser(httpRequest);
        List<Recipe> recipeList = rp.getRecipeList();
        for (Recipe i : recipeList) {
            System.out.println(i.getLabel());
        }
    }

    // tests that recipe parser can retrieve information from edamam
    @Test
    public void testChickenRequest() {
        String httpRequest =
                "https://api.edamam.com/search?q=chicken&app_id=56d7887a&app_key=4740dac00a0df8a5f23c6f81ad502e26";
        RecipeParser rp = new RecipeParser();
        String response = "failed";
        try {
            response = rp.request(httpRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Expected start: {" +
                "\"q\":\"chicken\"," +
                "\"from\":0," +
                "\"to\":10," +
                "\"more\":true," +
                "\"count\":168106," +
                "\"hits\":[{" +
                "\"recipe\":{" +
                "\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#recipe_b79327d05b8e5b838ad6cfd9576b30b6\"," +
                "\"label\":\"Chicken Vesuvio\",");
        System.out.println("Actual        : " + response);
    }
}
