package com.app.livefree.livefree.exception;

/**
 * Created by ANJUM on 29-Aug-15.
 */
public enum LiveFreeErrors {

    MAP_NOT_LOADED("LFE-1001","MAP_NOT_LOADED","Unable to Load map"),
    MAP_FILTER_NULL("LFE-1002","MAP_FILTER_NULL","Maps filter is null"),

    NO_INTERNET("LFE-2001","NO_INTERNET","Please check your internet connection");

    LiveFreeErrors(String errorCode, String reason, String message) {
        this.errorCode = errorCode;
        this.reason = reason;
        this.message = message;
    }

    private String message;
    private String reason;
    private String errorCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
