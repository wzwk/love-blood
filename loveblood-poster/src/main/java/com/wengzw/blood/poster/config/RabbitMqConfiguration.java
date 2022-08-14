package com.wengzw.blood.poster.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wengzw
 * @date 2022/8/13 18:32
 */

/**
 * 序列化
 * 我们需要把它的默认序列化器更改为json类型的序列化器
 */
@Configuration
@Slf4j
public class RabbitMqConfiguration {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        //消息发送到交换机后，是否调用回调方法
        connectionFactory.setPublisherConfirms(true);
        //消息从交换机发送到队列，是否调用回调方法
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());

        //消息是否发送到了交换机
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
            } else {
                log.info("消息发送失败:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
            }
        });
        //如果调用setReturnCallback方法，那么Mandatory必须为true，否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(true);
        //消息是否从交换机发送到队列，如果发送失败就会调用returnedMessage方法
        rabbitTemplate.setReturnsCallback(returned -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",
                returned.getExchange(), returned.getRoutingKey(), returned.getReplyCode(),
                returned.getReplyText(), returned.getMessage()));
        return rabbitTemplate;
    }

    /**
     * 邮件
     **/
    @Bean
    public Queue mailQueue() {
        return new Queue("MAIL_REGISTER_QUEUE");
    }

    @Bean
    public DirectExchange mailExchange() {
        return new DirectExchange("MAIL_REGISTER_EXCHANGE", true, false, null);
    }

    /**
     * 绑定交换机和队列
     *
     * @return
     */
    @Bean
    public Binding mailBinding() {
        return BindingBuilder
                .bind(mailQueue())
                .to(mailExchange())
                .with("MAIL_ROUTE_KEY");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
