package com.vallosdck.wordmob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.vallosdck.wordmob.models.AbstractNode;
import com.vallosdck.wordmob.models.Book;
import com.vallosdck.wordmob.models.Chapter;
import com.vallosdck.wordmob.models.Library;
import com.vallosdck.wordmob.models.Line;
import com.vallosdck.wordmob.models.Page;

import java.util.List;

/**
 * Created by vallos on 6/30/2016.
 */
public class GameManager {

	public static final String TAG = GameManager.class.getName();
	public static final GameManager instance = new GameManager();

	public Library library;
	public Book currentBook;
	public Chapter currentChapter;
	public Page currentPage;
	public Line currentLine;

	private GameManager() {}

	public void init() {
		loadData();
	}

	public void reset() {
		init();
		currentBook = library.getChildren().get(0);
		currentChapter = currentBook.getChildren().get(0);
		currentPage = currentChapter.getChildren().get(0);
		currentLine = currentPage.getChildren().get(0);
	}

	public boolean anyMoreBooks() {
		return currentBook.getNextSibling() != null;
	}

	public boolean anyMoreChapters() {
		return currentChapter.getNextSibling() != null;
	}

	public boolean anyMorePages() {
		return currentPage.getNextSibling() != null;
	}

	public boolean anyMoreLines() {
		return currentLine.getNextSibling() != null;
	}

	public Book nextBook() throws Exception {
		if(anyMoreBooks()) {
			currentBook = currentBook.getNextSibling();
			currentChapter = currentBook.getChildren().get(0);
			currentPage = currentChapter.getChildren().get(0);
			currentLine = currentPage.getChildren().get(0);
			return currentBook;
		}
		throw new Exception("No more books");
	}

	public Chapter nextChapter() throws Exception {
		if(anyMoreChapters()) {
			currentChapter = currentChapter.getNextSibling();
			currentPage = currentChapter.getChildren().get(0);
			currentLine = currentPage.getChildren().get(0);
			return currentChapter;
		}
		throw new Exception("No more chapters");
	}

	public Page nextPage() throws Exception {
		if(anyMorePages()) {
			currentPage = currentPage.getNextSibling();
			currentLine = currentPage.getChildren().get(0);
			return currentPage;
		}
		throw new Exception("No more paragraphs");
	}

	public Line nextLine() throws Exception {
		if(anyMoreLines()) {
			currentLine = currentLine.getNextSibling();
			return currentLine;
		}
		throw new Exception("No more lines");
	}

	private void loadData() {
		FileHandle libraryJson = Gdx.files.internal("data/library.json");
		Json json = new Json();
		json.setOutputType(JsonWriter.OutputType.minimal);
		library = json.fromJson(Library.class, libraryJson.readString());

		for(Book book : library.getChildren()) {
			buildRelationships(book);
		}

		int x = 1;
	}

	private <T extends AbstractNode> void buildRelationships(T parent) {
		List<T> children = parent.getChildren();
		if(children != null && children.size() > 0) {
			T previousSibling = null;
			for(T child : children) {
				buildRelationships(child);
				child.setParent(parent);
				if(previousSibling != null) {
					child.setPreviousSibling(previousSibling);
					previousSibling.setNextSibling(child);
				}
				previousSibling = child;
			}
		}
	}
}
