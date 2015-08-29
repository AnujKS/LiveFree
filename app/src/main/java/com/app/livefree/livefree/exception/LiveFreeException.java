package com.app.livefree.livefree.exception;

/**
 * Created by ANJUM on 29-Aug-15.
 */
public class LiveFreeException extends Exception {

    private LiveFreeErrors liveFreeError;

    public LiveFreeException(LiveFreeErrors liveFreeError) {
        this.liveFreeError = liveFreeError;
    }

    public String getMessage() {
        return this.liveFreeError.getMessage();
    }

    public LiveFreeErrors getLiveFreeError() {
        return liveFreeError;
    }
}
