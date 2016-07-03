package com.vallosdck.wordmob.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vallosdck.wordmob.Assets;

/**
 * Created by vallos on 6/28/2016.
 */
public class WordBubbleEdge extends Actor {

	private final boolean flip;
	private final float height;
	private TextureRegion bubbleEdge;

	public WordBubbleEdge(boolean flip, float height) {
		this.flip = flip;
		this.height = height;
		bubbleEdge = Assets.instance.wordBubble.edge;
		setWidth(bubbleEdge.getRegionWidth());
		setHeight(height);
	}

	@Override
	public void draw(Batch batch, float alpha) {

		batch.setColor(new Color(87/255f, 119/255f, 240/255f, 1f));
		batch.draw(bubbleEdge.getTexture(),
				getX(), getY(),
				getOriginX(), getOriginY(),
				bubbleEdge.getRegionWidth(), height,
				getScaleX(), getScaleY(),
				getRotation(),
				bubbleEdge.getRegionX(), bubbleEdge.getRegionY(),
				bubbleEdge.getRegionWidth(), bubbleEdge.getRegionHeight(),
				flip, false);

		batch.setColor(Color.WHITE);
	}

}
