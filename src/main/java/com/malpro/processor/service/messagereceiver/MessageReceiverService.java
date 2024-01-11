package com.malpro.processor.service.messagereceiver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.malpro.processor.dto.catalog.CatalogProductDto;
import com.malpro.processor.dto.model.FeaturesTextDataDto;
import com.malpro.processor.dto.model.ModelProductDto;
import com.malpro.processor.repository.CatalogConnectorV1;
import com.malpro.processor.repository.ModelConnectorV1;
import com.malpro.processor.util.IProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by fahian on 07.10.22.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MessageReceiverService {

    private final CatalogConnectorV1 catalogConnectorV1;
    private final ModelConnectorV1 modelConnectorV1;
    private final IProductMapper iProductMapper;

    @RabbitListener(queues = "${rabbitmq.store-product.queue}")
    public void process(List<ModelProductDto> modelProductDtos, @Header("supplier") String supplierUUID) {
        if (supplierUUID !=null && !supplierUUID.isBlank()) {
            List<CatalogProductDto> catalogProductDtoList = new ArrayList<>();

            log.debug("supplierUUID: {}", supplierUUID);

            for (ModelProductDto modelProductDto : modelProductDtos) {
                final var catalogProductDto = convertProduct(modelProductDto);
                catalogProductDtoList.add(catalogProductDto);
            }

            catalogConnectorV1.storeProducts(supplierUUID, catalogProductDtoList);
        }
    }

    private CatalogProductDto convertProduct(ModelProductDto modelProductDto) {
        // todo support for multiple product features
        log.debug("converting: {}", modelProductDto.getCode());
        // final FeaturesTextDataDto productData = modelProductDto.getProductFeatures().stream()
        //         .findFirst()
        //         .orElse(new FeaturesTextDataDto());

        final var productData = new FeaturesTextDataDto();
        productData.setEtimClass(modelProductDto.getEtimClass());
        productData.setReferenceFeatureSystem(modelProductDto.getReferenceFeatureSystem());
        productData.setFeaturesMap(modelProductDto.getFeaturesMap());

        final var featuresCodeDataDto = modelConnectorV1.convertToCode(productData);
        log.debug("class:" + featuresCodeDataDto.getEtimClass() + ", reference system:" + featuresCodeDataDto.getReferenceFeatureSystem());
        return iProductMapper.toCatalogProductDto(modelProductDto, featuresCodeDataDto);
    }
}
