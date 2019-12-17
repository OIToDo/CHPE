package com.mygdx.game.Simulation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Analysis.Data;
import com.mygdx.game.DebugLog;
import com.mygdx.game.PoseEstimation.nn.MPI;

public class CubeAnalyse {
    float data_scale = -25;
    public Cube cube_left_hip = new Cube(new Vector3(-0.5555555f,0.2734378f,0.0f), 1f, 1f, 1f, Color.RED);
    public Cube cube_right_hip = new Cube(new Vector3(0.5555555f,0.2734378f,0.0f), 1f, 1f, 1f, Color.RED);
    public Cube cube_left_head = new Cube(new Vector3(-2.5555555f,4.5f,0.0f), 4f, 4f, 2f, Color.RED);
    public Cube cube_right_head = new Cube(new Vector3(0.5555555f,4.5f,0.0f), 4f, 4f, 2f, Color.RED);

    public CubeAnalyse(){}

    public Array<ModelInstance> renderables(){
        Array<ModelInstance> result = new Array<>();
        result.add(cube_left_hip.getInstance());
        result.add(cube_right_hip.getInstance());
        result.add(cube_left_head.getInstance());
//        result.add(cube_right_head.getInstance());
        return result;
    }

    public boolean isPointInBox(float x1, float y1, Cube cube){
        return (x1 >= cube.coords_.x && x1 <= cube.coords_.x + cube.width_) &&
                (y1 >= cube.coords_.y && y1 <= cube.coords_.y + cube.height_);
    }

    public boolean collision(Cube cube, Data data, MPI.body_part bp, int frame){
        Vector3 joint_data = new Vector3((data.getCoord(frame, bp).x * -data_scale - (data.getCoord(frame, MPI.body_part.values()[MPI.body_part.waist.ordinal()]).x * -data_scale)),
                (data.getCoord(frame, bp).y * data_scale - (data.getCoord(frame, MPI.body_part.values()[MPI.body_part.waist.ordinal()]).y * data_scale)),
                (data.getCoord(frame, bp).z * data_scale - (data.getCoord(frame, MPI.body_part.values()[MPI.body_part.waist.ordinal()]).z * data_scale)));

        DebugLog.log("knolraap: " + new Vector3((data.getCoord(frame, bp).x * -data_scale - (data.getCoord(frame, MPI.body_part.values()[MPI.body_part.waist.ordinal()]).x * -data_scale)),
                (data.getCoord(frame, bp).y * data_scale - (data.getCoord(frame, MPI.body_part.values()[MPI.body_part.waist.ordinal()]).y * data_scale)),
                (data.getCoord(frame, bp).z * data_scale - (data.getCoord(frame, MPI.body_part.values()[MPI.body_part.waist.ordinal()]).z * data_scale))));

        //knolraap: (-2.5347233,3.8476562,0.0)

        //todo separate =)
        return(cube.coords_.x < joint_data.x + 1f &&
                cube.coords_.x + cube.width_ > joint_data.x &&
                cube.coords_.y < joint_data.y + 1f &&
                cube.coords_.y + cube.height_ > joint_data.y);

//        return isPointInBox(joint_data.x, joint_data.y, cube);


    }

    public void update(Data data, int frame){
        if(collision(cube_left_head, data, MPI.body_part.values()[MPI.body_part.l_wrist.ordinal()], frame)){
            cube_left_head.setColor(Color.GREEN);
            DebugLog.log("collision: TRUE");
        } else {
            cube_left_head.setColor(Color.RED);
            DebugLog.log("collision: FALSE");
        }
    }
}
