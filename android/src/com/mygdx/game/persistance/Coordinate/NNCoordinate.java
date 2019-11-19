package com.mygdx.game.persistance.Coordinate;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The type Nn coordinate.
 */
@Entity(tableName = "coordinate")
public class NNCoordinate {

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
