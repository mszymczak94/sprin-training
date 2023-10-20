package pl.training.chat.integration.jms;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.training.chat.ChatMessageDto;
import pl.training.chat.TimeProvider;

@Component
@RequiredArgsConstructor
public class JmsSender implements ApplicationRunner {

    private final JmsTemplate jmsTemplate;
    private final TimeProvider timeProvider;

    @Override
    public void run(ApplicationArguments args) {
        var message = ChatMessageDto.builder()
                .sender("Trainer")
                .timestamp(timeProvider.getFormattedTime())
                .text("Hello")
                .build();
        // jmsTemplate.send("message", session -> session.createObjectMessage(message));
        jmsTemplate.convertAndSend("messages", message);
    }

}
