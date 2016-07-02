package com.vallosdck.wordmob.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.vallosdck.wordmob.game.GameController;
import com.vallosdck.wordmob.screens.GameScreen;

/**
 * Created by vallos on 6/27/2016.
 */
public class WordBubble extends Group {

	private int index;
	private String word;
	private WordBubbleEdge leftEdge;
	private WordBubbleMiddle middle;
	private WordBubbleEdge rightEdge;
	private GameController controller;

	public WordBubble(String word, int index, BitmapFont font, GameController controller) {
		this.index = index;
		this.word = word;
		this.controller = controller;

		middle = new WordBubbleMiddle(word, font);
		leftEdge = new WordBubbleEdge(false, middle.getHeight());
		rightEdge = new WordBubbleEdge(true, middle.getHeight());

		middle.setX(leftEdge.getWidth());
		rightEdge.setX(leftEdge.getWidth() + middle.getWidth());

		setWidth(leftEdge.getWidth() + middle.getWidth() + rightEdge.getWidth());
		setHeight(middle.getHeight());
		setOrigin(this.getWidth()/2, this.getHeight()/2);
		setRotation(0);

		addActor(leftEdge);
		addActor(middle);
		addActor(rightEdge);
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		Actor result = super.hit(x, y, touchable);
		if(result != null) {
			if(Gdx.input.justTouched()) {
				if(controller.currentWordIndex == index) {
					this.setTouchable(Touchable.disabled);
					this.setVisible(false);
					controller.currentWordIndex++;
				} else {
					controller.onHitWrongWord();
				}
			}
		}
		return result;
	}
}
