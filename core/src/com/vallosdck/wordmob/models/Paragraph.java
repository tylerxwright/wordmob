package com.vallosdck.wordmob.models;

import java.util.List;

/**
 * Created by vallos on 6/30/2016.
 */
public class Paragraph {
	public Chapter parent;
	public Paragraph previousSibling;
	public Paragraph nextSibling;
	public List<Line> lines;

	public Paragraph() { }

	public void setRelationships(Chapter parent, Paragraph previousSibling, Paragraph nextSibling) {
		this.parent = parent;
		this.previousSibling = previousSibling;
		this.nextSibling = nextSibling;
	}
}
