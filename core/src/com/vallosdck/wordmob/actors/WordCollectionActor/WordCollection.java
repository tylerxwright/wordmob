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
	private GameController controller;

	public WordCollection(String sentence, float boundsWidth, float boundsHeight, GameController controller) {
		currentWordIndex = 0;
		words = new ArrayList<Word>();
		this.boundsWidth = boundsWidth;
		this.boundsHeight = boundsHeight;
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
		// This needs to be controlled via a difficulty level or something
		Random random = new Random();
		boolean randomPlacement = random.nextBoolean();
		boolean randomRotation = random.nextBoolean();
		Word lastWord = null;
		for(int i = 0; i < words.size(); i++) {
			Word word = words.get(i);

			if(randomPlacement) {
				word.setPosition(random.nextInt(Math.round(boundsWidth) - 100) + 50, random.nextInt(Math.round(boundsHeight) - 100) + 50);
			} else {
				if (lastWord == null) {
					word.setPosition(20, boundsHeight / 2);
				} else {
					word.setPosition(lastWord.getX() + lastWord.getWidth() + 10f, boundsHeight / 2);
				}
			}

			if(randomRotation) {
				word.setRotation(random.nextInt(360));
			}

			lastWord = word;
		}
	}
}
