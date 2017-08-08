package com.project.dto.common;

import com.project.enums.MessageType;

/**
 * Created by abdullah.alnoman on 06.08.17.
 */
public class ChatMessage {

    private MessageType type;

    private String to;

    private String from;

    private String text;



    public String getFrom() {
        return from;
    }

    public String getText() {
        return text;
    }


    public void setFrom(String from) {
        this.from = from;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
