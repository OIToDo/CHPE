package com.mygdx.game.persistance.Relations;


import com.mygdx.game.persistance.Coordinate.NNCoordinate;
import com.mygdx.game.persistance.Coordinates.NNCoordinates;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        primaryKeys = {
                "coordinatesId",
                "coordinateId"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = NNCoordinates.class,
                        parentColumns = "id",
                        childColumns = "coordinatesId"
                ),
                @ForeignKey(
                        entity = NNCoordinate.class,
                        parentColumns = "id",
                        childColumns = "coordinateId"
                )
        },
        tableName = "coordinatescoordinate"
)


public class NNCoordinatesCoordinate {
    @ColumnInfo(index = true)
    public int coordinatesId;
    @ColumnInfo(index = true)
    public int coordinateId;
}
