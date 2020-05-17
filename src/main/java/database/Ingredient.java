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
}
