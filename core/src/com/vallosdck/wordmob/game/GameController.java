package com.vallosdck.wordmob.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.vallosdck.wordmob.Assets;
import com.vallosdck.wordmob.Constants;
import com.vallosdck.wordmob.DirectedGame;
import com.vallosdck.wordmob.GameManager;
import com.vallosdck.wordmob.actors.Background;
import com.vallosdck.wordmob.actors.WordBubble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by vallos on 6/28/2016.
 */
public class GameController {

	public int currentWordIndex;
	public int sentenceLength;

	private enum State {
		PREGAME, GAME, ENDGAME
	}

	private DirectedGame game;
	private Stage stage;

	private String sentence;
	private State state;
	private int misses;

	public GameController(DirectedGame game, Stage stage) {
		this.game = game;
		this.stage = stage;
		this.misses = 0;
		initialize();
	}

	public void start() {
		configureStage();
		startPregame();
	}

	public void update(float delta) {
		if(state == State.GAME && currentWordIndex == sentenceLength) {
			state = State.ENDGAME;
			startEndgame();
		}
	}

	public void onHitWrongWord() {

	}

	private void initialize() {
		state = State.PREGAME;
		sentence = GameManager.instance.currentLine.sentence;
	}

	private void configureStage() {
		stage.addActor(new Background());
	}

	private void startPregame() {
		final Label label = new Label(sentence, new Label.LabelStyle(Assets.instance.fonts.defaultNormal, new Color(Color.WHITE)));
		label.setPosition(10, 50);
		label.setFontScaleY(0.001f);
		stage.addActor(label);

		label.addAction(Actions.sequence(
				new Action() {
					@Override
					public boolean act(float delta) {
						float scaleY = label.getFontScaleY();
						scaleY += .05;
						label.setFontScaleY(scaleY);
						if(scaleY >= 1) {
							return true;
						}
						return false;
					}
				},
				Actions.delay(1f),
				Actions.fadeOut(1f),
				Actions.run(new Runnable() {
								@Override
								public void run() {
									setupGame();
								}
							}
				)
		));
	}

	private void setupGame() {
		currentWordIndex = 0;
		List<WordBubble> wordBubbleList = new ArrayList<WordBubble>();

		List<String> sentenceList = Arrays.asList(sentence.split("\\s+"));
		sentenceLength = sentenceList.size();
		for(int i = 0; i < sentenceList.size(); i++) {
			WordBubble wordBubble = new WordBubble(sentenceList.get(i), i, Assets.instance.fonts.defaultNormal, this);
			wordBubbleList.add(wordBubble);
		}

		Collections.shuffle(wordBubbleList);

		WordBubble lastWord = null;
		for(int i = 0; i < wordBubbleList.size(); i++) {
			WordBubble wordBubble = wordBubbleList.get(i);

			if(lastWord == null) {
				wordBubble.setPosition(20, 50);
			} else {
				wordBubble.setPosition(lastWord.getX() + lastWord.getWidth() + 10f, 50f);
			}

			stage.addActor(wordBubble);
			lastWord = wordBubble;
		}

		startGame();
	}

	private void startGame() {
		state = State.GAME;
	}

	private void startEndgame() {
		final Label label = new Label("You Won!", new Label.LabelStyle(Assets.instance.fonts.defaultNormal, new Color(Color.WHITE)));
		label.setPosition(Constants.VIEWPORT_WIDTH/2-label.getWidth()/2, Constants.VIEWPORT_HEIGHT/2-label.getHeight()/2);
		stage.addActor(label);

		label.addAction(Actions.sequence(
				//Actions.delay(2),
				Actions.run(new Runnable() {
					@Override
					public void run() {
						label.remove();
						if(GameManager.instance.anyMoreLines()) {
							try {
								GameManager.instance.nextLine();
								initialize();
								start();
							} catch(Exception e){}
						} else {
							if(GameManager.instance.anyMoreParagraphs()) {
								try {
									GameManager.instance.nextParagraph();
									initialize();
									start();
								} catch(Exception e) {}
							} else {
								if(GameManager.instance.anyMoreChapters()) {
									try {
										GameManager.instance.nextChapter();
										initialize();
										start();
									} catch(Exception e) {}
								} else {
									GameManager.instance.reset();
									initialize();
									start();
								}
							}
						}

					}
				})
		));
	}
}
