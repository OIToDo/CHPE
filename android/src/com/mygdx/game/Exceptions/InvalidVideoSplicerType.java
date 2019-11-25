package com.mygdx.game.Exceptions;

public class InvalidVideoSplicerType extends Exception {
    String message;
    Throwable cause;

    public InvalidVideoSplicerType() {
        super();
    }

    public InvalidVideoSplicerType(String message, Throwable cause) {
        super(message, cause);

        this.cause = cause;
        this.message = message;
    }

}


