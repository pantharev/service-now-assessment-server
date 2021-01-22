package com.studentslist.servicenowassessmentserver.dto;

public class TokenDTO {
    private String recaptcha;
    public TokenDTO(String recaptcha){ this.recaptcha = recaptcha; }
    public TokenDTO() { }
    public String getRecaptcha() { return recaptcha; }
}
