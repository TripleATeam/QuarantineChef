package database;

public class Ingredient {
    private String name;
    private IngredientGroup group;

    /**
     * Returns an Ingredient with the given name and IngredientGroup.
     *
     * @param name      Ingredient name
     * @param group     IngredientGroup associated with ingredient
     */
    public Ingredient(String name, IngredientGroup group) {
        this.name = name;
        this.group = group;
    }

    /**
     * Returns the ingredient's name.
     *
     * @return  Ingredient's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ingredient's IngredientGroup.
     *
     * @return  Ingredient's IngredientGroup
     */
    public IngredientGroup getGroup() {
        return group;
    }

    /**
     * Returns a string containing the name and IngredientGroup
     * of the Ingredient of the form ([name], [group]).
     *
     * @return String representing the Ingredient
     */
    @Override
    public String toString() {
        return "(" + name + ", " + group + ")";
    }

    /**
     * Returns a boolean denoting if another Object is equal to this one.
     * If the other Object is anything other than a Ingredient with the exact
     * same values in the same fields, this will be false. If it is such
     * an Ingredient, then this method will return true.
     *
     * @param other Another Object that this will be compared to
     * @return      A boolean that signifies if other is equal to this.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Ingredient)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        Ingredient otherIng = (Ingredient) other;
        boolean bool1 = name.equals(otherIng.name);
        boolean bool2 = group.equals(otherIng.group);
        return bool1 && bool2;
    }
}
