package com.mygdx.game.persistance.Frame;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Update;

/**
 * The interface Nn frame dao.
 */
@Dao
public interface NNFrameDAO {

    /**
     * Insert long.
     *
     * @param nnFrame the nn frame
     * @return the long
     */
    @Insert
    long insert(NNFrame nnFrame);

    /**
     * Delete.
     *
     * @param nnFrame the nn frame
     */
    @Delete
    void delete(NNFrame nnFrame);

    /**
     * Update.
     *
     * @param nnFrame the nn frame
     */
    @Update
    void update(NNFrame nnFrame);

    /**
     * Nuke table.
     */
    @Query("DELETE FROM frame")
    void nukeTable(); // Naming is about as clear as it can be.

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @Query("SELECT * FROM frame where id=:id")
    NNFrame getById(long id);

}
