package com.mygdx.game.persistance.Relations;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface NNFrameCoordinateDAO {

    @Insert
    void insert(NNFrameCoordinate nnFrameCoordinate);

    @Update
    void update(NNFrameCoordinate nnFrameCoordinate);

    @Delete
    void delete(NNFrameCoordinate nnFrameCoordinate);

}
