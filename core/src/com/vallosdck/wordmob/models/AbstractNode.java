package com.vallosdck.wordmob.models;

import java.util.List;

/**
 * Created by vallos on 7/1/2016.
 */
public abstract class AbstractNode <P extends AbstractNode, C extends AbstractNode> {
	private P parent;
	private List<C> children;
	private AbstractNode<P, C> previousSibling;
	private AbstractNode<P, C> nextSibling;

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

	public AbstractNode<P, C> getPreviousSibling() {
		return previousSibling;
	}

	public void setPreviousSibling(AbstractNode<P, C> previousSibling) {
		this.previousSibling = previousSibling;
	}

	public AbstractNode<P, C> getNextSibling() {
		return nextSibling;
	}

	public void setNextSibling(AbstractNode<P, C> nextSibling) {
		this.nextSibling = nextSibling;
	}

	public void addChild(C child) {
		C sibling = children.get(children.size()-1);
		child.setPreviousSibling(sibling);
		children.add(child);
	}
}
