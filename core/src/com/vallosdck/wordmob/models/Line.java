package com.vallosdck.wordmob.models;

/**
 * Created by vallos on 6/30/2016.
 */
public class Line {
	public Paragraph parent;
	public String sentence;
	public Line previousSibling;
	public Line nextSibling;

	public Line(String sentence) {

		this.sentence = sentence;
	}

	public void setRelationships(Paragraph parent, Line previousSibling, Line nextSibling) {
		this.parent = parent;
		this.previousSibling = previousSibling;
		this.nextSibling = nextSibling;
	}
}
