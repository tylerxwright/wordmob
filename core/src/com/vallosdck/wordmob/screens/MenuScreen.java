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
public class MenuScreen extends AbstractGameScreen {

	private Stage stage;
	private Skin skinUi;
	private Skin skinLibgdx;

	public MenuScreen(DirectedGame game) {
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
		skinLibgdx = new Skin(Gdx.files.internal(Constants.SKIN_LIBGDX_UI), new TextureAtlas(Constants.TEXTURE_ATLAS_LIBGDX_UI));

		Table layerBackground = buildBackgroundLayer();
		Table layerControls = buildControlsLayer();

		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		stack.add(layerBackground);
		stack.add(layerControls);

	}

	private Table buildBackgroundLayer() {
		Table layer = new Table();
		Image imgBackground = new Image(skinUi, "menu-background");
		layer.add(imgBackground);
		return layer;
	}

	private Table buildControlsLayer() {
		Table layer = new Table();
		TextButton newGameBtn = new TextButton("New Game", skinLibgdx);
		layer.add(newGameBtn).width(150).padBottom(10);
		newGameBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen( new GameScreen(game) );
			};
		});

		layer.row();

		TextButton continueBtn = new TextButton("Continue", skinLibgdx);
		layer.add(continueBtn).width(150).padBottom(10);

		layer.row();

		TextButton optionsBtn = new TextButton("Options", skinLibgdx);
		layer.add(optionsBtn).width(150);

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
		skinLibgdx.dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public InputProcessor getInputProcessor() {
		return stage;
	}
}
