package backend;
import java.util.*;
import database.Ingredient;
import database.UserProfile;
import database.Pantry;
import database.Date;

public class RecipeFilter {

    /**
     * currentUser represents the current logged in user
     */
    private UserProfile currentUser;

    /**
     * currentPantry is the pantry of the user, whether logged in or not
     */
    private Pantry currentPantry;

    private static final String APP_ID = "app id here";
    private static final String APP_KEY = "app key here";


    public RecipeFilter(UserProfile currentUser) {
        this.currentUser = currentUser;
        setUserPantry();
    }

    public void setUserPantry() {
        //only attempt getting the custom user pantry if the user is logged in
        if (this.currentUser != null) {
            Pantry userPantry = databaseAPI.getPantry();
            this.currentPantry = userPantry;
        } else {
            //this.currentPantry = input from frontend for non-user
        }
    }

    public List<Recipe> getNewRecipes() {
        List<Recipe> unfilteredRecipes = getRecipes();
        List<Recipe> filteredRecipes = filterRecipes(unfilteredRecipes);

        //TODO:format the recipes here for frontend

        ///////////////////////////////////////////

        return filteredRecipes;
    }


    private List<Recipe> getRecipes() {
        //Example: "https://api.edamam.com/search?q=chicken&app_id=${YOUR_APP_ID}&app_key=${YOUR_APP_KEY}&from=0&to=3&calories=591-722&health=alcohol-free"
        StringBuilder query = new StringBuilder("https://api.edamam.com/search?q=");

        String priorityIngredientName = getPriorityIngredient().getName();
        query.append(priorityIngredientName);

        String IDandKEY = "&app_id=${" + APP_ID + "&app_key=${" + APP_KEY + "}";
        query.append(IDandKEY);

        Set<String> excludedIngredientNames = negatePantryIngredients();
        for (String exlcudedIngredientName : excludedIngredientNames) {
            String exclusionParameter = "&excluded=" + exlcudedIngredientName;
            query.append(exclusionParameter);
        }

        String finalQuery = query.toString();
        RecipeParser recipeParser = new RecipeParser(finalQuery);

        return recipeParser.getRecipeList();
    }

    private List<Recipe> filterRecipes(List<Recipe> unfilteredRecipes) {
        //method returns 10 randomly selected recipes for the beta
        return unfilteredRecipes;
    }

    //TODO: what to do if no ingredients have an expiration date?
    private Ingredient getPriorityIngredient() {
        if (this.currentPantry == null) {
            setUserPantry();
        }

        Date[] expirationDate = this.currentPantry.getExpirations();
        Ingredient[] userIngredients = this.currentPantry.getIngredients();

        //only find a priority ingredient if the user has a ingredients to choose from
        if (expirationDate.length > 0 && userIngredients.length > 0) {
            Ingredient priorityIngredient = userIngredients[0];
            Date earliestExpirationDate = expirationDate[0];
            for (int i = 1; i < userIngredients.length; i++) {
                Date ingredientExpirationDate = expirationDate[i];
                if (earliestExpirationDate.compareTo(ingredientExpirationDate) < 0) {
                    earliestExpirationDate = ingredientExpirationDate;
                    priorityIngredient = userIngredients[i];
                }
            }

            return priorityIngredient;

        } else {
            //TODO: empty pantry... throw exception?
            return null;
        }

    }

    private Set<String> negatePantryIngredients() {
        if (this.currentPantry == null) {
            setUserPantry();
        }

        //Creates the set of every single possible ingredient name
        Ingredient[] allIngredientsArray = getAllIngredients();
        Set<String> allIngredientNames = ingredientsToNameSet(allIngredientsArray);

        //Creates a set of ingredient names according to current pantry
        Ingredient[] userIngredients = this.currentPantry.getIngredients();
        Set<String> pantryIngredientNames = ingredientsToNameSet(userIngredients);

        //every ingredient that the current pantry does not have is thrown into negativeIngredientSet
        Set<String> negativeIngredientSet = new HashSet<String>();
        for (String ingredientName : allIngredientNames) {
            if (!pantryIngredientNames.contains(ingredientName)) {
                negativeIngredientSet.add(ingredientName);
            }
        }

        return negativeIngredientSet;
    }

    private Set<String> ingredientsToNameSet(Ingredient[] ingredients) {
        Set<String> nameSet = new HashSet<String>();
        for (int i = 0; i < ingredients.length; i++) {
            String ingredientName = ingredients[i].getName();
            nameSet.add(ingredientName);
        }
        return nameSet;
    }

    private Ingredient[] getAllIngredients() {
        Ingredient[] allIngredients = databaseAPI.getAllIngredients();
        return allIngredients;
    }

}