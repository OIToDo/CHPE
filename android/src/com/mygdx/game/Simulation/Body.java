package com.mygdx.game.Simulation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Analysis.Data;
import com.mygdx.game.DebugLog;
import com.mygdx.game.PoseEstimation.nn.MPI.body_part;

import java.util.HashMap;

import static com.mygdx.game.PoseEstimation.nn.PoseModel.POSE_PAIRS;
import static com.mygdx.game.Simulation.HelperClass.vec3Subtraction;

/**
 * The body class combines BodyPart and BodyLimb objects to create the layout of the human body into a singular object.
 * This class provides functionality to update the position of these arranged BodyParts and Limbs according to the provides data from the database.
 */
public class Body {
    public float scale;
    public float limbDiameter = 1f;
    public float jointDiameter = 1f;
    private boolean scaled = false;
    private float head_scale = 1;
    public float data_scale = -25f;

    public HashMap<body_part, BodyPart> jointMap = new HashMap();
    public Array<BodyLimb> limbArray = new Array<>();

    // Prevent from creating a copy upon every time this method is called.
    private static final int body_part_size = body_part.values().length;

    // Array to store joint coordinates from the data
    public Array<Vector3> jointCoords = new Array<>();

    /**
     * This function currently defines the object with its parameters.
     * Will be replaced with a constructor in the future.
     * @param scaleInstance
     * @param data
     */
    public void create(float scaleInstance, Data data){
        scale = scaleInstance;

        // check if scale is a positive number
        try
        {
            if (scale <= 0)
            {
                throw new IllegalArgumentException("Scale must be a positive float value.");
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        // BodyParts a.k.a joints -------------------------------------------------------------------------------------------------------|

        // Create the joints and give them color
        for(body_part bp : body_part.values()){
            jointMap.put(bp, new BodyPart(new Vector3(), jointDiameter * scale, Color.ORANGE));
        }
        jointMap.get(body_part.head).change_color(Color.GREEN);

        //Fill JointCoords with al the needed arrays for the separate joints
        for(int i = 0; i < body_part_size; i++){
            jointCoords.add(new Vector3());
        }

        // BodyLimbs -------------------------------------------------------------------------------------------------------------------|
        for (int i = 0; i < jointCoords.size; i++) {
            jointCoords.set(i, vec3Subtraction(data.getCoord(0, body_part.values()[i]), data.getCoord(0, body_part.values()[body_part.waist.ordinal()])));
        }
        for (int[] pp : POSE_PAIRS){
            limbArray.add(new BodyLimb(
                    new Vector2(jointCoords.get(pp[0]).x * - data_scale, jointCoords.get(pp[0]).y * data_scale),
                    new Vector2(jointCoords.get(pp[1]).x * - data_scale, jointCoords.get(pp[1]).y * data_scale), limbDiameter * scale * 0.7f, 0, Color.ORANGE));
        }
    }

    /**
     * Returns all the BodyLimb objects as a ModelInstance Array.
     * @return Array filled with ModelInstances.
     */
    public Array<ModelInstance> getLimbArray(){
        Array<ModelInstance> result = new Array<>();
        for(BodyLimb bl : limbArray){
            result.add(bl.getInstance());
        }
        return result;
    }

    /**
     * Returns all the BodyPart objects as a ModelInstance Array.
     * @return Array filled with ModelInstances.
     */
    public Array<ModelInstance> getJointArray(){
        Array<ModelInstance> result = new Array<>();
        for(BodyPart jv : jointMap.values()){
            result.add(jv.getInstance());
        }
        return result;
    }

    /**
     * This update function updates al the BodyLimb and BodyPart object with the data for the given frame.
     * @param frame
     * @param data
     */
    public void update(int frame, Data data){
        //update joints -------------------------------------------------------------------------------------------------|
        for (int i = 0; i < jointCoords.size; i++){
            jointCoords.set(i, vec3Subtraction(data.getCoord(frame, body_part.values()[i]), data.getCoord(frame, body_part.values()[body_part.waist.ordinal()])));
            jointMap.get(body_part.values()[i]).update(new Vector3((
                    (jointCoords.get(i).x * -data_scale) - (jointCoords.get(body_part.waist.ordinal()).x * -data_scale)),
                    (jointCoords.get(i).y * data_scale) - (jointCoords.get(body_part.waist.ordinal()).y * data_scale),
                    (jointCoords.get(i).z * data_scale) - (jointCoords.get(body_part.waist.ordinal()).z * data_scale)));
            DebugLog.log("kanker: " + new Vector3((
                    (jointCoords.get(i).x * -data_scale) - (jointCoords.get(body_part.waist.ordinal()).x * -data_scale)),
                    (jointCoords.get(i).y * data_scale) - (jointCoords.get(body_part.waist.ordinal()).y * data_scale),
                    (jointCoords.get(i).z * data_scale) - (jointCoords.get(body_part.waist.ordinal()).z * data_scale)));
        }

        //update limbs --------------------------------------------------------------------------------------------------|
        int index = 0;
        for (int[] pp : POSE_PAIRS){
            limbArray.get(index).update(
                    new Vector2(jointCoords.get(pp[0]).x * -data_scale, jointCoords.get(pp[0]).y * data_scale),
                    new Vector2(jointCoords.get(pp[1]).x * -data_scale, jointCoords.get(pp[1]).y * data_scale), 0);
            index++;
        }
        if(scaled == false){
            head_scale = (jointCoords.get(body_part.waist.ordinal()).y - jointCoords.get(body_part.neck.ordinal()).y);
            scaled = true;
            limbDiameter = limbDiameter * 0.7f;
        }
        jointMap.get(body_part.head).set_scale(20f * head_scale);

    }
}
