/*
 * Main.java
 * Copyright (c) 2011-2012 Kno, Inc. All rights reserved.
 */
package com.dan.bibletools;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	
		//	mapping between a word and it's references.
		final Map<String, Set<BibleReference>> wordRefMap = new HashMap<String, Set<BibleReference>>();
		
		final String infile = "bible-translations/niv.xml";
		
		final JAXBContext ctx = JAXBContext.newInstance(new Class[] {Bible.class});
		final Unmarshaller um = ctx.createUnmarshaller();
		final Bible bible = (Bible) um.unmarshal(new File(infile));
		for (final Book book : bible.getBook()) {
			if (book.getName().equals("Joshua")) {
				for (final Chapter chapter : book.getChapter()) {
					for (final Verse verse : chapter.getVerse()) {
						final BibleReference bibleReference = new BibleReference("niv", book.getName(), chapter.getName(), verse.getName());
						final String text = verse.value;
						final String [] words = text.split(" ");
						for (final String word : words) {
							final String _word = word.trim();
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
    
		for (final Map.Entry<String, Set<BibleReference>> entry : wordRefMap.entrySet()) {
			final String word = entry.getKey();
			final Set<BibleReference> refs = entry.getValue();
			System.out.println(word + ":" + refs.toString());
		}
		
	}
	
}
