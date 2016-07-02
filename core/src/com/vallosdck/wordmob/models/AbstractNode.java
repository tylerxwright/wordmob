package com.vallosdck.wordmob.models;

import java.util.Arrays;
import java.util.List;

/**
 * Created by vallos on 7/1/2016.
 */
public abstract class AbstractNode <S extends AbstractNode, P extends AbstractNode, C extends AbstractNode> {
	private P parent;
	private List<C> children;
	private S previousSibling;
	private S nextSibling;

	public AbstractNode() {

	}

	public P getParent() {
		return parent;
	}

	public void setParent(P parent) {
		this.parent = parent;
	}

	public List<C> getChildren() {
		return children;
	}

	public void setChildren(List<C> children) {
		this.children = children;
	}

	public void setChildren(C... children) {
		this.children = Arrays.asList(children);
	}

	public S getPreviousSibling() {
		return previousSibling;
	}

	public void setPreviousSibling(S previousSibling) {
		this.previousSibling = previousSibling;
	}

	public S getNextSibling() {
		return nextSibling;
	}

	public void setNextSibling(S nextSibling) {
		this.nextSibling = nextSibling;
	}

	public void setSiblings(S previousSibling, S nextSibling) {
		this.previousSibling = previousSibling;
		this.nextSibling = nextSibling;
	}

	public void addChild(C child) {
		C sibling = children.get(children.size()-1);
		child.setPreviousSibling(sibling);
		children.add(child);
	}
}
