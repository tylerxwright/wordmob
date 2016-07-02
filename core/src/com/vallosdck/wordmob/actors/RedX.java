package com.vallosdck.wordmob.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vallosdck.wordmob.Assets;

/**
 * Created by vallos on 6/30/2016.
 */
public class RedX extends Actor{
	private TextureRegion redX;

	public RedX() {
		redX = Assets.instance.redX.redX;
		setWidth(redX.getRegionWidth());
		setHeight(redX.getRegionHeight());
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(redX, getX(), getY(), redX.getRegionWidth(), redX.getRegionHeight());
	}
}
