package backend;

import database.Ingredient;
import database.Pantry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// takes a list of recipes and a pantry
// returns a list of recipes
public class FilterRecipeList {
    private List<Recipe> rawList;
    private List<Ingredient> storedIngredients;

    public FilterRecipeList(List<Recipe> recipeList, Pantry pantry) {
        this.rawList = recipeList;
        this.storedIngredients = new ArrayList<>();
        storedIngredients.addAll(Arrays.asList(pantry.getIngredients()));
    }

    // super slow but works
    // outputs things that can be made with only what is in the pantry
    public List<Recipe> pantrySufficientFilter() {
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
    // this seems way harder

}
