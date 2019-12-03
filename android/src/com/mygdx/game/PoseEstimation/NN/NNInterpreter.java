package com.mygdx.game.PoseEstimation.NN;

/**
 * The enum Nn interpreter.
 */
public enum NNInterpreter {
    /**
     * Cpu nn interpreter.
     */
    CPU,
    /**
     * Nnapi nn interpreter.
     */
    NNAPI, // Android Neural Network API
    /**
     * Gpu nn interpreter.
     */
    GPU
}
