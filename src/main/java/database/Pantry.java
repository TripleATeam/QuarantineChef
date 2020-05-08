package database;

import java.util.Arrays;

public class Pantry {
    private Ingredient[] ingredients;
    private String[] expirations;
    private long[] quantities;

    public Pantry (Ingredient[] ingredients, String[] expirations, long[] quantities) {
        this.ingredients = ingredients;
        this.expirations = expirations;
        this.quantities = quantities;
    }

    @Override
    public String toString() {
        return "[" + Arrays.deepToString(this.ingredients) + ", " +
                Arrays.deepToString(this.expirations) + ", " +
                Arrays.toString(this.quantities) + "]";
    }
}
