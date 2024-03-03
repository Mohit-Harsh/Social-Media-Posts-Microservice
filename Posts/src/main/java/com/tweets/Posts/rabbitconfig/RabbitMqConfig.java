package com.tweets.Posts.rabbitconfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig
{

    @Bean
    public Queue likequeue()
    {
        return new Queue("likequeue");
    }

    @Bean
    public Queue commentqueue()
    {
        return new Queue("commentqueue");
    }

    @Bean
    public Queue postqueue()
    {
        return new Queue("postqueue");
    }

    @Bean
    public TopicExchange exchange()
    {
        return new TopicExchange("queue_exchange");
    }

    @Bean
    public Binding likeBinding()
    {
        return BindingBuilder
                .bind(likequeue())
                .to(exchange())
                .with("like_routing_key");
    }

    @Bean
    public Binding commentBinding()
    {
        return BindingBuilder
                .bind(commentqueue())
                .to(exchange())
                .with("comment_routing_key");
    }

    @Bean
    public Binding postBinding()
    {
        return BindingBuilder
                .bind(postqueue())
                .to(exchange())
                .with("post_routing_key");
    }

    @Bean
    public MessageConverter converter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
