package backend;
import java.util.*;
import database.Ingredient;
import database.UserProfile;
import database.Pantry;
import database.databaseAPI;

public class RecipeFilter {

    /**
     * currentUser represents the current logged in user, optional and can be null
     */
    private UserProfile currentUser;

    /**
     * currentPantry is the pantry of the user, whether logged in or not
     */
    private Pantry currentPantry;

    /**
     * User specified key ingredient, optional and can be null
     */
    private Ingredient keyIngredient;

    //Edamam APP ID and KEY for API usage
    private static final String APP_ID = "56d7887a";
    private static final String APP_KEY = "4740dac00a0df8a5f23c6f81ad502e26";

    /**
     *
     * @param currentUser UserProfile for current user, input null if temporary user
     * @param keyIngredient Ingredient object for a key ingredient, input null if none specified
     * @param tempPantry Pantry object, only input a pantry if no user is specified
     */
    public RecipeFilter(UserProfile currentUser, Ingredient keyIngredient, Pantry tempPantry) {
        if (currentUser == null && tempPantry == null) {
            throw new IllegalArgumentException();
        }
        this.currentUser = currentUser;
        this.keyIngredient = keyIngredient;
        this.currentPantry = tempPantry;
        setUserPantry();
    }

    /**
     *Sets the current user's pantry, either retrieving the custom pantry from
     * the database or retrieving temporary selections from frontend
     */
    public void setUserPantry() {
        //only attempt getting the custom user pantry if the user is logged in
        if (this.currentUser != null) {
            Pantry userPantry = databaseAPI.getPantry(this.currentUser);
            this.currentPantry = userPantry;
        }
    }

    /**
     * Gets recipes to display
     * @return Recipes formatted for frontend
     */
    public List<Recipe> getNewRecipes() {
        List<Recipe> unfilteredRecipes = getRecipes();
        List<Recipe> filteredRecipes = filterRecipes(unfilteredRecipes);

        //TODO:format the recipes here for frontend

        ///////////////////////////////////////////

        return filteredRecipes;
    }


    //Gathers data from the Edamam API using the current pantry
    private List<Recipe> getRecipes() {
        //Example: "https://api.edamam.com/search?q=chicken&app_id=${YOUR_APP_ID}&app_key=${YOUR_APP_KEY}&from=0&to=3&calories=591-722&health=alcohol-free"
        StringBuilder query = new StringBuilder("https://api.edamam.com/search?q=");

        String priorityIngredientName = getPriorityIngredient().getName();
        // Fix: change space to %20 (url)
        priorityIngredientName = priorityIngredientName.replaceAll("\\s", "%20");
        query.append(priorityIngredientName);

        String IDandKEY = "&app_id=" + APP_ID + "&app_key=" + APP_KEY;
        query.append(IDandKEY);

        // TODO: below does not work
        /*Set<String> excludedIngredientNames = negatePantryIngredients();
        for (String exlcudedIngredientName : excludedIngredientNames) {
            String exclusionParameter = "&excluded=" + exlcudedIngredientName;
            query.append(exclusionParameter);
        }*/

        String finalQuery = query.toString();
        RecipeParser recipeParser = new RecipeParser(finalQuery);

        return recipeParser.getRecipeList();
    }

    //Chooses which recipes to display to the user
    private List<Recipe> filterRecipes(List<Recipe> unfilteredRecipes) {
        //method returns 10 randomly selected recipes for the beta
        return unfilteredRecipes;
    }

    //TODO: what to do if no ingredients have an expiration date?
    private Ingredient getPriorityIngredient() {
        if (this.keyIngredient != null) {
            return this.keyIngredient;
        }

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
                if (earliestExpirationDate.compareTo(ingredientExpirationDate) > 0) {
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

    //Returns a set of ingredient names not currently in the user's current pantry
    private Set<String> negatePantryIngredients() {
        if (this.currentPantry == null) {
            setUserPantry();
            //if current pantry is still null return an empty set
            if (this.currentPantry == null) {
                return new HashSet<String>();
            }
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

    //Converts an array of ingredients to a set of ingredient names
    private Set<String> ingredientsToNameSet(Ingredient[] ingredients) {
        Set<String> nameSet = new HashSet<String>();
        for (int i = 0; i < ingredients.length; i++) {
            String ingredientName = ingredients[i].getName();
            nameSet.add(ingredientName);
        }
        return nameSet;
    }

    //Gets all the ingredients from the database
    //Returns an array of all ingredients
    private Ingredient[] getAllIngredients() {
        Ingredient[] allIngredients = databaseAPI.getAllIngredients();
        return allIngredients;
    }

}