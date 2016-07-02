package com.vallosdck.wordmob;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.vallosdck.wordmob.screens.GameScreen;

public class WordMobGame extends DirectedGame {

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		Assets.instance.init(new AssetManager());
		GameManager.instance.init();
		GameManager.instance.reset();

		setScreen(new GameScreen(this));
	}

}
