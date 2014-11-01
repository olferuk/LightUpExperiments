package ru.vsu.csf.enlightened.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.vsu.csf.enlightened.LightUp;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = LightUp.WIDTH;
        config.height = LightUp.HEIGHT;
		new LwjglApplication(new LightUp(), config);
	}
}
