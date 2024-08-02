package com.mygame;

import com.jme3.math.Matrix4f;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;
import com.jme3.scene.VertexBuffer;

public class Utils {

    public static Vector2f ssMousePos;

    public static Matrix4f calc2DProjMat(float ww, float wh) {
        float right = ww;
        float left = 0;
        float top = 0;
        float bottom = wh;
        float near = -1;
        float far = 1;

        // row-major
        return new Matrix4f(
                2f / (right - left), 0, 0, -(right + left) / (right - left),
                0, 2f / (top - bottom), 0, -(top + bottom) / (top - bottom),
                0, 0, -2f / (far - near), -(far + near) / (far - near),
                0, 0, 0, 1);
    }

    public static Vector2f CartesianToSS(Vector2f pos) {
        return CartesianToSS(pos, new Vector2f(0, Factory.screenSize.y));
    }

    public static Vector2f CartesianToSS(Vector2f pos, Vector2f cartesianOrigin) {
        return new Vector2f(pos.x - cartesianOrigin.x, cartesianOrigin.y - pos.y);
    }

    // ss-Coord
    public static boolean isPointInRect(Vector2f p, Vector4f rect) {
        return p.x > rect.x && p.x < rect.x + rect.z && p.y > rect.y && p.y < rect.y + rect.w;
    }

    // ss-Coord
    public static boolean isMouseInRect(Vector4f rect) {
        return isPointInRect(ssMousePos, rect);
    }

    public void printBuffer(VertexBuffer buffer) {
        for (int i = 0; i < buffer.getNumElements(); i++) {
            for (int j = 0; j < buffer.getNumComponents(); j++) {
                System.out.print(buffer.getElementComponent(i, j) + ",");
            }
            System.err.println();
        }
    }
};
