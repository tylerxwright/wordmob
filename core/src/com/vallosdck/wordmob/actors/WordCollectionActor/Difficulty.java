package com.vallosdck.wordmob.actors.WordCollectionActor;

/**
 * Created by vallos on 7/4/2016.
 */
public class Difficulty {

	public enum RotationDifficulty {
		ZERO, FLIP, VERTICAL, FORTYFIVE, NINTY, ONETHIRTYFIVE, THREESIXTY, ROTATE
	}

	private boolean isXRandom;
	public boolean isXRandom() {
		return isXRandom;
	}
	public void setXRandom(boolean xRandom) {
		isXRandom = xRandom;
	}

	private boolean isYRandom;
	public boolean isYRandom() {
		return isYRandom;
	}
	public void setYRandom(boolean yRandom) {
		isYRandom = yRandom;
	}

	private boolean isCentered;
	public boolean isCentered() {
		return isCentered;
	}
	public void setCentered(boolean centered) {
		isCentered = centered;
	}

	private RotationDifficulty rotationDifficulty;

	public RotationDifficulty getRotationDifficulty() {
		return rotationDifficulty;
	}
	public void setRotationDifficulty(RotationDifficulty rotationDifficulty) {
		this.rotationDifficulty = rotationDifficulty;
	}

	public Difficulty(boolean isXRandom, boolean isYRandom, boolean isCentered, RotationDifficulty rotationDifficulty) {
		this.isXRandom = isXRandom;
		this.isYRandom = isYRandom;
		this.isCentered = isCentered;
		this.rotationDifficulty = rotationDifficulty;
	}

	public Difficulty(int level) {
		switch (level) {
			case 1:  setDifficulty(false, false, true, RotationDifficulty.ZERO); break;
			case 2:  setDifficulty(false, true, true, RotationDifficulty.ZERO); break;
			case 3:  setDifficulty(false, false, true, RotationDifficulty.VERTICAL); break;
			case 4:  setDifficulty(false, true, true, RotationDifficulty.VERTICAL); break;
			case 5:  setDifficulty(true, true, true, RotationDifficulty.ZERO); break;
			case 6:  setDifficulty(false, false, true, RotationDifficulty.FLIP); break;
			case 7:  setDifficulty(false, true, true, RotationDifficulty.NINTY); break;
			case 8:  setDifficulty(true, true, true, RotationDifficulty.FLIP); break;
			case 9:  setDifficulty(true, true, true, RotationDifficulty.THREESIXTY); break;
			case 10: setDifficulty(true, true, true, RotationDifficulty.ROTATE); break;
			default: setDifficulty(false, false, true, RotationDifficulty.ZERO); break;
		}
	}

	public void setDifficulty(boolean isXRandom, boolean isYRandom, boolean isCentered, RotationDifficulty rotationDifficulty) {
		this.isXRandom = isXRandom;
		this.isYRandom = isYRandom;
		this.isCentered = isCentered;
		this.rotationDifficulty = rotationDifficulty;
	}
}
