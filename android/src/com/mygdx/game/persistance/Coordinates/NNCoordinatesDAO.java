package com.mygdx.game.persistance.Coordinates;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;


@Dao
public interface NNCoordinatesDAO {

    @Insert
    void insert(NNCoordinates nnCoordinates);

    @Delete
    void delete(NNCoordinates nnCoordinates);

    @Update
    void update(NNCoordinates nnCoordinates);
}
