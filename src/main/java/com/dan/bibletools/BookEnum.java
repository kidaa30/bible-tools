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

	Genesis(TestamentEnum.Old, "Genesis"),
	Exodus(TestamentEnum.Old, "Exodus"),
	Leviticus(TestamentEnum.Old, "Leviticus"),
	Numbers(TestamentEnum.Old, "Numbers"),
	Deuteronomy(TestamentEnum.Old, "Deuteronomy"),
	
	Matthew(TestamentEnum.New, "Matthew"),
	Mark(TestamentEnum.New, "Mark"),
	Luke(TestamentEnum.New, "Luke"),
	John(TestamentEnum.New, "John");
	
	private final TestamentEnum testament;
	
	private final String bookName;
	
	private BookEnum(final TestamentEnum testament, final String name) {
		this.testament = testament;
		this.bookName = name;
	}

	public TestamentEnum getTestament() {
		return testament;
	}

	public String getName() {
		return bookName;
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
	
}