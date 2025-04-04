package paupedros.m8.uf3.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import paupedros.m8.uf3.actors.Asteroid;
import paupedros.m8.uf3.actors.ScrollHandler;
import paupedros.m8.uf3.actors.Spacecraft;
import paupedros.m8.uf3.helpers.AssetManager;
import paupedros.m8.uf3.helpers.InputHandler;
import paupedros.m8.uf3.utils.Settings;

public class GameScreen implements Screen {

    private Stage stage;
    private Texture texture;
    private Spacecraft spacecraft;
    private ScrollHandler scrollHandler;

    private ShapeRenderer shapeRenderer;
    private Batch batch;

    Boolean gameOver = false;

    private float explosionTime = 0;

    private GlyphLayout textLayout;

    public enum GameState {
        READY, RUNNING, GAMEOVER
    }
    private GameState currentState;

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

    public GameScreen(Batch prevBatch, Viewport prevViewport) {
         // Iniciem la música
        AssetManager.music.play();
         // Creem el ShapeRenderer
        shapeRenderer = new ShapeRenderer();
         // Creem l'stage i assginem el viewport
        stage = new Stage(prevViewport, prevBatch);

        batch = stage.getBatch();
         // Creem la nau i la resta d'objectes
        spacecraft = new Spacecraft(Settings.SPACECRAFT_STARTX, Settings.SPACECRAFT_STARTY, Settings.SPACECRAFT_WIDTH, Settings.SPACECRAFT_HEIGHT);
        scrollHandler = new ScrollHandler();
         // Afegim els actors a l'stage
        stage.addActor(scrollHandler);
        stage.addActor(spacecraft);
        // Donem nom a l'Actor
        spacecraft.setName("spacecraft");
         // Iniciem el GlyphLayout
        textLayout = new GlyphLayout();
        textLayout.setText(AssetManager.font, "Are you\nready?");

        currentState = GameState.READY;
        // Assignem com a gestor d'entrada la classe InputHandler
        Gdx.input.setInputProcessor(new InputHandler(this));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Dibuixem tots els actors de l'stage
        stage.draw();
        // Depenent de l'estat del joc farem unes accions o unes altres
        switch (currentState) {

            case GAMEOVER:
                updateGameOver(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            case READY:
                updateReady();
                break;

        }
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

    private void updateReady(){
        batch.begin();
        AssetManager.font.draw(batch, textLayout,
            (Settings.GAME_WIDTH/2)-textLayout.width/2,
            (Settings.GAME_HEIGHT/2)+textLayout.height/2);
        batch.end();
    }

    private void updateRunning(float delta){
        stage.act(delta);
        if(scrollHandler.collides(spacecraft)){
            AssetManager.explosionSound.play();
            stage.getRoot().findActor("spacecraft").remove();
            textLayout.setText(AssetManager.font,"Game Over:'(");
            currentState = GameState.GAMEOVER;
        }
    }

    private void updateGameOver(float delta){
        stage.act(delta);
        batch.begin();
        AssetManager.font.draw(batch, textLayout,
            (Settings.GAME_WIDTH/2)-textLayout.width/2,
            (Settings.GAME_HEIGHT/2)+textLayout.height/2);
        batch.draw(AssetManager.explosionAnim.getKeyFrame(explosionTime, false),
            spacecraft.getX(), spacecraft.getY(),
            spacecraft.getWidth(), spacecraft.getHeight());
        batch.end();
        explosionTime += delta;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public void reset() {

        // Posem el text d'inici
        textLayout.setText(AssetManager.font, "Are you\nready?");

        // Cridem als restart dels elements
        spacecraft.reset();
        scrollHandler.reset();

        // Posem l'estat a READY
        currentState = GameState.READY;

        // Afegim la nau a l'stage
        stage.addActor(spacecraft);

        // Posem a 0 les variables per controlar el temps jugat i l'animació de l'explosió
        explosionTime = 0.0f;

    }
}
