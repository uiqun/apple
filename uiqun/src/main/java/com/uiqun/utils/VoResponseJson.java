package com.uiqun.utils;

import java.io.Serializable;

public class VoResponseJson implements Serializable {
    private String state="success";
    private int errorCode=0000;
    private String message="Server connection successful";
    private Object datas;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    public VoResponseJson() {
    }

    public VoResponseJson(String state, int errorCode, String message) {
        this.state = state;
        this.errorCode = errorCode;
        this.message = message;
    }

    public VoResponseJson(Object datas) {
        this.datas = datas;
    }

    public VoResponseJson(String state, int errorCode, String message, Object datas) {
        this.state = state;
        this.errorCode = errorCode;
        this.message = message;
        this.datas = datas;
    }
}
