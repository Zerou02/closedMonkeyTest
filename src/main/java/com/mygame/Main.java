package com.mygame;

import java.io.IOException;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;
import com.jme3.network.Client;
import com.jme3.network.Network;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;
import com.mygame.Monkey2D.Context2D;
import com.mygame.Monkey2D.Sprite2D;
import com.mygame.Monkey2D.Keycodes;
import com.mygame.messages.SetColourMessage;

public class Main extends SimpleApplication {

  Slider slider;
  Client client;
  ColourPicker picker;
  GameState state;
  Context2D ctx;
  SpriteHit60 spriteHit;

  public static void main(String[] args) {

    Main app = new Main();

    var settings = new AppSettings(true);
    float ww = 800;
    float wh = 600;

    settings.setResolution((int) ww, (int) wh);
    app.settings = settings;

    app.showSettings = false;
    app.setDisplayStatView(false);
    app.start();

  }

  @Override
  public void simpleInitApp() {
    this.ctx = new Context2D(new Vector2f(800, 600), this.assetManager, this.inputManager);

    this.state = new GameState();
    this.slider = new Slider(this.ctx);
    spriteHit = new SpriteHit60(ctx);

    try {
      client = Network.connectToServer("localhost", 6969);
      this.picker = new ColourPicker(new Vector2f(100, 200), this.ctx, client);
    } catch (IOException e) {
      e.printStackTrace();
    }
    client.addMessageListener(new ClientListener(state), SetColourMessage.class);
    client.start();

  }

  @Override
  public void simpleRender(RenderManager r) {
    this.slider.render(r);
    this.picker.render(r);
    this.spriteHit.render(r);
  }

  @Override
  public void simpleUpdate(float delta) {
    inputManager.setCursorVisible(true);
    if (ctx.isKeyPressed(Keycodes.ESCAPE)) {
      this.stop();
    }
    this.spriteHit.setSpeedModifier(this.slider.getPercentage());
    this.spriteHit.process(delta);
    this.slider.process();
    this.picker.process();
    this.slider.setColour(state.sliderColour);

    // Am Ende aufrufen
    ctx.process();
    // glfw.pollEvents() oder sowas wird wohl hier passieren
  }
}
