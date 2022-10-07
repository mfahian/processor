package com.malpro.processor.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("connectors.catalog")
@Data
public class CatalogConnectorConfiguration {
    private String url;
}
