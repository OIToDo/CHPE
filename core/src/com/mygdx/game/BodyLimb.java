package com.mygdx.game;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class BodyLimb {
    public ModelInstance create(float x1, float y1, float x2, float y2, float z, float diameter){
        float length = HelperClass.PythagorasTheorem(x1, y1, x2, y2);
        float rotation = HelperClass.getAngle(x1, y1, x2, y2);
        System.out.println("Rotation: " + rotation);

        ModelBuilder modelBuilder = new ModelBuilder();
        Model model = modelBuilder.createCylinder(diameter, length, diameter, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        ModelInstance cylinder = new ModelInstance(model);
        cylinder.transform.setToTranslation(x1, y1, z);
        cylinder.transform.rotate(Vector3.Z, rotation - 90);

        return cylinder;
    }
}
