package com.mygdx.game.persistance.Relations;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.mygdx.game.persistance.Frame.NNFrame;
import com.mygdx.game.persistance.Video.NNVideo;

@Entity(
        primaryKeys = {
                "video_id",
                "frame_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = NNVideo.class,
                        parentColumns = "id",
                        childColumns = "video_id"
                ),
                @ForeignKey(
                        entity = NNFrame.class,
                        parentColumns = "id",
                        childColumns = "frame_id"
                )
        },
        tableName = "video_frame"
)
public class NNVideoFrame {
    @ColumnInfo(index = true) public long  video_id;
    @ColumnInfo(index = true) public long  frame_id;
}
