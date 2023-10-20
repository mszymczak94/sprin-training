package pl.training.chat.integration.jms;

import lombok.extern.java.Log;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.training.chat.ChatMessageDto;

@Component
@Log
public class JmsMessagesListener {

    @JmsListener(destination = "messages"/*, containerFactory = "trainingJmsContainerFactory"*/)
    public void onMessage(ChatMessageDto chatMessageDto) {
        log.info("New JMS message: " + chatMessageDto);
    }

}
