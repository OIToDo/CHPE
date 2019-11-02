package com.mygdx.game;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Body {

    public Model model;
    public BodyPart bodyPart;

    public ModelInstance BodyBot;
    public ModelInstance BodyTop;
    public ModelInstance ShoulderLeft;
    public ModelInstance ShoulderRight;
    public ModelInstance Neck;
    public ModelInstance ElbowLeft;
    public ModelInstance ElbowRight;
    public ModelInstance WristLeft;
    public ModelInstance WristRight;
    public ModelInstance KneeLeft;
    public ModelInstance KneeRight;
    public ModelInstance AnkleLeft;
    public ModelInstance AnkleRight;

    public Array<ModelInstance> create(float x, float y, float z, float scale){
        Array<ModelInstance> BodyPartInstances = new Array<ModelInstance>();
        bodyPart = new BodyPart();

//        check if scale is a positive number
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

        ModelInstance bodyBot = bodyPart.create(0f * scale, 0 * scale,0 * scale,0.5f * scale, Vector3.Z, 0, model);
        BodyPartInstances.add(bodyBot);

        ModelInstance bodyTop = bodyPart.create(0 * scale, 7.5f * scale,0 * scale,0.5f * scale, Vector3.Z, 0, model);
        BodyPartInstances.add(bodyTop);

        ModelInstance shoulderLeft = bodyPart.create(2 * scale, 7.5f * scale,0 * scale,0.5f * scale, Vector3.Z, 0, model);
        BodyPartInstances.add(shoulderLeft);

        ModelInstance shoulderRight = bodyPart.create(-2 * scale, 7.5f * scale,0 * scale,0.5f * scale, Vector3.Z, 0, model);
        BodyPartInstances.add(shoulderRight);

        ModelInstance neck = bodyPart.create(0 * scale, 8.5f * scale,0 * scale,0.5f * scale, Vector3.Z, 0, model);
        BodyPartInstances.add(neck);

        ModelInstance elbowLeft = bodyPart.create(4 * scale, 4 * scale,0 * scale,0.5f * scale, Vector3.Z, 0, model);
        BodyPartInstances.add(elbowLeft);

        ModelInstance elbowRight = bodyPart.create(-4 * scale, 4 * scale,0 * scale,0.5f * scale, Vector3.Z, 0, model);
        BodyPartInstances.add(elbowRight);

        ModelInstance wristLeft = bodyPart.create(4 * scale, -1 * scale,0 * scale,0.5f * scale, Vector3.Z, 0, model);
        BodyPartInstances.add(wristLeft);

        ModelInstance wristRight = bodyPart.create(-4 * scale, -1 * scale,0 * scale,0.5f * scale, Vector3.Z, 0, model);
        BodyPartInstances.add(wristRight);

        ModelInstance kneeLeft = bodyPart.create(2 * scale, -6 * scale,0 * scale,0.5f * scale, Vector3.Z, 0, model);
        BodyPartInstances.add(kneeLeft);

        ModelInstance kneeRight = bodyPart.create(-2 * scale, -6 * scale,0 * scale,0.5f * scale, Vector3.Z, 0, model);
        BodyPartInstances.add(kneeRight);

        ModelInstance ankleLeft = bodyPart.create(2 * scale, -12 * scale,0 * scale,0.5f * scale, Vector3.Z, 0, model);
        BodyPartInstances.add(ankleLeft);

        ModelInstance ankleRight = bodyPart.create(-2 * scale, -12 * scale,0 * scale,0.5f * scale, Vector3.Z, 0, model);
        BodyPartInstances.add(ankleRight);

        return BodyPartInstances;
    }
}
