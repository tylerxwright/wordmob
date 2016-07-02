package com.vallosdck.wordmob;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.vallosdck.wordmob.screens.AbstractGameScreen;

/**
 * Created by vallos on 6/27/2016.
 */
public abstract class DirectedGame implements ApplicationListener {

	private boolean isInitialized;
	private AbstractGameScreen currentScreen;

	public void setScreen(AbstractGameScreen screen) {

		if(!isInitialized) {
			isInitialized = true;
		}

		currentScreen = screen;
		currentScreen.show();
		currentScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.input.setInputProcessor(currentScreen.getInputProcessor());
	}

	@Override
	public void render() {
		float deltaTime = Math.min(Gdx.graphics.getDeltaTime(), 1.0f / 60.0f);
		currentScreen.render(deltaTime);
	}

	@Override
	public void resize(int width, int height) {
		if(currentScreen != null) {
			currentScreen.resize(width, height);
		}
	}

	@Override
	public void pause() {
		if(currentScreen != null) {
			currentScreen.pause();
		}
	}

	@Override
	public void resume() {
		if(currentScreen != null) {
			currentScreen.resume();
		}
	}

	@Override
	public void dispose() {
		if(currentScreen != null) {
			currentScreen.hide();
		}

		if(isInitialized) {
			currentScreen = null;
			isInitialized = false;
		}
	}
}
