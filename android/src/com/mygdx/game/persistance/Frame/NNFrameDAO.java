package com.mygdx.game.persistance.Frame;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NNFrameDAO {

    @Insert
    long insert(NNFrame nnFrame);

    @Delete
    void delete(NNFrame nnFrame);

    @Update
    void update(NNFrame nnFrame);

    @Query("DELETE FROM frame")
    void nukeTable(); // Naming is about as clear as it can be.

    @Query("SELECT * FROM frame where id=:id")
    NNFrame getById(long id);

}
