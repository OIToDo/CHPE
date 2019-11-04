package com.mygdx.game.persistance.Session;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NNSessionDAO {
    @Insert
    void insert(NNSession nnSession);

    @Update
    void update(NNSession nnSession);

    @Delete
    void delete(NNSession nnSession);

    @Query("SELECT frames_per_second from session") // TODO: Get latest record
    float getFramesPerSecond();

    @Query("SELECT frame_count from session") // TODO: Get latest record
    int getFrameCount();
}
