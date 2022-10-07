package com.malpro.processor.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by fahian on 07.10.22.
 */
@Getter
@Setter
public class FeaturesCodeDataDto extends ModelFeaturesDto{
    private List<FeatureCodeDataDto> productFeature;
}
