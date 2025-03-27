package paupedros.m8.uf3.utils;

public class Settings {

    // Mida del joc, s'escalarà segons la necessitat
    public static final int GAME_WIDTH = 240;
    public static final int GAME_HEIGHT = 135;

    // Propietats de la nau
    public static final int SPACECRAFT_WIDTH = 16;
    public static final int SPACECRAFT_HEIGHT = 16;
    public static final int SPACECRAFT_VELOCITY = 5;
    public static final int SPACECRAFT_STARTX = 20;
    public static final int SPACECRAFT_STARTY = GAME_HEIGHT / 2 - SPACECRAFT_HEIGHT / 2;

    public static final float MAX_ASTEROID = 1.5f;
    public static final float MIN_ASTEROID = 0.5f;

    public static final int ASTEROID_SPEED = 150;
    public static final int ASTEROID_GAP = 75;
    public static final int BG_SPEED = -100;
}
