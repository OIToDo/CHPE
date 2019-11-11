package com.mygdx.game.Analysis;

import java.util.Set;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;


import com.mygdx.game.PoseEstimation.nn.PoseModel;


/**
 * "Hardcoded" enumeration for body parts.
 * TODO: figure out a way to abstract this too.
 */
enum body_part {
    head, neck, l_shoulder, l_elbow,
    l_wrist, r_shoulder, r_elbow,
    r_wrist, l_hip, l_knee, l_foot,
    r_hip, r_knee, r_foot, waist
}

/**
 * this class provides an interface to the vector data so the analysis
 * implementation doesn't depend on the external format.
 * @author Nico van Bentum
 */
public interface Data {
    /**
     * 
     * @param frame Integer index to a specific frame in the data structure.
     * @param bp Body part (also used as index) you want the coordinate for.
     * @return A 2 component integer vector that contains the specified body part's coordinate
     * in indexed frames' screen space.
     */
    public abstract Vec2 getCoord(int frame, body_part bp);

    /**
     * 
     * @return The number of body parts used in the data structure.
     */
    public abstract int getBodyPartCount();

    /**
     * 
     * @return The number of total frames in the videos' data structure.
     */
    public abstract int getFrameCount();

    /**
     * 
     * @return The number of frames per second of the original video.
     */
    public abstract float getFps();

    /**
     * Serializes the processed data back to the data structure object.
     * TODO: WIP, this might end up being split into multiple functions
     */
    public abstract void serialize();
}