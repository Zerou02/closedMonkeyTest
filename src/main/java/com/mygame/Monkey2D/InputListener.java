package com.mygame.Monkey2D;

import java.util.ArrayList;
import java.util.HashMap;

import com.jme3.input.RawInputListener;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;

public class InputListener implements RawInputListener {

    private HashMap<Integer, Boolean> keysDown = new HashMap<Integer, Boolean>();
    private ArrayList<Integer> keysThisFramePressed = new ArrayList<Integer>();

    private final int[] mouseBtnOffsets;

    public InputListener() {
        this.mouseBtnOffsets = new int[] { Keycodes.LEFT_MOUSE_BUTTON.getKeyCode(),
                Keycodes.RIGHT_MOUSE_BUTTON.getKeyCode() };
    }

    @Override
    public void beginInput() {
    }

    @Override
    public void endInput() {
    }

    @Override
    public void onJoyAxisEvent(JoyAxisEvent arg0) {
    }

    @Override
    public void onJoyButtonEvent(JoyButtonEvent arg0) {
    }

    @Override
    public void onKeyEvent(KeyInputEvent ev) {
        if (ev.isRepeating()) {
            return;
        }
        if (ev.isPressed()) {
            this.keysThisFramePressed.add(ev.getKeyCode());
        }
        keysDown.put(ev.getKeyCode(), ev.isPressed());
    }

    @Override
    public void onMouseButtonEvent(MouseButtonEvent ev) {
        if (ev.getButtonIndex() >= 2) {
            return;
        }
        var mouseIdx = this.mouseBtnOffsets[ev.getButtonIndex()];
        if (ev.isPressed()) {
            this.keysThisFramePressed.add(mouseIdx);
        }
        keysDown.put(mouseIdx, ev.isPressed());
    }

    @Override
    public void onMouseMotionEvent(MouseMotionEvent arg0) {
    }

    @Override
    public void onTouchEvent(TouchEvent arg0) {
    }

    public void process() {
        keysThisFramePressed.clear();
    }

    public boolean isKeyDown(Keycodes keycode) {
        return this.keysDown.getOrDefault(keycode.getKeyCode(), false);
    }

    public boolean isKeyPressed(Keycodes keycode) {
        return this.keysThisFramePressed.contains(keycode.getKeyCode());
    }
}
