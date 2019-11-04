package com.mygdx.game.persistance.Relations;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.mygdx.game.persistance.Frame.NNFrame;
import com.mygdx.game.persistance.Session.NNSession;

@Entity(
        primaryKeys = {
                "session_id",
                "frame_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = NNSession.class,
                        parentColumns = "id",
                        childColumns = "session_id"
                ),
                @ForeignKey(
                        entity = NNFrame.class,
                        parentColumns = "id",
                        childColumns = "frame_id"
                )
        },
        tableName = "session_frame"
)
public class NNSessionFrame {
    @ColumnInfo(index = true) public int session_id;
    @ColumnInfo(index = true) public int frame_id;
}
