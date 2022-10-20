package com.malpro.processor.service.messagereceiver;

import com.malpro.processor.dto.message.ProcessProductMessageDto;
import com.malpro.processor.dto.model.FeaturesTextDataDto;
import com.malpro.processor.repository.CatalogConnectorV1;
import com.malpro.processor.repository.ModelConnectorV1;
import com.malpro.processor.util.IProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by fahian on 07.10.22.
 */
@Component
@RequiredArgsConstructor
public class MessageReceiverService {

    private final CatalogConnectorV1 catalogConnectorV1;
    private final ModelConnectorV1 modelConnectorV1;
    private final IProductMapper iProductMapper;

    @RabbitListener(queues = "${rabbitmq.store-product.queue}")
    public void process(ProcessProductMessageDto processProductMessageDto) {
        // todo support for multiple product features
        final FeaturesTextDataDto productData = processProductMessageDto.getProductData().getProductFeatures().stream()
                .findFirst()
                .orElse(new FeaturesTextDataDto());

        final var featuresCodeDataDto = modelConnectorV1.convertToCode(productData);

        final var catalogProductDto = iProductMapper.toCatalogProductDto(processProductMessageDto.getProductData(), featuresCodeDataDto);

        catalogConnectorV1.storeProduct(processProductMessageDto.getSupplierUuid(), catalogProductDto);
    }
}
