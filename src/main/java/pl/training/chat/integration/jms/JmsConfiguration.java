package pl.training.chat.integration.jms;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsConfiguration {

  /*  @Bean
    public ConnectionFactory connectionFactory() throws JMSException {
        var connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        return new CachingConnectionFactory(connectionFactory);
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        var template = new JmsTemplate(connectionFactory);
        template.setPubSubDomain(true); // topic
        return template;
    }

    @Bean
    public DefaultJmsListenerContainerFactory trainingJmsContainerFactory(ConnectionFactory connectionFactory) {
        var container = new DefaultJmsListenerContainerFactory();
        container.setConnectionFactory(connectionFactory);
        container.setConcurrency("1-10");
        container.setPubSubDomain(true); // topic
        return container;
    }*/

}
