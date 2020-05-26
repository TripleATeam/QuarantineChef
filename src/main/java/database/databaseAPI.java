package database;

import org.neo4j.driver.v1.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.neo4j.driver.v1.Values.parameters;

public class databaseAPI {
    private static final int CUISINE_SIZE = 11;  //Kyle: 11
    private static final int DIET_SIZE = 5;  //Kyle: 5
    private static final int HEALTH_SIZE = 9;  // Kyle: 9
    private static final int MEAL_SIZE = 3;

    private static final String SANDBOX_URL = "bolt://34.224.17.116:47989";
    private static final String SANDBOX_PASSWORD = "military-disassemblies-bases";

    private static boolean initialized = false;

    private static Ingredient[] allIngredients = null;
    private static HashMap<String, IngredientGroup> namesToGroups = null;
    private static HashMap<IngredientGroup, ArrayList<String>> groupsToNames = null;

    /**
     * Initializes allIngredients, namesToGroups, and groupsToNames, then sets
     * the initialized flag to true.
     */
    private static void init() {
        try {
            File file = new File("ingredients.csv");
            Scanner scan = new Scanner(file);
            scan.nextLine();
            namesToGroups = new HashMap<>();
            groupsToNames = new HashMap<>();
            ArrayList<Ingredient> allIng = new ArrayList<>();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String group = line.substring(0, line.indexOf(","));
                String name = line.substring(line.indexOf(",") + 1);

                IngredientGroup ingGroup = findIngredientGroupByName(group);
                allIng.add(new Ingredient(name, ingGroup));
                namesToGroups.put(name.toLowerCase(), ingGroup);
                ArrayList<String> strList = groupsToNames.get(ingGroup);
                if (strList == null) {
                    strList = new ArrayList<>();
                }
                strList.add(name);
                groupsToNames.put(ingGroup, strList);
            }
            allIngredients = new Ingredient[allIng.size()];
            for (int i = 0; i < allIng.size(); i++) {
                allIngredients[i] = allIng.get(i);
            }
            initialized = true;
        } catch (FileNotFoundException e) {
        }
    }

    /**
     * Tests the functionality of the other methods.
     *
     * @param args The arguments
     */
    public static void main(String[] args) {
        UserProfile up = new UserProfile(-1, new int[CUISINE_SIZE], new int[DIET_SIZE], new int[HEALTH_SIZE], new int[MEAL_SIZE]);
        Ingredient[] ingArr = new Ingredient[2];
        ingArr[0] = getIngredient("Beef Steak");
        ingArr[1] = getIngredient("Chicken Breast");
        Pantry p = new Pantry(ingArr, new String[]{null, "02-01-2020"}, new int[]{1, 1});
        System.out.println(placeInDatabase(up, p));
        Pantry p2 = getPantry(up);
        updateUser(up);
        System.out.println(p2);
//        System.exit(0);
        //System.out.println(Arrays.deepToString(getAllIngredients()));
    }

    /**
     * This method will remove any UserProfile in the database with the
     * same UserID as the passed UserProfile. It will also remove any Pantry
     * objects associated with those UserProfiles.
     *
     * @param up    The UserProfile to be removed from the database
     */
    public static void removeFromDatabase(UserProfile up) {
        String cypherQuery = "MATCH (n:User) WHERE n.userID = " + up.userID + " DETACH DELETE n";
        doQuery(cypherQuery);
        cypherQuery = "MATCH (n:Pantry) WHERE n.userID = " + up.userID + " DETACH DELETE n";
        doQuery(cypherQuery);
    }

    /**
     * This method will place a specified UserProfile into the database
     * and the Pantry associated with it. A null UserProfile will result
     * in no addition to the database. A null Pantry and a non-null
     * UserProfile will result in only the UserProfile being added to the
     * database. If a UserProfile with the same UserID was already added
     * to the database, this will overwrite the previous UserProfile with
     * the new one, and replace the associated Pantry of the previous one
     * with the one passed into the method. Returns true if the values were
     * able to be placed in the database, false otherwise.
     *
     * @param up        The UserProfile to be placed in the database
     * @param pantry    The Pantry to be placed in the database
     * @return          a boolean that represents if these were placed into
     *                  the database successfully.
     */
    public static boolean placeInDatabase(UserProfile up, Pantry pantry) {
        if (up == null) {
            return false;
        }
        UserProfile otherProf = getUserProfile(up.userID);
        if (otherProf == null) {
            // Create it if it doesn't exist
            createUserProfile(up);
        } else if (!otherProf.equals(up)) {
            // Otherwise just update it
            updateUser(up);
        }

        Pantry otherPantry = getPantry(up);
        // Same with the Pantry
        if (otherPantry == null) {
            createPantry(up, pantry);
        } else if (!otherPantry.equals(pantry)) {
            updatePantry(up, pantry);
        }
        return true;
    }

    /**
     * Places the passed Pantry into the database, associated
     * with the passed UserProfile.
     *
     * @param up        UserProfile associated with pantry
     * @param pantry    Pantry to be placed into database
     */
    private static void createPantry(UserProfile up, Pantry pantry) {
        if (pantry == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE (n:Pantry {userID: ");
        sb.append(up.userID);
        sb.append(", numIngredients: ");
        sb.append(pantry.getIngredients().length);
        sb.append(", ingredients: [\"");
        sb.append(pantry.getIngredients()[0].getName());
        for (int i = 1; i < pantry.getIngredients().length; i++) {
            sb.append( "\", \"");
            sb.append(pantry.getIngredients()[i].getName());
        }
        sb.append("\"], expirations: [\"");
        sb.append(pantry.getExpirationsAsStrings()[0]);
        for (int i = 1; i < pantry.getExpirationsAsStrings().length; i++) {
            sb.append( "\", \"");
            sb.append(pantry.getExpirationsAsStrings()[i]);
        }
        sb.append("\"], quantities: [");
        sb.append(pantry.getQuantities()[0]);
        for (int i = 1; i < pantry.getQuantities().length; i++) {
            sb.append( ", ");
            sb.append(pantry.getQuantities()[i]);
        }
        sb.append("]})");
        String cypherQuery = sb.toString();
        doQuery(cypherQuery);
    }

    /**
     * Places the passed UserProfile into the database.
     *
     * @param up    Pantry to be placed into database
     */
    private static void createUserProfile(UserProfile up) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE (n:User {userID: ");
        sb.append(up.userID);
        sb.append(", cuisinePreferences: [");
        sb.append(up.preferences[0]);
        for (int i = 1; i < up.preferences.length; i++) {
            sb.append( ", ");
            sb.append(up.preferences[i]);
        }
        sb.append("], dietTypes: [");
        sb.append(up.diet[0]);
        for (int i = 1; i < up.diet.length; i++) {
            sb.append( ", ");
            sb.append(up.diet[i]);
        }
        sb.append("], healthRestrictions: [");
        sb.append(up.health[0]);
        for (int i = 1; i < up.health.length; i++) {
            sb.append( ", ");
            sb.append(up.health[i]);
        }
        sb.append("], mealTypes: [");
        sb.append(up.meal[0]);
        for (int i = 1; i < up.diet.length; i++) {
            sb.append( ", ");
            sb.append(up.diet[i]);
        }
        sb.append("]})");
        String cypherQuery = sb.toString();
        doQuery(cypherQuery);
    }

    /**
     * If another Pantry with the same userID as the passed
     * UserProfile exists in the database (UP2), this method
     * will set any Pantry associated with UP2 to be equivalent
     * to the passed Pantry.
     *
     * @param up        UserProfile associated with passed Pantry
     * @param pantry    Pantry to be added to the database
     */
    public static void updatePantry(UserProfile up, Pantry pantry) {
        if (pantry == null || up == null) {
            return;
        }
        if (pantry.getIngredients().length == 0) {
            String cypherQuery = "MATCH (n:Pantry) WHERE n.userID = "
                + up.userID + " SET n.numIngredients = 0, n.ingredients = [], n.expirations = [], n.quantities = []";
            doQuery(cypherQuery);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("MATCH (n:Pantry) WHERE n.userID = ");
        sb.append(up.userID);
        sb.append(" SET n.numIngredients = ");
        sb.append(pantry.getIngredients().length);
        sb.append(", n.ingredients = [\"");
        sb.append(pantry.getIngredients()[0].getName());
        for (int i = 1; i < pantry.getIngredients().length; i++) {
            sb.append( "\", \"");
            sb.append(pantry.getIngredients()[i].getName());
        }
        sb.append("\"], n.expirations = [\"");
        sb.append(pantry.getExpirationsAsStrings()[0]);
        for (int i = 1; i < pantry.getExpirationsAsStrings().length; i++) {
            sb.append( "\", \"");
            sb.append(pantry.getExpirationsAsStrings()[i]);
        }
        sb.append("\"], n.quantities = [");
        sb.append(pantry.getQuantities()[0]);
        for (int i = 1; i < pantry.getQuantities().length; i++) {
            sb.append( ", ");
            sb.append(pantry.getQuantities()[i]);
        }
        sb.append("]");
        String cypherQuery = sb.toString();
        doQuery(cypherQuery);
    }

    /**
     * If another UserProfile with the same userID as the passed
     * UserProfile exists in the database (UP2), this method will
     * set UP2 to be equivalent to the passed UserProfile.
     *
     * @param up    The new UserProfile that should be used to update the database
     */
    public static void updateUser(UserProfile up) {
        if (up.preferences.length < 1) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("MATCH (n:User) WHERE n.userID = ");
        sb.append(up.userID);
        sb.append(" SET n.cuisinePreferences = [");
        sb.append(up.preferences[0]);
        for (int i = 1; i < up.preferences.length; i++) {
            sb.append( ", ");
            sb.append(up.preferences[i]);
        }
        sb.append("], n.dietTypes = [");
        sb.append(up.diet[0]);
        for (int i = 1; i < up.diet.length; i++) {
            sb.append( ", ");
            sb.append(up.diet[i]);
        }
        sb.append("], n.healthRestrictions = [");
        sb.append(up.health[0]);
        for (int i = 1; i < up.health.length; i++) {
            sb.append( ", ");
            sb.append(up.health[i]);
        }
        sb.append("], n.mealTypes = [");
        sb.append(up.meal[0]);
        for (int i = 1; i < up.meal.length; i++) {
            sb.append( ", ");
            sb.append(up.meal[i]);
        }
        sb.append("] RETURN n");
        String cypherQuery = sb.toString();
        doQuery(cypherQuery);
    }

    /**
     * If a UserProfile with the given userID exists in
     * the database, this will return that UserProfile.
     * If none exist, this will return null.
     *
     * @param userID    The userID of the UserProfile to be retrieved
     * @return          A UserProfile from the database with the given userID
     */
    public static UserProfile getUserProfile(int userID) {
        String cypherQuery = "MATCH (n:User) WHERE n.userID = " + userID + " RETURN" +
                " n.cuisinePreferences as preferences, n.dietTypes as diet, " +
                "n.healthRestrictions as health, n.mealTypes as meal";
        StatementResult sr = doQuery(cypherQuery);
        UserProfile ret = null;
        int[] preferences;
        int[] diet;
        int[] health;
        int[] meal;
        while (sr.hasNext()) {
            org.neo4j.driver.v1.Record curr = sr.next();

            preferences =  new int[CUISINE_SIZE];
            diet = new int[DIET_SIZE];
            health = new int[HEALTH_SIZE];
            meal = new int[MEAL_SIZE];

            Object[] objArr1 = curr.get("preferences").asList().toArray();
            Object[] objArr2 = curr.get("diet").asList().toArray();
            Object[] objArr3 = curr.get("health").asList().toArray();
            Object[] objArr4 = curr.get("meal").asList().toArray();
            for (int i = 0; i < CUISINE_SIZE; i++) {
                Long l = (Long) objArr1[i];
                preferences[i] = l.intValue();
            }
            for (int i = 0; i < DIET_SIZE; i++) {
                Long l = (Long) objArr2[i];
                diet[i] = l.intValue();
            }
            for (int i = 0; i < HEALTH_SIZE; i++) {
                Long l = (Long) objArr3[i];
                health[i] = l.intValue();
            }
            for (int i = 0; i < MEAL_SIZE; i++) {
                Long l = (Long) objArr4[i];
                meal[i] = l.intValue();
            }
            ret = new UserProfile(userID, preferences, health, diet, meal);
        }
        return ret;
    }

    /**
     * If a Pantry exists in the database that is associated with
     * a UserProfile with the same userID as the UserProfile that
     * is passed into this function, this function returns that Pantry.
     * If no such pantry exists, returns null.
     *
     * @param up    The UserProfile associated with the Pantry to be retrieved
     * @return      The Pantry associated with the passed UserProfile
     */
    public static Pantry getPantry(UserProfile up) {
        Pantry ret = null;
        String cypherQuery = "MATCH (a:User),(b:Pantry) WHERE a.userID = " +
                up.userID + " AND a.userID = b.userID RETURN b.numIngredients as numIngredients, " +
                "b.ingredients as ingredients, b.quantities as quantities, b.expirations as expirations";
        StatementResult result = doQuery(cypherQuery);
        String[] expirations;
        Ingredient[] ingredients;
        int[] quantities;
        while (result.hasNext()) {
            org.neo4j.driver.v1.Record curr = result.next();

            int size = curr.get("numIngredients").asInt();
            expirations =  new String[size];
            ingredients = new Ingredient[size];
            quantities = new int[size];

            Object[] objArr1 = curr.get("expirations").asList().toArray();
            Object[] objArr2 = curr.get("ingredients").asList().toArray();
            Object[] objArr3 = curr.get("quantities").asList().toArray();
            for (int i = 0; i < size; i++) {
                expirations[i] = (String) objArr1[i];
                String ingName = (String) objArr2[i];
                ingredients[i] = new Ingredient(ingName, getIngredientGroup(ingName));
                Long q = (Long) objArr3[i];
                quantities[i] = q.intValue();
            }
            ret = new Pantry(ingredients, expirations, quantities);
        }
        return ret;
    }

    /**
     * Returns an Ingredient that has the same name as
     * the String that was passed into the function, as
     * well as its associated IngredientGroup.
     *
     * @param ingName   Name of ingredient
     * @return          The Ingredient with the passed name, if it exists
     */
    public static Ingredient getIngredient(String ingName) {
        if (!initialized) {
            init();
        }
        return new Ingredient(ingName, getIngredientGroup(ingName));
    }

    /**
     * Returns an array of all Ingredients.
     *
     * @return  An array of all possible Ingredients
     */
    public static Ingredient[] getAllIngredients() {
        if (!initialized) {
            init();
        }
        return allIngredients.clone();
    }

    /**
     * Returns an IngredientGroup that corresponds to the
     * passed String, the name of the ingredient. If no such
     * ingredient name exists, returns null.
     *
     * @param ingName   The name of an Ingredient, case sensitive
     * @return          The associated IngredientGroup with the name
     */
    public static IngredientGroup getIngredientGroup(String ingName) {
        if (!initialized) {
            init();
        }
        return namesToGroups.get(ingName.toLowerCase());
    }

    /**
     * Returns an array of all the Ingredients that fall into
     * the passed IngredientGroup.
     *
     * @param ingGroup  The IngredientGroup with the associated Ingredients
     * @return          All Ingredients that belong to the passed IngredientGroup
     */
    public static Ingredient[] getIngredientsByGroup(IngredientGroup ingGroup) {
        if (!initialized) {
            init();
        }
        ArrayList<String> list = groupsToNames.get(ingGroup);
        Ingredient[] retlist = new Ingredient[list.size()];
        for (int i = 0; i < list.size(); i++) {
            retlist[i] = new Ingredient(list.get(i), ingGroup);
        }
        return retlist;
    }

    /**
     * Returns an IngredientGroup that corresponds to the passed String.
     * If an IngredientGroup with that name exists, returns that
     * IngredientGroup. Otherwise, returns IngredientGroup.ETC.
     *
     * @param ingredientGroupName   The name of an IngredientGroup. Case sensitive.
     * @return                      The IngredientGroup with that name.
     */
    public static IngredientGroup findIngredientGroupByName(String ingredientGroupName) {
        if (ingredientGroupName.toUpperCase().equals("POULTRY")) {
            return IngredientGroup.POULTRY;
        } else if (ingredientGroupName.toUpperCase().equals("SEAFOOD")) {
            return IngredientGroup.SEAFOOD;
        } else if (ingredientGroupName.toUpperCase().equals("MEAT")) {
            return IngredientGroup.MEAT;
        } else if (ingredientGroupName.toUpperCase().equals("VEGETABLES")) {
            return IngredientGroup.VEGETABLES;
        } else if (ingredientGroupName.toUpperCase().equals("FRUIT")) {
            return IngredientGroup.FRUIT;
        } else if (ingredientGroupName.toUpperCase().equals("DAIRY")) {
            return IngredientGroup.DAIRY;
        } else if (ingredientGroupName.toUpperCase().equals("GRAINS")) {
            return IngredientGroup.GRAINS;
        } else if (ingredientGroupName.toUpperCase().equals("SPICES")) {
            return IngredientGroup.SPICES;
        } else if (ingredientGroupName.toUpperCase().equals("CONDIMENTS")) {
            return IngredientGroup.CONDIMENTS;
        } else if (ingredientGroupName.toUpperCase().equals("SWEETENERS")) {
            return IngredientGroup.SWEETENERS;
        } else if (ingredientGroupName.toUpperCase().equals("NUTS")) {
            return IngredientGroup.NUTS;
        } else if (ingredientGroupName.toUpperCase().equals("BEVERAGES")) {
            return IngredientGroup.BEVERAGES;
        } else if (ingredientGroupName.toUpperCase().equals("BAKING")) {
            return IngredientGroup.BAKING;
        } else if (ingredientGroupName.toUpperCase().equals("SOUPS")) {
            return IngredientGroup.SOUPS;
        } else {
            return IngredientGroup.ETC;
        }
    }

    /**
     * Returns the result of a Cypher query being passed into the database.
     *
     * @param cypherQuery   A query in Cypher language
     * @return              The StatementResult that represents the result
     *                      of the query
     */
    private static StatementResult doQuery(String cypherQuery) {
        Config noSSL = Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig();
        Driver driver = GraphDatabase.driver(SANDBOX_URL, AuthTokens.basic("neo4j",SANDBOX_PASSWORD),noSSL); // <password>
        try (Session session = driver.session()) {
            return session.run(cypherQuery, parameters());
        }
    }
}
