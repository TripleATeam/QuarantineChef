package database;

import org.neo4j.driver.v1.*;

import java.util.ArrayList;

import static org.neo4j.driver.v1.Values.parameters;

public class databaseAPI {
    public static Ingredient[] allIngredients = null;

    // @Alek
    public Ingredient[] getAllIngredients() {
        return allIngredients.clone();
    }

    // @Julia
    public boolean placeInDatabase(UserProfile up, Ingredient[] ingArr) {
        // TODO: Finish implementation of this method after driver support
        return false;
    }

    // @Alek
    public Ingredient[] getIngredients(UserProfile up) {
//        Config noSSL = Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig();
//        Driver driver = GraphDatabase.driver("bolt://34.224.17.173:33236", AuthTokens.basic("neo4j","radiator-sink-heights"),noSSL); // <password>
//        try (Session session = driver.session()) {
//            String cypherQuery =
//                    "MATCH (n) " +
//                            "RETURN n.username as username"; // Type the query
//            StatementResult result = session.run(cypherQuery, parameters());
//            while (result.hasNext()) {
//                System.out.println(result.next().get("username")); // Find the fields you want
//            }
//        }
        return null;
    }

    // @Julia
    public Ingredient[] getIngredientsByGroup(IngredientGroup ingGroup) {
        // Might be a faster way to implement this
        // @Julia sorry, I just wanted a little implementation, feel free to improve it
        ArrayList<Ingredient> retlist = new ArrayList<>();
        for (Ingredient i : getAllIngredients()) {
            if (i.group == ingGroup) {
                retlist.add(i);
            }
        }
        return (Ingredient[]) retlist.toArray();
    }
}
