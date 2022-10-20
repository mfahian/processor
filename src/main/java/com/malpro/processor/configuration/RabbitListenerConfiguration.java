package com.malpro.processor.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fahian on 07.10.22.
 */
@Configuration
@EnableRabbit
@ComponentScan(basePackages = "com.malpro.processor.service.messagereceiver")
public class RabbitListenerConfiguration {

    @Value("${rabbitmq.store-product.queue}")
    private String storeProductQueue;
    @Value("${rabbitmq.store-product.dead-letter-queue}")
    private String storeProductDeadLetterQueue;
    @Value("${rabbitmq.store-product.exchange}")
    private String storeProductExchange;

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue messagesQueue() {
        return QueueBuilder.durable(storeProductQueue)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", storeProductDeadLetterQueue)
                .build();
    }

    @Bean
    public Queue createStoreProductQueueDlq() {
        return QueueBuilder.durable(storeProductDeadLetterQueue).build();
    }

    @Bean
    DirectExchange messagesExchange() {
        return new DirectExchange(storeProductExchange);
    }

    @Bean
    Binding bindingMessages() {
        return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(storeProductQueue);
    }
}
