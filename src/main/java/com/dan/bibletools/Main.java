/*
 * Main.java
 * Copyright (c) 2011-2012 Kno, Inc. All rights reserved.
 */
package com.dan.bibletools;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.dan.bibletools.Bible.Book;
import com.dan.bibletools.Bible.Book.Chapter;
import com.dan.bibletools.Bible.Book.Chapter.Verse;

/**
 * Just a simple initial hack.
 * <p>
 * 
 * @author Dan Borza [dborza@kno.com]
 * @since Jul 3, 2012
 */
public class Main {

	public static void main(String[] args) throws JAXBException {
	
		//	translation
		final String infile = "bible-translations/niv.xml";

		final BookEnum bookEnum = BookEnum.Joshua;
		
		//	chapters for which we'll execute the code
		final Collection<Integer> chapters = new CollectionUtils().buildIntervalCollection(bookEnum.getFirstChapter(), bookEnum.getLastChapter());
		
		final JAXBContext ctx = JAXBContext.newInstance(new Class[] {Bible.class});
		final Unmarshaller um = ctx.createUnmarshaller();
		final Bible bible = (Bible) um.unmarshal(new File(infile));
		
		final Map<String, Set<BibleReference>> wordRefMap = new TreeMap<String, Set<BibleReference>>(getAllNamesAndReferencesForBookAndChapters(bookEnum, chapters, bible));
    
		for (final Map.Entry<String, Set<BibleReference>> entry : wordRefMap.entrySet()) {
			final String word = entry.getKey();
			final Set<BibleReference> refs = entry.getValue();
			System.out.println(word + " : " + refs.toString());
		}
		
	}

	private static Map<String, Set<BibleReference>> getAllNamesAndReferencesForBookAndChapters(
			final BookEnum bookEnum,
			final Collection<Integer> chapters, 
			final Bible bible) {
		
		//	mapping between a word and it's references.
		final Map<String, Set<BibleReference>> wordRefMap = new HashMap<String, Set<BibleReference>>();
		
		final ProperNameFormatter properNameFormatter = new ProperNameFormatter();
		
		for (final Book book : bible.getBook()) {
			if (bookEnum.getName().equalsIgnoreCase(book.getName())) {
				for (final Chapter chapter : book.getChapter()) {
					if (chapters.contains(chapter.getName())) {
						for (final Verse verse : chapter.getVerse()) {
							final BibleReference bibleReference = new BibleReference("niv", book.getName(), chapter.getName(), verse.getName());
							final String text = verse.value;
							final String [] words = text.split(" |\\-");
							for (final String word : words) {
								if (word.length() != 0) {
									final String _word = properNameFormatter.formatName(word);
									if (_word.length() != 0) {
										if (Character.isUpperCase(_word.charAt(0))) {
											if (wordRefMap.get(_word) == null) {
												wordRefMap.put(_word, new HashSet<BibleReference>());
											}
											wordRefMap.get(_word).add(bibleReference);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return wordRefMap;
	}
	
}
