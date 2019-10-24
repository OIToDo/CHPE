package com.mygdx.game.persistance.Relations;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.mygdx.game.persistance.Coordinate.NNCoordinate;

@Dao
public interface NNCoordinatesCoordinateDAO {

    @Insert
    void insert(NNCoordinatesCoordinate nnCoordinatesCoordinate);

    @Update
    void update(NNCoordinatesCoordinate nnCoordinatesCoordinate);

    @Delete
    void delete(NNCoordinatesCoordinate nnCoordinatesCoordinate);

}
