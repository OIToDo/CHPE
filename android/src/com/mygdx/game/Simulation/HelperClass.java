package com.mygdx.game.Simulation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

public class HelperClass {
    private static ShapeRenderer debugRenderer = new ShapeRenderer();

    public static void DrawDebugLine(Vector2 start, Vector2 end, int lineWidth, Color color, Matrix4 projectionMatrix)
    {
        Gdx.gl.glLineWidth(lineWidth);
        debugRenderer.setProjectionMatrix(projectionMatrix);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.setColor(color);
        debugRenderer.line(start, end);
        debugRenderer.end();
        Gdx.gl.glLineWidth(1);
    }

    public static float PythagorasTheorem(float x1, float y1, float x2, float y2){
        double side1 = Math.abs(x1-x2);
        double side2 = Math.abs(y1-y2);
        double length = Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2));
        return (float)length;
    }

    public static float getAngle(float x1, float y1, float x2, float y2) {
        float angle = (float) Math.toDegrees(Math.atan2(y1 - y2, x1 - x2));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }
}
