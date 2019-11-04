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
    public int limbAmount = 0;

    //Joint coords
    public Vector3 BodyBotCoords = new Vector3();
    public Vector3 BodyTopCoords = new Vector3();
    public Vector3 ShoulderLeftCoords = new Vector3();
    public Vector3 ShoulderRightCoords = new Vector3();
    public Vector3 NeckCoords = new Vector3();
    public Vector3 ElbowLeftCoords = new Vector3();
    public Vector3 ElbowRightCoords = new Vector3();
    public Vector3 WristLeftCoords = new Vector3();
    public Vector3 WristRightCoords = new Vector3();
    public Vector3 KneeLeftCoords = new Vector3();
    public Vector3 KneeRightCoords = new Vector3();
    public Vector3 AnkleLeftCoords = new Vector3();
    public Vector3 AnkleRightCoords = new Vector3();

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

        ModelInstance shoulderLeft = bodyPart.create(-2 * scale, 7.5f * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(shoulderLeft);

        ModelInstance shoulderRight = bodyPart.create(2 * scale, 7.5f * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(shoulderRight);

        ModelInstance neck = bodyPart.create(0 * scale, 8.5f * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(neck);

        ModelInstance elbowLeft = bodyPart.create(-4 * scale, 4 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(elbowLeft);

        ModelInstance elbowRight = bodyPart.create(4 * scale, 4 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(elbowRight);

        ModelInstance wristLeft = bodyPart.create(-4 * scale, -1 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(wristLeft);

        ModelInstance wristRight = bodyPart.create(4 * scale, -1 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(wristRight);

        ModelInstance kneeLeft = bodyPart.create(-2 * scale, -6 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(kneeLeft);

        ModelInstance kneeRight = bodyPart.create(2 * scale, -6 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(kneeRight);

        ModelInstance ankleLeft = bodyPart.create(-2 * scale, -12 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(ankleLeft);

        ModelInstance ankleRight = bodyPart.create(2 * scale, -12 * scale,0 * scale,0.5f * scale, Vector3.Z, 0);
        BodyPartInstances.add(ankleRight);

        // BodyLimbs -------------------------------------------------------------------------------------------------------------------|
        BodyBotCoords.add(0f * scale, 0 * scale,0 * scale);
        BodyTopCoords.add(0 * scale, 7.5f * scale,0 * scale);
        ShoulderLeftCoords.add(-2 * scale, 7.5f * scale,0 * scale);
        ShoulderRightCoords.add(2 * scale, 7.5f * scale,0 * scale);
        NeckCoords.add(0 * scale, 8.5f * scale,0 * scale);
        ElbowLeftCoords.add(-4 * scale, 4 * scale,0 * scale);
        ElbowRightCoords.add(4 * scale, 4 * scale,0 * scale);
        WristLeftCoords.add(-4 * scale, -1 * scale,0 * scale);
        WristRightCoords.add(4 * scale, -1 * scale,0 * scale);
        KneeLeftCoords.add(-2 * scale, -6 * scale,0 * scale);
        KneeRightCoords.add(2 * scale, -6 * scale,0 * scale);
        AnkleLeftCoords.add(-2 * scale, -12 * scale,0 * scale);
        AnkleRightCoords.add(2 * scale, -12 * scale,0 * scale);

        //Neck
        ModelInstance neckLimb = bodyLimb.create(NeckCoords.x, NeckCoords.y, BodyTopCoords.x, BodyTopCoords.y, 0, 0.25f * scale);
        BodyPartInstances.add(neckLimb);
        limbAmount += 1;
        //Shoulder Left
        ModelInstance shoulderLeftLimb = bodyLimb.create(BodyTopCoords.x, BodyTopCoords.y, ShoulderLeftCoords.x, ShoulderLeftCoords.y, 0, 0.25f * scale);
        BodyPartInstances.add(shoulderLeftLimb);
        limbAmount += 1;
        //Shoulder Right
        ModelInstance shoulderRightLimb = bodyLimb.create(BodyTopCoords.x, BodyTopCoords.y, ShoulderRightCoords.x, ShoulderRightCoords.y, 0, 0.25f * scale);
        BodyPartInstances.add(shoulderRightLimb);
        limbAmount += 1;
        //Upper Arm Left
        ModelInstance upperArmLeftLimb = bodyLimb.create(ShoulderLeftCoords.x, ShoulderLeftCoords.y, ElbowLeftCoords.x, ElbowLeftCoords.y, 0, 0.25f * scale);
        BodyPartInstances.add(upperArmLeftLimb);
        limbAmount += 1;
        //Upper Arm Right
        ModelInstance upperArmRightLimb = bodyLimb.create(ShoulderRightCoords.x, ShoulderRightCoords.y, ElbowRightCoords.x, ElbowRightCoords.y, 0, 0.25f * scale);
        BodyPartInstances.add(upperArmRightLimb);
        limbAmount += 1;
        //Lower Arm Left
        ModelInstance lowerArmLeftLimb = bodyLimb.create(ElbowLeftCoords.x, ElbowLeftCoords.y, WristLeftCoords.x, WristLeftCoords.y, 0, 0.25f * scale);
        BodyPartInstances.add(lowerArmLeftLimb);
        limbAmount += 1;
        //Upper Arm Right
        ModelInstance lowerArmRightLimb = bodyLimb.create(ElbowRightCoords.x, ElbowRightCoords.y, WristRightCoords.x, WristRightCoords.y, 0, 0.25f * scale);
        BodyPartInstances.add(lowerArmRightLimb);
        limbAmount += 1;
        //Waist
        ModelInstance waist = bodyLimb.create(BodyTopCoords.x, BodyTopCoords.y, BodyBotCoords.x, BodyBotCoords.y, 0, 0.25f * scale);
        BodyPartInstances.add(waist);
        limbAmount += 1;
        //Left upper leg
        ModelInstance upperLegLeftLimb = bodyLimb.create(BodyBotCoords.x, BodyBotCoords.y, KneeLeftCoords.x, KneeLeftCoords.y, 0, 0.25f * scale);
        BodyPartInstances.add(upperLegLeftLimb);
        limbAmount += 1;
        //Right upper leg
        ModelInstance upperLegRightLimb = bodyLimb.create(BodyBotCoords.x, BodyBotCoords.y, KneeRightCoords.x, KneeRightCoords.y, 0, 0.25f * scale);
        BodyPartInstances.add(upperLegRightLimb);
        limbAmount += 1;
        //Left lower leg
        ModelInstance lowerLegLeftLimb = bodyLimb.create(KneeLeftCoords.x, KneeLeftCoords.y, AnkleLeftCoords.x, AnkleLeftCoords.y, 0, 0.25f * scale);
        BodyPartInstances.add(lowerLegLeftLimb);
        limbAmount += 1;
        //Right lower leg
        ModelInstance lowerLegRightLimb = bodyLimb.create(KneeRightCoords.x, KneeRightCoords.y, AnkleRightCoords.x, AnkleRightCoords.y, 0, 0.25f * scale);
        BodyPartInstances.add(lowerLegRightLimb);
        limbAmount += 1;

        for (int i = 0; i < BodyPartInstances.size - limbAmount + 1; i++) {
            BodyPartInstances.get(i).transform.translate(x, y, z);
            BodyPartInstances.get(i).materials.get(0).set(ColorAttribute.createDiffuse(Color.PURPLE));
        }
        for (int i = limbAmount + 1; i < BodyPartInstances.size; i++) {
            BodyPartInstances.get(i).transform.translate(x, y, z);
            BodyPartInstances.get(i).materials.get(0).set(ColorAttribute.createDiffuse(Color.PINK));
        }

        return BodyPartInstances;
    }
}
