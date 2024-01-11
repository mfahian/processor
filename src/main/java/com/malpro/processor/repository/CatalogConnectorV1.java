package com.malpro.processor.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.malpro.processor.dto.catalog.CatalogProductDto;


/**
 * Created by fahian on 07.10.22.
 */
@FeignClient(value = "CatalogConnector",
        url = "${connectors.catalog.url}",
        path = "/api/v1")
public interface CatalogConnectorV1 {
    @PostMapping(value="/supplier/{supplierUuid}/products/list")
    String storeProducts(@PathVariable String supplierUuid, @RequestBody List<CatalogProductDto> catalogProductDto);
}
