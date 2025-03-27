package paupedros.m8.uf3.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import paupedros.m8.uf3.helpers.AssetManager;
import paupedros.m8.uf3.utils.Settings;

public class Spacecraft extends Actor {
    // Diferents posicions de l'SpaceCraft: recte, pujant i baixant
    public static final int SPACECRAFT_STRAIGHT = 0;
    public static final int SPACECRAFT_UP = 1;
    public static final int SPACECRAFT_DOWN = 2;

    // Parametres de l'Spacecraft
    private Vector2 position;
    private int width, height;
    private int direction;

    public Spacecraft(float x, float y, int width, int height) {
        // Inicialitzem els arguments segons la crida del constructor
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        // Inicialitzem l'Spacecraft a l'estat normal
        direction = SPACECRAFT_STRAIGHT;
    }

    public void act(float delta) {
        // Movem l'Spacecraft depenent la direccio controlant que no surti de la pantalla
        switch (direction) {
            case SPACECRAFT_UP:
                if (this.position.y - Settings.SPACECRAFT_VELOCITY * delta >= 0) {
                    this.position.y -= Settings.SPACECRAFT_VELOCITY * delta;
                }
                break;
            case SPACECRAFT_DOWN:
                if (this.position.y + height + Settings.SPACECRAFT_VELOCITY * delta <= Settings.GAME_HEIGHT) {
                    this.position.y += Settings.SPACECRAFT_VELOCITY * delta;
                }
                break;
            case SPACECRAFT_STRAIGHT:
                break;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.spacecraft, position.x, position.y, width, height);
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }


    public void goUp() {
        direction = SPACECRAFT_UP;
    }

    public void goDown() {
        direction = SPACECRAFT_DOWN;
    }

    public void goStraight() {
        direction = SPACECRAFT_STRAIGHT;
    }
}
