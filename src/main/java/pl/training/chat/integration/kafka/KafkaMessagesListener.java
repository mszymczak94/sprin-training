package pl.training.chat.integration.kafka;

import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.training.chat.ChatMessageDto;

@Component
@Log
public class KafkaMessagesListener {

    @KafkaListener(topics = "messages")
    public void onMessage(ChatMessageDto chatMessageDto) {
        log.info("New Kafka message: " + chatMessageDto);
    }

}
