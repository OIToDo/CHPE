package com.mygdx.game.persistance.Relations;


import com.mygdx.game.persistance.Coordinate.NNCoordinate;
import com.mygdx.game.persistance.Frame.NNFrame;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

/**
 * The type Nn frame coordinate.
 */
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
    /**
     * The Frame id.
     */
    @ColumnInfo(index = true)
    public long  frame_id;

    /**
     * The Coordinate id.
     */
    @ColumnInfo(index = true)
    public long  coordinate_id;
}
