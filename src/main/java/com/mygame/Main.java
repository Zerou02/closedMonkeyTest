package com.mygame;

import java.io.IOException;

import com.jme3.app.SimpleApplication;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;
import com.jme3.network.Client;
import com.jme3.network.Network;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;
import com.mygame.messages.SetColourMessage;

public class Main extends SimpleApplication {

  Slider slider;
  Client client;
  ColourPicker picker;
  GameState state;

  public static void main(String[] args) {

    Main app = new Main();

    var settings = new AppSettings(true);
    float ww = 800;
    float wh = 600;
    Factory.screenSize = new Vector2f(800, 600);
    settings.setResolution((int) ww, (int) wh);
    app.settings = settings;

    app.showSettings = false;

    app.setDisplayStatView(false);

    app.start();

    System.out.println(app.assetManager);
  }

  @Override
  public void simpleInitApp() {

    this.cam.setProjectionMatrix(null);
    cam.setParallelProjection(true);
    Factory.root = rootNode;
    Factory.manager = assetManager;
    Factory.ortho2DProj = Utils.calc2DProjMat(Factory.screenSize.x, Factory.screenSize.y);
    this.state = new GameState();

    this.slider = new Slider();
    this.picker = new ColourPicker(new Vector2f(100, 200));
    try {
      client = Network.connectToServer("localhost", 6969);
    } catch (IOException e) {
      e.printStackTrace();
    }
    client.addMessageListener(new ClientListener(state), SetColourMessage.class);
    client.start();

    initKeys();
  }

  private ActionListener actionListener = new ActionListener() {
    @Override
    public void onAction(String name, boolean keyPressed, float delta) {
      if (name.equals("LeftClick")) {
        slider.handleLeftClick();
        picker.handleLeftClick(client);
      }
    }
  };

  @Override
  public void simpleRender(RenderManager r) {
    this.slider.render(r);
    picker.render(r);
  }

  private void initKeys() {
    inputManager.addMapping("LeftClick", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    inputManager.addListener(actionListener, "LeftClick");
  }

  @Override
  public void simpleUpdate(float delta) {
    Utils.ssMousePos = Utils.CartesianToSS(inputManager.getCursorPosition());
    inputManager.setCursorVisible(true);
    this.slider.knob.setColour(state.sliderColour);
  }

}
