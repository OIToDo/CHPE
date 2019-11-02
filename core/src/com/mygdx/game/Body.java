package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Body {
    public BodyPart bodyPart;
    public BodyLimb bodyLimb;

    public float scale;
    public Vector3 neckCoords = new Vector3();
    public Vector3 bodyTopCoords = new Vector3();
    public Vector3 bodyBotCoords = new Vector3();
    public Vector3 kneeRightCoords = new Vector3();

    //Joints
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

    //Limbs
    public ModelInstance LimbNeck;

    public Array<ModelInstance> create(float x, float y, float z, float scaleInstance){
        scale = scaleInstance;
        Array<ModelInstance> BodyPartInstances = new Array<ModelInstance>();
        bodyPart = new BodyPart();
        bodyLimb = new BodyLimb();

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
        // BodyParts -------------------------------------------------------------------------------------------------------------------|
        ModelInstance bodyBot = bodyPart.create(0f * scale, 0 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(bodyBot);

        ModelInstance bodyTop = bodyPart.create(0 * scale, 7.5f * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(bodyTop);

        ModelInstance shoulderLeft = bodyPart.create(2 * scale, 7.5f * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(shoulderLeft);

        ModelInstance shoulderRight = bodyPart.create(-2 * scale, 7.5f * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(shoulderRight);

        ModelInstance neck = bodyPart.create(0 * scale, 8.5f * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(neck);

        ModelInstance elbowLeft = bodyPart.create(4 * scale, 4 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(elbowLeft);

        ModelInstance elbowRight = bodyPart.create(-4 * scale, 4 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(elbowRight);

        ModelInstance wristLeft = bodyPart.create(4 * scale, -1 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(wristLeft);

        ModelInstance wristRight = bodyPart.create(-4 * scale, -1 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(wristRight);

        ModelInstance kneeLeft = bodyPart.create(2 * scale, -6 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(kneeLeft);

        ModelInstance kneeRight = bodyPart.create(-2 * scale, -6 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(kneeRight);

        ModelInstance ankleLeft = bodyPart.create(2 * scale, -12 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(ankleLeft);

        ModelInstance ankleRight = bodyPart.create(-2 * scale, -12 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(ankleRight);

        // BodyLimbs -------------------------------------------------------------------------------------------------------------------|
        neckCoords.add(0 * scale, 8.5f * scale,0 * scale);
        bodyTopCoords.add(0 * scale, 7.5f * scale,0 * scale);
        ModelInstance neckLimb = bodyLimb.create(neckCoords.x, neckCoords.y, bodyTopCoords.x, bodyTopCoords.y, 0, 0.25f * scale);
        BodyPartInstances.add(neckLimb);

        bodyBotCoords.add(0f * scale, 0 * scale,0 * scale);
        kneeRightCoords.add(-2 * scale, -6 * scale,0 * scale);
        ModelInstance upperLegLimbRight = bodyLimb.create(bodyBotCoords.x, bodyBotCoords.y, kneeRightCoords.x, kneeRightCoords.y, 0, 0.25f * scale);
        BodyPartInstances.add(upperLegLimbRight);

        for (int i = 0; i < BodyPartInstances.size; i++) {
            BodyPartInstances.get(i).transform.translate(x, y, z);
            BodyPartInstances.get(i).materials.get(0).set(ColorAttribute.createDiffuse(Color.GREEN));
        }

        return BodyPartInstances;
    }
}
