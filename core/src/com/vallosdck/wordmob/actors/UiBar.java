package com.vallosdck.wordmob.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vallosdck.wordmob.Assets;

/**
 * Created by vallos on 7/1/2016.
 */
public class UiBar extends Actor {
	private TextureRegion uiBar;

	public UiBar() {
		uiBar = Assets.instance.uiBar.uiBar;
		setWidth(uiBar.getRegionWidth());
		setHeight(uiBar.getRegionHeight());
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(uiBar, getX(), getY(), uiBar.getRegionWidth(), uiBar.getRegionHeight());
	}
}
