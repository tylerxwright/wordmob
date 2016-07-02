package com.vallosdck.wordmob;

import com.vallosdck.wordmob.models.Book;
import com.vallosdck.wordmob.models.Chapter;
import com.vallosdck.wordmob.models.Line;
import com.vallosdck.wordmob.models.Paragraph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by vallos on 6/30/2016.
 */
public class GameManager {

	public static final String TAG = GameManager.class.getName();
	public static final GameManager instance = new GameManager();

	private Book book;
	public Book currentBook;
	public Chapter currentChapter;
	public Paragraph currentParagraph;
	public Line currentLine;

	private GameManager() {}

	public void init() {

		Tree
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

		Paragraph paragraph1 = new Paragraph();
		paragraph1.lines = new ArrayList<Line>(
				Arrays.asList(line1, line2, line3, line4, line5, line6));

		line1.setRelationships(paragraph1, null, line2);
		line2.setRelationships(paragraph1, line1, line3);
		line3.setRelationships(paragraph1, line2, line4);
		line4.setRelationships(paragraph1, line3, line5);
		line5.setRelationships(paragraph1, line4, line6);
		line6.setRelationships(paragraph1, line5, null);

		Paragraph paragraph2 = new Paragraph();
		paragraph2.lines = new ArrayList<Line>(
				Arrays.asList(line7, line8, line9, line10, line11, line12));

		line7.setRelationships(paragraph2, null, line8);
		line8.setRelationships(paragraph2, line7, line9);
		line9.setRelationships(paragraph2, line8, line10);
		line10.setRelationships(paragraph2, line9, line11);
		line11.setRelationships(paragraph2, line10, line12);
		line12.setRelationships(paragraph2, line11, null);

		Paragraph paragraph3 = new Paragraph();
		paragraph3.lines = new ArrayList<Line>(
				Arrays.asList(line13, line14, line15, line16, line17, line18));

		line13.setRelationships(paragraph3, null, line14);
		line14.setRelationships(paragraph3, line13, line15);
		line15.setRelationships(paragraph3, line14, line16);
		line16.setRelationships(paragraph3, line15, line17);
		line17.setRelationships(paragraph3, line16, line18);
		line18.setRelationships(paragraph3, line17, null);

		Paragraph paragraph4 = new Paragraph();
		paragraph4.lines = new ArrayList<Line>(
				Arrays.asList(line19, line20, line21, line22, line23, line24));

		line19.setRelationships(paragraph4, null, line20);
		line20.setRelationships(paragraph4, line19, line21);
		line21.setRelationships(paragraph4, line20, line22);
		line22.setRelationships(paragraph4, line21, line23);
		line23.setRelationships(paragraph4, line22, line24);
		line24.setRelationships(paragraph4, line23, null);

		Chapter chapter1 = new Chapter("Chapter 1", "The first chapter");
		chapter1.paragraphs = new ArrayList<Paragraph>(
				Arrays.asList(paragraph1, paragraph2));

		paragraph1.setRelationships(chapter1, null, paragraph2);
		paragraph2.setRelationships(chapter1, paragraph1, null);

		Chapter chapter2 = new Chapter("Chapter 2", "The second chapter");
		chapter2.paragraphs = new ArrayList<Paragraph>(
				Arrays.asList(paragraph3, paragraph4));

		paragraph3.setRelationships(chapter2, null, paragraph4);
		paragraph4.setRelationships(chapter2, paragraph3, null);

		book = new Book("Book 1", "The first book");
		book.chapters = new ArrayList<Chapter>(
				Arrays.asList(chapter1, chapter2));

		chapter1.setRelationships(book, null, chapter2);
		chapter2.setRelationships(book, chapter1, null);

		book.setRelationships(null, null);
	}

	public void reset() {
		init();
		currentBook = book;
		currentChapter = currentBook.chapters.get(0);
		currentParagraph = currentChapter.paragraphs.get(0);
		currentLine = currentParagraph.lines.get(0);
	}

	public boolean anyMoreLines() {
		return currentLine.nextSibling != null;
	}

	public boolean anyMoreParagraphs() {
		return currentParagraph.nextSibling != null;
	}

	public boolean anyMoreChapters() {
		return currentChapter.nextSibling != null;
	}

	public boolean anyMoreBooks() {
		return currentBook.nextSibling != null;
	}

	public Line nextLine() throws Exception {
		if(anyMoreLines()) {
			currentLine = currentLine.nextSibling;
			return currentLine;
		}
		throw new Exception("No more lines");
	}

	public Paragraph nextParagraph() throws Exception {
		if(anyMoreParagraphs()) {
			currentParagraph = currentParagraph.nextSibling;
			currentLine = currentParagraph.lines.get(0);
			return currentParagraph;
		}
		throw new Exception("No more paragraphs");
	}

	public Chapter nextChapter() throws Exception {
		if(anyMoreChapters()) {
			currentChapter = currentChapter.nextSibling;
			currentParagraph = currentChapter.paragraphs.get(0);
			currentLine = currentParagraph.lines.get(0);
			return currentChapter;
		}
		throw new Exception("No more chapters");
	}

	public Book nextBook() throws Exception {
		if(anyMoreBooks()) {
			currentBook = currentBook.nextSibling;
			currentChapter = currentChapter.nextSibling;
			currentParagraph = currentChapter.paragraphs.get(0);
			currentLine = currentParagraph.lines.get(0);
			return currentBook;
		}
		throw new Exception("No more books");
	}
}
