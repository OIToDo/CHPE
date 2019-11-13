package com.mygdx.game.Simulation;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class BodyLimb {
    public float x_1, x_2, y_1, y_2, z_;
    ModelInstance cylinder;
    ModelBuilder modelBuilder = new ModelBuilder();
    public ModelInstance create(float x1, float y1, float x2, float y2, float z, float limbDiameter){
        x_1 = x1;
        x_2 = x2;
        y_1 = y1;
        y_2 = y2;
        z_ = z;
        float length = HelperClass.PythagorasTheorem(x1, y1, x2, y2);
        float rotation = HelperClass.getAngle(x1, y1, x2, y2);
        Model model = modelBuilder.createCylinder(limbDiameter, length, limbDiameter, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        cylinder = new ModelInstance(model);
        cylinder.transform.setToTranslation(x1 - (0.5f * (x1 - x2)), y1 - (0.5f * (y1 - y2)), z);
        cylinder.transform.rotate(Vector3.Z, rotation - 90);

        return cylinder;
    }

    public void update(float x1, float x2, float y1, float y2, float z, float limbDiameter){
        x_1 = x1;
        x_2 = x2;
        y_1 = y1;
        y_2 = y2;
        z_ = z;
        float length = HelperClass.PythagorasTheorem(x1, y1, x2, y2);
        float rotation = HelperClass.getAngle(x1, y1, x2, y2);
        Model model = modelBuilder.createCylinder(limbDiameter, length, limbDiameter, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        cylinder = new ModelInstance(model);
        cylinder.transform.setToTranslation(x1 - (0.5f * (x1 - x2)), y1 - (0.5f * (y1 - y2)), z);
        cylinder.transform.rotate(Vector3.Z, rotation - 90);
    }
}
