package com.malpro.processor.repository;

import com.malpro.processor.dto.model.FeaturesCodeDataDto;
import com.malpro.processor.dto.model.FeaturesTextDataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by fahian on 07.10.22.
 */
@FeignClient(value = "ModelConnector",
        url = "${connectors.model.url}",
        path = "/api/v1")
public interface ModelConnectorV1 {

    @PostMapping(path = "convert/to-code")
    FeaturesCodeDataDto convertToCode(FeaturesTextDataDto productData);
}
