package database;

import org.neo4j.driver.v1.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.neo4j.driver.v1.Values.parameters;

public class databaseAPI {
    private static final int CUISINE_SIZE = 2;
    private static final int DIET_SIZE = 2;
    private static final int HEALTH_SIZE = 2;

    private static Ingredient[] allIngredients = null;
    private static HashMap<String, IngredientGroup> namesToGroups = null;
    private static HashMap<IngredientGroup, String[]> groupsToNames = null;

    public static void main(String...args) {
        UserProfile up = new UserProfile(0, new int[2], new int[2], new int[2]);
        Ingredient[] ingArr = new Ingredient[2];
        ingArr[0] = getIngredient("Beef Steak");
        ingArr[1] = getIngredient("Chicken Breast");
        Pantry p = new Pantry(ingArr, new String[]{"01-06-2020", "02-01-2020"}, new int[]{1, 1});
        System.out.println(placeInDatabase(up, p));
        Pantry p2 = getPantry(up);
        updateUser(up);
    }

    // @Julia/Alek
    public void removeFromDatabase(UserProfile up) {
        String cypherQuery = "MATCH (n:User) WHERE n.userID = " + up.userID + " DETACH DELETE n";
        doQuery(cypherQuery);
        cypherQuery = "MATCH (n:Pantry) WHERE n.userID = " + up.userID + " DETACH DELETE n";
        doQuery(cypherQuery);
    }

    // @Julia
    public static boolean placeInDatabase(UserProfile up, Pantry pantry) {

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

    private static void createPantry(UserProfile up, Pantry pantry) {
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
        /*sb.append(pantry.getExpirations()[0]);
        for (int i = 1; i < pantry.getExpirations().length; i++) {
            sb.append( "\", \"");
            sb.append(pantry.getExpirations()[i]);
        }*/
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
        sb.append(pantry.getExpirations()[0]);
        for (int i = 1; i < pantry.getExpirations().length; i++) {
            sb.append( "\", \"");
            sb.append(pantry.getExpirations()[i]);
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

    // @Alek
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
            ret = new UserProfile(userID, preferences, diet, health);
        }
        return ret;
    }

    // @Alek
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
        return new Ingredient(ingName, getIngredientGroup(ingName));
    }

    // TODO: allIngredients is always null
    public static Ingredient[] getAllIngredients() {
        return allIngredients.clone();
    }

    private static IngredientGroup getIngredientGroup(String ing) {
        // TODO: Use the hashmap
        return IngredientGroup.ETC;
    }

    // @Julia
    public static Ingredient[] getIngredientsByGroup(IngredientGroup ingGroup) {
        // TODO: Use the hashmap
        ArrayList<Ingredient> retlist = new ArrayList<>();
        for (Ingredient i : getAllIngredients()) {
            if (i.getGroup() == ingGroup) {
                retlist.add(i);
            }
        }
        return (Ingredient[]) retlist.toArray();
    }

    // Returns the result of a Cypher query being passed into the database.
    private static StatementResult doQuery(String cypherQuery) {
        Config noSSL = Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig();
        Driver driver = GraphDatabase.driver("bolt://34.224.17.173:33236", AuthTokens.basic("neo4j","radiator-sink-heights"),noSSL); // <password>
        try (Session session = driver.session()) {
            return session.run(cypherQuery, parameters());
        }
    }
}
