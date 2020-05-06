package database;

public class Ingredient {
    public String name;
    public IngredientGroup group;

    public Ingredient(String name, IngredientGroup group) {
        this.name = name;
        this.group = group;
    }

    @Override
    public String toString() {
        return "(" + name + ", " + group + ")";
    }
}
