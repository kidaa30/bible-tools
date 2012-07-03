/*
 * BibleReference.java
 * Copyright (c) 2011-2012 Kno, Inc. All rights reserved.
 */
package com.dan.bibletools;

/**
 * Models a reference to a bible translation, book, chapter and verse.
 * <p>
 * 
 * @author Dan Borza [dborza@kno.com]
 * @since Jul 3, 2012
 */
public class BibleReference {

	private final String translation;
	
	private final String book;
	
	private final Integer chapter;
	
	private final Integer verse;

	public BibleReference(String translation, String book, Integer chapter, Integer verse) {
		this.translation = translation;
		this.book = book;
		this.chapter = chapter;
		this.verse = verse;
	}

	public String getTranslation() {
		return translation;
	}

	public String getBook() {
		return book;
	}

	public Integer getChapter() {
		return chapter;
	}

	public Integer getVerse() {
		return verse;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (obj == null || !(obj instanceof BibleReference)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		final BibleReference that = (BibleReference) obj;
		//	not pretty, but don't care for now.
		return translation.equals(that.translation) && book.equals(that.book) && chapter.equals(that.chapter) && verse.equals(that.verse);
	}
	
	@Override
	public int hashCode() {
		//	not pretty, but don't care for now.
		return translation.hashCode() + book.hashCode() + chapter.hashCode() + verse.hashCode();
	}

	@Override
	public String toString() {
		return "BibleReference [translation=" + translation + ", book=" + book
				+ ", chapter=" + chapter + ", verse=" + verse + "]";
	}
	
}
