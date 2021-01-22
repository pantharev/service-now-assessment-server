package com.studentslist.servicenowassessmentserver.dto;

public class TokenResponseDTO {
    private boolean success;
    private String challenge_ts;
    private String hostname;

    public TokenResponseDTO(boolean success, String challenge_ts, String hostname) {
        this.success = success;
        this.challenge_ts = challenge_ts;
        this.hostname = hostname;
    }

    public TokenResponseDTO() { }

    public boolean getSuccess() { return success; }
    public String getChallenge_ts(){ return challenge_ts; }
    public String getHostname() { return hostname; }
}
