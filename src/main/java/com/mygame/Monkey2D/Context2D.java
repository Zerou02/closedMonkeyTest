package com.mygame.Monkey2D;

import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;

public class Context2D {

    private AssetManager assetManager;
    private InputManager inputManager;
    private Matrix4f ortho2DProj;
    private Vector2f screenSize;
    private InputListener inputListener;

    public Context2D(Vector2f screenSize, AssetManager assetManager, InputManager inputManager) {
        this.assetManager = assetManager;
        this.screenSize = screenSize;
        this.inputManager = inputManager;
        this.ortho2DProj = this.calc2DProjMat(screenSize.x, screenSize.y);
        inputManager.clearMappings();
        inputManager.clearRawInputListeners();
        this.inputListener = new InputListener();
        inputManager.addRawInputListener(this.inputListener);

    }

    public Quad2D createQuad(Vector4f dim, ColorRGBA colour) {
        return new Quad2D(dim, this.assetManager, this.ortho2DProj, colour);
    }

    public Sprite2D createSprite(Vector4f dim) {
        return new Sprite2D(dim, this.assetManager, this.ortho2DProj);
    }

    public Matrix4f calc2DProjMat(float ww, float wh) {
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

    public Vector2f cartesianToSS(Vector2f pos) {
        return cartesianToSS(pos, new Vector2f(0, this.screenSize.y));
    }

    public Vector2f cartesianToSS(Vector2f pos, Vector2f cartesianOrigin) {
        return new Vector2f(pos.x - cartesianOrigin.x, cartesianOrigin.y - pos.y);
    }

    // ss-Coord
    public boolean isPointInRect(Vector2f p, Vector4f rect) {
        return p.x > rect.x && p.x < rect.x + rect.z && p.y > rect.y && p.y < rect.y + rect.w;
    }

    // ss-Coord
    public boolean isMouseInRect(Vector4f rect) {
        return isPointInRect(this.getMousePos(), rect);
    }

    // ss-Coord
    public Vector2f getMousePos() {
        return cartesianToSS(this.inputManager.getCursorPosition(), new Vector2f(0, this.screenSize.y));
    }

    public void process() {
        this.inputListener.process();
    }

    public boolean isKeyDown(Keycodes keycode) {
        return this.inputListener.isKeyDown(keycode);
    }

    public boolean isKeyPressed(Keycodes keycode) {
        return this.inputListener.isKeyPressed(keycode);
    }
}