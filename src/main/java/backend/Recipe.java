package backend;

import database.Ingredient;

import java.util.List;

/*
 * Object to contain information about a recipe such as ingredient list, image url, ect
 */
public class Recipe {
    // Fields
    private List<Ingredient> ingredientList;
    private String imageUrl;
    private String recipeUrl;

    public Recipe(List<Ingredient> ingredientList, String imageUrl, String recipeUrl) {
        this.ingredientList = ingredientList;
        this.imageUrl = imageUrl;
        this.recipeUrl = recipeUrl;
    }

    // getters

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRecipeUrl() {
        return recipeUrl;
    }
}
