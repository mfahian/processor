package com.malpro.processor.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;

/**
 * Created by fahian on 07.10.22.
 */
@Configuration
@EnableRabbit
@ComponentScan(basePackages = "com.malpro.processor.service.messagereceiver")
@AllArgsConstructor
public class RabbitListenerConfiguration {

    private final RabbitConfiguration rabbitConfiguration;

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue messagesQueue() {
        return QueueBuilder.durable(rabbitConfiguration.getQueue())
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", rabbitConfiguration.getDeadLetterQueue())
                .build();
    }

    @Bean
    public Queue createStoreProductQueueDlq() {
        return QueueBuilder.durable(rabbitConfiguration.getDeadLetterQueue()).build();
    }

    @Bean
    DirectExchange messagesExchange() {
        return new DirectExchange(rabbitConfiguration.getExchange());
    }

    @Bean
    Binding bindingMessages() {
        return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(rabbitConfiguration.getQueue());
    }
}
