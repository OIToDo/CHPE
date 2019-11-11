package com.mygdx.game.persistance.Session;


import com.mygdx.game.persistance.Coordinate.NNCoordinate;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NNSessionDAO {
    @Insert
    long insert(NNSession nnSession);

    @Update
    void update(NNSession nnSession);

    @Delete
    void delete(NNSession nnSession);
    
    @Query("SELECT * FROM session ORDER BY id DESC LIMIT 1")
    NNSession getLastSession();

    @Query("SELECT frames_per_second FROM session WHERE :id")
    float getFramesPerSecond(int id);

    @Query("SELECT frame_count from session WHERE :id")
    int getFrameCount(int id);

    @Query("SELECT coordinate.id, coordinate.x, coordinate.y from coordinate, frame, frame_coordinate, session_frame WHERE session_frame.session_id = :sessionId and frame.frame_count = :frameCount LIMIT 1 OFFSET :bodyPart;")
    NNCoordinate get_coordinates(int frameCount, int bodyPart, int sessionId);  // TODO: Vec2

    @Query("DELETE FROM session_frame")
    void nukeTable();
}
