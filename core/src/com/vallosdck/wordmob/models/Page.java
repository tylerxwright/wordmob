package com.vallosdck.wordmob.models;

/**
 * Created by vallos on 6/30/2016.
 */
public class Page extends AbstractNode<Page, Chapter, Line> {

	private String name;

	public Page() { }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
