package de.hybris.myshoestore.core.recipe.dao;
import de.hybris.myshoestore.core.model.IngredientModel;
import de.hybris.myshoestore.core.model.RecipeModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeDaoImpl implements RecipeDao{

    private ModelService modelService;

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    private FlexibleSearchService flexibleSearchService;

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<RecipeModel> getAllRecipes() {
        final String query = "SELECT {PK} FROM {Recipe}";
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString());
        final SearchResult searchResult = getFlexibleSearchService().search(searchQuery);
        return searchResult.getResult();
    }

    @Override
    public void createRecipe(RecipeModel recipeModel) {
        getModelService().save(recipeModel);
    }

    @Override
    public List<IngredientModel> getAllIngredients() {
        final String query = "SELECT {PK} FROM {Ingredient}";
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString());
        final SearchResult searchResult = getFlexibleSearchService().search(searchQuery);
        return searchResult.getResult();
    }

    @Override
    public List<RecipeModel> getSpecificRecipeDetails(String code) {
        final Map<String, Object> params = new HashMap<String, Object>();
        final String query = "SELECT {PK} FROM {Recipe} WHERE {code}=?code";
        params.put("code",code);
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString());
        searchQuery.addQueryParameters(params);
        final SearchResult searchResult = getFlexibleSearchService().search(searchQuery);
        return searchResult.getResult() == null ? Collections.emptyList() : searchResult.getResult();
    }

    @Override
    public void removeRecipe(List<RecipeModel> recipe) {
        getModelService().removeAll(recipe);
    }

    @Override
    public void replaceRecipe(RecipeModel recipe, String code) {
        getModelService().removeAll(getSpecificRecipeDetails(code));
        getModelService().save(recipe);

    }
}
