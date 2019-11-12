package com.mygdx.game.Simulation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Analysis.Data;
import com.mygdx.game.PoseEstimation.nn.MPI.body_part;

public class Body {
    public BodyPart bodyPart;
    public BodyLimb bodyLimb;

    public float scale;
    public int limbAmount = 0;
    public float limbDiameter = 1f;
    public float jointDiameter = 1f;


    //Joint coords
    public Vector3 waistCoords = new Vector3();
    public Vector3 neckCoords = new Vector3();
    public Vector3 l_shoulderCoords = new Vector3();
    public Vector3 r_shoulderCoords = new Vector3();
    public Vector3 headCoords = new Vector3();
    public Vector3 l_elbowCoords = new Vector3();
    public Vector3 r_elbowCoords = new Vector3();
    public Vector3 l_wristCoords = new Vector3();
    public Vector3 r_wristCoords = new Vector3();
    public Vector3 l_kneeCoords = new Vector3();
    public Vector3 r_kneeCoords = new Vector3();
    public Vector3 l_footCoords = new Vector3();
    public Vector3 r_footCoords = new Vector3();
    public Vector3 l_hipCoords = new Vector3();
    public Vector3 r_hipCoords = new Vector3();

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
        ModelInstance bodyBot = bodyPart.create(0f * scale, 0 * scale,0 * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(bodyBot);

        ModelInstance bodyTop = bodyPart.create(0 * scale, 7.5f * scale,0 * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(bodyTop);

        ModelInstance shoulderLeft = bodyPart.create(-2 * scale, 7.5f * scale,0 * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(shoulderLeft);

        ModelInstance shoulderRight = bodyPart.create(2 * scale, 7.5f * scale,0 * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(shoulderRight);

        ModelInstance neck = bodyPart.create(0 * scale, 8.5f * scale,0 * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(neck);

        ModelInstance elbowLeft = bodyPart.create(-4 * scale, 4 * scale,0 * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(elbowLeft);

        ModelInstance elbowRight = bodyPart.create(4 * scale, 4 * scale,0 * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(elbowRight);

        ModelInstance wristLeft = bodyPart.create(-4 * scale, -1 * scale,0 * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(wristLeft);

        ModelInstance wristRight = bodyPart.create(4 * scale, -1 * scale,0 * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(wristRight);

        ModelInstance kneeLeft = bodyPart.create(-2 * scale, -6 * scale,0 * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(kneeLeft);

        ModelInstance kneeRight = bodyPart.create(2 * scale, -6 * scale,0 * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(kneeRight);

        ModelInstance ankleLeft = bodyPart.create(-2 * scale, -12 * scale,0 * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(ankleLeft);

        ModelInstance ankleRight = bodyPart.create(2 * scale, -12 * scale,0 * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(ankleRight);

        // BodyLimbs -------------------------------------------------------------------------------------------------------------------|
        waistCoords.add(0f * scale, 0 * scale,0 * scale);
        neckCoords.add(0 * scale, 7.5f * scale,0 * scale);
        l_shoulderCoords.add(-2 * scale, 7.5f * scale,0 * scale);
        r_shoulderCoords.add(2 * scale, 7.5f * scale,0 * scale);
        headCoords.add(0 * scale, 8.5f * scale,0 * scale);
        l_elbowCoords.add(-4 * scale, 4 * scale,0 * scale);
        r_elbowCoords.add(4 * scale, 4 * scale,0 * scale);
        l_wristCoords.add(-4 * scale, -1 * scale,0 * scale);
        r_wristCoords.add(4 * scale, -1 * scale,0 * scale);
        l_kneeCoords.add(-2 * scale, -6 * scale,0 * scale);
        r_kneeCoords.add(2 * scale, -6 * scale,0 * scale);
        l_footCoords.add(-2 * scale, -12 * scale,0 * scale);
        r_footCoords.add(2 * scale, -12 * scale,0 * scale);

        //Neck
        ModelInstance neckLimb = bodyLimb.create(headCoords.x, headCoords.y, neckCoords.x, neckCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(neckLimb);
        limbAmount += 1;
        //Shoulder Left
        ModelInstance shoulderLeftLimb = bodyLimb.create(neckCoords.x, neckCoords.y, l_shoulderCoords.x, l_shoulderCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(shoulderLeftLimb);
        limbAmount += 1;
        //Shoulder Right
        ModelInstance shoulderRightLimb = bodyLimb.create(neckCoords.x, neckCoords.y, r_shoulderCoords.x, r_shoulderCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(shoulderRightLimb);
        limbAmount += 1;
        //Upper Arm Left
        ModelInstance upperArmLeftLimb = bodyLimb.create(l_shoulderCoords.x, l_shoulderCoords.y, l_elbowCoords.x, l_elbowCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(upperArmLeftLimb);
        limbAmount += 1;
        //Upper Arm Right
        ModelInstance upperArmRightLimb = bodyLimb.create(r_shoulderCoords.x, r_shoulderCoords.y, r_elbowCoords.x, r_elbowCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(upperArmRightLimb);
        limbAmount += 1;
        //Lower Arm Left
        ModelInstance lowerArmLeftLimb = bodyLimb.create(l_elbowCoords.x, l_elbowCoords.y, l_wristCoords.x, l_wristCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(lowerArmLeftLimb);
        limbAmount += 1;
        //Upper Arm Right
        ModelInstance lowerArmRightLimb = bodyLimb.create(r_elbowCoords.x, r_elbowCoords.y, r_wristCoords.x, r_wristCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(lowerArmRightLimb);
        limbAmount += 1;
        //Waist
        ModelInstance waist = bodyLimb.create(neckCoords.x, neckCoords.y, waistCoords.x, waistCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(waist);
        limbAmount += 1;
        //Left upper leg
        ModelInstance upperLegLeftLimb = bodyLimb.create(waistCoords.x, waistCoords.y, l_kneeCoords.x, l_kneeCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(upperLegLeftLimb);
        limbAmount += 1;
        //Right upper leg
        ModelInstance upperLegRightLimb = bodyLimb.create(waistCoords.x, waistCoords.y, r_kneeCoords.x, r_kneeCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(upperLegRightLimb);
        limbAmount += 1;
        //Left lower leg
        ModelInstance lowerLegLeftLimb = bodyLimb.create(l_kneeCoords.x, l_kneeCoords.y, l_footCoords.x, l_footCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(lowerLegLeftLimb);
        limbAmount += 1;
        //Right lower leg
        ModelInstance lowerLegRightLimb = bodyLimb.create(r_kneeCoords.x, r_kneeCoords.y, r_footCoords.x, r_footCoords.y, 0, limbDiameter * scale);
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

    public void update(int frame, Array<ModelInstance> instances, Data data){
        headCoords = data.getCoord(frame, body_part.head);
        neckCoords = data.getCoord(frame, body_part.neck);
        l_shoulderCoords = data.getCoord(frame, body_part.l_shoulder);
        r_shoulderCoords = data.getCoord(frame, body_part.r_shoulder);
        l_elbowCoords = data.getCoord(frame, body_part.l_elbow);
        r_elbowCoords = data.getCoord(frame, body_part.r_elbow);
        l_wristCoords = data.getCoord(frame, body_part.l_wrist);
        r_wristCoords = data.getCoord(frame, body_part.r_wrist);
        waistCoords = data.getCoord(frame, body_part.waist);
        l_hipCoords = data.getCoord(frame, body_part.l_hip);
        r_hipCoords = data.getCoord(frame, body_part.r_hip);
        l_kneeCoords = data.getCoord(frame, body_part.l_knee);
        r_kneeCoords = data.getCoord(frame, body_part.r_knee);
        l_footCoords = data.getCoord(frame, body_part.l_knee);
        r_kneeCoords = data.getCoord(frame, body_part.r_knee);
    }
}
