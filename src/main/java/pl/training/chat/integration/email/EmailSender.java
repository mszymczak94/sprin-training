package pl.training.chat.integration.email;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailSender implements ApplicationRunner {

    private final JavaMailSender mailSender;
    private final TemplateService templateService;

    @Override
    public void run(ApplicationArguments args) {
        Map<String, Object> data = Map.of("value", "Test message");
        var text = templateService.evaluate("ChatNotification", data, "pl");
        var mailMesage = createMessage("trainer@training.pl", new String[]{"user@trainin.pl"}, "Test mail", text);
        mailSender.send(mailMesage);
    }

    private MimeMessagePreparator createMessage(String sender, String[] recipients, String subject, String text) {
        return mimeMessage -> {
          var message = new MimeMessageHelper(mimeMessage);
          message.setFrom(sender);
          message.setTo(recipients);
          message.setSubject(subject);
          message.setText(text, true);
        };
    }

}
