package com.vallosdck.wordmob.models;

import java.util.List;

/**
 * Created by vallos on 6/30/2016.
 */
public class Book {
	public Book previousSibling;
	public Book nextSibling;
	public String name;
	public String description;
	public List<Chapter> chapters;

	public Book(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public void setRelationships(Book previousSibling, Book nextSibling) {
		this.previousSibling = previousSibling;
		this.nextSibling = nextSibling;
	}
}
