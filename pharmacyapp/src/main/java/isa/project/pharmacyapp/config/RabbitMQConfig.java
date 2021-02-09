package isa.project.pharmacyapp.config;

import com.rabbitmq.client.ConnectionFactory;
//import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class RabbitMQConfig {
    @Autowired
    private Environment env;

    @Bean
    public ConnectionFactory connectionFactory() throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(env.getProperty("spring.rabbitmq.host").trim());
        connectionFactory.setPort(Integer.parseInt(env.getProperty("spring.rabbitmq.port").trim()));
        connectionFactory.setVirtualHost(env.getProperty("mq.vhost").trim());
        connectionFactory.setUsername(env.getProperty("spring.rabbitmq.username").trim());
        connectionFactory.setPassword(env.getProperty("spring.rabbitmq.password").trim());

        return connectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() throws Exception {
        return new CachingConnectionFactory(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() throws Exception {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory());
        rabbitTemplate.setChannelTransacted(true);
        return rabbitTemplate;
    }

    @Bean
    public AmqpAdmin amqpAdmin() throws Exception {
        return new RabbitAdmin(cachingConnectionFactory());
    }

    @Bean
    public Queue queue() {
        String name = env.getProperty("mq.queue").trim();
        return new Queue(name,true,false,false);
    }

    @Bean
    public TopicExchange topicExchange(){

        String name = env.getProperty("mq.topicexchange").trim();
        return new TopicExchange(name, true,false);
    }

    @Bean
    Binding binding() {
        String routekey = env.getProperty("mq.routekey").trim();
        return BindingBuilder.bind(queue()).to(topicExchange()).with(routekey);
    }





}
