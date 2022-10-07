package com.malpro.processor.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("rabbitmq.store-product")
@Data
public class RabbitConfiguration {
    private String exchange;
    private String queue;
    private String deadLetterQueue;
}
