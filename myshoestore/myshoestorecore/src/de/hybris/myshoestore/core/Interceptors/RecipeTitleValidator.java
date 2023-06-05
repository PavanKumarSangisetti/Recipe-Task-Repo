package de.hybris.myshoestore.core.Interceptors;

import de.hybris.myshoestore.core.model.RecipeModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import org.springframework.util.StringUtils;

public class RecipeTitleValidator implements ValidateInterceptor {
    private static final int MAX_TITLE_LENGTH = 50;
    @Override
    public void onValidate(Object obj, InterceptorContext ctx) throws InterceptorException {

        if (obj instanceof RecipeModel) {
            final RecipeModel model = (RecipeModel) obj;
            if (StringUtils.isEmpty(model.getTitle()) && model.getTitle().length() < MAX_TITLE_LENGTH) {
                throw new InterceptorException("Recipe Title cannot have empty and size not greater than 50");
            }
        }



    }
}
