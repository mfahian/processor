package com.malpro.processor.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by fahian on 07.10.22.
 */
@Getter
@Setter
public class FeaturesTextDataDto extends ModelFeaturesDto{
    private Map<String, String> featuresMap;
}
