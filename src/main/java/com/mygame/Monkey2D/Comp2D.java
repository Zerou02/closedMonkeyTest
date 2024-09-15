package com.mygame.Monkey2D;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Vector4f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;

public abstract class Comp2D {

    protected Vector4f dim;
    protected Matrix4f projMat;
    protected Geometry geometry;
    protected AssetManager manager;

    Comp2D(Vector4f dim, AssetManager manager, Matrix4f proj) {
        this.dim = dim;
        this.projMat = proj;
        this.manager = manager;
        this.geometry = new Geometry();

        var mesh = new com.jme3.scene.shape.Quad(dim.z, dim.w);
        clearBuffer(mesh);
        this.geometry.setMesh(mesh);
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
        this.geometry.getMaterial().setColor("colour", colour);
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
        r.renderGeometry(this.geometry);
    }

}
