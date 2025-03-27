package paupedros.m8.uf3.actors;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

import paupedros.m8.uf3.helpers.AssetManager;
import paupedros.m8.uf3.utils.Methods;
import paupedros.m8.uf3.utils.Settings;

public class Asteroid extends Scrollable {
    private float runTime;

    public Asteroid(float x, float y, float width,
                    float height, float velocity) {
        super(x, y, width, height, velocity);
        runTime = Methods.randomFloat(0,1);
    }

    public float getRunTime() {
        return runTime;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        runTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.asteroidAnim.getKeyFrame(runTime), position.x, position.y, width, height);
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);

        // Generate a random size for the asteroid
        float newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID);
        width = height = 34 * newSize;

        // Validate that GAME_HEIGHT is greater than height
        if (Settings.GAME_HEIGHT <= height) {
            throw new IllegalArgumentException("GAME_HEIGHT must be greater than the asteroid's height");
        }

        // Calculate a random Y position within the screen bounds
        position.y = new Random().nextInt(Settings.GAME_HEIGHT - (int) height);
    }



}
