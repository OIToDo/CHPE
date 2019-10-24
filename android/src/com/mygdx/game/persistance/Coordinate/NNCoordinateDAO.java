package com.mygdx.game.persistance.Coordinate;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.mygdx.gamepersistance.Coordinate.NNCoordinate;

@Dao
public interface NNCoordinateDAO {

    @Query("SELECT COUNT(*) FROM coordinate")
    int getCount();

    @Insert
    void insert(NNCoordinate nnCoordinate);

    @Update
    void update(NNCoordinate nnCoordinate);

    @Delete
    void delete(NNCoordinate nnCoordinate);

}
