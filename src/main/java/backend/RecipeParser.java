package backend;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Requests information from edamam, parses it, and stores recipes found.
 * Recipes can be retrieved with getRecipeList()
 */
public class RecipeParser {
    // Fields
    private List<Recipe> recipeList;

    /**
     * constructor
     * @param httpRequest string of exact http request to be sent to edamam api
     */
    public RecipeParser(String httpRequest) {
        recipeList = new ArrayList<>();
        try {
            parseJson(request(httpRequest));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Parse failed with IOException");
        }
    }

    /**
     * default constructor for testing purposes, should not be used in general
     */
    public RecipeParser() {
        recipeList = new ArrayList<>();
    }
    // .. probably something like protected i could do instead of public but i forget how

    /**
     * @return the list of recipes found by edamam
     */
    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    /**
     * * takes a string for the http request
     *      * return the json format string that the api gives back
     * @param httpRequest exact string we want to request from edamam
     * @return entire json formatted output returned by edamam
     * @throws IOException runtime.getruntime fails... not sure what that means...
     */
    public String request(String httpRequest) throws IOException {
        // super janky but it works
        // should replace with httpRequest if I have time
        // also should use something other than a string, gson doesnt work with very large strings
        String command = "curl " + httpRequest;
        Process process = Runtime.getRuntime().exec(command);
        InputStream inputStream = process.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.next());
        }
        return stringBuilder.toString();
    }

    /**
     * parses the json and stores found recipes in recipeList
     * @param json json formatted string to parse
     */
    private void parseJson(String json) {
        // JsonParser method is depricated but should work for a while...
        // convert string to json object
        // also should use something other than a string, gson doesnt work with very large strings
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        // get array of hits
        JsonArray hitArr = jsonObject.getAsJsonArray("hits");
        for (int i = 0; i < hitArr.size(); i++) {
            JsonObject hit = hitArr.get(i).getAsJsonObject();

            // in each hit, get the recipe
            JsonObject recipe = hit.getAsJsonObject("recipe");

            // for each recipe, 1 per hit, get the information we care about
            String label = recipe.get("label").getAsString();
            String imageUrl = recipe.get("image").getAsString();
            String recipeUrl = recipe.get("url").getAsString();

            // make ingredient list for the recipe and fill it
            List<EdamamIngredient> ingredientList = new ArrayList<>();
            JsonArray ingredientArr = recipe.getAsJsonArray("ingredients");
            for (int j = 0; j < ingredientArr.size(); j++) {
                JsonObject ingredient = ingredientArr.get(j).getAsJsonObject();
                String text = ingredient.get("text").getAsString();
                double weight = ingredient.get("weight").getAsDouble();
                ingredientList.add(new EdamamIngredient(text, weight));
            }

            // add the recipe to the recipeList
            recipeList.add(new Recipe(label, ingredientList, imageUrl, recipeUrl));
        }
    }
}
