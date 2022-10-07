package com.malpro.processor.dto.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fahian on 07.10.22.
 */
@Getter
@Setter
public class FeatureCodeDataDto {
    private String type;
    private String etimFeatureCode;
    private String etimValueCode;
    private String etimUnitCode;
    private String booleanValue;
    private String numericValue;
    private String lowerBound;
    private String upperBound;
}
