package backend.tests;

import backend.EdamamIngredient;
import backend.FilterRecipeList;
import backend.Recipe;
import backend.RecipeParser;
import database.Ingredient;
import database.IngredientGroup;
import database.Pantry;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FilterRecipeListTest {

    @Test
    void testSimplePantrySufficientFilter() {
        // set up test pantry
        Ingredient[] ingredients = new Ingredient[5];
        ingredients[0] = new Ingredient("aaa", IngredientGroup.ETC);
        ingredients[1] = new Ingredient("bbb", IngredientGroup.ETC);
        ingredients[2] = new Ingredient("ccc", IngredientGroup.ETC);
        ingredients[3] = new Ingredient("ddd", IngredientGroup.ETC);
        ingredients[4] = new Ingredient("eee", IngredientGroup.ETC);
        String[] expiration = new String[5];
        int[] quantities = new int[5];
        Pantry pantry = new Pantry(ingredients, expiration, quantities);

        // set up test recipeList
        List<Recipe> recipeList = new ArrayList<>();
        // should be in final list
        List<EdamamIngredient> r1Ing = new ArrayList<>();
        r1Ing.add(new EdamamIngredient("ccc", 1));
        r1Ing.add(new EdamamIngredient("Aaa", 1));
        r1Ing.add(new EdamamIngredient("1 chopped bbb", 1));
        recipeList.add(new Recipe("r1", r1Ing, "r1img", "r1url"));

        // should NOT be in final list
        List<EdamamIngredient> r2Ing = new ArrayList<>();
        r2Ing.add(new EdamamIngredient("ccc", 1));
        r2Ing.add(new EdamamIngredient("fff", 1));
        r2Ing.add(new EdamamIngredient("1 chopped bbb", 1));
        recipeList.add(new Recipe("r2", r2Ing, "r2img", "r2url"));

        // should be in final list
        List<EdamamIngredient> r3Ing = new ArrayList<>();
        r3Ing.add(new EdamamIngredient("ccc", 1));
        recipeList.add(new Recipe("r3", r3Ing, "r3img", "r3url"));

        FilterRecipeList filterRecipeList = new FilterRecipeList(recipeList, pantry);
        List<Recipe> resultList = filterRecipeList.pantrySufficientFilter();

        System.out.println("Expect: r1 and r3; Not r2");
        System.out.println("Actual:");
        for (Recipe i : resultList) {
            System.out.println(i.getLabel());
        }
    }

    @Test
    void testChickenLargePantrySufficientFilter() {
        // set up test pantry
        Ingredient[] ingredients = new Ingredient[9];
        ingredients[0] = new Ingredient("chicken", IngredientGroup.ETC);
        ingredients[1] = new Ingredient("pepper", IngredientGroup.ETC);
        ingredients[2] = new Ingredient("thyme", IngredientGroup.ETC);
        ingredients[3] = new Ingredient("olive oil", IngredientGroup.ETC);
        ingredients[4] = new Ingredient("pork", IngredientGroup.ETC);
        ingredients[5] = new Ingredient("lemon", IngredientGroup.ETC);
        ingredients[6] = new Ingredient("garlic", IngredientGroup.ETC);
        ingredients[7] = new Ingredient("wine", IngredientGroup.ETC);
        ingredients[8] = new Ingredient("bacon", IngredientGroup.ETC);

        String[] expiration = new String[9];
        int[] quantities = new int[9];
        Pantry pantry = new Pantry(ingredients, expiration, quantities);

        String httpRequest =
                "https://api.edamam.com/search?q=chicken&app_id=56d7887a&app_key=4740dac00a0df8a5f23c6f81ad502e26";
        RecipeParser rp = new RecipeParser(httpRequest);
        List<Recipe> recipeList = rp.getRecipeList();

        FilterRecipeList filterRecipeList = new FilterRecipeList(recipeList, pantry);
        List<Recipe> resultList = filterRecipeList.pantrySufficientFilter();

        System.out.println("Expected: Catalan Chicken, Roast Chicken");
        System.out.println("Actual:");
        for (Recipe i : resultList) {
            System.out.println(i.getLabel());
        }
    }

    // 200 too large for string probably
    @Test
    void testPork100SufficientFilter() {
        // set up test pantry
        Ingredient[] ingredients = new Ingredient[9];
        ingredients[0] = new Ingredient("pork", IngredientGroup.ETC);
        ingredients[1] = new Ingredient("pepper", IngredientGroup.ETC);
        ingredients[2] = new Ingredient("thyme", IngredientGroup.ETC);
        ingredients[3] = new Ingredient("olive oil", IngredientGroup.ETC);
        ingredients[4] = new Ingredient("onion", IngredientGroup.ETC);
        ingredients[5] = new Ingredient("pasta", IngredientGroup.ETC);
        ingredients[6] = new Ingredient("garlic", IngredientGroup.ETC);
        ingredients[7] = new Ingredient("wine", IngredientGroup.ETC);
        ingredients[8] = new Ingredient("bacon", IngredientGroup.ETC);

        String[] expiration = new String[9];
        int[] quantities = new int[9];
        Pantry pantry = new Pantry(ingredients, expiration, quantities);

        String httpRequest =
                "https://api.edamam.com/search?q=pork&app_id=56d7887a&app_key=4740dac00a0df8a5f23c6f81ad502e26&to=100";
        RecipeParser rp = new RecipeParser(httpRequest);
        List<Recipe> recipeList = rp.getRecipeList();

        FilterRecipeList filterRecipeList = new FilterRecipeList(recipeList, pantry);
        List<Recipe> resultList = filterRecipeList.pantrySufficientFilter();

        System.out.println("Expected: Unknown, filtering 100 recipes");
        System.out.println("Actual:");
        for (Recipe i : resultList) {
            System.out.println(i.getLabel());
        }
    }

    @Test
    void testSimpleMissingSort() {
        // set up test pantry
        Ingredient[] ingredients = new Ingredient[5];
        ingredients[0] = new Ingredient("aaa", IngredientGroup.ETC);
        ingredients[1] = new Ingredient("bbb", IngredientGroup.ETC);
        ingredients[2] = new Ingredient("ccc", IngredientGroup.ETC);
        ingredients[3] = new Ingredient("ddd", IngredientGroup.ETC);
        ingredients[4] = new Ingredient("eee", IngredientGroup.ETC);
        String[] expiration = new String[5];
        int[] quantities = new int[5];
        Pantry pantry = new Pantry(ingredients, expiration, quantities);

        // set up test recipeList
        List<Recipe> recipeList = new ArrayList<>();
        // should be in final list
        List<EdamamIngredient> r1Ing = new ArrayList<>();
        r1Ing.add(new EdamamIngredient("ccc", 1));
        r1Ing.add(new EdamamIngredient("Aaa", 1));
        r1Ing.add(new EdamamIngredient("1 chopped bbb", 1));
        recipeList.add(new Recipe("r1", r1Ing, "r1img", "r1url"));

        // should NOT be in final list
        List<EdamamIngredient> r2Ing = new ArrayList<>();
        r2Ing.add(new EdamamIngredient("ccc", 1));
        r2Ing.add(new EdamamIngredient("fff", 1));
        r2Ing.add(new EdamamIngredient("1 chopped bbb", 1));
        recipeList.add(new Recipe("r2", r2Ing, "r2img", "r2url"));

        // should be in final list
        List<EdamamIngredient> r3Ing = new ArrayList<>();
        r3Ing.add(new EdamamIngredient("ccc", 1));
        recipeList.add(new Recipe("r3", r3Ing, "r3img", "r3url"));

        // should be in final list
        List<EdamamIngredient> r4Ing = new ArrayList<>();
        r4Ing.add(new EdamamIngredient("ccc", 1));
        r4Ing.add(new EdamamIngredient("ff", 1));
        r4Ing.add(new EdamamIngredient("gg", 1));
        r4Ing.add(new EdamamIngredient("hh", 1));
        r4Ing.add(new EdamamIngredient("jj", 1));
        r4Ing.add(new EdamamIngredient("kk", 1));
        r4Ing.add(new EdamamIngredient("ll", 1));
        recipeList.add(new Recipe("r4", r4Ing, "img", "url"));

        FilterRecipeList filterRecipeList = new FilterRecipeList(recipeList, pantry);
        List<Recipe> resultList = filterRecipeList.sortByMissing();

        System.out.println("Expect: r1, r3, r2, r4");
        System.out.println("Actual:");
        for (Recipe i : resultList) {
            System.out.println(i.getLabel());
        }
    }



    @Test
    void testChickenMissingSort() {
        // set up test pantry
        Ingredient[] ingredients = new Ingredient[9];
        ingredients[0] = new Ingredient("chicken", IngredientGroup.ETC);
        ingredients[1] = new Ingredient("pepper", IngredientGroup.ETC);
        ingredients[2] = new Ingredient("thyme", IngredientGroup.ETC);
        ingredients[3] = new Ingredient("olive oil", IngredientGroup.ETC);
        ingredients[4] = new Ingredient("pork", IngredientGroup.ETC);
        ingredients[5] = new Ingredient("lemon", IngredientGroup.ETC);
        ingredients[6] = new Ingredient("garlic", IngredientGroup.ETC);
        ingredients[7] = new Ingredient("wine", IngredientGroup.ETC);
        ingredients[8] = new Ingredient("bacon", IngredientGroup.ETC);

        String[] expiration = new String[9];
        int[] quantities = new int[9];
        Pantry pantry = new Pantry(ingredients, expiration, quantities);

        String httpRequest =
                "https://api.edamam.com/search?q=chicken&app_id=56d7887a&app_key=4740dac00a0df8a5f23c6f81ad502e26";
        RecipeParser rp = new RecipeParser(httpRequest);
        List<Recipe> recipeList = rp.getRecipeList();

        FilterRecipeList filterRecipeList = new FilterRecipeList(recipeList, pantry);
        List<Recipe> resultList = filterRecipeList.sortByMissing();

        System.out.println("Expected: Catalan Chicken, Roast Chicken, others..");
        System.out.println("Actual:");
        for (Recipe i : resultList) {
            System.out.println(i.getLabel());
        }
    }
}