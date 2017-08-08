package com.project.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Arrays;

/**
 * Created by abdullah.alnoman on 07.08.17.
 */
public class StompConnectEvent implements ApplicationListener<SessionConnectEvent> {

     @Autowired
    SimpMessagingTemplate messagingTemplate;

    private final Log logger = LogFactory.getLog(StompConnectEvent.class);

    public void onApplicationEvent(SessionConnectEvent event) {
        logger.info("connected event");
        // on connect event can be captured here

    }


}


