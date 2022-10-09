package com.malpro.processor.repository;

import com.malpro.processor.dto.catalog.CatalogProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Created by fahian on 07.10.22.
 */
@FeignClient(value = "CatalogConnector",
        url = "${connectors.catalog.url}",
        path = "/api/v1")
public interface CatalogConnectorV1 {
    @PostMapping(value="/supplier/{supplierUuid}/product")
    String storeProduct(@PathVariable String supplierUuid, @RequestBody CatalogProductDto catalogProductDto);
}
