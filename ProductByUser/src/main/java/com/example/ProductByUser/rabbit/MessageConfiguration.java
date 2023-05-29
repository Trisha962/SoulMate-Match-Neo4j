package com.example.ProductByUser.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration
{
    private String exchangeName="Soul-Exchange";//stores the name of the exchange to be used.
    private String queueName="SoulQueue";// stores the name of the queue to be used.
    @Bean
    public DirectExchange getDirectExchange(){
        return new DirectExchange(exchangeName);
    }
    //use amqp queue
    @Bean
    public Queue getQueue(){
        return new Queue(queueName);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();//converts message to json
    }
    //use import org.springframework.amqp.rabbit.connection.ConnectionFactory;
    @Bean
    // takes ConnectionFactory as the argument to establish a connection to the RabbitMQ broker
    // and sets the message converter to jackson2JsonMessageConverter().
    public RabbitTemplate getRabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean//binds the queue to the exchange with a routing key of "Soul-key"
    public Binding getBinding(){
        return BindingBuilder.bind(getQueue()).to(getDirectExchange()).with("Soul-key");
    }
}