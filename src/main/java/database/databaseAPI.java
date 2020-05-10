package database;

import org.neo4j.driver.v1.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.neo4j.driver.v1.Values.parameters;

public class databaseAPI {
    private static Ingredient[] allIngredients = null;
    private static HashMap<String, IngredientGroup> namesToGroups = null;
    private static HashMap<IngredientGroup, String[]> groupsToNames = null;

    public static void main(String...args) {
        UserProfile up = new UserProfile(0, new int[2], new int[2], new int[2]);
        Ingredient[] ingArr = new Ingredient[2];
        ingArr[0] = new Ingredient("salt", IngredientGroup.ETC);
        ingArr[1] = new Ingredient("pepper", IngredientGroup.ETC);
        Pantry p = new Pantry(ingArr, new String[]{"01-01-2020", "02-01-2020", "03-01-2020"}, new long[]{0, 1, 2});
        System.out.println(placeInDatabase(up, p));
        System.out.println(getPantry(up));
        updateUser(up);
    }

    // @Alek
    public static Pantry getPantry(UserProfile up) {
        Pantry ret = null;
        String cypherQuery = "MATCH (a:User)-[r:UserPantry]->(b:Pantry) WHERE a.userID = " +
                up.userID + " RETURN b.numIngredients as numIngredients, " +
                "b.ingredients as ingredients, b.quantities as quantities, b.expirations as expirations";
        StatementResult result = doQuery(cypherQuery);
        String[] expirations;
        Ingredient[] ingredients;
        long[] quantities;
        while (result.hasNext()) {
            Record curr = result.next();

            int size = curr.get("numIngredients").asInt();
            expirations =  new String[size];
            ingredients = new Ingredient[size];
            quantities = new long[size];

            Object[] objArr1 = curr.get("expirations").asList().toArray();
            Object[] objArr2 = curr.get("ingredients").asList().toArray();
            Object[] objArr3 = curr.get("quantities").asList().toArray();
            for (int i = 0; i < size; i++) {
                expirations[i] = (String) objArr1[i];
                String ingName = (String) objArr2[i];
                ingredients[i] = new Ingredient(ingName, getIngredientGroup(ingName));
                quantities[i] = (long) objArr3[i];
            }
            ret = new Pantry(ingredients, expirations, quantities);
        }
        return ret;
    }

    // @Alek- update this after we get the ingredients
    public Ingredient[] getAllIngredients() {
        return allIngredients.clone();
    }

    // @Julia/Alek
    public void removeFromDatabase(UserProfile up) {
        String cypherQuery = "MATCH (n:User) WHERE n.userID = " + up.userID + " DETACH DELETE n";
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

    // @Julia
    public static boolean placeInDatabase(UserProfile up, Pantry pantry) {
        // TODO: Check if thing already exists
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

        sb = new StringBuilder();
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
        sb.append(pantry.getExpirations()[0]);
        for (int i = 1; i < pantry.getExpirations().length; i++) {
            sb.append( "\", \"");
            sb.append(pantry.getExpirations()[i]);
        }
        sb.append("\"], quantities: [");
        sb.append(pantry.getQuantities()[0]);
        for (int i = 1; i < pantry.getQuantities().length; i++) {
            sb.append( ", ");
            sb.append(pantry.getQuantities()[i]);
        }
        sb.append("]})");
        cypherQuery = sb.toString();
        doQuery(cypherQuery);

        return true;
    }

    private static IngredientGroup getIngredientGroup(String ing) {
        // Use the hashmap
        return IngredientGroup.ETC;
    }

    // @Julia
    public Ingredient[] getIngredientsByGroup(IngredientGroup ingGroup) {
        // Use the hashmap
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
