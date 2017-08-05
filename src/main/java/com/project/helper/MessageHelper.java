package com.project.helper;

import com.project.dto.common.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by abdullah.alnoman on 06.08.17.
 */
@Component
public class MessageHelper {

    @Autowired
    private MessageSource messageSource;

    public Message buildMessage(HttpStatus httpStatus, String code, Object[] args, Locale locale) {
        Message message = new Message();
        message.setCode(httpStatus.value());
        message.setMessage(messageSource.getMessage(code, args, locale));
        return message;
    }

    public Message buildMessage201(String code, Object[] args, Locale locale) {
        return buildMessage(HttpStatus.CREATED, code, args, locale);
    }

    public Message buildMessage201(String code, Locale locale) {
        return buildMessage201(code, null, locale);
    }

    public String buildSNSMessage(String code, Object[] args, Locale locale) {
        return messageSource.getMessage(code, args, locale);
    }
}
