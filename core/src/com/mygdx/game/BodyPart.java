package com.mygdx.game;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class BodyPart {
    public ModelInstance create(float x, float y, float z, float size, Vector3 axis, float rotation, Model model){

        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createSphere(size, size, size, 20, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        ModelInstance cylinder = new ModelInstance(model);
        cylinder.transform.setToTranslation(x, y, z);
        cylinder.transform.rotate(axis, rotation);

        return cylinder;
    }
}
