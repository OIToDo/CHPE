package com.mygdx.game.persistance.Coordinates;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "coordinates")
public class NNCoordinates {
    @PrimaryKey
    public int id;
    public int frameCount;
}
