package com.mygame;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector4f;
import com.jme3.renderer.RenderManager;
import com.mygame.Monkey2D.Context2D;
import com.mygame.Monkey2D.Keycodes;
import com.mygame.Monkey2D.Quad2D;

public class Slider {
    private Quad2D knob;
    private Quad2D bg;
    private Context2D ctx;

    private boolean mouseDown;

    public Slider(Context2D ctx) {
        this.ctx = ctx;
        this.bg = this.ctx.createQuad(new Vector4f(300, 100, 300, 10), new ColorRGBA(0, 1, 1, 1f));
        this.knob = this.ctx.createQuad(new Vector4f(300, 100, 20, 10), new ColorRGBA(1, 1, 1, 1f));
    }

    public void render(RenderManager r) {
        this.bg.render(r);
        this.knob.render(r);
    }

    public void handleLeftClick() {
        if (this.ctx.isKeyPressed(Keycodes.LEFT_MOUSE_BUTTON) && this.ctx.isMouseInRect(bg.getDim()) && !mouseDown) {
            mouseDown = true;
        }
        if (mouseDown) {
            knob.moveTo(this.ctx.getMousePos().x, knob.getDim().y);
        }

        if (!this.ctx.isKeyDown(Keycodes.LEFT_MOUSE_BUTTON) && mouseDown) {
            mouseDown = false;
        }
    }

    public void process() {
        handleLeftClick();
    }

    public void setColour(ColorRGBA c) {
        this.knob.setColour(c);
    }

}
