package de.hybris.myshoestore.facades.recipe;

import de.hybris.myshoestore.facades.product.data.MyEmployeeData;
import de.hybris.platform.commercefacades.user.data.IngredientsData;
import de.hybris.platform.commercefacades.user.data.RecipeData;

import java.util.List;

public interface RecipeFacade {

    public List<RecipeData> getAllRecipes();

    public List<IngredientsData> getAllIngredients();

    public void createRecipe(final RecipeData RecipeData);

    public List<RecipeData> getSpecificRecipeDetails(final String code);

    public void removeRecipe(final String empId);

    public void replaceRecipe(final RecipeData recipeData, final String code);

}
