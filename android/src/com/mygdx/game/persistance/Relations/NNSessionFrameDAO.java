package com.mygdx.game.persistance.Relations;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface NNSessionFrameDAO {

    @Insert
    void insert(NNSessionFrame nnSessionFrame);

    @Update
    void update(NNSessionFrame nnSessionFrame);

    @Delete
    void delete(NNSessionFrame nnSessionFrame);
}
