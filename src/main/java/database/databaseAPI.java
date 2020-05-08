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
        System.out.println(getPantry(up));
    }

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
                ingredients[i] = new Ingredient(ingName, /*namesToGroups.get(ingName)*/ IngredientGroup.ETC);
                quantities[i] = (long) objArr3[i];
            }
            ret = new Pantry(ingredients, expirations, quantities);
        }
        return ret;
    }

    // @Alek
    public Ingredient[] getAllIngredients() {
        return allIngredients.clone();
    }

    // @Julia/Alek
    public UserProfile removeFromDatabase() {
        return null;
    }

    // @Julia
    public boolean placeInDatabase(UserProfile up, Ingredient[] ingArr) {
        // TODO: Finish implementation of this method after driver support
        return false;
    }

    // @Alek
    public static Ingredient[] getIngredients(UserProfile up) {
        ArrayList<Ingredient> retlist = new ArrayList<>();
        String cypherQuery = "MATCH (n) RETURN n.Username as username";
        StatementResult result = doQuery(cypherQuery);
        while (result.hasNext()) {
            retlist.add(new Ingredient(result.next().get("username").asString(), IngredientGroup.ETC));
        }
        Ingredient[] retArr = new Ingredient[retlist.size()];
        for (int i = 0; i < retlist.size(); i++) {
            retArr[i] = retlist.get(i);
        }
        return retArr;
    }

    private IngredientGroup getIngredientGroup(Ingredient ing) {
        return IngredientGroup.ETC;
    }

    // @Julia
    public Ingredient[] getIngredientsByGroup(IngredientGroup ingGroup) {
        // Might be a faster way to implement this
        // @Julia sorry, I just wanted a little implementation, feel free to improve it
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
