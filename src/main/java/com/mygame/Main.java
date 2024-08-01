package com.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;
import com.jme3.scene.VertexBuffer;
import com.jme3.system.AppSettings;

public class Main extends SimpleApplication {

  Quad testQuad;
  Vector2f vel = new Vector2f(10, 10);
  float speed = 10.0f;

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

    System.out.println(app.assetManager);
  }

  @Override
  public void simpleInitApp() {
    Factory.manager = assetManager;
    Factory.ortho2DProj = Utils.calc2DProjMat(800, 600);

    this.testQuad = Factory.createQuad(new Vector4f(0, 0, 100, 100));

    rootNode.attachChild(testQuad.quad);
  }

  @Override
  public void simpleUpdate(float delta) {
    var newPos = testQuad.dim.add(new Vector4f(vel.x * delta * speed, vel.y * delta * speed, 0, 0));
    if (newPos.x + testQuad.dim.z > 800 || newPos.x < 0) {
      vel.x *= -1;
    }
    if (newPos.y < 0 || newPos.y + testQuad.dim.w > 600) {
      vel.y *= -1;
    }
    testQuad.move(vel.x, vel.y);
  }

  public void printBuffer(VertexBuffer buffer) {
    for (int i = 0; i < buffer.getNumElements(); i++) {
      for (int j = 0; j < buffer.getNumComponents(); j++) {
        System.out.print(buffer.getElementComponent(i, j) + ",");
      }
      System.err.println();
    }
  }
}
