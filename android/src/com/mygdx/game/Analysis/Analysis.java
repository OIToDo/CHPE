package com.mygdx.game.Analysis;

import java.util.HashMap;


import com.mygdx.game.persistance.*;

/**
 * Main class that provides (and performs) the functionality for analyzing a data set
 * of vector coordinates for detecting human body language and actions.
 */
public class Analysis {
    /**
     * Constructor. Initializes member fields.
     * @param data Object for interfacing with the data set.
     */
    public Analysis(final Data data) {
        this.data = data;
        detection = new Detection(data);
    }
    
    /**
     * Lets the Detection object perform it's operations.
     * @return A hash map containing every action and whether it was detected or not.
     * TODO: Implementation
     */
    public HashMap<Action, Boolean> detect() {
        HashMap<Action, Boolean> results = new HashMap<Action, Boolean>();

        Action action = new Action("handAbovehead");
        action.setOccurance(detection.handsAboveHead(1));
        results.put(action, action.Occured());

        action = new Action("handsIdle");
        action.setOccurance(detection.HandsIdle(10));
        results.put(action, action.Occured());

        action = new Action("handsNotFound");
        action.setOccurance(detection.handsFound(1.0f));
        results.put(action, action.Occured());

        return results;
    }
    
    /**
     * Filters and processes the data and writes the improved version back to the data object.
     * TODO: implementation missing. 
     * Most of this will be done in Python first because of faster - and visual - testing.
     */
    public void process() {
        return;
    }

    /**
     * An interface object to the vector data used needed for filtering and processing.
     */
    private final Data data;

    /**
     * A handle to the detection class that handles detecting specific Actions.
     */
    private final Detection detection;
}