package com.studentslist.servicenowassessmentserver.dto;

public class ResponseMessage {
    private String message;
    private boolean success;
    public ResponseMessage(String message, boolean success){
        this.message = message;
        this.success = success;
    }
    public ResponseMessage() { }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public boolean getSuccess(){ return success; }
}
