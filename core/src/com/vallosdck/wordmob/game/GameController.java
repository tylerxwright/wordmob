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
import com.vallosdck.wordmob.actors.Clock;
import com.vallosdck.wordmob.actors.Rating;
import com.vallosdck.wordmob.actors.RedX;
import com.vallosdck.wordmob.actors.TypeWriterActor.TypeWriter;
import com.vallosdck.wordmob.actors.WordBubble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by vallos on 6/28/2016.
 */
public class GameController {

	private static final int MAX_MISSES = 3;

	public int currentWordIndex;
	public int sentenceLength;

	private enum State {
		PREGAME, GAME, ENDGAME
	}

	private DirectedGame game;
	private Stage stage;

	private String sentence;
	List<String> sentenceList;
	private State state;
	private int misses;
	private List<WordBubble> wordBubbleList;
	private List<RedX> redXList;
	private Clock clock;
	private Label timer;
	private Label chapter;
	private Label page;
	private Label line;
	private double time;
	private String completedSentence;
	private TypeWriter typeWriter;

	public GameController(DirectedGame game, Stage stage) {
		this.game = game;
		this.stage = stage;

		if(Constants.DEBUG) {
			this.stage.setDebugAll(true);
		}

		configureStage();
		initialize();
	}

	private void configureStage() {
		typeWriter = new TypeWriter();
		typeWriter.setPosition(0, 0);
		stage.addActor(typeWriter);

		clock = new Clock();
		clock.setPosition(5, stage.getHeight()-clock.getHeight() - 5);
		stage.addActor(clock);

		timer = new Label(String.format("%.2f", time), new Label.LabelStyle(Assets.instance.fonts.defaultSmall, new Color(Color.WHITE)));
		timer.setPosition(clock.getX() + clock.getWidth() + 10, stage.getHeight()-clock.getHeight() - 4);
		stage.addActor(timer);

		line = new Label("Line " + (GameManager.instance.currentLine.getIndex()+1), new Label.LabelStyle(Assets.instance.fonts.defaultSmall, new Color(Color.WHITE)));
		line.setPosition(stage.getWidth()-line.getWidth()-10, stage.getHeight()-line.getHeight()-5);
		stage.addActor(line);

		page = new Label("Page " + (GameManager.instance.currentPage.getIndex()+1), new Label.LabelStyle(Assets.instance.fonts.defaultSmall, new Color(Color.WHITE)));
		page.setPosition(line.getX()-page.getWidth()-10, stage.getHeight()-page.getHeight()-5);
		stage.addActor(page);

		chapter = new Label("Chapter " + (GameManager.instance.currentChapter.getIndex()+1), new Label.LabelStyle(Assets.instance.fonts.defaultSmall, new Color(Color.WHITE)));
		chapter.setPosition(page.getX()-chapter.getWidth()-10, stage.getHeight()-chapter.getHeight()-5);
		stage.addActor(chapter);
	}

	private void initialize() {
		misses = 0;
		time = GameManager.instance.currentLine.getTime();
		line.setText("Line " + (GameManager.instance.currentLine.getIndex()+1));
		page.setText("Page " + (GameManager.instance.currentPage.getIndex()+1));
		chapter.setText("Chapter " + (GameManager.instance.currentChapter.getIndex()+1));
		redXList = new ArrayList<RedX>();
		state = State.PREGAME;
		sentence = GameManager.instance.currentLine.getSentence();
		completedSentence = "";
		typeWriter.reset();
	}

	public void start() {
		timer.setText(String.format("%.2f", time));
		startPregame();
	}

	public void update(float delta) {
		if(state == State.GAME) {
			time -= delta;
			timer.setText(String.format("%.2f", time));
			if(time <= 0) {
				reset();
			}
		}

		if(state == State.GAME && currentWordIndex == sentenceLength) {
			state = State.ENDGAME;
			startEndgame();
		}
	}

	public void onHitRightWord() {
		typeWriter.typeWord(sentenceList.get(currentWordIndex));

		// I'm thinking about making a class for the type bar and moving this into that
		/*float widthBefore = typeWriterLine.getWidth();
		completedSentence += sentenceList.get(currentWordIndex) + " ";
		typeWriterLine.setText(completedSentence);
		float widthAfter = typeWriterLine.getWidth();
		typeBar.setX(typeBar.getX() - 30);*/

		currentWordIndex++;
	}

	public void onHitWrongWord() {
		misses++;
		if(misses > MAX_MISSES) {
			reset();
		} else {
			int xPadding = 5;
			RedX redX = new RedX();
			if(redXList.size() == 0) {
				redX.setPosition(stage.getWidth() - (xPadding*MAX_MISSES + redX.getWidth()*MAX_MISSES + xPadding), xPadding);
			} else {
				redX.setPosition(redXList.get(redXList.size()-1).getX() + redX.getWidth() +  xPadding, xPadding);
			}
			redXList.add(redX);
			stage.addActor(redX);
		}
	}

	private void reset() {
		for(WordBubble wordBubble : wordBubbleList) {
			wordBubble.remove();
		}

		for(RedX redX : redXList) {
			redX.remove();
		}

		initialize();
		start();
	}

	private void startPregame() {
		final Label label = new Label(sentence, new Label.LabelStyle(Assets.instance.fonts.brainFlowerNormal, new Color(Color.WHITE)));

		// ((800 - (200 + 50))/2) + (200 + 50)

		label.setPosition(stage.getWidth()/2 - label.getWidth()/2, ((stage.getHeight() - typeWriter.getHeight())/2 + (typeWriter.getHeight() - label.getHeight()/2)));
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
									label.remove();
									setupGame();
								}
							}
				)
		));
	}

	private void setupGame() {
		currentWordIndex = 0;
		wordBubbleList = new ArrayList<WordBubble>();

		sentenceList = Arrays.asList(sentence.split("\\s+"));
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
				wordBubble.setPosition(20, ((stage.getHeight() - typeWriter.getHeight())/2 + (typeWriter.getHeight() - wordBubble.getHeight()/2)));
			} else {
				wordBubble.setPosition(lastWord.getX() + lastWord.getWidth() + 10f, ((stage.getHeight() - typeWriter.getHeight())/2 + (typeWriter.getHeight() - wordBubble.getHeight()/2)));
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
		double startTime = GameManager.instance.currentLine.getTime();
		Double starRating = time/(startTime/4);
		final Rating rating = new Rating(starRating.intValue());
		rating.setPosition(stage.getWidth()/2-rating.getWidth()/2, ((stage.getHeight() - typeWriter.getHeight())/2 + (typeWriter.getHeight() - rating.getHeight()/2)));
		stage.addActor(rating);

		stage.addAction(Actions.sequence(
				Actions.delay(2),
				Actions.run(new Runnable() {
					@Override
					public void run() {
						rating.remove();
						endgame();
					}
				})
		));
	}

	private void endgame() {
		if(GameManager.instance.anyMoreLines()) {
			try {
				GameManager.instance.nextLine();
				reset();
			} catch(Exception e){}
		} else {
			if(GameManager.instance.anyMorePages()) {
				try {
					GameManager.instance.nextPage();
					reset();
				} catch(Exception e) {}
			} else {
				if(GameManager.instance.anyMoreChapters()) {
					try {
						GameManager.instance.nextChapter();
						reset();
					} catch(Exception e) {}
				} else {
					GameManager.instance.reset();
					reset();
				}
			}
		}
	}
}
