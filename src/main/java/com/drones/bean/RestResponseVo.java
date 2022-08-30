package com.drones.bean;

public class RestResponseVo {

    private String message;

    public RestResponseVo() {
    }

    public RestResponseVo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
