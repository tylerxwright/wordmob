package com.vallosdck.wordmob.actors.WordCollectionActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.vallosdck.wordmob.Assets;

/**
 * Created by vallos on 7/4/2016.
 */
public class Word extends Group {
	private final static float ROTATION_SPEED = 50f;

	private int index;
	private Label text;
	private WordCollection parent;

	private boolean isRotating;
	public void setRotating(boolean isRotating) {
		this.isRotating = isRotating;
	}

	private boolean isRotatingClockwise;
	public void setRotatingClockwise(boolean isRotatingClockwise) {
		this.isRotatingClockwise = isRotatingClockwise;
	}

	public Word(int index, String word, WordCollection parent) {
		this.index = index;
		this.parent = parent;
		text = new Label(word, new Label.LabelStyle(Assets.instance.fonts.defaultNormal, new Color(Color.WHITE)));
		addActor(text);

		setWidth(text.getWidth());
		setHeight(text.getHeight());

		setOrigin(text.getWidth()/2, text.getHeight()/2);
	}

	@Override
	public void act (float delta) {
		if(isRotating) {
			if(isRotatingClockwise) {
				setRotation(getRotation() + ROTATION_SPEED * delta);
			} else {
				setRotation(getRotation() - ROTATION_SPEED * delta);
			}
		}
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		Actor result = super.hit(x, y, touchable);
		if(result != null) {
			if(Gdx.input.justTouched()) {
				setTouchable(Touchable.disabled);
				if(parent.currentWordIndex == index) {
					setVisible(false);
					parent.onTouchedCorrectWord(String.valueOf(text.getText()));
				} else {
					parent.onTouchedWrongWord();
					Timer.schedule(new Timer.Task() {
						@Override
						public void run() {
							setTouchable(Touchable.enabled);
						}
					}, 1, 1, 0);
				}
			}
		}
		return result;
	}
}
