package com.mygdx.game.persistance.Video;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "video")
public class NNVideo {
    @PrimaryKey(autoGenerate = true)
    public long id;
    //public long  creation_date; TODO: Create date field
    public float frames_per_second;
    public int frame_count;

}
