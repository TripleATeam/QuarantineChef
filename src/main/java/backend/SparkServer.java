package backend;

import static spark.Spark.*;

import backend.Recipe;
import backend.RecipeParser;
import backend.RecipeFilter;
import com.google.gson.Gson;
import database.*;

import java.util.ArrayList;
import java.util.List;

public class SparkServer {

    public static void main(String[] args) {
        enableCors();

        get("/find-recipe", (req, res) -> {
            String keyIngredient = req.queryParams("key-ingredient");
            List<RecipeView> recipes = new ArrayList<>();

            int userID = 0; //should be received from frontend

            //create a userKeyIngredient, with unknown ingredient group
            Ingredient userKeyIngredient = keyIngredient != null && !keyIngredient.equals("")
                    ? new Ingredient(keyIngredient, null)
                    : null;

            //Creation of a UserProfile retrieved from frontend
            UserProfile currentLoggedInUser = databaseAPI.getUserProfile(userID);

            //Creation of a Pantry for RecipeFilter
            Pantry currentPantry = null;

            RecipeFilter recipeFilter = new RecipeFilter(currentLoggedInUser, userKeyIngredient, currentPantry);
            List<Recipe> recipeList = recipeFilter.getNewRecipes();

            for (Recipe i : recipeList) {
                recipes.add(new RecipeView(i.getLabel(),i.getRecipeUrl(), i.getImageUrl()));
            }

            Gson gson = new Gson();
            return gson.toJson(recipes);
        });

        get("/get-pantry", (req, res) -> {
            int userId = Integer.parseInt(req.queryParams("userId"));
            UserProfile userProfile = new UserProfile(userId, null, null, null);
            Pantry pantry = databaseAPI.getPantry(userProfile);
            Gson gson = new Gson();
            return gson.toJson(pantry);
        });

        post("/save-pantry",  (req, res) -> {
            String body = req.body();
            Gson gson = new Gson();
            PantryView pantryView = gson.fromJson(body, PantryView.class);
            UserProfile up = new UserProfile(0, new int[2], new int[2], new int[2]);
            
            Ingredient[] ingArr = new Ingredient[pantryView.ingredients.length];
            for (int i = 0; i < pantryView.ingredients.length; i++) {
                ingArr[i] = databaseAPI.getIngredient(pantryView.ingredients[i]);
            }
            Pantry p = new Pantry(ingArr, pantryView.expirations, pantryView.quantities);
            databaseAPI.updatePantry(up, p);
            return "OK";
        });
    }

    private static void enableCors() {
        // Enable CORS in Spark Java: https://gist.github.com/saeidzebardast/e375b7d17be3e0f4dddf
        options("/*",(request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers",
                        accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods",
                        accessControlRequestMethod);
            }
            return "OK";
        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
        //
    }

    private static class RecipeView {
        public String Name;
        public String Url;
        public String Img;

        public RecipeView(String name, String url, String img) {
            Name = name;
            Url = url;
            Img = img;
        }
    }

    private class PantryView {
        public String[] ingredients;
        public String[] expirations;
        public int[] quantities;
    }
}