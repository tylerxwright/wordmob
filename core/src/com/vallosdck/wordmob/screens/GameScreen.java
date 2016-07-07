package com.vallosdck.wordmob.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.vallosdck.wordmob.Constants;
import com.vallosdck.wordmob.DirectedGame;
import com.vallosdck.wordmob.game.GameController;

/**
 * Created by vallos on 6/26/2016.
 */
public class GameScreen extends AbstractGameScreen {

	private GameController controller;
	private Stage stage;

	public GameScreen(DirectedGame game) {
		super(game);

		OrthographicCamera camera = new OrthographicCamera();
		FitViewport viewport = new FitViewport(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, camera);
		viewport.apply();

		stage = new Stage(viewport);

		controller = new GameController(game, stage);
	}

	@Override
	public void show() {
		controller.start();
	}

	@Override
	public InputProcessor getInputProcessor() {
		return stage;
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		controller.update(deltaTime);

		stage.act(deltaTime);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void hide() {
		// TODO: This might not be a good idea
		stage.dispose();
	}

	@Override
	public void pause() {
	}
}
