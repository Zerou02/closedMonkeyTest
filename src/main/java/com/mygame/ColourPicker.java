package com.mygame;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;
import com.jme3.network.Client;
import com.jme3.renderer.RenderManager;
import com.mygame.Monkey2D.Context2D;
import com.mygame.Monkey2D.Keycodes;
import com.mygame.Monkey2D.Quad2D;
import com.mygame.messages.SetColourMessage;

public class ColourPicker {

    private Quad2D[] quads;
    private ColorRGBA currColour;
    private ColorRGBA[] colours;
    private Context2D ctx;
    private Client client;

    public ColourPicker(Vector2f base, Context2D ctx, Client client) {
        this.colours = new ColorRGBA[] { ColorRGBA.Red, ColorRGBA.Blue, ColorRGBA.Green };
        quads = new Quad2D[colours.length];
        var gap = 20f;
        this.ctx = ctx;
        this.client = client;

        for (int i = 0; i < colours.length; i++) {
            this.quads[i] = this.ctx.createQuad(new Vector4f(base.x, base.y + i * (50 + gap), 50, 50),
                    colours[i]);
        }
        this.currColour = colours[0];
    }

    private void handleLeftClick() {
        if (!this.ctx.isKeyPressed(Keycodes.LEFT_MOUSE_BUTTON)) {
            return;
        }
        for (int i = 0; i < quads.length; i++) {
            if (this.ctx.isMouseInRect(quads[i].getDim())) {
                currColour = this.colours[i];
                this.client.send(new SetColourMessage(this.currColour));
                break;
            }
        }
    }

    public void render(RenderManager r) {
        for (var quads : this.quads) {
            quads.render(r);
        }
    }

    public void process() {
        handleLeftClick();
    }

}
