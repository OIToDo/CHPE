package com.mygdx.game.Persistance.Coordinate;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The type Nn coordinate.
 */
@Entity(tableName = "coordinate")
public class NNCoordinate {

    /**
     * Instantiates a new NNCoordinate.
     */
    public NNCoordinate(){}

    /**
     * Instantiates a new NNCoordinate.
     * Useful when inserting new coordinates.
     *
     * @param raw_x the raw x coordinate
     * @param raw_y the raw y coordinate
     */
    public NNCoordinate(int raw_x, int raw_y){
        this.raw_x = raw_x;
        this.raw_y = raw_y;
    }

    /**
     * Instantiates a new NNCoordinate based on it's id.
     * Useful when values need to be gathered based on the ID.
     *
     * @param id the id
     */
    public NNCoordinate(int id){
        this.id = id;
    }


    /**
     * Instantiates a new NNCoordinate.
     * Useful for updating the x and y values.
     *
     * @param id the id
     * @param x  the x coordinate
     * @param y  the y coordinate
     */
    public NNCoordinate(int id, int x, int y){
        this.id = id;
        this.x = x;
        this.y = y;
    }

    /**
     * The Id.
     */
    @PrimaryKey(autoGenerate = true)
    public long id;
    /**
     * The Raw x.
     */
    public int raw_x;
    /**
     * The Raw y.
     */
    public int raw_y;
    /**
     * The X.
     */
    public double x;
    /**
     * The Y.
     */
    public double y;
}
