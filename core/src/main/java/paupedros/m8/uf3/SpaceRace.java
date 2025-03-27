package paupedros.m8.uf3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import paupedros.m8.uf3.helpers.AssetManager;
import paupedros.m8.uf3.screens.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SpaceRace extends Game {

    @Override
    public void create() {
        // A l'iniciar el joc carreguem els recursos
        AssetManager.load();
        // I definim la pantalla principal com a la pantalla
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetManager.dispose();
    }
}
