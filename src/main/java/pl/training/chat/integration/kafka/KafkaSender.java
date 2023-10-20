package pl.training.chat.integration.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.training.chat.ChatMessageDto;
import pl.training.chat.TimeProvider;

@Component
@RequiredArgsConstructor
public class KafkaSender implements ApplicationRunner {

    private final KafkaTemplate<String, ChatMessageDto> kafkaTemplate;
    private final TimeProvider timeProvider;

    @Override
    public void run(ApplicationArguments args) {
        var message = ChatMessageDto.builder()
                .sender("Trainer")
                .timestamp(timeProvider.getFormattedTime())
                .text("Hello")
                .build();
        kafkaTemplate.send("messages", message);
    }

}
