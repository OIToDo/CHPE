package com.mygdx.game.Simulation;

import android.os.Debug;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Analysis.Data;
import com.mygdx.game.DebugLog;
import com.mygdx.game.PoseEstimation.nn.MPI.body_part;

public class Body {
    public BodyPart bodyPart;
    public BodyLimb bodyLimb;

    public float scale;
    public int limbAmount = 0;
    public float limbDiameter = 1f;
    public float jointDiameter = 1f;

    ModelBuilder modelBuilder = new ModelBuilder();

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
        // BodyParts a.k.a joints -------------------------------------------------------------------------------------------------------|
        ModelInstance head_joint = bodyPart.create(0f * scale, 8.5f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(head_joint);

        ModelInstance neck_joint = bodyPart.create(0f * scale, 7.5f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(neck_joint);

        ModelInstance l_shoulder_joint = bodyPart.create(-2f * scale, 7f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(l_shoulder_joint);

        ModelInstance r_shoulder_joint = bodyPart.create(2f * scale, 7f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(r_shoulder_joint);

        ModelInstance l_elbow_joint = bodyPart.create(-4f * scale, 4f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(l_elbow_joint);

        ModelInstance r_elbow_joint = bodyPart.create(4f * scale, 4f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(r_elbow_joint);

        ModelInstance l_wrist_joint = bodyPart.create(-4f * scale, -1f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(l_wrist_joint);

        ModelInstance r_wrist_joint = bodyPart.create(4f * scale, -1f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(r_wrist_joint);

        ModelInstance waist_joint = bodyPart.create(0f * scale, 0f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(waist_joint);

        ModelInstance l_hip_joint = bodyPart.create(-1f * scale, -0.5f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(l_hip_joint);

        ModelInstance r_hip_joint = bodyPart.create(1f * scale, -0.5f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(r_hip_joint);

        ModelInstance l_knee_joint = bodyPart.create(-2f * scale, -6f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(l_knee_joint);

        ModelInstance r_knee_joint = bodyPart.create(2f * scale, -6f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(r_knee_joint);

        ModelInstance l_foot_joint = bodyPart.create(-2f * scale, -12f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(l_foot_joint);

        ModelInstance r_foot_joint = bodyPart.create(2f * scale, -12f * scale,0f * scale,jointDiameter * scale, Vector3.Z, 0);
        BodyPartInstances.add(r_foot_joint);

        // Store all the current coords for all the joints.
        headCoords.add(0f * scale, 8.5f * scale,0f * scale);
        neckCoords.add(0f * scale, 7.5f * scale, 0f * scale);
        l_shoulderCoords.add(-2f * scale, 7f * scale, 0f * scale);
        r_shoulderCoords.add(2f * scale, 7f * scale, 0f * scale);
        l_elbowCoords.add(-4f * scale, 4f * scale, 0f * scale);
        r_elbowCoords.add(4f * scale, 4f * scale, 0f * scale);
        l_wristCoords.add(-4f * scale, -1f * scale, 0f * scale);
        r_wristCoords.add(4f * scale, -1f * scale, 0f * scale);
        waistCoords.add(0f * scale, 0f * scale, 0f * scale);
        l_hipCoords.add(-1f * scale, -0.5f * scale, 0f * scale);
        r_hipCoords.add(1f * scale, -0.5f * scale, 0f * scale);
        l_kneeCoords.add(-2f * scale, -6f * scale, 0f * scale);
        r_kneeCoords.add(2f * scale, -6f * scale, 0f * scale);
        l_footCoords.add(-2f * scale, -12f * scale, 0f * scale);
        r_footCoords.add(2f * scale, -12f * scale, 0f * scale);

        // BodyLimbs -------------------------------------------------------------------------------------------------------------------|
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
        //Lower Arm Right
        ModelInstance lowerArmRightLimb = bodyLimb.create(r_elbowCoords.x, r_elbowCoords.y, r_wristCoords.x, r_wristCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(lowerArmRightLimb);
        limbAmount += 1;
        //Waist
        ModelInstance waist = bodyLimb.create(neckCoords.x, neckCoords.y, waistCoords.x, waistCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(waist);
        limbAmount += 1;
        //Left hip
        ModelInstance leftHipLimb = bodyLimb.create(waistCoords.x, waistCoords.y, l_hipCoords.x, l_hipCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(leftHipLimb);
        limbAmount += 1;
        //Right hip
        ModelInstance rightHipLimb = bodyLimb.create(waistCoords.x, waistCoords.y, r_hipCoords.x, r_hipCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(rightHipLimb);
        limbAmount += 1;
        //Left upper leg
        ModelInstance upperLegLeftLimb = bodyLimb.create(l_hipCoords.x, l_hipCoords.y, l_kneeCoords.x, l_kneeCoords.y, 0, limbDiameter * scale);
        BodyPartInstances.add(upperLegLeftLimb);
        limbAmount += 1;
        //Right upper leg
        ModelInstance upperLegRightLimb = bodyLimb.create(r_hipCoords.x, r_hipCoords.y, r_kneeCoords.x, r_kneeCoords.y, 0, limbDiameter * scale);
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
        float data_scale = -0.015f;
        // todo enum for instances. =)

        DebugLog.log("FrameNumber" + frame);
        //update joints -------------------------------------------------------------------------------------------------|
        headCoords = data.getCoord(frame, body_part.head);
        instances.get(0).transform.setToTranslation(headCoords.x * data_scale, headCoords.y * data_scale, headCoords.z * data_scale);

        neckCoords = data.getCoord(frame, body_part.neck);
        instances.get(1).transform.setToTranslation(neckCoords.x * data_scale, neckCoords.y * data_scale, neckCoords.z * data_scale);

        l_shoulderCoords = data.getCoord(frame, body_part.l_shoulder);
        instances.get(2).transform.setToTranslation(l_shoulderCoords.x * data_scale, l_shoulderCoords.y * data_scale, l_shoulderCoords.z * data_scale);

        r_shoulderCoords = data.getCoord(frame, body_part.r_shoulder);
        instances.get(3).transform.setToTranslation(r_shoulderCoords.x * data_scale, r_shoulderCoords.y * data_scale, r_shoulderCoords.z * data_scale);

        l_elbowCoords = data.getCoord(frame, body_part.l_elbow);
        instances.get(4).transform.setToTranslation(l_elbowCoords.x * data_scale, l_elbowCoords.y * data_scale, l_elbowCoords.z * data_scale);

        r_elbowCoords = data.getCoord(frame, body_part.r_elbow);
        instances.get(5).transform.setToTranslation(r_elbowCoords.x * data_scale, r_elbowCoords.y * data_scale, r_elbowCoords.z * data_scale);

        l_wristCoords = data.getCoord(frame, body_part.l_wrist);
        instances.get(6).transform.setToTranslation(l_wristCoords.x * data_scale, l_wristCoords.y * data_scale, l_wristCoords.z * data_scale);

        r_wristCoords = data.getCoord(frame, body_part.r_wrist);
        instances.get(7).transform.setToTranslation(r_wristCoords.x * data_scale, r_wristCoords.y * data_scale, r_wristCoords.z * data_scale);

        waistCoords = data.getCoord(frame, body_part.waist);
        instances.get(8).transform.setToTranslation(waistCoords.x * data_scale, waistCoords.y * data_scale, waistCoords.z * data_scale);

        l_hipCoords = data.getCoord(frame, body_part.l_hip);
        instances.get(9).transform.setToTranslation(l_hipCoords.x * data_scale, l_hipCoords.y * data_scale, l_hipCoords.z * data_scale);

        r_hipCoords = data.getCoord(frame, body_part.r_hip);
        instances.get(10).transform.setToTranslation(r_hipCoords.x * data_scale, r_hipCoords.y * data_scale, r_hipCoords.z * data_scale);

        l_kneeCoords = data.getCoord(frame, body_part.l_knee);
        instances.get(11).transform.setToTranslation(l_kneeCoords.x * data_scale, l_kneeCoords.y * data_scale, l_kneeCoords.z * data_scale);

        r_kneeCoords = data.getCoord(frame, body_part.r_knee);
        instances.get(12).transform.setToTranslation(r_kneeCoords.x * data_scale, r_kneeCoords.y * data_scale, r_kneeCoords.z * data_scale);

        l_footCoords = data.getCoord(frame, body_part.l_foot);
        instances.get(13).transform.setToTranslation(l_footCoords.x * data_scale, l_footCoords.y * data_scale, l_footCoords.z * data_scale);

        r_footCoords = data.getCoord(frame, body_part.r_foot);
        instances.get(14).transform.setToTranslation(r_footCoords.x * data_scale, r_footCoords.y * data_scale, r_footCoords.z * data_scale);

        //update limbs --------------------------------------------------------------------------------------------------|
        // todo if something terribly messes up here, look at the math order (1st for the neck limb should be in the correct order)!
        float neck_length = HelperClass.PythagorasTheorem(headCoords.x * data_scale, headCoords.y * data_scale, neckCoords.x * data_scale, neckCoords.y * data_scale);
        float neck_rotation = HelperClass.getAngle(headCoords.x * data_scale, headCoords.y * data_scale, neckCoords.x * data_scale, neckCoords.y * data_scale);
        Model neck_model = modelBuilder.createCylinder(limbDiameter * scale, neck_length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances.set(15, new ModelInstance(neck_model));
        instances.get(15).transform.setToTranslation(headCoords.x * data_scale - (0.5f * (headCoords.x * data_scale - neckCoords.x * data_scale)), headCoords.y * data_scale - (0.5f * (headCoords.y * data_scale - neckCoords.y * data_scale)), 0f);
        instances.get(15).transform.rotate(Vector3.Z, neck_rotation - 90);

        float l_shoulder_length = HelperClass.PythagorasTheorem(neckCoords.x * data_scale, neckCoords.y * data_scale, l_shoulderCoords.x * data_scale, l_shoulderCoords.y * data_scale);
        float l_shoulder_rotation = HelperClass.getAngle(neckCoords.x * data_scale, neckCoords.y * data_scale, l_shoulderCoords.x * data_scale, l_shoulderCoords.y * data_scale);
        Model l_shoulder_model = modelBuilder.createCylinder(limbDiameter * scale, l_shoulder_length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances.set(16, new ModelInstance(l_shoulder_model));
        instances.get(16).transform.setToTranslation(neckCoords.x * data_scale - (0.5f * (neckCoords.x * data_scale - l_shoulderCoords.x * data_scale)), neckCoords.y * data_scale - (0.5f * (neckCoords.y * data_scale - l_shoulderCoords.y * data_scale)), 0f);
        instances.get(16).transform.rotate(Vector3.Z, l_shoulder_rotation - 90);

        float r_shoulder_length = HelperClass.PythagorasTheorem(neckCoords.x * data_scale, neckCoords.y * data_scale, r_shoulderCoords.x * data_scale, r_shoulderCoords.y * data_scale);
        float r_shoulder_rotation = HelperClass.getAngle(neckCoords.x * data_scale, neckCoords.y * data_scale, r_shoulderCoords.x * data_scale, r_shoulderCoords.y * data_scale);
        Model r_shoulder_model = modelBuilder.createCylinder(limbDiameter * scale, r_shoulder_length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances.set(17, new ModelInstance(r_shoulder_model));
        instances.get(17).transform.setToTranslation(neckCoords.x * data_scale - (0.5f * (neckCoords.x * data_scale - r_shoulderCoords.x * data_scale)), neckCoords.y * data_scale - (0.5f * (neckCoords.y * data_scale - r_shoulderCoords.y * data_scale)), 0f);
        instances.get(17).transform.rotate(Vector3.Z, r_shoulder_rotation - 90);

        float l_elbow_length = HelperClass.PythagorasTheorem(l_elbowCoords.x * data_scale, l_elbowCoords.y * data_scale, l_shoulderCoords.x * data_scale, l_shoulderCoords.y * data_scale);
        float l_elbow_rotation = HelperClass.getAngle(l_elbowCoords.x * data_scale, l_elbowCoords.y * data_scale, l_shoulderCoords.x * data_scale, l_shoulderCoords.y * data_scale);
        Model l_elbow_model = modelBuilder.createCylinder(limbDiameter * scale, l_elbow_length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances.set(18, new ModelInstance(l_elbow_model));
        instances.get(18).transform.setToTranslation(l_elbowCoords.x * data_scale - (0.5f * (l_elbowCoords.x * data_scale - l_shoulderCoords.x * data_scale)), l_elbowCoords.y * data_scale - (0.5f * (l_elbowCoords.y * data_scale - l_shoulderCoords.y * data_scale)), 0f);
        instances.get(18).transform.rotate(Vector3.Z, l_elbow_rotation - 90);

        float r_elbow_length = HelperClass.PythagorasTheorem(r_elbowCoords.x * data_scale, r_elbowCoords.y * data_scale, r_shoulderCoords.x * data_scale, r_shoulderCoords.y * data_scale);
        float r_elbow_rotation = HelperClass.getAngle(r_elbowCoords.x * data_scale, r_elbowCoords.y * data_scale, r_shoulderCoords.x * data_scale, r_shoulderCoords.y * data_scale);
        Model r_elbow_model = modelBuilder.createCylinder(limbDiameter * scale, r_elbow_length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances.set(19, new ModelInstance(r_elbow_model));
        instances.get(19).transform.setToTranslation(r_elbowCoords.x * data_scale - (0.5f * (r_elbowCoords.x * data_scale - r_shoulderCoords.x * data_scale)), r_elbowCoords.y * data_scale - (0.5f * (r_elbowCoords.y * data_scale - r_shoulderCoords.y * data_scale)), 0f);
        instances.get(19).transform.rotate(Vector3.Z, r_elbow_rotation - 90);

        float l_wrist_length = HelperClass.PythagorasTheorem(l_elbowCoords.x * data_scale, l_elbowCoords.y * data_scale, l_wristCoords.x * data_scale, l_wristCoords.y * data_scale);
        float l_wrist_rotation = HelperClass.getAngle(l_elbowCoords.x * data_scale, l_elbowCoords.y * data_scale, l_wristCoords.x * data_scale, l_wristCoords.y * data_scale);
        Model l_wrist_model = modelBuilder.createCylinder(limbDiameter * scale, l_wrist_length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances.set(20, new ModelInstance(l_wrist_model));
        instances.get(20).transform.setToTranslation(l_elbowCoords.x * data_scale - (0.5f * (l_elbowCoords.x * data_scale - l_wristCoords.x * data_scale)), l_elbowCoords.y * data_scale - (0.5f * (l_elbowCoords.y * data_scale - l_wristCoords.y * data_scale)), 0f);
        instances.get(20).transform.rotate(Vector3.Z, l_wrist_rotation - 90);
        DebugLog.log("l_wrist_x: " + l_wristCoords.x + "l_wrist_y: " + l_wristCoords.y);

        float r_wrist_length = HelperClass.PythagorasTheorem(r_elbowCoords.x * data_scale, r_elbowCoords.y * data_scale, r_wristCoords.x * data_scale, r_wristCoords.y * data_scale);
        float r_wrist_rotation = HelperClass.getAngle(r_elbowCoords.x * data_scale, r_elbowCoords.y * data_scale, r_wristCoords.x * data_scale, r_wristCoords.y * data_scale);
        Model r_wrist_model = modelBuilder.createCylinder(limbDiameter * scale, r_wrist_length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances.set(21, new ModelInstance(r_wrist_model));
        instances.get(21).transform.setToTranslation(r_elbowCoords.x * data_scale - (0.5f * (r_elbowCoords.x * data_scale - r_wristCoords.x * data_scale)), r_elbowCoords.y * data_scale - (0.5f * (r_elbowCoords.y * data_scale - r_wristCoords.y * data_scale)), 0f);
        instances.get(21).transform.rotate(Vector3.Z, r_wrist_rotation - 90);

        float waist_length = HelperClass.PythagorasTheorem(neckCoords.x * data_scale, neckCoords.y * data_scale, waistCoords.x * data_scale, waistCoords.y * data_scale);
        float waist_rotation = HelperClass.getAngle(neckCoords.x * data_scale, neckCoords.y * data_scale, waistCoords.x * data_scale, waistCoords.y * data_scale);
        Model waist_model = modelBuilder.createCylinder(limbDiameter * scale, waist_length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances.set(22, new ModelInstance(waist_model));
        instances.get(22).transform.setToTranslation(neckCoords.x * data_scale - (0.5f * (neckCoords.x * data_scale - waistCoords.x * data_scale)), neckCoords.y * data_scale - (0.5f * (neckCoords.y * data_scale - waistCoords.y * data_scale)), 0f);
        instances.get(22).transform.rotate(Vector3.Z, waist_rotation - 90);

        float l_hip_length = HelperClass.PythagorasTheorem(l_hipCoords.x * data_scale, l_hipCoords.y * data_scale, waistCoords.x * data_scale, waistCoords.y * data_scale);
        float l_hip_rotation = HelperClass.getAngle(l_hipCoords.x * data_scale, l_hipCoords.y * data_scale, waistCoords.x * data_scale, waistCoords.y * data_scale);
        Model l_hip_model = modelBuilder.createCylinder(limbDiameter * scale, l_hip_length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances.set(23, new ModelInstance(l_hip_model));
        instances.get(23).transform.setToTranslation(l_hipCoords.x * data_scale - (0.5f * (l_hipCoords.x * data_scale - waistCoords.x * data_scale)), l_hipCoords.y * data_scale - (0.5f * (l_hipCoords.y * data_scale - waistCoords.y * data_scale)), 0f);
        instances.get(23).transform.rotate(Vector3.Z, l_hip_rotation - 90);

        float r_hip_length = HelperClass.PythagorasTheorem(r_hipCoords.x * data_scale, r_hipCoords.y * data_scale, waistCoords.x * data_scale, waistCoords.y * data_scale);
        float r_hip_rotation = HelperClass.getAngle(r_hipCoords.x * data_scale, r_hipCoords.y * data_scale, waistCoords.x * data_scale, waistCoords.y * data_scale);
        Model r_hip_model = modelBuilder.createCylinder(limbDiameter * scale, r_hip_length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances.set(24, new ModelInstance(r_hip_model));
        instances.get(24).transform.setToTranslation(r_hipCoords.x * data_scale - (0.5f * (r_hipCoords.x * data_scale - waistCoords.x * data_scale)), r_hipCoords.y * data_scale - (0.5f * (r_hipCoords.y * data_scale - waistCoords.y * data_scale)), 0f);
        instances.get(24).transform.rotate(Vector3.Z, r_hip_rotation - 90);

        float l_knee_length = HelperClass.PythagorasTheorem(l_hipCoords.x * data_scale, l_hipCoords.y * data_scale, l_kneeCoords.x * data_scale, l_kneeCoords.y * data_scale);
        float l_knee_rotation = HelperClass.getAngle(l_hipCoords.x * data_scale, l_hipCoords.y * data_scale, l_kneeCoords.x * data_scale, l_kneeCoords.y * data_scale);
        Model l_knee_model = modelBuilder.createCylinder(limbDiameter * scale, l_knee_length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances.set(25, new ModelInstance(l_knee_model));
        instances.get(25).transform.setToTranslation(l_hipCoords.x * data_scale - (0.5f * (l_hipCoords.x * data_scale - l_kneeCoords.x * data_scale)), l_hipCoords.y * data_scale - (0.5f * (l_hipCoords.y * data_scale - l_kneeCoords.y * data_scale)), 0f);
        instances.get(25).transform.rotate(Vector3.Z, l_knee_rotation - 90);

        float r_knee_length = HelperClass.PythagorasTheorem(r_hipCoords.x * data_scale, r_hipCoords.y * data_scale, r_kneeCoords.x * data_scale, r_kneeCoords.y * data_scale);
        float r_knee_rotation = HelperClass.getAngle(r_hipCoords.x * data_scale, r_hipCoords.y * data_scale, r_kneeCoords.x * data_scale, r_kneeCoords.y * data_scale);
        Model r_knee_model = modelBuilder.createCylinder(limbDiameter * scale, r_knee_length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances.set(26, new ModelInstance(r_knee_model));
        instances.get(26).transform.setToTranslation(r_hipCoords.x * data_scale - (0.5f * (r_hipCoords.x * data_scale - r_kneeCoords.x * data_scale)), r_hipCoords.y * data_scale - (0.5f * (r_hipCoords.y * data_scale - r_kneeCoords.y * data_scale)), 0f);
        instances.get(26).transform.rotate(Vector3.Z, r_knee_rotation - 90);

        float l_foot_length = HelperClass.PythagorasTheorem(l_footCoords.x * data_scale, l_footCoords.y * data_scale, l_kneeCoords.x * data_scale, l_kneeCoords.y * data_scale);
        float l_foot_rotation = HelperClass.getAngle(l_footCoords.x * data_scale, l_footCoords.y * data_scale, l_kneeCoords.x * data_scale, l_kneeCoords.y * data_scale);
        Model l_foot_model = modelBuilder.createCylinder(limbDiameter * scale, l_foot_length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances.set(27, new ModelInstance(l_foot_model));
        instances.get(27).transform.setToTranslation(l_footCoords.x * data_scale - (0.5f * (l_footCoords.x * data_scale - l_kneeCoords.x * data_scale)), l_footCoords.y * data_scale - (0.5f * (l_footCoords.y * data_scale - l_kneeCoords.y * data_scale)), 0f);
        instances.get(27).transform.rotate(Vector3.Z, l_foot_rotation - 90);

        float r_foot_length = HelperClass.PythagorasTheorem(r_footCoords.x * data_scale, r_footCoords.y * data_scale, r_kneeCoords.x * data_scale, r_kneeCoords.y * data_scale);
        float r_foot_rotation = HelperClass.getAngle(r_footCoords.x * data_scale, r_footCoords.y * data_scale, r_kneeCoords.x * data_scale, r_kneeCoords.y * data_scale);
        Model r_foot_model = modelBuilder.createCylinder(limbDiameter * scale, r_foot_length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances.set(28, new ModelInstance(r_foot_model));
        instances.get(28).transform.setToTranslation(r_footCoords.x * data_scale - (0.5f * (r_footCoords.x * data_scale - r_kneeCoords.x * data_scale)), r_footCoords.y * data_scale - (0.5f * (r_footCoords.y * data_scale - r_kneeCoords.y * data_scale)), 0f);
        instances.get(28).transform.rotate(Vector3.Z, r_foot_rotation - 90);
    }
}
