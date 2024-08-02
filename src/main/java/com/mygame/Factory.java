package com.mygame;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;
import com.jme3.scene.Node;

public class Factory {

    public static AssetManager manager;
    public static Matrix4f ortho2DProj;
    public static Vector2f screenSize;
    public static Node root;

    public static Quad createQuad(Vector4f dim, ColorRGBA colour) {
        return new Quad(dim, manager, ortho2DProj, colour, root);
    }
}
