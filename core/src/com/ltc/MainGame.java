package com.ltc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends Game {
	SpriteBatch batch;
	Texture img;


	private AssetManager manager;

	public AssetManager getManager() {
		return manager;
	}

	@Override
	public void create () {
		manager = new AssetManager();
		manager.load("badlogic.jpg", Texture.class);
		manager.finishLoading();

//        setScreen(new MenuScreen(this));
		setScreen(new MenuScreen(this));
	}

}
