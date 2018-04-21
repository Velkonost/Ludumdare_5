package com.ltc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class MainGame extends Game {

	/** This is the asset manager we use to centralize the assets. */
	private AssetManager manager;

	/**
	 * These are the screens that we use in this game. I invite you to use a better system than
	 * just public variables. For instance, you could create an ArrayList or maybe use some
	 * structure such as a map where you can associate a number or a string to a screen.
	 */
	public BaseScreen loadingScreen, menuScreen, gameScreen, gameOverScreen, creditsScreen;

	@Override
	public void create() {
		// Initialize the asset manager. We add every aset to the manager so that it can be loaded
		// inside the LoadingScreen screen. Remember to put the name of the asset in the first
		// argument, then the type of the asset in the second argument.
		manager = new AssetManager();
//		manager.load("floor.png", Texture.class);
//		manager.load("gameover.png", Texture.class);
//		manager.load("overfloor.png", Texture.class);
//		manager.load("logo.png", Texture.class);
		manager.load("spike.png", Texture.class);
		manager.load("player.png", Texture.class);
//		manager.load("audio/die.ogg", Sound.class);
//		manager.load("audio/jump.ogg", Sound.class);
//		manager.load("audio/song.ogg", Music.class);
		manager.finishLoading();

		// Enter the loading screen to load the assets.
//		loadingScreen = new LoadingScreen(this);
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

	/**
	 * This method is invoked by LoadingScreen when all the assets are loaded. Use this method
	 * as a second-step loader. You can load the rest of the screens here and jump to the main
	 * screen now that everything is loaded.
	 */
	public void finishLoading() {
//		menuScreen = new es.danirod.jddprototype.game.MenuScreen(this);
		gameScreen = new GameScreen(this);
//		gameOverScreen = new GameOverScreen(this);
//		creditsScreen = new CreditsScreen(this);
		setScreen(menuScreen);
	}

	public AssetManager getManager() {
		return manager;
	}

}
