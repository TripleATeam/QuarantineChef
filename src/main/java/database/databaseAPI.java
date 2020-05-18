package database;

import org.neo4j.driver.v1.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.neo4j.driver.v1.Values.parameters;

public class databaseAPI {
    private static final int CUISINE_SIZE = 2;
    private static final int DIET_SIZE = 2;
    private static final int HEALTH_SIZE = 2;

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
        UserProfile up = new UserProfile(0, new int[2], new int[2], new int[2]);
        Ingredient[] ingArr = new Ingredient[2];
        ingArr[0] = getIngredient("Beef Steak");
        ingArr[1] = getIngredient("Chicken Breast");
        Pantry p = new Pantry(ingArr, new String[]{"01-06-2020", "02-01-2020"}, new int[]{1, 1});
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
     * Creates a Pantry to put into the database, associated with given UserProfile.
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
     * Creates a UserProfile to put into the database.
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
        sb.append("]})");
        String cypherQuery = sb.toString();
        doQuery(cypherQuery);
    }

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
        sb.append("] RETURN n");
        String cypherQuery = sb.toString();
        doQuery(cypherQuery);
    }

    public static UserProfile getUserProfile(int userID) {
        String cypherQuery = "MATCH (n:User) WHERE n.userID = " + userID + " RETURN" +
                " n.cuisinePreferences as preferences, n.dietTypes as diet, n.healthRestrictions as health";
        StatementResult sr = doQuery(cypherQuery);
        UserProfile ret = null;
        int[] preferences;
        int[] diet;
        int[] health;
        while (sr.hasNext()) {
            Record curr = sr.next();

            preferences =  new int[CUISINE_SIZE];
            diet = new int[DIET_SIZE];
            health = new int[HEALTH_SIZE];

            Object[] objArr1 = curr.get("preferences").asList().toArray();
            Object[] objArr2 = curr.get("diet").asList().toArray();
            Object[] objArr3 = curr.get("health").asList().toArray();
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
            ret = new UserProfile(userID, preferences, health, diet);
        }
        return ret;
    }

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
            Record curr = result.next();

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

    public static Ingredient getIngredient(String ingName) {
        if (!initialized) {
            init();
        }
        return new Ingredient(ingName, getIngredientGroup(ingName));
    }

    public static Ingredient[] getAllIngredients() {
        if (!initialized) {
            init();
        }
        return allIngredients.clone();
    }

    public static IngredientGroup getIngredientGroup(String ingName) {
        if (!initialized) {
            init();
        }
        return namesToGroups.get(ingName.toLowerCase());
    }

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

    // Returns the result of a Cypher query being passed into the database.
    private static StatementResult doQuery(String cypherQuery) {
        Config noSSL = Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig();
        Driver driver = GraphDatabase.driver("bolt://100.25.153.235:33482", AuthTokens.basic("neo4j","temper-attempt-observers"),noSSL); // <password>
        try (Session session = driver.session()) {
            return session.run(cypherQuery, parameters());
        }
    }
}
