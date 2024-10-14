package com.group18.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.group18.game.SkyRushBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(SkyRushBird.WIDTH, SkyRushBird.HEIGHT);
		config.setTitle("SkyRush Bird"); /** Title of the window */
        config.setForegroundFPS(60);
		new Lwjgl3Application(new SkyRushBird(), config);
	}
}
