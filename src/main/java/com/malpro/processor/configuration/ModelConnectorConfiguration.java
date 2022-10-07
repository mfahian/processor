package com.malpro.processor.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("connectors.model")
@Data
public class ModelConnectorConfiguration {
    private String url;
}
