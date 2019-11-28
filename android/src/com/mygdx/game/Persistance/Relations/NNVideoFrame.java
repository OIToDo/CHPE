package com.mygdx.game.Persistance.Relations;

import com.mygdx.game.Persistance.Frame.NNFrame;
import com.mygdx.game.Persistance.Video.NNVideo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

/**
 * The type Nn video frame.
 */
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

    /**
     * The Video id.
     */
    @ColumnInfo(index = true)
    public long video_id;
    /**
     * The Frame id.
     */
    @ColumnInfo(index = true)
    public long frame_id;
    public NNVideoFrame(long video_id, long frame_id) {
        this.video_id = video_id;
        this.frame_id = frame_id;
    }
}