package database;

import org.neo4j.driver.v1.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.neo4j.driver.v1.Values.parameters;

public class databaseAPI {
    private static Ingredient[] allIngredients = null;

    public static void main(String...args) {
        UserProfile up = new UserProfile("alek", new int[2], new int[2], new int[2]);
        System.out.println(Arrays.deepToString(getIngredients(up)));
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
