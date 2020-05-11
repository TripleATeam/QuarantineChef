package backend;

import java.util.List;

/*
 * Object to contain information about a recipe such as ingredient list, image url, ect
 */
public class Recipe {
    // Fields
    private String label;
    private List<EdamamIngredient> ingredientList;
    private String imageUrl;
    private String recipeUrl;

    Recipe(String label, List<EdamamIngredient> ingredientList, String imageUrl, String recipeUrl) {
        this.label = label;
        this.ingredientList = ingredientList;
        this.imageUrl = imageUrl;
        this.recipeUrl = recipeUrl;
    }

    // getters

    public String getLabel() {
        return label;
    }

    public List<EdamamIngredient> getIngredientList() {
        return ingredientList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRecipeUrl() {
        return recipeUrl;
    }
}
