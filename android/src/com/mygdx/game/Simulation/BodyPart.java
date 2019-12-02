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
    Vector3 coords_;
    float size_, rotation_, data_scale_;
    Color color_;
    ModelInstance sphere_;
    ModelBuilder modelBuilder_ = new ModelBuilder();
    Model model_;

    public BodyPart(Vector3 coords, float size, Color color){
        coords_ = coords;
        size_ = size;
        color_ = color;
        model_ = modelBuilder_.createSphere(size, size, size, 20, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        sphere_ = new ModelInstance(model_);
        sphere_.transform.setToTranslation(coords_);
        sphere_.transform.rotate(Vector3.Z, rotation_);
        sphere_.materials.get(0).set(ColorAttribute.createDiffuse(color_));
    }

//    public ModelInstance create(float x, float y, float z, float size, Vector3 axis, float rotation, Color color){
//        x_ = x;
//        y_ = y;
//        z_ = z;
//        size_ = size;
//        rotation_ = rotation;
//        axis_ = axis;
//        Model model = modelBuilder.createSphere(size, size, size, 20, 20,
//                new Material(),
//                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
//        sphere = new ModelInstance(model);
//        sphere.transform.setToTranslation(x, y, z);
//        sphere.transform.rotate(axis, rotation);
//        sphere.materials.get(0).set(ColorAttribute.createDiffuse(color));
//
//        return sphere;
//    }

    public void change_color(Color color){
        color_ = color;
        sphere_.materials.get(0).set(ColorAttribute.createDiffuse(color_));
    }

    public void set_scale(float data_scale){
        data_scale_ = data_scale;
        sphere_.transform.scale(data_scale_, data_scale_, data_scale_);
    }

    public ModelInstance getInstance(){
        return sphere_;
    }

    public void update(Vector3 coords){
        coords_ = coords;
        sphere_.transform.setToTranslation(coords_);
    }
}