package com.wyl.lottery.domain.dto;


import java.io.Serializable;

public class WYLResult<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6732312763880543546L;


    private T data;
    private int code;
    private String message;
    private boolean successful;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public WYLResult(int code, String message, boolean successful) {
        this.code = code;
        this.message = message;
        this.successful = successful;
    }
}
