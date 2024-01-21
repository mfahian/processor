package com.malpro.processor.util;

import com.malpro.processor.dto.catalog.CatalogProductDto;
import com.malpro.processor.dto.catalog.CatalogProductFeatureDto;
import com.malpro.processor.dto.catalog.CatalogProductFeaturesDto;
import com.malpro.processor.dto.model.FeatureCodeDataDto;
import com.malpro.processor.dto.model.FeaturesCodeDataDto;
import com.malpro.processor.dto.model.ModelProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fahian on 07.10.22.
 */
@Mapper(componentModel = "spring")
public interface IProductMapper {
    @Mapping(source = "featuresCodeDataDto", target = "productFeatures", qualifiedByName = "featureMapping")
    @Mapping(source = "modelProductDto.unit", target = "productOrderDetails.orderUnit")
    CatalogProductDto toCatalogProductDto(ModelProductDto modelProductDto, FeaturesCodeDataDto featuresCodeDataDto);

    @Named("featureMapping")
    default List<CatalogProductFeaturesDto> featureMapping(FeaturesCodeDataDto featuresCodeDataDto) {
        final ArrayList<CatalogProductFeaturesDto> catalogProductFeaturesDtos = new ArrayList<>();

        catalogProductFeaturesDtos.add(toCatalogProductFeaturesDto(featuresCodeDataDto));
        return catalogProductFeaturesDtos;
    }

    @Mapping(source = "etimClass", target = "etimGroup")
    CatalogProductFeaturesDto toCatalogProductFeaturesDto (FeaturesCodeDataDto featuresCodeDataDto);

    default CatalogProductFeatureDto toCatalogProductFeatureDto (FeatureCodeDataDto featureCodeDataDto) {
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
