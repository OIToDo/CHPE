package com.mygdx.game.persistance.Video;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * The type Nn video.
 */
@Entity(tableName = "video")
public class NNVideo {
    /**
     * The Id.
     */
    @PrimaryKey(autoGenerate = true)
    public long id;
    /**
     * The Frames per second.
     */
//public long  creation_date; TODO: Create date field
    public float frames_per_second;
    /**
     * The Frame count.
     */
    public int frame_count;
    /**
     * The Width.
     */
    public int width;
    /**
     * The Height.
     */
    public int height;

}
