package backend;

/**
 * ingredient class copied from edamam to make parsing easier than using our ingredient class
 */
public class EdamamIngredient {
    private String text;
    private double weight;

    /**
     * constructs an edamam ingredient
     * @param text the name of the ingredient
     * @param weight the amount of the ingredient
     */
    public EdamamIngredient(String text, double weight) {
        this.text = text;
        this.weight = weight;
    }

    /**
     * @return the amount of the ingredient
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @return the name of the ingredient
     */
    public String getText() {
        return text;
    }
}
