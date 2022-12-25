package com.kamilabiyev.ecommerce.domain.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Data
public class ErrorResponse {
    private List<String> messages;
    private HttpStatus status;
    private Timestamp timestamp;
    private String path;

    public ErrorResponse(List<String> messages, HttpStatus status, String path) {
        this.messages = messages;
        this.status = status;
        this.path = path;
        this.timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }
}
