package com.mygdx.game.Simulation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class BodyLimb {
    Vector2 coords1_ , coords2_;
    float limbDiameter_, length_, rotation_, z_, scale_, data_scale_;
    Color color_;
    ModelInstance cylinder_;
    ModelBuilder modelBuilder_ = new ModelBuilder();
    Model model_;

    public BodyLimb(Vector2 coords1, Vector2 coords2, float limbdiameter, float z, Color color){
        coords1_ = coords1;
        coords2_ = coords2;
        z_ = z;
        limbDiameter_ = limbdiameter;
        length_ = HelperClass.PythagorasTheorem(coords1_, coords2_);
        System.out.println("length" + length_);
        rotation_ = HelperClass.getAngle(coords1_, coords2_);
        color_ = color;
        model_ = modelBuilder_.createCylinder(limbDiameter_, length_, limbDiameter_, 20,
                new Material(),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        cylinder_ = new ModelInstance(model_);
        cylinder_.transform.setToTranslation(coords1_.x - (0.5f * (coords1_.x - coords2_.x)), coords1_.y - (0.5f * (coords1_.y - coords2_.y)), z_);
        cylinder_.transform.rotate(Vector3.Z, rotation_ - 90);
        cylinder_.materials.get(0).set(ColorAttribute.createDiffuse(color_));
    }

//    public ModelInstance create(float x1, float y1, float x2, float y2, float z, float limbDiameter){
//        x_1 = x1;
//        x_2 = x2;
//        y_1 = y1;
//        y_2 = y2;
//        z_ = z;
//        float length = HelperClass.PythagorasTheorem(x1, y1, x2, y2);
//        float rotation = HelperClass.getAngle(x1, y1, x2, y2);
//        Model model = modelBuilder.createCylinder(limbDiameter, length, limbDiameter, 20,
//                new Material(),
//                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
//        cylinder = new ModelInstance(model);
//        cylinder.transform.setToTranslation(x1 - (0.5f * (x1 - x2)), y1 - (0.5f * (y1 - y2)), z);
//        cylinder.transform.rotate(Vector3.Z, rotation - 90);
//
//        return cylinder;
//    }

    public void change_color(Color color){
        color_ = color;
        cylinder_.materials.get(0).set(ColorAttribute.createDiffuse(color_));
    }

//    public void set_scale(float data_scale){
//        data_scale_ = data_scale;
//        cylinder_.transform.scale(data_scale_, data_scale_, data_scale_);
//    }

    public ModelInstance getInstance(){
        return cylinder_;
    }

    public void update(Vector2 coords1, Vector2 coords2, float z){
        coords1_ = coords1;
        coords2_ = coords2;
        z_ = z;
        float new_length = HelperClass.PythagorasTheorem(coords1, coords2);
        scale_ = new_length / length_;
        length_ = new_length;
        rotation_ = HelperClass.getAngle(coords1_, coords2_);
        System.out.println("coords1: " + coords1_ + "coords2: " + coords2);
        cylinder_.transform.setToTranslation(coords1_.x - (0.5f * (coords1_.x - coords2_.x)), coords1_.y - (0.5f * (coords1_.y - coords2_.y)), z_);
        cylinder_.transform.rotate(Vector3.Z, rotation_ - 90);
        cylinder_.transform.scale(1f,scale_, 1f);
    }
}
