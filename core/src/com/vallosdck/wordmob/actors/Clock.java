package com.vallosdck.wordmob.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vallosdck.wordmob.Assets;

/**
 * Created by vallos on 7/1/2016.
 */
public class Clock extends Actor {
	private TextureRegion clock;

	public Clock() {
		clock = Assets.instance.clock.clock;
		setWidth(clock.getRegionWidth());
		setHeight(clock.getRegionHeight());
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(clock, getX(), getY(), clock.getRegionWidth(), clock.getRegionHeight());
	}
}
