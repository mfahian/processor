package com.malpro.processor.dto.catalog;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by fahian on 03.06.22.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CatalogProductFeatureDto {
    private String etimFeatureCode;
    private String value1;
    private String value2;
    private String etimUnitCode;
}
