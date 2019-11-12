package com.mygdx.game.persistance.Video;


import com.mygdx.game.persistance.Coordinate.NNCoordinate;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NNVideoDAO {
    @Insert
    long insert(NNVideo nnSession);

    @Update
    void update(NNVideo nnSession);

    @Delete
    void delete(NNVideo nnSession);
    
    @Query("SELECT * FROM video ORDER BY id DESC LIMIT 1")
    NNVideo getLastSession();

    @Query("SELECT frames_per_second FROM video WHERE :id")
    float getFramesPerSecond(int id);

    @Query("SELECT frame_count from video WHERE :id")
    int getFrameCount(int id);

    @Query("SELECT coordinate.id, coordinate.x, coordinate.y from coordinate, frame, frame_coordinate, video_frame, video WHERE video.id = :videoId AND video.id = video_frame.video_id AND video_frame.frame_id = frame.id AND frame.frame_count = :frameCount AND frame_coordinate.frame_id = frame.id LIMIT 1 OFFSET :bodyPart")
    NNCoordinate get_coordinates(int frameCount, int bodyPart, long videoId);  // TODO: Vec2

    @Query("DELETE FROM video")
    void nukeTable();
}
