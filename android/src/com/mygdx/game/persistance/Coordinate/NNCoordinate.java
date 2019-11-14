package com.mygdx.game.persistance.Coordinate;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "coordinate")
public class NNCoordinate {
    @PrimaryKey(autoGenerate = true)
    public long  id;
    public double  x;
    public double y;
}
