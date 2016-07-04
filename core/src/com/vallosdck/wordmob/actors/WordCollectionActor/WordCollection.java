package com.vallosdck.wordmob.actors.WordCollectionActor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.vallosdck.wordmob.game.GameController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by vallos on 7/4/2016.
 */
public class WordCollection extends Group {
	protected int currentWordIndex;
	private List<Word> words;
	private GameController controller;

	public WordCollection(String sentence, float boundsWidth, float boundsHeight, GameController controller) {
		currentWordIndex = 0;
		words = new ArrayList<Word>();
		this.controller = controller;

		List<String> splitSentence = Arrays.asList(sentence.split("\\s+"));
		for(int i=0; i<splitSentence.size(); i++) {
			Word newWord = new Word(i, splitSentence.get(i), this);
			words.add(newWord);
			addActor(newWord);
		}

		Collections.shuffle(words);

		Word lastWord = null;
		for(int i = 0; i < words.size(); i++) {
			Word word = words.get(i);

			if(lastWord == null) {
				word.setPosition(20, 0);
			} else {
				word.setPosition(lastWord.getX() + lastWord.getWidth() + 10f, 0);
			}

			lastWord = word;
		}
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
}
