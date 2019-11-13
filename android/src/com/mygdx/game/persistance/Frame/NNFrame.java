package com.mygdx.game.persistance.Frame;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "frame")
public class NNFrame {
    @PrimaryKey(autoGenerate = true)
    public long  id;
    public int  frame_count;
}
