package com.vallosdck.wordmob.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.vallosdck.wordmob.Constants;
import com.vallosdck.wordmob.DirectedGame;

/**
 * Created by vallos on 6/30/2016.
 */
public class SplashScreen extends AbstractGameScreen {
	private Stage stage;
	private Skin skinUi;
	private Skin skinLibgdx;

	public SplashScreen(DirectedGame game) {
		super(game);
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(deltaTime);
		stage.draw();
	}

	private void rebuildStage() {
		skinUi = new Skin(Gdx.files.internal(Constants.SKIN_UI), new TextureAtlas(Constants.TEXTURE_ATLAS_UI));

		Table layerBackground = buildBackgroundLayer();

		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		stack.add(layerBackground);

	}

	private Table buildBackgroundLayer() {
		Table layer = new Table();
		Image imgBackground = new Image(skinUi, "logo");
		layer.add(imgBackground);
		return layer;
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		OrthographicCamera camera = new OrthographicCamera();
		FitViewport viewport = new FitViewport(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, camera);
		viewport.apply();

		stage = new Stage(viewport);
		rebuildStage();
	}

	@Override
	public void hide() {
		stage.dispose();
		skinUi.dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public InputProcessor getInputProcessor() {
		return stage;
	}
}
