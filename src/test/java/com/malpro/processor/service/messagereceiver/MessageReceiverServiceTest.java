package com.malpro.processor.service.messagereceiver;

import com.malpro.processor.dto.message.ProcessProductMessageDto;
import com.malpro.processor.dto.catalog.CatalogProductDto;
import com.malpro.processor.dto.model.FeaturesCodeDataDto;
import com.malpro.processor.dto.model.FeaturesTextDataDto;
import com.malpro.processor.repository.CatalogConnectorV1;
import com.malpro.processor.repository.ModelConnectorV1;
import com.malpro.processor.util.IProductMapper;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by fahian on 07.10.22.
 */
@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class MessageReceiverServiceTest {

    @Mock
    private ModelConnectorV1 modelConnectorV1;

    @Mock
    private CatalogConnectorV1 catalogConnectorV1;

    @Mock
    private IProductMapper IProductMapper;

    @InjectMocks
    private MessageReceiverService messageReceiverService;

    @Test
    @DisplayName("Product is processed")
    void productIsProcessed(@Random ProcessProductMessageDto processProductMessageDto,
                            @Random FeaturesCodeDataDto featuresCodeDataDto,
                            @Random CatalogProductDto catalogProductDto) {

        when(modelConnectorV1.convertToCode(any(FeaturesTextDataDto.class))).thenReturn(featuresCodeDataDto);
        when(IProductMapper.toCatalogProductDto(processProductMessageDto.getProductData(), featuresCodeDataDto)).thenReturn(catalogProductDto);

        messageReceiverService.process(processProductMessageDto);

        verify(modelConnectorV1).convertToCode(any(FeaturesTextDataDto.class));
        verify(IProductMapper).toCatalogProductDto(processProductMessageDto.getProductData(), featuresCodeDataDto);
        verify(catalogConnectorV1).storeProduct(processProductMessageDto.getSupplierUuid(), catalogProductDto);

    }
}