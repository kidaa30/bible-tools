/*
 * Main.java
 * Copyright (c) 2011-2012 Kno, Inc. All rights reserved.
 */
package com.dan.bibletools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;

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
public class GenerateProperNamesFromBook {

	public static void main(String[] args) throws JAXBException {
	
		//	bible translation
		final String infile = "bible-translations/niv.xml";

		//	book
		final BookEnum bookEnum = BookEnum.Joshua;
		
		//	build xml in-memory object 
		final JAXBContext ctx = JAXBContext.newInstance(new Class[] {Bible.class});
		final Unmarshaller um = ctx.createUnmarshaller();
		final Bible bible = (Bible) um.unmarshal(new File(infile));
		
		//	build chapter interval
		final Collection<Integer> chapterInterval = new CollectionUtils().buildIntervalCollection(1, 24);
		
		//	get the map between proper names and their bible references + sort them by key 
		final Map<String, Set<BibleReference>> wordRefMap = new TreeMap<String, Set<BibleReference>>(getAllNamesAndReferencesForBookAndChapters(bookEnum, chapterInterval, bible));
    
		//	print the results
		for (final Map.Entry<String, Set<BibleReference>> entry : wordRefMap.entrySet()) {
			final String word = entry.getKey();
			final String referencesString = buildReferencesString(entry.getValue());
			System.out.println(word + " - " + referencesString);
		}
		
		final Set<String> nivPlaces = wordRefMap.keySet(); 
				
		System.out.println("Places found in http://en.wikipedia.org/wiki/List_of_biblical_places (niv, wiki): ");
		
		final StringUtils stringUtils = new StringUtils();
		
		try {
			final Collection<String> wikiPlaces = FileUtils.readLines(new File("src/main/resources/wikiplaces.txt"));
			for (final String wikiPlace : wikiPlaces) {
				final String [] wikiPlaceParts = wikiPlace.split("-");
				for (final String wikiPlacePart : wikiPlaceParts) {
					for (final String nivPlace : nivPlaces) {
						if (stringUtils.stringsMightMatch(wikiPlacePart, nivPlace)) {
							System.out.println(nivPlace + "-" + wikiPlacePart);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static String buildReferencesString(final Set<BibleReference> set) {
		final StringBuilder sb = new StringBuilder();
		for (final BibleReference ref : set) {
			sb.append(ref.getChapter()).append(":").append(ref.getVerse()).append(", ");
		}
		return sb.toString();
	}

	@SuppressWarnings("unused")
	private static Map<String, Set<BibleReference>> getAllNamesAndReferencesForBook(
			final BookEnum bookEnum,
			final Bible bible) {
	
		return getAllNamesAndReferencesForBookAndChapters(
				bookEnum, 
				new CollectionUtils().buildIntervalCollection(bookEnum.getFirstChapter(), bookEnum.getLastChapter()),
				bible);
	}
	
	private static Map<String, Set<BibleReference>> getAllNamesAndReferencesForBookAndChapters(
			final BookEnum bookEnum,
			final Collection<Integer> chapters, 
			final Bible bible) {
		
		//	mapping between a word and it's references.
		final Map<String, Set<BibleReference>> wordRefMap = new LinkedHashMap<String, Set<BibleReference>>();
		
		final ProperNameFormatter properNameFormatter = new ProperNameFormatter();
		
		final Collection<String> stopWords = new ArrayList<String>();
		
		try {
			final Collection<String> englishStopWords = FileUtils.readLines(new File("src/main/resources/english_stopwords.txt"));
			stopWords.addAll(englishStopWords);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final StopWordsChecker stopWordsChecker = new StopWordsChecker(stopWords);
		
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
										if (!stopWordsChecker.isStopWord(_word)) {
											if (Character.isUpperCase(_word.charAt(0))) {
												if (wordRefMap.get(_word) == null) {
													wordRefMap.put(_word, new LinkedHashSet<BibleReference>());
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
		}
		
		return wordRefMap;
	}
	
}
