package com.mygdx.game.Analysis;

import com.mygdx.game.DebugLog;
import com.badlogic.gdx.math.Vector2;
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
                if(!previous.isZero() && current.isZero()) {
                    data.setX(f, bp, previous.x);
                    data.setY(f, bp, previous.y);
                }
            }
        }
    }

    public void averageOf(body_part toUpdate, body_part left, body_part right) {
        for(int f = 0; f < data.getFrameCount(); f++) {
            double x = data.getCoord(f, left).x +
                    Math.abs((data.getCoord(f, right).x - data.getCoord(f, left).x) / 2);

            double y = data.getCoord(f, left).y +
                    Math.abs((data.getCoord(f, right).y - data.getCoord(f, left).y) / 2);

            data.setX(f, toUpdate, x);
            data.setY(f, toUpdate, y);
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
                // TODO: right now it's filtering in-place which gives weird results, pls fix
                data.setX(f, bp, acc.x / sum);
                data.setY(f, bp, acc.y / sum);
            }
        }

    }

    
}
