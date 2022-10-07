package com.malpro.processor.dto.catalog;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by fahian on 03.06.22.
 */
@Getter
@Setter
@Builder
public class CatalogProductFeaturesDto {
    private String etimGroup;
    private String referenceFeatureSystem;
    private List<CatalogProductFeatureDto> productFeature; //todo change to list, then mapper list->set
}
