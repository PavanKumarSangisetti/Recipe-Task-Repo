package de.hybris.myshoestore.facades.recipe;
import de.hybris.myshoestore.core.model.IngredientModel;
import de.hybris.myshoestore.core.model.MyEmployeeModel;
import de.hybris.myshoestore.core.model.RecipeModel;
import de.hybris.myshoestore.core.recipe.service.RecipeService;
import de.hybris.myshoestore.facades.populators.MyEmployeeDataReversePopulator;
import de.hybris.myshoestore.facades.populators.RecipeDataReversePopulator;
import de.hybris.myshoestore.facades.product.data.MyEmployeeData;
import de.hybris.platform.commercefacades.user.data.IngredientsData;
import de.hybris.platform.commercefacades.user.data.RecipeData;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
public class RecipeFacadeImpl implements RecipeFacade{
    private RecipeService recipeService;

    private ModelService modelService;

    private RecipeDataReversePopulator recipeDataReversePopulator;

    public RecipeDataReversePopulator getRecipeDataReversePopulator() {
        return recipeDataReversePopulator;
    }

    public void setRecipeDataReversePopulator(RecipeDataReversePopulator recipeDataReversePopulator) {
        this.recipeDataReversePopulator = recipeDataReversePopulator;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public RecipeService getRecipeService() {
        return recipeService;
    }
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @Resource(name = "recipeDataConverter")
    private Converter<RecipeModel, RecipeData> recipeDataConverter;

    @Resource(name = "recipeDataReverseConverter")
    private Converter<RecipeData, RecipeModel> recipeDataReverseConverter;

    public Converter<RecipeData, RecipeModel> getRecipeDataReverseConverter() {
        return recipeDataReverseConverter;
    }

    public void setRecipeDataReverseConverter(Converter<RecipeData, RecipeModel> recipeDataReverseConverter) {
        this.recipeDataReverseConverter = recipeDataReverseConverter;
    }

    public Converter<RecipeModel, RecipeData> getRecipeDataConverter() {
        return recipeDataConverter;
    }

    public void setRecipeDataConverter(Converter<RecipeModel, RecipeData> recipeDataConverter) {
        this.recipeDataConverter = recipeDataConverter;
    }

    @Resource(name = "ingredientsDataConverter")
    private Converter<IngredientModel, IngredientsData> ingredientsDataConverter;
    @Override
    public List<RecipeData> getAllRecipes() {
        final List<RecipeData> products = new ArrayList<RecipeData>();
        final List<RecipeModel> model = getRecipeService().getAllRecipes();
        final List<RecipeData> recipeData = recipeDataConverter.convertAll(model);
        products.addAll(recipeData );
        return products;
    }
    @Override
    public List<IngredientsData> getAllIngredients() {
        final List<IngredientsData> products = new ArrayList<IngredientsData>();
        final List<IngredientModel> model = getRecipeService().getAllIngredients();
        final List<IngredientsData> ingredientData = ingredientsDataConverter.convertAll(model);
        products.addAll(ingredientData );
        return products;
    }

    @Override
    public void createRecipe(RecipeData recipeData) {
        final RecipeModel recipeModel = getModelService().create(RecipeModel.class);
        getRecipeDataReversePopulator().populate(recipeData,recipeModel);
        getRecipeService().createRecipe(recipeModel);
    }

    @Override
    public List<RecipeData> getSpecificRecipeDetails(String code) {

        final List<RecipeData> recipe = new ArrayList<RecipeData>();
        final List<RecipeModel> model = getRecipeService().getSpecificRecipeDetails(code);
        final List<RecipeData> recipeData = recipeDataConverter.convertAll(model);
        recipe.addAll(recipeData);
        return recipe;
    }

    @Override
    public void removeRecipe(String code) {
        getRecipeService().removeRecipe(code);

    }

    @Override
    public void replaceRecipe(RecipeData recipeData, String code) {

        final RecipeModel recipeModel = getModelService().create(RecipeModel.class);

        getRecipeDataReversePopulator().populate(recipeData,recipeModel);

        getRecipeService().replaceRecipe(recipeModel, code);

    }
}
