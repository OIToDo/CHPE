package com.mygdx.game;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class BodyPart {
    public float x, y, z;

    public ModelInstance create(float xPos, float yPos, float zPos, float size, Vector3 axis, float rotation, Model model){

        x = xPos;
        y = yPos;
        z = zPos;

        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createSphere(size, size, size, 20, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        ModelInstance sphere = new ModelInstance(model);
        sphere.transform.setToTranslation(x, y, z);
        sphere.transform.rotate(axis, rotation);

        return sphere;
    }
}
