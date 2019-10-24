package com.mygdx.game.persistance.Relations;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface NNSessionCoordinatesDAO {

    @Insert
    void insert(NNSessionCoordinates nnSessionCoordinates);

    @Update
    void update(NNSessionCoordinates nnSessionCoordinates);

    @Delete
    void delete(NNSessionCoordinates nnSessionCoordinates);
}
