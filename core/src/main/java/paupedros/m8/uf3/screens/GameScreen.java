package paupedros.m8.uf3.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;

import paupedros.m8.uf3.actors.Asteroid;
import paupedros.m8.uf3.actors.ScrollHandler;
import paupedros.m8.uf3.actors.Spacecraft;
import paupedros.m8.uf3.utils.Settings;

public class GameScreen implements Screen {

    private Stage stage;
    private Texture texture;
    private Spacecraft spacecraft;
    private ScrollHandler scrollHandler;

    private ShapeRenderer shapeRenderer;
    private Batch batch;

    public Spacecraft getSpacecraft() {
        return spacecraft;
    }

    public Texture getTexture() {
        return texture;
    }

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    public Stage getStage() {
        return stage;
    }

    public GameScreen() {
        shapeRenderer = new ShapeRenderer();

        OrthographicCamera camera =
            new OrthographicCamera(Settings.GAME_WIDTH,
                Settings.GAME_HEIGHT);
        camera.setToOrtho(true);

        StretchViewport viewport =
            new StretchViewport(Settings.GAME_WIDTH,
                Settings.GAME_HEIGHT, camera);
        // Creem l'stage
        stage = new Stage(viewport);
        batch = stage.getBatch();
        // Creem la nau i la resta d'objectes
        spacecraft = new Spacecraft(Settings.SPACECRAFT_STARTX, Settings.SPACECRAFT_STARTY,
            Settings.SPACECRAFT_WIDTH, Settings.SPACECRAFT_HEIGHT);
        scrollHandler = new ScrollHandler();
        // Afegim els actors
        stage.addActor(spacecraft);
        stage.addActor(scrollHandler);
    }

    private void drawElements() {
        //Gdx.gl20.glClearColor(0.0f,0.0f, 0.0f, 1.0f);
        //Gdx.gl20.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.rect(spacecraft.getX(),
            spacecraft.getY(),
            spacecraft.getWidth(),
            spacecraft.getHeight());

        ArrayList<Asteroid> asteroids = scrollHandler.getAsteroids();
        Asteroid asteroid;

        for (int i = 0; i < asteroids.size(); i++) {
            asteroid = asteroids.get(i);
            switch (i) {
                case 0:
                    shapeRenderer.setColor(1, 0, 0, 1);
                    break;
                case 1:
                    shapeRenderer.setColor(0, 0, 1, 1);
                    break;
                case 2:
                    shapeRenderer.setColor(1, 1, 0, 1);
                    break;
                default:
                    shapeRenderer.setColor(1,1,1,1);
                    break;
            }
            shapeRenderer.circle(asteroid.getX() + asteroid.getWidth() / 2,
                asteroid.getY() + asteroid.getWidth() / 2,
                asteroid.getWidth() / 2);
        }
        shapeRenderer.end();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Dibuixem i actualitzem tots els actors de l'stage
        stage.draw();
        stage.act(delta);
        //drawElements();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
