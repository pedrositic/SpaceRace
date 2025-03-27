package paupedros.m8.uf3.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {
    public static Texture sheet;
    public static TextureRegion spacecraft, spacecraftDown,
        spacecraftUp, background;
    public static TextureRegion[] asteroid;
    public static Animation<TextureRegion> asteroidAnim;
    public static TextureRegion[] explosion;
    public static Animation explosionAnim;

    public static void load(){
        sheet = new Texture(Gdx.files.internal("sheet.png"));
        sheet.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        spacecraft = new TextureRegion(sheet, 0, 0, 36, 15);
        spacecraft.flip(false, true);
        spacecraftUp = new TextureRegion(sheet, 36, 0, 36, 15);
        spacecraftUp.flip(false, true);
        spacecraftDown = new TextureRegion(sheet, 72, 0, 36, 15);
        spacecraftDown.flip(false, true);

        asteroid = new TextureRegion[16];
        for (int i = 0; i < asteroid.length; i++) {
            asteroid[i] = new TextureRegion(sheet, i*34, 15, 34, 34);
            asteroid[i].flip(false, true);
        }
        asteroidAnim = new Animation(0.05f, asteroid);
        asteroidAnim.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        explosion = new TextureRegion[16];
        int index = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                explosion[index++] = new TextureRegion(sheet, j*64,
                    i*64 + 49, 64, 64);
                explosion[index-1].flip(false, true);
            }
        }

        background = new TextureRegion(sheet, 0, 177, 480, 135);
        background.flip(false, true);
    }
    public static void dispose(){
        sheet.dispose();
    }

}
