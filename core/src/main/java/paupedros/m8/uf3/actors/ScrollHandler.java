package paupedros.m8.uf3.actors;

import com.badlogic.gdx.scenes.scene2d.Group;
import java.util.ArrayList;
import java.util.Random;
import paupedros.m8.uf3.utils.Methods;
import paupedros.m8.uf3.utils.Settings;

public class ScrollHandler extends Group {

    // Background objects
    private Background bg, bg_back;

    // Asteroids
    private int numAsteroids;
    private ArrayList<Asteroid> asteroids;

    // Random object for generating random values
    private Random r;

    public ScrollHandler() {
        // Create the two backgrounds
        bg = new Background(0, 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED);
        bg_back = new Background(bg.getTailX(), 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED);

        // Add backgrounds to the group
        addActor(bg);
        addActor(bg_back);

        // Initialize random object
        r = new Random();

        // Start with 3 asteroids
        numAsteroids = 3;
        asteroids = new ArrayList<Asteroid>();

        // Generate the first asteroid
        float newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID) * 34;
        Asteroid asteroid = new Asteroid(
            Settings.GAME_WIDTH,
            r.nextInt(Settings.GAME_HEIGHT - (int) newSize),
            newSize, newSize, Settings.ASTEROID_SPEED
        );
        asteroids.add(asteroid);
        addActor(asteroid);

        // Generate the remaining asteroids
        for (int i = 1; i < numAsteroids; i++) {
            newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID) * 34;
            asteroid = new Asteroid(
                asteroids.get(asteroids.size() - 1).getTailX() + Settings.ASTEROID_GAP,
                r.nextInt(Settings.GAME_HEIGHT - (int) newSize),
                newSize, newSize, Settings.ASTEROID_SPEED
            );
            asteroids.add(asteroid);
            addActor(asteroid);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Reset backgrounds if they are off-screen
        if (bg.isLeftOfScreen()) {
            bg.reset(bg_back.getTailX());
        } else if (bg_back.isLeftOfScreen()) {
            bg_back.reset(bg.getTailX());
        }

        // Reset asteroids if they are off-screen
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid asteroid = asteroids.get(i);
            if (asteroid.isLeftOfScreen()) {
                if (i == 0) {
                    asteroid.reset(asteroids.get(asteroids.size() - 1).getTailX() + Settings.ASTEROID_GAP);
                } else {
                    asteroid.reset(asteroids.get(i - 1).getTailX() + Settings.ASTEROID_GAP);
                }
            }
        }
    }

    public boolean collides(Spacecraft nau) {
        for (Asteroid asteroid : asteroids) {
            if (asteroid.collides(nau)) return true;
        }
        return false;
    }

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }
    public void reset() {
        // Posem el primer asteroide fora de la pantalla per la dreta
        asteroids.get(0).reset(Settings.GAME_WIDTH);
        // Calculem les noves posicions de la resta d'asteroides
        for (int i = 1; i < asteroids.size(); i++) {

            asteroids.get(i).reset(asteroids.get(i - 1).getTailX() + Settings.ASTEROID_GAP);

        }
    }
}
