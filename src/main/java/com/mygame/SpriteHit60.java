package com.mygame;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;
import com.jme3.renderer.RenderManager;
import com.mygame.Monkey2D.Context2D;
import com.mygame.Monkey2D.Keycodes;
import com.mygame.Monkey2D.Sprite2D;

public class SpriteHit60 {
    private Sprite2D sprite;
    private Context2D ctx;
    private float speedModifier = 0.5f;
    private Vector2f vel = new Vector2f(0.3f, 0.4f);

    public SpriteHit60(Context2D ctx) {
        this.ctx = ctx;
        this.sprite = ctx.createSprite(new Vector4f(300, 300, 100, 100));
    }

    public void setSpeedModifier(float x) {
        this.speedModifier = x;
    }

    public void process(float delta) {
        var speed = 1000.0f * this.speedModifier * delta;
        if (ctx.isKeyDown(Keycodes.W)) {
            this.sprite.move(0, -speed);
        }
        if (ctx.isKeyDown(Keycodes.S)) {
            this.sprite.move(0, speed);
        }
        if (ctx.isKeyDown(Keycodes.D)) {
            this.sprite.move(speed, 0);
        }
        if (ctx.isKeyDown(Keycodes.A)) {
            this.sprite.move(-speed, 0);
        }
        vel = vel.normalize();
        this.sprite.move(vel.x * speed, vel.y * speed);
        if (this.sprite.getDim().x <= 0 || this.sprite.getDim().x + this.sprite.getDim().z >= 800) {
            this.vel.x *= -1;
        }
        if (this.sprite.getDim().y <= 0 || this.sprite.getDim().y + this.sprite.getDim().w >= 600) {
            this.vel.y *= -1;
        }
    }

    public void render(RenderManager r) {
        this.sprite.render(r);
    }
}
