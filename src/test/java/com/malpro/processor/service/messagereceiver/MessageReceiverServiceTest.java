package com.malpro.processor.service.messagereceiver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.malpro.processor.dto.catalog.CatalogProductDto;
import com.malpro.processor.dto.message.ProcessProductMessageDto;
import com.malpro.processor.dto.model.FeaturesCodeDataDto;
import com.malpro.processor.dto.model.FeaturesTextDataDto;
import com.malpro.processor.dto.model.ModelProductDto;
import com.malpro.processor.repository.CatalogConnectorV1;
import com.malpro.processor.repository.ModelConnectorV1;
import com.malpro.processor.util.IProductMapper;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;

/**
 * Created by fahian on 07.10.22.
 */
@ExtendWith({ MockitoExtension.class, RandomBeansExtension.class })
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
    @DisplayName("Products are processed test")
    void productIsProcessedTest(@Random ProcessProductMessageDto processProductMessageDto,
                            @Random FeaturesCodeDataDto featuresCodeDataDto,
                            @Random CatalogProductDto catalogProductDto,
                            @Random UUID supplierUUID) {

        
        when(modelConnectorV1.convertToCode(any(FeaturesTextDataDto.class))).thenReturn(featuresCodeDataDto);
        when(IProductMapper.toCatalogProductDto(any(ModelProductDto.class), eq(featuresCodeDataDto))).thenReturn(catalogProductDto);

        messageReceiverService.process(processProductMessageDto, supplierUUID.toString());

        verify(modelConnectorV1, times(processProductMessageDto.getProductData().size())).convertToCode(any(FeaturesTextDataDto.class));
        verify(IProductMapper,times(processProductMessageDto.getProductData().size())).toCatalogProductDto(any(ModelProductDto.class), eq(featuresCodeDataDto));
        verify(catalogConnectorV1).storeProduct(eq(supplierUUID.toString()), anyList());

    }

    @Test
    @DisplayName("Supplier uuid is null test")
    void supplierUuidIsNullTest(@Random ProcessProductMessageDto processProductMessageDto) {

        messageReceiverService.process(processProductMessageDto, null);

        verify(modelConnectorV1, never()).convertToCode(any(FeaturesTextDataDto.class));
        verify(IProductMapper, never()).toCatalogProductDto(any(ModelProductDto.class), any(FeaturesCodeDataDto.class));
        verify(catalogConnectorV1, never()).storeProduct(anyString(), anyList());
    }

    @Test
    @DisplayName("Supplier uuid is empty test")
    void supplierUuidIsEmptyTest(@Random ProcessProductMessageDto processProductMessageDto) {

        messageReceiverService.process(processProductMessageDto, " ");

        verify(modelConnectorV1, never()).convertToCode(any(FeaturesTextDataDto.class));
        verify(IProductMapper, never()).toCatalogProductDto(any(ModelProductDto.class), any(FeaturesCodeDataDto.class));
        verify(catalogConnectorV1, never()).storeProduct(anyString(), anyList());
    }
}