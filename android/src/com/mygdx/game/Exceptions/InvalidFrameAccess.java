package com.mygdx.game.PoseEstimation;

class InvalidFrameAccess extends Exception {

    String message;
    Throwable cause;

    public InvalidFrameAccess() {
        super();
    }

    public InvalidFrameAccess(String message, Throwable cause) {
        super(message, cause);

        this.cause = cause;
        this.message = message;
    }


}
