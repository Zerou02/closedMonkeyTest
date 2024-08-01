package com.mygame;

import com.jme3.math.Matrix4f;

public class Utils {
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
};
