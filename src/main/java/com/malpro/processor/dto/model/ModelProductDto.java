package com.malpro.processor.dto.model;

import com.malpro.processor.dto.catalog.CatalogProductOrderDetailsDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private String manufacturerCode;
    private String manufacturerName;
    private List<FeaturesTextDataDto> productFeatures;
    private CatalogProductOrderDetailsDto productOrderDetails;
}