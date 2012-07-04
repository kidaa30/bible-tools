/*
 * StopWordsChecker.java
 * Copyright (c) 2011-2012 Kno, Inc. All rights reserved.
 */
package com.dan.bibletools;

import java.util.Collection;
import java.util.TreeSet;

/**
 * Check if a given word is contained in a stop words list.
 * <p>
 * 
 * @author Dan Borza [dborza@kno.com]
 * @since Jul 4, 2012
 */
public class StopWordsChecker {

	private final Collection<String> stopWords = new TreeSet<String>();
	
	public StopWordsChecker(final Collection<String> stopWords) {
		for (final String stopWord : stopWords) {
			this.stopWords.add(stopWord.trim().toLowerCase());
		}
	}
	
	public boolean isStopWord(final String str) {
		return stopWords.contains(str.trim().toLowerCase());
	}
	
}
