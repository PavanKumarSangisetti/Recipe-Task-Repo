package de.hybris.myshoestore.core.recipe.dao;
import de.hybris.myshoestore.core.model.IngredientModel;
import de.hybris.myshoestore.core.model.RecipeModel;
import java.util.List;
public interface RecipeDao {
    public List<RecipeModel> getAllRecipes();
    public void createRecipe(RecipeModel recipeModel);
    public List<IngredientModel> getAllIngredients();
    public List<RecipeModel> getSpecificRecipeDetails(final String code);
    public void removeRecipe(List<RecipeModel> recipe);

    public void replaceRecipe(RecipeModel recipe, final String code);

}
