/*
 * BookEnum.java
 * Copyright (c) 2011-2012 Kno, Inc. All rights reserved.
 */
package com.dan.bibletools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;

/*
Genesis
Exodus
Leviticus
Numbers
Deuteronomy
Joshua
Judges
Ruth
1 Samuel
2 Samuel
1 Kings
2 Kings
1 Chronicles
2 Chronicles
Ezra
Nehemiah
Esther
Job
Psalms
Proverbs
Ecclesiastes
Song of Solomon
Isaiah
Jeremiah
Lamentations
Ezekiel
Daniel
Hosea
Joel
Amos
Obadiah
Jonah
Micah
Nahum
Habakkuk
Zephaniah
Haggai
Zechariah
Malachi
Matthew
Mark
Luke
John
Acts
Romans
1 Corinthians
2 Corinthians
Galatians
Ephesians
Philippians
Colossians
1 Thessalonians
2 Thessalonians
1 Timothy
2 Timothy
Titus
Philemon
Hebrews
James
1 Peter
2 Peter
1 John
2 John
3 John
Jude
Revelation 
 */

/**
 * {@link Enum} holding the books of the Bible.
 * <p>
 * 
 * @author Dan Borza [dborza@kno.com]
 * @since Jul 4, 2012
 */
public enum BookEnum {

	Genesis(TestamentEnum.Old, "Genesis", 1, 1),
	Exodus(TestamentEnum.Old, "Exodus", 1, 1),
	Leviticus(TestamentEnum.Old, "Leviticus", 1, 1),
	Numbers(TestamentEnum.Old, "Numbers", 1, 1),
	Deuteronomy(TestamentEnum.Old, "Deuteronomy", 1, 1),
	Joshua(TestamentEnum.Old, "Joshua", 1, 24),
	
	Matthew(TestamentEnum.New, "Matthew", 1, 1),
	Mark(TestamentEnum.New, "Mark", 1, 1),
	Luke(TestamentEnum.New, "Luke", 1, 1),
	John(TestamentEnum.New, "John", 1, 1),
    Corinthians_1(TestamentEnum.New, "1 Corinthians", 1, 13),
	Corinthians_2(TestamentEnum.New, "2 Corinthians", 1, 13),
    Colossians(TestamentEnum.New, "Colossians", 1, 4),
	Peter_1(TestamentEnum.New, "1 Peter", 1, 5),
	Peter_2(TestamentEnum.New, "2 Peter", 1, 3);
	
	private final TestamentEnum testament;
	
	private final String bookName;
	
	private final int firstChapter;
	
	private final int lastChapter;
	
	private BookEnum(final TestamentEnum testament, final String name, final int firstChapter, final int lastChapter) {
		this.testament = testament;
		this.bookName = name;
		this.firstChapter = firstChapter;
		this.lastChapter = lastChapter;
	}

	/**
	 * @return A {@link EnumSet} containing all books from the {@link TestamentEnum#New} testament.
	 */
	public EnumSet<BookEnum> getAllForNewTestament() {
		return getAllForTestament(TestamentEnum.New);
	}
	
	/**
	 * @return A {@link EnumSet} containing all books from the {@link TestamentEnum#Old} testament.
	 */
	public EnumSet<BookEnum> getAllForOldTestament() {
		return getAllForTestament(TestamentEnum.Old);
	}
	
	/**
	 * @param testament	The {@link TestamentEnum} for which we want to find all the {@link BookEnum}s.
	 * @return	A {@link EnumSet} containing all books for the specified {@link TestamentEnum} testament.
	 */
	public EnumSet<BookEnum> getAllForTestament(final TestamentEnum testament) {		
		final Collection<BookEnum> books = new ArrayList<BookEnum>();
		for (final BookEnum book : BookEnum.values()) {
			if (book.getTestament() == testament) {
				books.add(book);
			}
		}
		return EnumSet.copyOf(books);
	};
	
	public TestamentEnum getTestament() {
		return testament;
	}

	public String getName() {
		return bookName;
	}
	
	
	public String getBookName() {
		return bookName;
	}

	public int getFirstChapter() {
		return firstChapter;
	}

	public int getLastChapter() {
		return lastChapter;
	}
	
}