package com.malpro.processor.dto.catalog;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fahian on 22.05.22.
 */
@Getter
@Setter
public class CatalogProductOrderDetailsDto {
    private String orderUnit;
    private String contentUnit;
    private Float priceQuantity;
}
