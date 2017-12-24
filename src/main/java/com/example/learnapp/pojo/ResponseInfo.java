package com.example.learnapp.pojo;

public class ResponseInfo {
    public static final Integer OK = 1;
    public static final Integer ERROR = -1;

    private Integer Code;
    private String Message;

    public ResponseInfo(Integer code, String message) {
        Code = code;
        Message = message;
    }

    public ResponseInfo() {
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "Code=" + Code +
                ", Message='" + Message + '\'' +
                '}';
    }
}
