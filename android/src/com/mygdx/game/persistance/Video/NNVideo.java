package com.mygdx.game.persistance.Session;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "session")
public class NNSession {
    @PrimaryKey(autoGenerate = true)
    public long id;
    //public long  creation_date; TODO: Create date field
    public float frames_per_second;
    public int frame_count;

}
