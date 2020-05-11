package backend;

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
}
