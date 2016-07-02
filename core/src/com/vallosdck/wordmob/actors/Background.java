package com.vallosdck.wordmob.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vallosdck.wordmob.Assets;

/**
 * Created by vallos on 6/27/2016.
 */
public class Background extends Actor {
	private TextureRegion background;

	public Background() {
		background = Assets.instance.background.background;
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(background, getX(), getY(), background.getRegionWidth(), background.getRegionHeight());
	}
}
