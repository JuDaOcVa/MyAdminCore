package com.judaocva.myadmincore.services;

import com.judaocva.myadmincore.config.app.AppConfig;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class MessageService {

    private final ResourceBundle messages;

    public MessageService(AppConfig appConfig) {
        Locale locale = new Locale(appConfig.getLanguage());
        this.messages = ResourceBundle.getBundle("languages/messages", locale);
    }

    public String getMessage(String key, Object... args) {
        String optionalMessage = args.length > 0 ? args[0].toString() : "";
        String message = messages.getString(key);
        return String.format(message, optionalMessage);
    }
}