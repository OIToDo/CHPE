package com.mygdx.game.Analysis;

/**
 * @author Nico van Bentum
 * Class used for defining actions we want to detect.
 * TODO: Should become a more sophisticated object people can implement and tie attributes to.
 */
public class Action { 
    /**
     * @param name Name of the action.
     * Constructor.
     */
    public Action(final String name) {
        this.name = name;
    }

    /**
     * Requests if the Action has occurred or not.
     * @return Whether the Action occurred or not.
     */
    public boolean occurred() {
        return occurred;
    }

    /**
     * Sets the occurrence.
     * @param occ New occurrence to set.
     */
    public void setOccurrence(boolean occ) {
        occurred = occ;
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
     * If the action occurred or not.
     */
    private Boolean occurred;
}