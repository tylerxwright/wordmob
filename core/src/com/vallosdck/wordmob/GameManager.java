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

	private void manuallyLoadData() {
		/*library = new Library();

		Book book1 = new Book();

		Chapter chapter1 = new Chapter("Chapter 1", "The first chapter");
		Chapter chapter2 = new Chapter("Chapter 2", "The second chapter");

		Page page1 = new Page();
		Page page2 = new Page();
		Page page3 = new Page();
		Page page4 = new Page();

		Line line1 = new Line("A long time ago");
		Line line2 = new Line("Very close to here");
		Line line3 = new Line("There lived a man");
		Line line4 = new Line("A very strange man");
		Line line5 = new Line("His name was Steve");
		Line line6 = new Line("Steve would save the world");

		Line line7 = new Line("Steve led a simple life");
		Line line8 = new Line("He had no wife or kids");
		Line line9 = new Line("He worked a boring job");
		Line line10 = new Line("And he was always alone");
		Line line11 = new Line("Except for his dog Max");
		Line line12 = new Line("Max was his only friend");

		Line line13 = new Line("One day Steve went walking");
		Line line14 = new Line("He walked down the block");
		Line line15 = new Line("He walked through town");
		Line line16 = new Line("Steve walked over the tracks");
		Line line17 = new Line("Steve just kept on walking");
		Line line18 = new Line("Max walked by his side");

		Line line19 = new Line("Steve did not look back");
		Line line20 = new Line("He had decided his fate");
		Line line21 = new Line("His past was left behind");
		Line line22 = new Line("Along with his boring life");
		Line line23 = new Line("No one would miss him");
		Line line24 = new Line("Steve was breaking free");

		library.setChildren(book1);
		book1.setChildren(chapter1, chapter2);
		chapter1.setNextSibling(chapter2);
		chapter1.setChildren(page1, page2);
		page1.setNextSibling(page2);
		page1.setChildren(line1, line2, line3, line4, line5, line6);
		line1.setNextSibling(line2);
		line2.setSiblings(line1, line3);
		line3.setSiblings(line2, line4);
		line4.setSiblings(line4, line5);
		line5.setSiblings(line5, line6);
		line6.setPreviousSibling(line5);
		page2.setPreviousSibling(page1);
		page2.setChildren(line7, line8, line9, line10, line11, line12);
		line7.setNextSibling(line8);
		line8.setSiblings(line7, line9);
		line9.setSiblings(line8, line10);
		line10.setSiblings(line9, line11);
		line11.setSiblings(line10, line12);
		line12.setPreviousSibling(line11);
		chapter2.setPreviousSibling(chapter1);
		chapter2.setChildren(page3, page4);
		page3.setNextSibling(page4);
		page3.setChildren(line13, line14, line15, line16, line17, line18);
		line13.setNextSibling(line14);
		line14.setSiblings(line13, line15);
		line15.setSiblings(line14, line16);
		line16.setSiblings(line15, line17);
		line17.setSiblings(line16, line18);
		line18.setPreviousSibling(line17);
		page4.setPreviousSibling(page3);
		page4.setChildren(line19, line20, line21, line22, line23, line24);
		line19.setNextSibling(line20);
		line20.setSiblings(line19, line21);
		line21.setSiblings(line20, line22);
		line22.setSiblings(line21, line23);
		line23.setSiblings(line22, line24);
		line24.setPreviousSibling(line23);*/
	}
}
