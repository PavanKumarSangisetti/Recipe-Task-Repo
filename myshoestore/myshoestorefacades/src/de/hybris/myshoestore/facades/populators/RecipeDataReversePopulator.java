package de.hybris.myshoestore.facades.populators;


import de.hybris.myshoestore.core.model.RecipeModel;
import de.hybris.platform.commercefacades.user.data.RecipeData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class RecipeDataReversePopulator implements Populator<RecipeData, RecipeModel> {
    @Override
    public void populate(RecipeData source, RecipeModel target) throws ConversionException {

        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());

    }
}
