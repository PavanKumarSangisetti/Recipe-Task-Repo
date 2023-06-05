package de.hybris.myshoestore.core.Interceptors;

import de.hybris.myshoestore.core.model.IngredientModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import org.springframework.util.StringUtils;

public class IngrediantNameValidator implements ValidateInterceptor {
    private static final int MAX_TITLE_LENGTH = 100;
    @Override
    public void onValidate(Object obj, InterceptorContext ctx) throws InterceptorException {

        if (obj instanceof IngredientModel) {
            final IngredientModel model = (IngredientModel) obj;
            if (StringUtils.isEmpty(model.getName()) && model.getName().length()<MAX_TITLE_LENGTH ) {
                throw new InterceptorException("Ingredient Name cannot have empty and size not greater than 50");
            }

        }

    }
}
