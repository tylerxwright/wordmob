package com.vallosdck.wordmob.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vallosdck.wordmob.Assets;

/**
 * Created by vallos on 7/2/2016.
 */
public class Star extends Actor {

	private TextureRegion starEmpty;
	private TextureRegion starFilled;
	private boolean isStarFilled;

	public Star() {
		this(1f);
	}

	public Star(float scale) {
		starEmpty = Assets.instance.star.starEmpty;
		starFilled = Assets.instance.star.starFilled;
		isStarFilled = false;

		setScale(scale);
		resize();
	}

	@Override
	public void draw(Batch batch, float alpha) {
		resize();

		if(isStarFilled) {
			batch.draw(starFilled,
					getX(), getY(),
					getOriginX(), getOriginY(),
					starFilled.getRegionWidth(), starFilled.getRegionHeight(),
					getScaleX(), getScaleY(),
					getRotation());
		} else {
			batch.draw(starEmpty,
					getX(), getY(),
					getOriginX(), getOriginY(),
					starFilled.getRegionWidth(), starFilled.getRegionHeight(),
					getScaleX(), getScaleY(),
					getRotation());
		}
	}

	public void fillStar(boolean filled) {
		isStarFilled = filled;
	}

	private void resize() {
		setWidth(starFilled.getRegionWidth()*getScaleX());
		setHeight(starFilled.getRegionHeight()*getScaleY());
	}
}