package paupedros.m8.uf3;

import com.badlogic.gdx.Game;

import paupedros.m8.uf3.helpers.AssetManager;
import paupedros.m8.uf3.screens.GameScreen;
import paupedros.m8.uf3.screens.SplashScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SpaceRace extends Game {

    @Override
    public void create() {
        // A l'iniciar el joc carreguem els recursos
        AssetManager.load();
        // I definim la pantalla principal com a la pantalla
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetManager.dispose();
    }
}
