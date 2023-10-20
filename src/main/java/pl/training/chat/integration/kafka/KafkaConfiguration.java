package pl.training.chat.integration.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import pl.training.chat.ChatMessageDto;

import java.util.HashMap;

@Configuration
public class KafkaConfiguration {

/*    @Bean
    public ProducerFactory<String, ChatMessageDto> producerFactory() {
        var serializer = new JsonSerializer<ChatMessageDto>();
        serializer.setAddTypeInfo(true);
        var properties = new HashMap<String, Object>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return new DefaultKafkaProducerFactory<>(properties, new StringSerializer(), serializer);
    }

    @Bean
    public ConsumerFactory<String, ChatMessageDto> consumerFactory() {
        var deserializer = new JsonDeserializer<ChatMessageDto>();
        deserializer.addTrustedPackages("pl.training.chat");
        var properties = new HashMap<String, Object>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "chat");
        return new DefaultKafkaConsumerFactory<>(properties, new StringDeserializer(), deserializer);
    }

    @Bean
    public NewTopic mainTopic() {
        return TopicBuilder.name("messages").build();
    }

    @Bean
    public KafkaTemplate<String, ChatMessageDto> kafkaTemplate(ProducerFactory<String, ChatMessageDto> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ChatMessageDto>> kafkaListenerContainerFactory(ConsumerFactory<String, ChatMessageDto> consumerFactory) {
        var containerFactory = new ConcurrentKafkaListenerContainerFactory<String, ChatMessageDto>();
        containerFactory.setConsumerFactory(consumerFactory);
        return containerFactory;
    }*/

}
