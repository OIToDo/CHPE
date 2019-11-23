package com.mygdx.game.Persistance.Coordinate;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


/**
 * The interface Nn coordinate dao.
 */
@Dao
public interface NNCoordinateDAO {

    /**
     * Gets count.
     *
     * @return the count
     */
    @Query("SELECT COUNT(*) FROM coordinate")
    int getCount();

    /**
     * Insert long.
     *
     * @param nnCoordinate the nn coordinate
     * @return the long
     */
    @Insert
    long insert(NNCoordinate nnCoordinate);

    /**
     * Update.
     *
     * @param nnCoordinate the nn coordinate
     */
    @Update
    void update(NNCoordinate nnCoordinate);

    /**
     * Delete.
     *
     * @param nnCoordinate the nn coordinate
     */
    @Delete
    void delete(NNCoordinate nnCoordinate);

    /**
     * Get all coordinates nn coordinate [ ].
     *
     * @return the nn coordinate [ ]
     */
    @Query("SELECT * FROM coordinate")
    NNCoordinate[] getAllCoordinates();

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @Query("SELECT * FROM coordinate WHERE id == :id LIMIT 1")
    NNCoordinate getById(int id);

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @Query("SELECT * FROM coordinate WHERE id == :id LIMIT 1")
    NNCoordinate getById(long id);

    /**
     * Nuke table.
     */
    @Query("DELETE FROM coordinate")
    void nukeTable(); // Naming is about as clear as it can be.

}

