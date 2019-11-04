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
    void insert(NNFrame nnFrame);

    @Delete
    void delete(NNFrame nnFrame);

    @Update
    void update(NNFrame nnFrame);


    @Query("SELECT width,height from coordinate") // TODO: Finish
    List<Integer> get_coordinate(int frame_id, int body_count);  // TODO: Vec2
}
