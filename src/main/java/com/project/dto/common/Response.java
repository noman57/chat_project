package com.project.dto.common;

import com.project.enums.StatusType;

/**
 * Created by abdullah.alnoman on 05.08.17.
 */
public class Response {

    private final StatusType status;
    private final Object data;
    private final Error error;


    public Response(StatusType status, Object data) {
        this(status, data, null);
    }

    public Response(StatusType status, Object data, Error error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public StatusType getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public Error getError() {
        return error;
    }
}
