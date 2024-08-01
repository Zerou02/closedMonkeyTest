package com.mygame;

import com.jme3.asset.AssetManager;
import com.jme3.math.Matrix4f;
import com.jme3.math.Vector4f;

public class Factory {

    public static AssetManager manager;
    public static Matrix4f ortho2DProj;

    public static Quad createQuad(Vector4f dim) {
        return new Quad(dim, manager, ortho2DProj);
    }
}
