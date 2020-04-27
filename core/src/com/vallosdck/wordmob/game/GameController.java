package com.vallosdck.wordmob.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.vallosdck.wordmob.Assets;
import com.vallosdck.wordmob.Constants;
import com.vallosdck.wordmob.DirectedGame;
import com.vallosdck.wordmob.GameManager;
import com.vallosdck.wordmob.actors.Background;
import com.vallosdck.wordmob.actors.Clock;
import com.vallosdck.wordmob.actors.Rating;
import com.vallosdck.wordmob.actors.RedX;
import com.vallosdck.wordmob.actors.TypeWriterActor.TypeWriter;
import com.vallosdck.wordmob.actors.UiBar;
import com.vallosdck.wordmob.actors.WordCollectionActor.Difficulty;
import com.vallosdck.wordmob.actors.WordCollectionActor.WordCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vallos on 6/28/2016.
 */
public class GameController {

	private static final int MAX_MISSES = 3;

	private enum State {
		PREGAME, GAME, ENDGAME
	}

	private final DirectedGame game;
	private final Stage stage;
	private final OrthographicCamera camera;

	private int misses;
	private String sentence;
	private State state;
	private double time;

	private List<RedX> redXList; // Move to a group
	private UiBar uiBar;
	private Clock clock;
	private Label timer;
	private Label chapter;
	private Label page;
	private Label line;
	private TypeWriter typeWriter;
	private WordCollection wordCollection;
	private Background background;

	public GameController(DirectedGame game, Stage stage, OrthographicCamera camera) {
		this.game = game;
		this.stage = stage;
		this.camera = camera;

		if(Constants.DEBUG) {
			this.stage.setDebugAll(true);
		}

		configureStage();
		initialize();
	}

	private void configureStage() {
		uiBar = new UiBar();
		uiBar.setPosition(0, stage.getHeight()-uiBar.getHeight());

		clock = new Clock();
		clock.setPosition(5, stage.getHeight()-clock.getHeight() - 5);

		timer = new Label(String.format("%.2f", time), new Label.LabelStyle(Assets.instance.fonts.defaultSmall, new Color(Color.WHITE)));
		timer.setPosition(clock.getX() + clock.getWidth() + 10, stage.getHeight()-clock.getHeight() - 4);

		line = new Label("Line " + (GameManager.instance.currentLine.getIndex()+1), new Label.LabelStyle(Assets.instance.fonts.defaultSmall, new Color(Color.WHITE)));
		line.setPosition(stage.getWidth()-line.getWidth()-10, stage.getHeight()-line.getHeight()-5);

		page = new Label("Page " + (GameManager.instance.currentPage.getIndex()+1), new Label.LabelStyle(Assets.instance.fonts.defaultSmall, new Color(Color.WHITE)));
		page.setPosition(line.getX()-page.getWidth()-10, stage.getHeight()-page.getHeight()-5);

		chapter = new Label("Chapter " + (GameManager.instance.currentChapter.getIndex()+1), new Label.LabelStyle(Assets.instance.fonts.defaultSmall, new Color(Color.WHITE)));
		chapter.setPosition(page.getX()-chapter.getWidth()-10, stage.getHeight()-chapter.getHeight()-5);

		typeWriter = new TypeWriter();
		typeWriter.setPosition(0, stage.getHeight() - typeWriter.getHeight() - uiBar.getHeight());

		background = new Background(stage.getWidth(), typeWriter.getHeight() + uiBar.getHeight(), Color.WHITE, this.camera);
		background.setPosition(0, stage.getHeight() - typeWriter.getHeight());

		stage.addActor(background);
		stage.addActor(uiBar);
		stage.addActor(chapter);
		stage.addActor(page);
		stage.addActor(line);
		stage.addActor(timer);
		stage.addActor(clock);
		stage.addActor(typeWriter);
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
	}

	public void onAllWordsTouched() {
		state = State.ENDGAME;
		startEndgame();
	}

	public void onTouchedRightWord(String word) {
		typeWriter.typeWord(word);
	}

	public void onTouchedWrongWord() {
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
		wordCollection.remove();

		for(RedX redX : redXList) {
			redX.remove();
		}

		initialize();
		start();
	}

	private void startPregame() {
		final Label label = new Label(sentence, new Label.LabelStyle(Assets.instance.fonts.brainFlowerNormal, new Color(Color.WHITE)));

		//label.setPosition(stage.getWidth()/2 - label.getWidth()/2, ((stage.getHeight() - typeWriter.getHeight())/2 + (typeWriter.getHeight() - label.getHeight()/2)));
		label.setPosition(stage.getWidth()/2 - label.getWidth()/2, typeWriter.getHeight()/2 - label.getHeight()/2);
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
		Difficulty difficulty = new Difficulty(GameManager.instance.currentLine.getDifficulty());
		//wordCollection = new WordCollection(sentence, stage.getWidth(), clock.getY() - typeWriter.getHeight(), difficulty, this);
		wordCollection = new WordCollection(sentence, stage.getWidth(), stage.getHeight() - typeWriter.getHeight()- uiBar.getHeight(), difficulty, this);
		wordCollection.setY(0);
		stage.addActor(wordCollection);

		startGame();
	}

	private void startGame() {
		state = State.GAME;
	}

	private void startEndgame() {
		double startTime = GameManager.instance.currentLine.getTime();
		Double starRating = time/(startTime/4);
		final Rating rating = new Rating(starRating.intValue());
		rating.setPosition(stage.getWidth()/2-rating.getWidth()/2, typeWriter.getHeight()/2 - rating.getHeight()/2);
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
