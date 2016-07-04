package com.vallosdck.wordmob.actors.TypeWriterActor;

import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by vallos on 7/4/2016.
 */
public class TypeWriter extends Group {
	private Bottom bottom;
	private Top top;

	public TypeWriter() {
		bottom = new Bottom();
		top = new Top();

		top.setY(bottom.getHeight());

		setHeight(bottom.getHeight() + top.getHeight());
		setWidth(bottom.getWidth());

		addActor(bottom);
		addActor(top);
	}

	public void typeWord(String word) {
		top.typeWord(word);
	}

	public void reset() {
		top.reset();
	}
}
