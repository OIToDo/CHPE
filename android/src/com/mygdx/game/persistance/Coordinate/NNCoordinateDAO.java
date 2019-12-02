package com.mygdx.game.persistance.Coordinate;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface NNCoordinateDAO {

    @Query("SELECT COUNT(*) FROM coordinate")
    int getCount();

    @Insert
    long insert(NNCoordinate nnCoordinate);

    @Update
    void update(NNCoordinate nnCoordinate);

    @Delete
    void delete(NNCoordinate nnCoordinate);

    @Query("SELECT * FROM coordinate")
    NNCoordinate[] getAllCoordinates();

    @Query("SELECT * FROM coordinate WHERE id == :id LIMIT 1")
    NNCoordinate getById(int id);

    @Query("SELECT * FROM coordinate WHERE id == :id LIMIT 1")
    NNCoordinate getById(long id);

    @Query("DELETE FROM coordinate")
    void nukeTable(); // Naming is about as clear as it can be.

}

