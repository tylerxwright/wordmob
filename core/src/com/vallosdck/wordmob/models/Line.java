package com.vallosdck.wordmob.models;

/**
 * Created by vallos on 6/30/2016.
 */
public class Line extends AbstractNode<Line, Page, Line>{
	private String name;
	private String sentence;
	private double time;

	public Line() {}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSentence() {
		return this.sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}
}
