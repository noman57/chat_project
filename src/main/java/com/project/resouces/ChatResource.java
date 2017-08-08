package com.project.resouces;

import com.project.dto.common.ChatMessage;
import com.project.enums.MessageType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by abdullah.alnoman on 06.08.17.
 */
@Controller
public class ChatResource {


    @Autowired
    SimpMessagingTemplate  messagingTemplate;

    @Autowired
    private MessageSource messageSource;

    private final Log logger = LogFactory.getLog(ChatResource.class);

    @MessageMapping("/chat")
    public void send(ChatMessage message) throws Exception {

        logger.info("Message received ");

        // furthure processing can be done to save messages and histories in database
        if(!message.getTo().equals(message.getFrom()))
            messagingTemplate.convertAndSendToUser(message.getTo(),"/replay",message);
        else{
            logger.info("Message not valid ");
            message.setType(MessageType.WARNING);
            message.setText("Invalid conncetion request");
            logger.info("Sending msg received ");
            messagingTemplate.convertAndSendToUser(message.getTo(),"/replay",message);
        }

        logger.info("Message sent from ");
    }




    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}
