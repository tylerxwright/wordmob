package com.vallosdck.wordmob.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vallosdck.wordmob.Assets;

/**
 * Created by vallos on 7/1/2016.
 */
public class Background extends Actor {
	private ShapeRenderer shapeRenderer;
	private OrthographicCamera camera;

	public Background(float width, float height, Color color, OrthographicCamera camera) {
		this.shapeRenderer = new ShapeRenderer();
		this.shapeRenderer.setProjectionMatrix(camera.combined);
		setWidth(width);
		setHeight(height);
		setColor(color);
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.end();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(getColor());
		shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
		shapeRenderer.end();
		batch.begin();
	}
}
