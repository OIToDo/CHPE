package com.mygdx.game.Analysis;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.DebugLog;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.PoseEstimation.nn.MPI.body_part;

public class Filter {
    Data data;

    public Filter(Data pData) {
        data = pData;
    }

    public void resolveZeros() {
        for(body_part bp : body_part.values()) {
            for(int f = 1; f < data.getFrameCount(); f++) {
                Vector3 previous = data.getCoord(f-1, bp);
                Vector3 current = data.getCoord(f, bp);
                if(previous.isZero() && current.isZero()) {
                    // TODO: pray this works, else just set it per component
                    current = previous;
                }
            }
        }
    }

    public void resolveWaist() {
        for(int f = 0; f < data.getFrameCount(); f++) {
            data.getCoord(f, body_part.waist).x = data.getCoord(f, body_part.l_hip).x +
                    Math.abs((data.getCoord(f, body_part.r_hip).x - data.getCoord(f, body_part.l_hip).x) / 2);
            data.getCoord(f, body_part.waist).y = data.getCoord(f, body_part.l_hip).y +
                    Math.abs((data.getCoord(f, body_part.r_hip).y - data.getCoord(f, body_part.l_hip).y) / 2);
        }
    }

    public void resolveNeck() {
        for(int f = 0; f < data.getFrameCount(); f++) {
            double neck_x = data.getCoord(f, body_part.l_shoulder).x +
                    Math.abs((data.getCoord(f, body_part.r_shoulder).x - data.getCoord(f, body_part.l_shoulder).x) / 2);
            double neck_y = data.getCoord(f, body_part.l_shoulder).y +
                    Math.abs((data.getCoord(f, body_part.r_shoulder).y - data.getCoord(f, body_part.l_shoulder).y) / 2);
            data.setX(f, body_part.neck, neck_x);
            data.setY(f, body_part.neck, neck_y);
        }
    }

    public void kernelFilter(double kernel[]) {
        int offset = kernel.length / 2;
        int sum = 0;
        for(double weight : kernel) {
            sum += weight;
        }

        for(body_part bp : body_part.values()) {
            for(int f = offset; f < data.getFrameCount()-offset; f++) {
                Vector2 acc = new Vector2(0, 0);
                for(int i = 0; i < kernel.length; i++) {
                    acc.x += kernel[i] * data.getCoord(f + (i = offset), bp).x;
                    acc.y += kernel[i] * data.getCoord(f + (i = offset), bp).y;
                }
                data.getCoord(f, bp).x = acc.x / sum;
                data.getCoord(f, bp).y = acc.y / sum;
            }
        }

    }

    
}
