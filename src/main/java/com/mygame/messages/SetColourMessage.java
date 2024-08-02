package com.mygame.messages;

import com.jme3.math.ColorRGBA;
import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class SetColourMessage extends AbstractMessage {
    private ColorRGBA c;

    public SetColourMessage() {
    }

    public SetColourMessage(ColorRGBA c) {
        this.c = c;
    }

    public ColorRGBA getVal() {
        return this.c;
    }
}