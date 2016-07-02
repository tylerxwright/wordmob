package com.vallosdck.wordmob.models;

/**
 * Created by vallos on 6/30/2016.
 */
public class Line extends AbstractNode<Line, Page, Line>{
	public String sentence;

	public Line(String sentence) {
		this.sentence = sentence;
	}
}
