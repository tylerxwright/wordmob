package com.vallosdck.wordmob.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vallosdck.wordmob.Assets;

/**
 * Created by vallos on 6/27/2016.
 */
public class TypeWriter extends Actor {
	private TextureRegion typeWriter;

	public TypeWriter() {
		typeWriter = Assets.instance.typeWriter.typeWriter;

		setWidth(typeWriter.getRegionWidth());
		setHeight(typeWriter.getRegionHeight());
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(typeWriter, getX(), getY(), typeWriter.getRegionWidth(), typeWriter.getRegionHeight());
	}
}
