package paupedros.m8.uf3.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;

import java.util.Random;

import paupedros.m8.uf3.helpers.AssetManager;
import paupedros.m8.uf3.utils.Methods;
import paupedros.m8.uf3.utils.Settings;

public class Asteroid extends Scrollable {

    private Circle collisionCircle;

    Random r;
    int assetAndroid;

    public Asteroid(float x, float y, float width, float height, float velocity) {
        super(x, y, width, height, velocity);

        collisionCircle = new Circle();

        r = new Random();
        assetAndroid = r.nextInt(15);

        setOrigin();

        RotateByAction rotateAction = new RotateByAction();
        rotateAction.setAmount(-90f);
        rotateAction.setDuration(0.2f);

        RepeatAction repeat = new RepeatAction();
        repeat.setAction(rotateAction);
        repeat.setCount(RepeatAction.FOREVER);

        this.addAction(repeat);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        collisionCircle.set(position.x + width / 2.0f,
            position.y + width / 2.0f,
            width / 2.0f);
    }

    public boolean collides(Spacecraft nau) {
        if (position.x <= nau.getX() + nau.getWidth()) {
            return (Intersector.overlaps(collisionCircle, nau.getCollisionRect()));
        }
        return false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        // Get the current animation frame and draw it
        batch.draw(AssetManager.asteroid[0], position.x, position.y,
            this.getOriginX(), this.getOriginY(), width, height, this.getScaleX(), this.getScaleY(),
            this.getRotation());
    }

    public void setOrigin() {
        this.setOrigin(width / 2.0f + 1, height / 2.0f);
    }

    @Override
    public void reset(float newX) {
        super.reset(newX); // Llama al metodo reset de la clase base Scrollable

        // Genera un nuevo tamaño aleatorio para el asteroide
        float newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID);
        width = height = 34 * newSize;

        // Calcula una nueva posición vertical aleatoria dentro de los límites de la pantalla
        position.y = new Random().nextInt(Settings.GAME_HEIGHT - (int) height);
        setOrigin();
    }
}
