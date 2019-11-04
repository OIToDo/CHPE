package com.mygdx.game.persistance.Relations;


import com.mygdx.game.persistance.Coordinate.NNCoordinate;
import com.mygdx.game.persistance.Frame.NNFrame;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        primaryKeys = {
                "frame_id",
                "coordinate_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = NNFrame.class,
                        parentColumns = "id",
                        childColumns = "frame_id"
                ),
                @ForeignKey(
                        entity = NNCoordinate.class,
                        parentColumns = "id",
                        childColumns = "coordinate_id"
                )
        },
        tableName = "frame_coordinate"
)


public class NNFrameCoordinate {
    @ColumnInfo(index = true)
    public int FrameId;

    @ColumnInfo(index = true)
    public int CoordinateId;
}
