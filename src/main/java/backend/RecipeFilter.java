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

        Ingredient[] sortedExpirationArray = sortIngredientsByDate();
        String firstPriorityIngredient = sortedExpirationArray[0].getName();
        String secondPriorityIngredient = "";
        if (sortedExpirationArray.length > 1) {
            secondPriorityIngredient = sortedExpirationArray[1].getName();
        }

        String priorityIngredientName = firstPriorityIngredient + " " + secondPriorityIngredient;

        // Fix: change space to %20 (url)
        priorityIngredientName = priorityIngredientName.replaceAll("\\s", "%20");
        query.append(priorityIngredientName);


        String IDandKEY = "&app_id=" + APP_ID + "&app_key=" + APP_KEY;
        query.append(IDandKEY);

        Set<String> excludedIngredientNames = excludeTop15Ingredients();
        for (String exlcudedIngredientName : excludedIngredientNames) {
            String exclusionParameter = "&excluded=" + exlcudedIngredientName;
            query.append(exclusionParameter);
        }

        String finalQuery = query.toString();
        System.out.println(finalQuery);
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

    private String getRandomIngredientNameFromPantry() {
        if (this.currentPantry != null) {
            Ingredient[] pantryIngredients = this.currentPantry.getIngredients();
            if (pantryIngredients.length > 0) {
                int randomIndex = (int) (Math.random() * pantryIngredients.length);
                return pantryIngredients[randomIndex].getName();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public Set<String> excludeTop15Ingredients() {

        //set creation
        Set<String> negSet = new HashSet<String>();
        negSet.add("chicken");
        negSet.add("salmon");
        negSet.add("beef");
        negSet.add("lamb");
        negSet.add("pork");
        negSet.add("cheese");
        negSet.add("milk");
        negSet.add("cream");
        negSet.add("bread");
        negSet.add("rice");
        negSet.add("bean");
        negSet.add("sausage");
        negSet.add("noodle");
        negSet.add("pasta");
        negSet.add("fish");

        Set<String> excludedIngredientSet = new HashSet<String>();
        excludedIngredientSet.addAll(negSet);


        Set<String> pantryIngredients = ingredientsToNameSet(this.currentPantry.getIngredients());

        for (String topIngredient : negSet) {
            for (String pantryIngredient : pantryIngredients) {
                pantryIngredient = pantryIngredient.toLowerCase();
                System.out.println("Comparing " + topIngredient + ":" + pantryIngredient);
                if (pantryIngredient.contains(topIngredient)) {
                    excludedIngredientSet.remove(topIngredient);
                }
            }
        }

        return excludedIngredientSet;
    }

    private class IngredientNode {
        private Ingredient ingredient;
        private Date date;

        public IngredientNode(Ingredient ingredient, Date date) {
            this.ingredient = ingredient;
            this.date = date;
        }

        public Ingredient getIngredient() {
            return ingredient;
        }

        public Date getDate() {
            return date;
        }
    }

    private class sortByExpirationComparator implements Comparator<IngredientNode> {
        @Override
        public int compare(IngredientNode i1, IngredientNode i2) {
            Date i1Date = i1.getDate();
            Date i2Date = i2.getDate();
            return i1Date.compareTo(i2Date);
        }
    }

    private Ingredient[] sortIngredientsByDate() {
        Ingredient[] pantryIngredients = this.currentPantry.getIngredients();
        Date[] expirationDates = this.currentPantry.getExpirations();

        List<IngredientNode> sortIngredientList = new ArrayList<IngredientNode>();
        for (int i = 0; i < pantryIngredients.length; i++) {
            IngredientNode ingredientNode = new IngredientNode(pantryIngredients[i], expirationDates[i]);
            sortIngredientList.add(ingredientNode);
        }

        Collections.sort(sortIngredientList, new sortByExpirationComparator());

        Ingredient[] sortedArray = new Ingredient[sortIngredientList.size()];

        for (int i = 0; i < sortedArray.length; i++) {
            Ingredient ing = sortIngredientList.get(i).getIngredient();
            sortedArray[i] = ing;
        }

        return sortedArray;
    }



}