package com.mygame;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector4f;
import com.jme3.renderer.RenderManager;

public class Slider {
    Quad knob;
    Quad bg;

    public Slider() {
        this.bg = Factory.createQuad(new Vector4f(300, 100, 300, 10), new ColorRGBA(0, 1, 1, 1f));
        this.knob = Factory.createQuad(new Vector4f(300, 100, 20, 10), new ColorRGBA(1, 1, 1, 1f));
    }

    public void render(RenderManager r) {
        this.bg.render(r);
        this.knob.render(r);
    }

    public void handleLeftClick() {
        if (Utils.isMouseInRect(bg.getDim())) {
            knob.moveTo(Utils.ssMousePos.x, knob.getDim().y);
        }
    }

}
