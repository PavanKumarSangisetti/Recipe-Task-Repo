package de.hybris.myshoestore.storefront.controllers.pages;

import de.hybris.myshoestore.core.enums.WorkTypeEnumeration;
import de.hybris.myshoestore.core.form.EmployeeForm;
import de.hybris.myshoestore.core.form.RecipeForm;
import de.hybris.myshoestore.facades.product.data.MyEmployeeData;
import de.hybris.myshoestore.facades.recipe.RecipeFacade;
import de.hybris.myshoestore.storefront.controllers.ControllerConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.user.data.IngredientsData;
import de.hybris.platform.commercefacades.user.data.RecipeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
@RequestMapping(value = "/recipe")
public class RecipeDetailsController extends AbstractPageController {
    @Autowired
    private RecipeFacade recipeFacade;
    public RecipeFacade getRecipeFacade() {
        return recipeFacade;
    }
    public void setRecipeFacade(RecipeFacade recipeFacade) {
        this.recipeFacade = recipeFacade;
    }
    private static final String UPDATE_OLD_PASSWORD_CMS_PAGE = "recipeDetails";
    private static final String UPDATE_OLD_PASSWORD_CMS_PAGES = "ingredientsDetails";

    private static final String FORM_GLOBAL_ERROR = "form.global.error";

    private static final String REDIRECT_TO_RECIPE = REDIRECT_PREFIX + "/recipe/recipeDetails";
    @RequestMapping(value="/recipeDetails", method= RequestMethod.GET)
    public String getAllRecipesDetails(final Model model) throws CMSItemNotFoundException
    {
        final List<RecipeData> recipeData = recipeFacade.getAllRecipes();
        model.addAttribute("recipeData", recipeData );
        storeCmsPageInModel(model, getContentPageForLabelOrId(UPDATE_OLD_PASSWORD_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(UPDATE_OLD_PASSWORD_CMS_PAGE));
        return ControllerConstants.Views.Pages.Product.recipeDetails;
    }
    @RequestMapping(value="/ingredientsDetails", method= RequestMethod.GET)
    public String getAllIngrediantsDetails(final Model model) throws CMSItemNotFoundException
    {
        final List<IngredientsData> IngredientsData = recipeFacade.getAllIngredients();

        model.addAttribute("IngrediantsData", IngredientsData );

        storeCmsPageInModel(model, getContentPageForLabelOrId(UPDATE_OLD_PASSWORD_CMS_PAGES));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(UPDATE_OLD_PASSWORD_CMS_PAGES));

        return ControllerConstants.Views.Pages.Product.ingredientsDetails;

    }


    @RequestMapping(value = "/create-recipe", method = RequestMethod.GET)
    public String createRecipe(final Model model) throws CMSItemNotFoundException
    {
        final RecipeForm recipeForm = getPreparedRecipeForm();
        model.addAttribute("recipeForm", new RecipeForm());
        final ContentPageModel createRecipePage = getContentPageForLabelOrId("create-recipe");
        storeCmsPageInModel(model, createRecipePage);
        setUpMetaDataForContentPage(model, createRecipePage);
        return ControllerConstants.Views.Pages.Product.CreateRecipe;
    }

    @RequestMapping(value = "/create-recipe", method = RequestMethod.POST)
    public String createEmployee(final RecipeForm recipeForm, final BindingResult bindingResult, final Model model,
                                 final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
    {
        if (bindingResult.hasErrors())
        {
            GlobalMessages.addErrorMessage(model, FORM_GLOBAL_ERROR);
            final ContentPageModel createrecipe = getContentPageForLabelOrId("create-recipe");
            storeCmsPageInModel(model, createrecipe);
            setUpMetaDataForContentPage(model, createrecipe);
            return getViewForPage(model);
        }
        String returnAction = REDIRECT_TO_RECIPE;
        final RecipeData recipe = new RecipeData();
        recipe.setCode(recipeForm.getCode());
        recipe.setTitle(recipeForm.getTitle());
        recipe.setDescription(recipeForm.getDescription());


        model.addAttribute("recipeForm", new RecipeForm());

        final ContentPageModel createRecipePage = getContentPageForLabelOrId("create-recipe");
        storeCmsPageInModel(model, createRecipePage);
        setUpMetaDataForContentPage(model, createRecipePage);
        getRecipeFacade().createRecipe(recipe);
        GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER,
                "Recipe Created!", null);

        return returnAction;
    }


    @RequestMapping(value = "/update-recipe", method = RequestMethod.GET)
    public String updateRecipe(final Model model) throws CMSItemNotFoundException
    {

        final RecipeForm recipeForm = getPreparedRecipeForm();
        model.addAttribute("recipeForm", new RecipeForm());
        final ContentPageModel updateRecipePage = getContentPageForLabelOrId("update-recipe");
        storeCmsPageInModel(model, updateRecipePage);
        setUpMetaDataForContentPage(model, updateRecipePage);
        return ControllerConstants.Views.Pages.Product.UpdateRecipeDetails;
    }

    @RequestMapping(value = "/update-recipe", method = RequestMethod.POST)
    public String updateRecipe(final RecipeForm recipeForm, final BindingResult bindingResult, final Model model,
                                 final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
    {

        if (bindingResult.hasErrors())
        {
            GlobalMessages.addErrorMessage(model, FORM_GLOBAL_ERROR);
            final ContentPageModel updateRecipe = getContentPageForLabelOrId("update-recipe");
            storeCmsPageInModel(model, updateRecipe);
            setUpMetaDataForContentPage(model, updateRecipe);
            return getViewForPage(model);
        }
        String returnAction = REDIRECT_TO_RECIPE;
        final RecipeData recipe = new RecipeData();
        recipe.setCode(recipeForm.getCode());
        recipe.setTitle(recipeForm.getTitle());
        recipe.setDescription(recipeForm.getDescription());


        model.addAttribute("recipeForm", new RecipeForm());

        final ContentPageModel updateRecipePage = getContentPageForLabelOrId("update-recipe");
        storeCmsPageInModel(model, updateRecipePage);
        setUpMetaDataForContentPage(model, updateRecipePage);
        if(getRecipeFacade().getSpecificRecipeDetails(recipe.getCode()).isEmpty())
        {
            GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, "Recipe Not Found", null);
            return REDIRECT_PREFIX + "update-recipe";
        }
        getRecipeFacade().replaceRecipe(recipe,recipeForm.getCode());
        GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER,
                "Recipe Updated!", null);

        return returnAction;
    }

    protected RecipeForm getPreparedRecipeForm()
    {
        final RecipeForm recipeForm = new RecipeForm();
        recipeForm.setCode("Recipe Code");
        recipeForm.setTitle("Recipe Title");
        recipeForm.setDescription("Recipe Description");

        return recipeForm;
    }

}
