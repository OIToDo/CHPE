package com.mygdx.game.persistance.Frame;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The type Nn frame.
 */
@Entity(tableName = "frame")
public class NNFrame {
    /**
     * The Id.
     */
    @PrimaryKey(autoGenerate = true)
    public long  id;
    /**
     * The Frame count.
     */
    public int  frame_count;
}
