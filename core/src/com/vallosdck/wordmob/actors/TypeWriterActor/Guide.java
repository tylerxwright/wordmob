package com.vallosdck.wordmob.actors.TypeWriterActor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vallosdck.wordmob.Assets;

/**
 * Created by vallos on 7/4/2016.
 */
public class Guide extends Actor {
	private TextureRegion guide;

	public Guide() {
		guide = Assets.instance.typeWriter.guide;

		setWidth(guide.getRegionWidth());
		setHeight(guide.getRegionHeight());
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(guide, getX(), getY(), guide.getRegionWidth(), guide.getRegionHeight());
	}
}
