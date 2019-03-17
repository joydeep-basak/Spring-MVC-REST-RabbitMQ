package com.joydeep.springmvc.rabbit;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class SpringAmqpConfig {

    public final static String queueName = "com.baeldung.spring-amqp-simple.queue";
    public final static String exchangeName = "com.baeldung.spring-amqp-simple.exchange";

    
    public final static String queueNameObject = "com.baeldung.spring-amqp-simple.queueObject";
    public final static String exchangeNameObject = "com.baeldung.spring-amqp-simple.exchangeObject";

    ////------- String queue / exchange binding starts ---------
    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    Exchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }
    
  //------- String queue / exchange binding end ---------
    
   ////------- String queue / exchange binding starts ---------
    @Bean
    Queue queueObject() {
        return new Queue(queueNameObject, false);
    }

    @Bean
    Exchange exchangeObject() {
        return new DirectExchange(exchangeNameObject);
    }

    @Bean
    Binding bindingObject(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueNameObject);
    }
  //------- Object queue / exchange binding end ---------
   /* @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }*/
    @Bean
    public ConnectionFactory connectionFactory() {
      com.rabbitmq.client.ConnectionFactory connectionFactory = new com.rabbitmq.client.ConnectionFactory();
      connectionFactory.setConnectionTimeout(10000);
      connectionFactory.setHost("localhost");
      connectionFactory.setPort(5672);
      connectionFactory.setUsername("guest");
      connectionFactory.setPassword("guest");
      CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(
          connectionFactory);
      return cachingConnectionFactory;
    }
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
      SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
      factory.setConnectionFactory(connectionFactory());
      factory.setMaxConcurrentConsumers(5);
      return factory;
    }
    
   /* @Bean
    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
        SimpleRabbitListenerContainerFactoryConfigurer containerFactoryConfigurer, 
        ConnectionFactory connectionFactory) {

        SimpleRabbitListenerContainerFactory listenerContainerFactory =
                new SimpleRabbitListenerContainerFactory();
        containerFactoryConfigurer.configure(listenerContainerFactory, connectionFactory);

        return listenerContainerFactory;
    }
 
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }*/
 
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    SimpleMessageListenerContainer springAmqpContainer(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MessageConsumer messageReceiver) {
        return new MessageListenerAdapter(messageReceiver, "receiveMessage");
    }
}
