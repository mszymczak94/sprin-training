package pl.training.chat.integration.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private static final String LANGUAGE_SEPARATOR = "_";

    private final TemplateEngine templateEngine;

    public String evaluate(String templateBaseName, Map<String, Object> data, String language) {
        var templeteName = getTemplateName(templateBaseName, language);
        var context = new Context();
        context.setVariables(data);
        return templateEngine.process(templeteName, context);
    }

    private String getTemplateName(String templateBaseName, String language) {
        return templateBaseName + LANGUAGE_SEPARATOR + language;
    }

}
