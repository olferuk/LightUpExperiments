package ru.vsu.csf.enlightened;

import com.badlogic.gdx.Game;
import ru.vsu.csf.enlightened.screens.GameScreen;

public class LightUp extends Game {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;

	@Override
	public void create () {
        setScreen(new GameScreen());
	}
}
