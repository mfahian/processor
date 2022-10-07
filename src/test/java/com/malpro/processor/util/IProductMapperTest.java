package com.malpro.processor.util;

import javax.xml.catalog.Catalog;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.malpro.processor.dto.catalog.CatalogProductDto;
import com.malpro.processor.dto.model.FeaturesCodeDataDto;
import com.malpro.processor.dto.model.ModelProductDto;

import au.com.origin.snapshots.Expect;
import au.com.origin.snapshots.junit5.SnapshotExtension;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;

/**
 * Created by fahian on 03.07.22.
 */
@Disabled("Always generating new values, does not use the snap")
@ExtendWith({MockitoExtension.class, RandomBeansExtension.class, SnapshotExtension.class})
class IProductMapperTest {

    private Expect expect;

    @InjectMocks
    private IProductMapperImpl productMapperImpl;

    @Test@Disabled("Always generating new values, does not use the snap")
    @DisplayName("Map product test")
    void toCatalogTest(@Random ModelProductDto modelProductDto,
                       @Random FeaturesCodeDataDto featureCodeDataDto) {
        final CatalogProductDto catalogProductDto = productMapperImpl.toCatalogProductDto(modelProductDto, featureCodeDataDto);
        expect.toMatchSnapshot(catalogProductDto);
    }
}