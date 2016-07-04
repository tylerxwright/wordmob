package com.vallosdck.wordmob.actors.TypeWriterActor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vallosdck.wordmob.Assets;

/**
 * Created by vallos on 7/4/2016.
 */
public class Bottom extends Actor {
	private TextureRegion bottom;

	public Bottom() {
		bottom = Assets.instance.typeWriter.bottom;

		setWidth(bottom.getRegionWidth());
		setHeight(bottom.getRegionHeight());
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(bottom, getX(), getY(), bottom.getRegionWidth(), bottom.getRegionHeight());
	}
}
