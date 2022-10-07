package com.malpro.processor.configuration;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fahian on 07.10.22.
 */
@Configuration
@EnableRabbit
@ComponentScan(basePackages= "com.malpro.processor.service.messagereceiver")
public class RabbitListenerConfiguration {
}
