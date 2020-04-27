package com.vallosdck.wordmob.actors.TypeWriterActor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.vallosdck.wordmob.Assets;

/**
 * Created by vallos on 7/4/2016.
 */
public class Top extends Group {
	private static final int TEXT_START_X = 400;
	private static final int TEXT_START_Y = -30;

	private Guide guide;
	private Label text;

	public Top() {
		guide = new Guide();
		text = new Label("", new Label.LabelStyle(Assets.instance.fonts.momsTypewriterNormal, new Color(Color.BLACK)));

		text.setPosition(TEXT_START_X, TEXT_START_Y);

		setHeight(guide.getHeight());
		setWidth(guide.getWidth());

		addActor(guide);
		addActor(text);
	}

	public void typeWord(String word) {

		GlyphLayout glyphLayout = new GlyphLayout();
		glyphLayout.setText(Assets.instance.fonts.momsTypewriterNormal, text.getText());
		float startWidth = glyphLayout.width;

		String newText = text.getText() + " " + word;

		glyphLayout.setText(Assets.instance.fonts.momsTypewriterNormal, newText);

		float newWidth = glyphLayout.width;;

		float moveAmount = newWidth - startWidth;

		text.setText(newText);
		text.setX(text.getX() - moveAmount);
		guide.setX(guide.getX() - moveAmount);
	}

	public void reset() {
		text.setText("");
		text.setX(TEXT_START_X);
		guide.setX(0);
	}

	@Override
	public void act (float delta) {

	}
}
