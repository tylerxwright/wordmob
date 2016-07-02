package com.vallosdck.wordmob.models;

/**
 * Created by vallos on 6/30/2016.
 */
public class Book extends AbstractNode<Book, Book, Chapter> {
	public String name;
	public String description;

	public Book(String name, String description) {
		this.name = name;
		this.description = description;
	}
}
