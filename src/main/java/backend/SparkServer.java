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

            int userID = -1; //should be received from frontend

            //create a userKeyIngredient, with unknown ingredient group
            Ingredient userKeyIngredient = new Ingredient(keyIngredient, null);

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

        // TODO: change to POST method
        get("/create-user", (req, res) -> {
            UserProfile up = new UserProfile(0, new int[2], new int[2], new int[2]);
            Ingredient[] ingArr = new Ingredient[2];
            ingArr[0] = new Ingredient("salt", IngredientGroup.ETC);
            ingArr[1] = new Ingredient("pepper", IngredientGroup.ETC);
            Pantry p = new Pantry(ingArr, new String[]{"01-01-2020", "02-01-2020", "03-01-2020"}, new long[]{0, 1, 2});
            databaseAPI.placeInDatabase(up, p);
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
        public String Url = "https://cafedelites.com/easy-honey-garlic-chicken/";
        public String Img = "https://app.slickstream.com/p/pageimg/JA1YSFU7/478";

        public RecipeView(String name, String url, String img) {
            Name = name;
            Url = url;
            Img = img;
        }
    }
}