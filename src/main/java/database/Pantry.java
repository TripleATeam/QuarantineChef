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

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public String[] getExpirations() {
        return expirations;
    }

    public long[] getQuantities() {
        return quantities;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Pantry)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        Pantry otherPan = (Pantry) other;
        boolean bool1 = Arrays.equals(ingredients, otherPan.ingredients);
        boolean bool2 = Arrays.equals(expirations, otherPan.expirations);
        boolean bool3 = Arrays.equals(quantities, otherPan.quantities);
        return bool1 && bool2 && bool3;
    }

    @Override
    public String toString() {
        return "[" + Arrays.deepToString(this.ingredients) + ", " +
                Arrays.deepToString(this.expirations) + ", " +
                Arrays.toString(this.quantities) + "]";
    }
}
