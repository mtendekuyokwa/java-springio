package com.example.lostfound.dto;

import org.springframework.http.HttpStatusCode;

public class GeneralResponseDTO <T> {
    private HttpStatusCode httpStatusCode;
    private String message;
    private T data;

    public GeneralResponseDTO(HttpStatusCode httpStatusCode, String message, T data) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.data = data;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatusCode httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
