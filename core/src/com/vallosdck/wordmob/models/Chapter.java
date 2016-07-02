package com.vallosdck.wordmob.models;

import java.util.List;

/**
 * Created by vallos on 6/30/2016.
 */
public class Chapter extends AbstractNode<Chapter, Book, Page> {
	public String name;
	public String description;

	public Chapter(String name, String description) {
		this.name = name;
		this.description = description;
	}
}
