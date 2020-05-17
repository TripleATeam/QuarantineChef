package backend;

import database.Ingredient;
import database.Pantry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Used to filter a list of recipes into what can be made from the pantry
 */
public class FilterRecipeList {
    private List<Recipe> rawList;
    private List<Ingredient> storedIngredients;

    /**
     * constructor
     * @param recipeList list of recipes to filter down from
     * @param pantry pantry to indicate what the user has
     */
    public FilterRecipeList(List<Recipe> recipeList, Pantry pantry) {
        this.rawList = recipeList;
        this.storedIngredients = new ArrayList<>();
        storedIngredients.addAll(Arrays.asList(pantry.getIngredients()));
    }

    /**
     * filters out recipes that the pantry is missing ingredients for
     * @return returns a list of recipes that can be made with only what is in the pantry
     */
    public List<Recipe> pantrySufficientFilter() {
        // super slow but works
        List<Recipe> retList = new ArrayList<>();
        for (Recipe i : rawList) {
            boolean canMake = true;
            for (EdamamIngredient j : i.getIngredientList()) {
                boolean hasIngredient = false;
                for (Ingredient k : storedIngredients) {
                    if (j.getText().toLowerCase().replaceAll("\\s","")
                            .contains(k.getName().toLowerCase().replaceAll("\\s",""))) {
                        hasIngredient = true;
                        break;
                    }
                }
                if (!hasIngredient) {
                    canMake = false;
                    break;
                }
            }
            if (canMake) {
                retList.add(i);
            }
        }
        return retList;
    }

    // TODO filter that is allows for some things not in pantry
    // wanted feature for the case of a mostly empty pantry
    // this seems way harder
}
