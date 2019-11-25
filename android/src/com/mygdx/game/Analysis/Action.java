package com.mygdx.game.Analysis;

/**
 * Class used for defining actions we want to detect.
 * TODO: Should become a more sophisticated object people can implement and tie attributes to.
 */
public class Action { 
    /**
     * @param name
     * Constructor.
     */
    public Action(final String name) {
        this.name = name;
    }

    /**
     * Requests if the Action has occured or not.
     * @return Whether the Action occured or not.
     */
    public boolean Occured() {
        return occured;
    }

    /**
     * Sets the occurance.
     * @param occ New occurence to set.
     */
    public void setOccurance(boolean occ) {
        occured = occ;
    }

    /**
     * Getter for the Action's name.
     * @return String containing the Action's name.
     */
    public String getName() { return name; }

    /**
     * Setter for the Action's name.
     * @param new_name
     * @return void
     */
    public void setName(String new_name) { name = new_name; }

    /**
     * Name of the action.
     */
    private String name;

    /**
     * If the action occured or not.
     */
    private Boolean occured;
}