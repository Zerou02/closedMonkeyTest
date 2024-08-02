package com.mygame;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;
import com.jme3.network.Client;
import com.mygame.messages.SetColourMessage;

public class ColourPicker {

    Quad[] quads;
    Vector2f base;
    ColorRGBA currColour;
    ColorRGBA[] colours;

    public ColourPicker(Vector2f base) {
        this.colours = new ColorRGBA[] { ColorRGBA.Red, ColorRGBA.Blue, ColorRGBA.Green };
        this.base = base;
        quads = new Quad[colours.length];
        var gap = 20f;
        for (int i = 0; i < colours.length; i++) {
            this.quads[i] = Factory.createQuad(new Vector4f(base.x, base.y + i * (50 + gap), 50, 50),
                    colours[i]);
        }
        this.currColour = colours[0];
    }

    public void handleLeftClick(Client client) {
        for (int i = 0; i < quads.length; i++) {
            if (Utils.isMouseInRect(quads[i].getDim())) {
                currColour = this.colours[i];
                client.send(new SetColourMessage(this.currColour));
                break;
            }
        }
    }
}
