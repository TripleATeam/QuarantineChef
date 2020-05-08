package database;

public class Ingredient {
    private String name;
    private IngredientGroup group;

    public Ingredient(String name, IngredientGroup group) {
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public IngredientGroup getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "(" + name + ", " + group + ")";
    }
}
