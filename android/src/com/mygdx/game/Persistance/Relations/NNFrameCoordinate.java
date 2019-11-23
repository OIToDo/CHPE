package com.mygdx.game.Persistance.Relations;


import com.mygdx.game.Persistance.Coordinate.NNCoordinate;
import com.mygdx.game.Persistance.Frame.NNFrame;

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

    public NNFrameCoordinate(long frame_id, long coordinate_id){
        this.frame_id = frame_id;
        this.coordinate_id = coordinate_id;
    }

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
