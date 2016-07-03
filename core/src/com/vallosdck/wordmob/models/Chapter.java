package com.vallosdck.wordmob.models;

import java.util.List;

/**
 * Created by vallos on 6/30/2016.
 */
public class Chapter extends AbstractNode<Chapter, Book, Page> {
	public String name;
	public String description;

	public Chapter() {}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
