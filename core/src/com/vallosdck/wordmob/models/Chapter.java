package com.vallosdck.wordmob.models;

import java.util.List;

/**
 * Created by vallos on 6/30/2016.
 */
public class Chapter {
	public Book parent;
	public Chapter previousSibling;
	public Chapter nextSibling;
	public String name;
	public String description;
	public List<Paragraph> paragraphs;

	public Chapter(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public void setRelationships(Book parent, Chapter previousSibling, Chapter nextSibling) {
		this.parent = parent;
		this.previousSibling = previousSibling;
		this.nextSibling = nextSibling;
	}
}
