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
public class WordBubbleMiddle extends Actor {

	private float paddingTop;
	private float paddingBottom;
	private float paddingLeft;
	private float paddingRight;
	private String word;
	BitmapFont font;
	private GlyphLayout glyphLayout;
	private TextureRegion bubbleMiddle;

	public WordBubbleMiddle(String word, BitmapFont font) {
		this.word = word;
		this.font = font;

		paddingTop = 10;
		paddingBottom = 8;
		paddingLeft = 1;
		paddingRight = 1;

		bubbleMiddle = Assets.instance.wordBubble.middle;

		glyphLayout = new GlyphLayout();
		glyphLayout.setText(font, word);

		setWidth(glyphLayout.width + paddingLeft + paddingRight);
		setHeight(glyphLayout.height + paddingTop + paddingBottom);
	}

	@Override
	public void draw(Batch batch, float alpha) {

		batch.setColor(new Color(87/255f, 119/255f, 240/255f, 1f));
		batch.draw(bubbleMiddle.getTexture(),
				getX(), getY(),
				getOriginX(), getOriginY(),
				getWidth(), getHeight(),
				getScaleX(), getScaleY(),
				getRotation(),
				bubbleMiddle.getRegionX(), bubbleMiddle.getRegionY(),
				bubbleMiddle.getRegionWidth(), bubbleMiddle.getRegionHeight(),
				false, false);

		batch.setColor(Color.WHITE);

		font.draw(batch, glyphLayout, getX() + paddingLeft, getY() + glyphLayout.height + paddingTop);
	}
}
