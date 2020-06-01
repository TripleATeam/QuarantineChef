package backend;

import static spark.Spark.*;

import backend.Recipe;
import backend.RecipeParser;
import backend.RecipeFilter;
import com.google.gson.Gson;
import database.*;
import spark.Request;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class SparkServer {

    public static void main(String[] args) {
        enableCors();


        get("/find-recipe", (req, res) -> {
            String filterData = req.queryParams("filter");
            Gson gson = new Gson();
            FilterView filterView = gson.fromJson(filterData, FilterView.class);

            int userID = getUserId(req); //should be received from frontend
            //Creation of a UserProfile retrieved from frontend
            UserProfile currentLoggedInUser = databaseAPI.getUserProfile(userID);

            //Creation of a Pantry for RecipeFilter
            Pantry currentPantry = null;

            // TODO: add filterView to recipe filter
            RecipeFilter recipeFilter = new RecipeFilter(currentLoggedInUser, null, currentPantry, filterView);
            List<Recipe> recipeList = recipeFilter.getNewRecipes();
            List<RecipeView> recipes = new ArrayList<>();
            for (Recipe i : recipeList) {
                recipes.add(new RecipeView(i.getLabel(),i.getRecipeUrl(), i.getImageUrl()));
            }


            return gson.toJson(recipes);
        });

        get("/get-pantry", (req, res) -> {

            int userId = getUserId(req);
            UserProfile userProfile = new UserProfile(userId, null, null, null, (int[]) null);
            Pantry pantry = databaseAPI.getPantry(userProfile);
            Gson gson = new Gson();
            return gson.toJson(pantry);
        });

        post("/save-pantry",  (req, res) -> {
            String body = req.body();
            Gson gson = new Gson();
            PantryView pantryView = gson.fromJson(body, PantryView.class);
            UserProfile up = new UserProfile(getUserId(req), new int[2], new int[2], new int[2], new int[2]);
            
            Ingredient[] ingArr = new Ingredient[pantryView.ingredients.length];
            for (int i = 0; i < pantryView.ingredients.length; i++) {
                ingArr[i] = databaseAPI.getIngredient(pantryView.ingredients[i]);
            }
            Pantry p = new Pantry(ingArr, pantryView.expirations, pantryView.quantities);
            databaseAPI.updatePantry(up, p);
            return "OK";
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            response.body(sw.toString());
        });
    }
    private static int getUserId(Request req){
        String googleUserId = req.cookie("googleUserId");
        if (googleUserId == null) {
            return 0;
        }
        int userId = databaseAPI.getUserIdFromGoogle(googleUserId);
        if (userId == -1) {
            userId = databaseAPI.generateUserID(googleUserId);
            UserProfile profile = new UserProfile(userId , new int[11], new int[9], new int[5], new int[3], googleUserId);
            Pantry pantry = new Pantry(new Ingredient[]{}, new String[]{}, new int[]{});
            databaseAPI.placeInDatabase(profile, pantry);
        }
        return userId;

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
            String accessControlRequestCredentials = request.headers("Access-Control-Allow-Credentials");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Credentials",
                        accessControlRequestCredentials);
            }
            return "OK";
        });
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://localhost:3000");
            response.header("Access-Control-Allow-Credentials", "true");
        });
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
    public class FilterView {
        public String[] mealType;
        public String[] cuisineType;
        public String[] diet;
        public String[] health;

    }
}