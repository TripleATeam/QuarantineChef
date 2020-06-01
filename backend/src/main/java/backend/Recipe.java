package backend;

import java.util.Comparator;
import java.util.List;

/**
 * Object to contain information about a recipe such as ingredient list, image url, ect
 */
public class Recipe {
    // Fields
    private String label;
    private List<EdamamIngredient> ingredientList;
    private String imageUrl;
    private String recipeUrl;
    // for filtering
    private int missing;

    /**
     * constructor
     * @param label name of recipe
     * @param ingredientList list of edamam ingredients the recipe uses
     * @param imageUrl url to an image of this recipe
     * @param recipeUrl url to recipe instructions
     */
    public Recipe(String label, List<EdamamIngredient> ingredientList, String imageUrl, String recipeUrl) {
        this.label = label;
        this.ingredientList = ingredientList;
        this.imageUrl = imageUrl;
        this.recipeUrl = recipeUrl;
        this.missing = Integer.MAX_VALUE;
    }

    /**
     * @return name of the recipe
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the list of edamam ingredients the recipe uses
     */
    public List<EdamamIngredient> getIngredientList() {
        return ingredientList;
    }

    /**
     * @return url to an image of this recipe
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @return url to recipe instructions
     */
    public String getRecipeUrl() {
        return recipeUrl;
    }

    /**
     * @param n sets the number of missing ingredients in this recipe
     */
    public void setMissing(int n) {
        this.missing = n;
    }
    /**
     * @return number of missing ingredients needed to make this recipe
     */
    public int getMissing() {
        return missing;
    }

    public static class CompareMissing implements Comparator<Recipe> {

        @Override
        public int compare(Recipe o1, Recipe o2) {
            return Integer.compare(o1.getMissing(), o2.getMissing());
        }
    }
}
