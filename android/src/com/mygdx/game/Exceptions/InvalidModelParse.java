package com.mygdx.game.Exceptions;



public class InvalidModelParse extends Exception {

    String message;
    Throwable cause;

    public InvalidModelParse() {
        super();
    }

    public InvalidModelParse(String message, Throwable cause) {
        super(message, cause);

        this.cause = cause;
        this.message = message;
    }


}

