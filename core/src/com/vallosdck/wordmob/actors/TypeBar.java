package com.vallosdck.wordmob.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vallosdck.wordmob.Assets;

/**
 * Created by vallos on 6/27/2016.
 */
public class TypeBar extends Actor {
	private TextureRegion typeBar;

	public TypeBar() {
		typeBar = Assets.instance.typeBar.typeBar;

		setWidth(typeBar.getRegionWidth());
		setHeight(typeBar.getRegionHeight());
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(typeBar, getX(), getY(), typeBar.getRegionWidth(), typeBar.getRegionHeight());
	}
}
