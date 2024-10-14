package com.group18.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group18.game.states.GameStateManager;
import com.group18.game.states.MenuState;


public class SkyRushBird extends ApplicationAdapter
{
	public static final int WIDTH = 1466; /** Width of the window */
	public static final int HEIGHT = 826; /** Height of the window */

	private GameStateManager gsm;
	private SpriteBatch batch;
	private Music music;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("bgm2.wav"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		img = new Texture("bg.jpg");
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		music.dispose();
	}
}
