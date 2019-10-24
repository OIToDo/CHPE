package com.mygdx.game.persistance.Relations;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.mygdx.gamepersistance.Coordinates.NNCoordinates;
import com.mygdx.gamepersistance.Session.NNSession;

@Entity(
        primaryKeys = {
                "sessionId",
                "coordinatesId"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = NNSession.class,
                        parentColumns = "id",
                        childColumns = "sessionId"
                ),
                @ForeignKey(
                        entity = NNCoordinates.class,
                        parentColumns = "id",
                        childColumns = "coordinatesId"
                )
        },
        tableName = "sessioncoordinates"
)
public class NNSessionCoordinates {
    @ColumnInfo(index = true) public int sessionId;
    @ColumnInfo(index = true) public int coordinatesId;
}
