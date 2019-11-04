package com.mygdx.game.persistance.Frame;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "frame")
public class NNFrame {
    @PrimaryKey
    public int id;
    public int frameCount;
}
