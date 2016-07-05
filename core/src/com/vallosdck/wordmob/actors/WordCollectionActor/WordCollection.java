package com.vallosdck.wordmob.actors.WordCollectionActor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.vallosdck.wordmob.game.GameController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by vallos on 7/4/2016.
 */
public class WordCollection extends Group {
	protected int currentWordIndex;
	private List<Word> words;
	private float boundsWidth;
	private float boundsHeight;
	private Difficulty difficulty;
	private GameController controller;

	public WordCollection(String sentence, float boundsWidth, float boundsHeight, Difficulty difficulty, GameController controller) {
		currentWordIndex = 0;
		words = new ArrayList<Word>();
		this.boundsWidth = boundsWidth;
		this.boundsHeight = boundsHeight;
		this.difficulty = difficulty;
		this.controller = controller;

		List<String> splitSentence = Arrays.asList(sentence.split("\\s+"));
		for(int i=0; i<splitSentence.size(); i++) {
			Word newWord = new Word(i, splitSentence.get(i), this);
			words.add(newWord);
			addActor(newWord);
		}

		Collections.shuffle(words);

		placeWords();
	}

	protected void onTouchedCorrectWord(String word) {
		currentWordIndex++;
		controller.onTouchedRightWord(word);

		if(currentWordIndex == words.size()) {
			controller.onAllWordsTouched();
		}
	}

	protected void onTouchedWrongWord() {
		controller.onTouchedWrongWord();
	}

	private void placeWords() {
		Random random = new Random();
		Word lastWord = null;
		float left = -1, right = -1, top = -1, bottom = -1;

		for(int i = 0; i < words.size(); i++) {
			Word word = words.get(i);

			if(difficulty.isXRandom()) {
				word.setX(random.nextInt(Math.round(boundsWidth) - 100) + 50);
			} else {
				if (lastWord == null) {
					word.setX(0);
				} else {
					word.setX(lastWord.getX() + lastWord.getWidth() + 10f);
				}
			}

			if(difficulty.isYRandom()) {
				word.setY(random.nextInt(Math.round(boundsHeight) - 100) + 50);
			} else {
				word.setY(boundsHeight/2);
			}

			switch(difficulty.getRotationDifficulty()) {
				case ZERO:
					word.setRotation(0);
					break;
				case FLIP:
					word.setRotation((random.nextBoolean() ? 0 : 180));
					break;
				case VERTICAL:
					word.setRotation((random.nextBoolean() ? -90 : 90));
					break;
				case FORTYFIVE:
					word.setRotation(random.nextInt(90)-45);
					break;
				case NINTY:
					word.setRotation(random.nextInt(180)-90);
					break;
				case ONETHIRTYFIVE:
					word.setRotation(random.nextInt(270)-135);
					break;
				case THREESIXTY:
					word.setRotation(random.nextInt(360)-180);
					break;
				case ROTATE:
					word.setRotation(random.nextInt(360)-180);
					word.setRotating(true);
					word.setRotatingClockwise(random.nextBoolean());
					break;
				default:
					word.setRotation(0);
					break;
			}

			if(left == -1 || word.getX() < left) {
				left = word.getX();
			}

			if(right == -1 || word.getX() + word.getWidth() > right) {
				right = word.getX() + word.getWidth();
			}

			if(top == -1 || word.getY() + word.getHeight() > top) {
				top = word.getY() + word.getHeight();
			}

			if(bottom == -1 || word.getY() < bottom) {
				bottom = word.getY();
			}

			lastWord = word;
		}

		if(difficulty.isCentered()) {
			setPosition((boundsWidth - (left + right))/2, (boundsHeight - (top + bottom))/2);
		}
	}
}
