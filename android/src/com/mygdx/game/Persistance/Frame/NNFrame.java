package com.mygdx.game.Persistance.Frame;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * The type Nn frame.
 */
@Entity(tableName = "frame")
public class NNFrame {

    /**
     * The Id.
     */
    @PrimaryKey(autoGenerate = true)
    public long id;
    /**
     * The Frame count.
     */
    public int frame_count;


    /**
     * Instantiates a new NN frame without data.
     */
    @Ignore
    NNFrame() {
    }

    /**
     * Instantiates a new Nn frame based on a LONG id.
     * Keep in mind that the value needs to be long, in the event that it isn't a long you will end
     * up with a very odd frame count.
     *
     * @param id the id
     */
    @Ignore
    NNFrame(long id) {
        this.id = id;
    }
    public NNFrame(int frame_count) {
        this.frame_count = frame_count;
    }

    /*
    TODO: define the modelType -> public String modelType;
    Usable for analysis and 3d preview to instantiate the model.
    */

}
