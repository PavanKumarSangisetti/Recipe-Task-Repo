package de.hybris.myshoestore.core.recipe.service;

import de.hybris.myshoestore.core.model.IngredientModel;
import de.hybris.myshoestore.core.model.MyEmployeeModel;
import de.hybris.myshoestore.core.model.RecipeModel;

import java.util.List;

public interface RecipeService {

    public List<RecipeModel> getAllRecipes();

    public List<IngredientModel> getAllIngredients();

    public void createRecipe(RecipeModel recipeModel);

    public List<RecipeModel> getSpecificRecipeDetails(final String code);

    public void removeRecipe(final String code);
    public void replaceRecipe(RecipeModel employee, final String code);


}
