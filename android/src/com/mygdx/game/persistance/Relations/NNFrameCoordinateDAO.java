package com.mygdx.game.persistance.Relations;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


/**
 * The interface Nn frame coordinate dao.
 */
@Dao
public interface NNFrameCoordinateDAO {

    /**
     * Insert.
     *
     * @param nnFrameCoordinate the nn frame coordinate
     */
    @Insert
    void insert(NNFrameCoordinate nnFrameCoordinate);

    /**
     * Update.
     *
     * @param nnFrameCoordinate the nn frame coordinate
     */
    @Update
    void update(NNFrameCoordinate nnFrameCoordinate);

    /**
     * Delete.
     *
     * @param nnFrameCoordinate the nn frame coordinate
     */
    @Delete
    void delete(NNFrameCoordinate nnFrameCoordinate);


    /**
     * Nuke table.
     */
    @Query("DELETE FROM frame_coordinate")
    void nukeTable(); // Naming is about as clear as it can be.

}
