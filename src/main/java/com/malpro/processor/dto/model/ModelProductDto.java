package com.malpro.processor.dto.model;

import com.malpro.processor.dto.catalog.CatalogProductOrderDetailsDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by fahian on 07.10.22.
 */
@Getter
@Setter
public class ModelProductDto {
    private String code;
    private String shortDescription;
    private String longDescription;
    private String globalTradeItemNumber;
    private String unit;
    private String priceQuantity;
    private String manufacturerCode;
    private String manufacturerName;
    private String etimClass;
    private String referenceFeatureSystem;
    private Map<String, String> featuresMap;
}