package com.mygame.Monkey2D;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Vector4f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;

public abstract class Comp2D extends Geometry {

    Vector4f dim;
    Matrix4f projMat;

    Comp2D(Vector4f dim, AssetManager manager, Matrix4f proj, ColorRGBA colour) {
        this.dim = dim;
        this.projMat = proj;
    }

    public void move(float x, float y) {
        this.dim.x += x;
        this.dim.y += y;
        setVertices();
    }

    public void moveTo(float x, float y) {
        this.dim.x = x;
        this.dim.y = y;
        setVertices();
    }

    public void setColour(ColorRGBA colour) {
        this.getMaterial().setColor("colour", colour);
    }

    protected void clearBuffer(Mesh mesh) {
        for (var x : mesh.getBufferList()) {
            mesh.clearBuffer(x.getBufferType());
        }
    }

    protected abstract void setVertices();

    public Vector4f getDim() {
        return this.dim.clone();
    }

    public void render(RenderManager r) {
        r.renderGeometry(this);
    }

}
