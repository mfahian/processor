package com.malpro.processor.util;

import com.malpro.processor.dto.catalog.CatalogProductDto;
import com.malpro.processor.dto.catalog.CatalogProductFeatureDto;
import com.malpro.processor.dto.model.FeatureCodeDataDto;
import com.malpro.processor.dto.model.FeaturesCodeDataDto;
import com.malpro.processor.dto.model.ModelProductDto;
import org.mapstruct.Mapper;

/**
 * Created by fahian on 07.10.22.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    CatalogProductDto mapToCatalogProduct(ModelProductDto modelProductDto, FeaturesCodeDataDto featuresCodeDataDto);

    default CatalogProductFeatureDto mapConvertedFeatures(FeatureCodeDataDto featureCodeDataDto) {
        final var catalogProductFeatureDto = new CatalogProductFeatureDto();
        catalogProductFeatureDto.setEtimFeatureCode(featureCodeDataDto.getEtimFeatureCode());

        switch (featureCodeDataDto.getType()) {
            case "alphanumeric" -> catalogProductFeatureDto.setValue1(featureCodeDataDto.getEtimValueCode());
            case "range" -> {
                catalogProductFeatureDto.setValue1(featureCodeDataDto.getLowerBound());
                catalogProductFeatureDto.setValue2(featureCodeDataDto.getUpperBound());
                catalogProductFeatureDto.setEtimUnitCode(featureCodeDataDto.getEtimUnitCode());
            }
            case "numeric" -> {
                catalogProductFeatureDto.setValue1(featureCodeDataDto.getNumericValue());
                catalogProductFeatureDto.setEtimUnitCode(featureCodeDataDto.getEtimUnitCode());
            }
            case "logical" -> catalogProductFeatureDto.setValue1(featureCodeDataDto.getBooleanValue());
            default -> {}
        }
        return catalogProductFeatureDto;
    }
}
