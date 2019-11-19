package com.mygdx.game.persistance.Relations;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * The interface Nn video frame dao.
 */
@Dao
public interface NNVideoFrameDAO {

    /**
     * Insert.
     *
     * @param nnVideoFrame the nn video frame
     */
    @Insert
    void insert(NNVideoFrame nnVideoFrame);

    /**
     * Update.
     *
     * @param nnVideoFrame the nn video frame
     */
    @Update
    void update(NNVideoFrame nnVideoFrame);

    /**
     * Delete.
     *
     * @param nnVideoFrame the nn video frame
     */
    @Delete
    void delete(NNVideoFrame nnVideoFrame);

    /**
     * Nuke table.
     */
    @Query("DELETE FROM video_frame")
    void nukeTable(); // Naming is about as clear as it can be.

}
