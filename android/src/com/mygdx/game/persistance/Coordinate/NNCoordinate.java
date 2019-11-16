package com.mygdx.game.persistance.Coordinate;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "coordinate")
public class NNCoordinate {
    @PrimaryKey(autoGenerate = true)
    public long  id;
    public double raw_x;
    public double raw_y;
    public int x;
    public int y;
}
