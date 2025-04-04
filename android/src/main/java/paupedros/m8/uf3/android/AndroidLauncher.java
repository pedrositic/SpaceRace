package paupedros.m8.uf3.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import paupedros.m8.uf3.SpaceRace;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.useImmersiveMode = true; // Recommended, but not required.

        configuration.useAccelerometer = false;
        configuration.useCompass = false;
        configuration.useWakelock = true;

        initialize(new SpaceRace(), configuration);
    }
}
