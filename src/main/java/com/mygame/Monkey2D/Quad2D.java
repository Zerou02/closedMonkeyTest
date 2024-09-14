package com.mygame.Monkey2D;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;
import com.jme3.scene.VertexBuffer.Type;

public class Quad2D extends Comp2D {
    public Quad2D(Vector4f dim, AssetManager manager, Matrix4f proj, ColorRGBA colour) {
        super(dim, manager, proj, colour);

        var mesh = new com.jme3.scene.shape.Quad(dim.z, dim.w);
        this.clearBuffer(mesh);
        super.setMesh(mesh);

        Material mat = new Material(manager, "MatDefs/quad.j3md");
        mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        mat.getAdditionalRenderState().setDepthWrite(false);
        mat.setColor("colour", new ColorRGBA(colour));
        mat.setMatrix4("proj", this.projMat);
        super.setMaterial(mat);

        this.setVertices();
    }

    @Override
    protected void setVertices() {
        var leftUp = new Vector2f(dim.x, dim.y);
        var leftDown = new Vector2f(dim.x, dim.y + dim.w);
        var rightUp = new Vector2f(dim.x + dim.z, dim.y);
        var rightDown = new Vector2f(dim.x + dim.z, dim.y + dim.w);
        // ccw order
        getMesh().setBuffer(Type.Position, 2,
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
