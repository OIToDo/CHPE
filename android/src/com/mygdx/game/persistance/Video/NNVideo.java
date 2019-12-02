package com.mygdx.game.Persistance.Video;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


/**
 * The type Nn video.
 */
@Entity(tableName = "video")
public class NNVideo {
    //TODO: Create date field


    /**
     * The Id.
     */
    @PrimaryKey(autoGenerate = true)
    public long id;
    /**
     * The Frames per second.
     */
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
    public NNVideo() {
    }
    @Ignore
    public NNVideo(long id) {
        this.id = id;
    }
    @Ignore
    public NNVideo(float frames_per_second, int frame_count, int width, int height) {
        this.frames_per_second = frames_per_second;
        this.frame_count = frame_count;
        this.width = width;
        this.height = height;
    }

}
