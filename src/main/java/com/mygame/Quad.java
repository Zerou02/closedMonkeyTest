package com.mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;
import com.jme3.scene.Geometry;
import com.jme3.scene.VertexBuffer.Type;

public class Quad {
    Matrix4f projMat;
    Vector4f dim;
    Geometry quad;

    public Quad(Vector4f dim, AssetManager manager, Matrix4f projection) {
        this.projMat = projection;
        this.dim = dim;
        var mesh = new com.jme3.scene.shape.Quad(dim.z, dim.w);
        this.quad = new Geometry("Box", mesh);
        Material mat = new Material(manager, "MatDefs/quad.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        mat.setMatrix4("proj", this.projMat);
        System.out.println(this.projMat);
        quad.setMaterial(mat);

        clearBuffer();
        setVertices();
    }

    public void move(float x, float y) {
        this.dim.x += x;
        this.dim.y += y;
        setVertices();
    }

    private void clearBuffer() {

        quad.getMesh().clearBuffer(Type.Index);
        quad.getMesh().clearBuffer(Type.TexCoord);
        quad.getMesh().clearBuffer(Type.Position);
        quad.getMesh().clearBuffer(Type.Normal);
    }

    private void setVertices() {
        var leftUp = new Vector2f(dim.x, dim.y);
        var leftDown = new Vector2f(dim.x, dim.y + dim.w);
        var rightUp = new Vector2f(dim.x + dim.z, dim.y);
        var rightDown = new Vector2f(dim.x + dim.z, dim.y + dim.w);
        // ccw order
        quad.getMesh().setBuffer(Type.Position, 2,
                new float[] {
                        leftUp.x, leftUp.y, // links oben
                        rightDown.x, rightDown.y, // rechts unten
                        rightUp.x, rightUp.y, // rechts oben
                        rightDown.x, rightDown.y, // rechts unten
                        leftUp.x, leftUp.y, // links oben
                        leftDown.x, leftDown.y, // links unten
                });
    }
}
