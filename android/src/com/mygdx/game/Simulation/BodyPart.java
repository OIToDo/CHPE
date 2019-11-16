package com.mygdx.game.Simulation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class BodyPart {
    // todo vec3 enzo. =)
    float x_, y_, z_, size_, rotation_;
    Vector3 axis_;
    ModelInstance sphere;
    ModelBuilder modelBuilder = new ModelBuilder();
    public ModelInstance create(float x, float y, float z, float size, Vector3 axis, float rotation, Color color){
        x_ = x;
        y_ = y;
        z_ = z;
        size_ = size;
        rotation_ = rotation;
        axis_ = axis;
        Model model = modelBuilder.createSphere(size, size, size, 20, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        sphere = new ModelInstance(model);
        sphere.transform.setToTranslation(x, y, z);
        sphere.transform.rotate(axis, rotation);
        sphere.materials.get(0).set(ColorAttribute.createDiffuse(color));

        return sphere;
    }

    public void update(float x, float y, float z, float size, Vector3 axis, float rotation){
        x_ = x;
        y_ = y;
        z_ = z;
        size_ = size;
        rotation_ = rotation;
        axis_ = axis;
        Model model = modelBuilder.createSphere(size, size, size, 20, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        sphere = new ModelInstance(model);
        sphere.transform.setToTranslation(x, y, z);
        sphere.transform.rotate(axis, rotation);
    }
}
