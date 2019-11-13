package com.mygdx.game.persistance.Relations;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NNVideoFrameDAO {

    @Insert
    void insert(NNVideoFrame nnVideoFrame);

    @Update
    void update(NNVideoFrame nnVideoFrame);

    @Delete
    void delete(NNVideoFrame nnVideoFrame);

    @Query("DELETE FROM video_frame")
    void nukeTable(); // Naming is about as clear as it can be.

}
