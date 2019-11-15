package com.mygdx.game.Simulation;

import android.view.Display;

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
import com.mygdx.game.PoseEstimation.nn.MPI.body_part;

import java.util.HashMap;

import static com.mygdx.game.PoseEstimation.nn.PoseModel.POSE_PAIRS;
import static com.mygdx.game.Simulation.HelperClass.vec3Subtraction;

public class Body {
    public BodyPart bodyPart;
    public BodyLimb bodyLimb;

    public float scale;
    public float limbDiameter = 1f;
    public float jointDiameter = 1f;

    public HashMap<body_part, ModelInstance> jointMap = new HashMap();
    public Array<ModelInstance> limbArray = new Array<>();

    // Prevent from creating a copy upon every time this method is called.
    private static final int body_part_size = body_part.values().length;

    ModelBuilder modelBuilder = new ModelBuilder();

    public Array<Vector3> jointCoords = new Array<>();
    //Joint coords


    public void create(float x, float y, float z, float scaleInstance){
        scale = scaleInstance;
        Array<ModelInstance> BodyPartInstances = new Array<>();
        bodyPart = new BodyPart();
        bodyLimb = new BodyLimb();

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
            jointMap.put(bp, bodyPart.create(0f,0f,0f, jointDiameter * scale, Vector3.Z, 0, Color.YELLOW));
        }
        jointMap.get(body_part.head).materials.get(0).set(ColorAttribute.createDiffuse(Color.GREEN));

        //Fill JointCoords with al the needed arrays for the separate joints
        for(int i = 0; i < body_part_size; i++){
            jointCoords.add(new Vector3(0f,0f,0f));
        }

        // BodyLimbs -------------------------------------------------------------------------------------------------------------------|
        for (int[] pp : POSE_PAIRS){
            limbArray.add(bodyLimb.create(jointCoords.get(pp[0]).x, jointCoords.get(pp[0]).y, jointCoords.get(pp[1]).x, jointCoords.get(pp[1]).y, 0, limbDiameter * scale));
        }
    }

    public Array<ModelInstance> getLimbArray(){
        return limbArray;
    }

    public Array<ModelInstance> getJointArray(){
        Array<ModelInstance> result = new Array<>();
        for(ModelInstance jv : jointMap.values()){
            result.add(jv);
        }
        return result;
    }

    public void create_limb(Vector3 joint_1, Vector3 joint_2, float ds, int index){
        float data_scale = ds;
        float length = HelperClass.PythagorasTheorem(joint_1.x * data_scale, joint_1.y * data_scale, joint_2.x * data_scale, joint_2.y * data_scale);
        float rotation = HelperClass.getAngle(joint_1.x * data_scale, joint_1.y * data_scale, joint_2.x * data_scale, joint_2.y * data_scale);
        Model model = modelBuilder.createCylinder(limbDiameter * scale, length, limbDiameter * scale, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        limbArray.set(index, new ModelInstance(model));
        limbArray.get(index).transform.setToTranslation(-joint_1.x * data_scale - (0.5f * (-joint_1.x * data_scale - -joint_2.x * data_scale)),
                joint_1.y * data_scale - (0.5f * (joint_1.y * data_scale - joint_2.y * data_scale)),
                0f);
        limbArray.get(index).transform.rotate(Vector3.Z, -rotation - 90);
        limbArray.get(index).materials.get(0).set(ColorAttribute.createDiffuse(Color.YELLOW));
    }

    public void update(int frame, Data data){
        float data_scale = -25f;

        //update joints -------------------------------------------------------------------------------------------------|
        for (int i = 0; i < jointCoords.size; i++){
            jointCoords.set(i, vec3Subtraction(data.getCoord(frame, body_part.values()[i]), data.getCoord(frame, body_part.values()[body_part.waist.ordinal()])));
            jointMap.get(body_part.values()[i]).transform.setToTranslation((
                            (jointCoords.get(i).x * -data_scale) - (jointCoords.get(body_part.waist.ordinal()).x * -data_scale)),
                    (jointCoords.get(i).y * data_scale) - (jointCoords.get(body_part.waist.ordinal()).y * data_scale),
                    (jointCoords.get(i).z * data_scale) - (jointCoords.get(body_part.waist.ordinal()).z * data_scale));
        }

        //update limbs --------------------------------------------------------------------------------------------------|
        int limbAmount = 0;
        for (int[] pp : POSE_PAIRS){
            create_limb(jointCoords.get(pp[0]), jointCoords.get(pp[1]),-25,limbAmount);
            limbAmount++;
        }
        jointMap.get(body_part.head).transform.scale(3f,3f,3f);
    }
}
