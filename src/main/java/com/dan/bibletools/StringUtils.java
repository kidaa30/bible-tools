/*
 * StringUtils.java
 * Copyright (c) 2011-2012 Kno, Inc. All rights reserved.
 */
package com.dan.bibletools;

/**
 * Utility methods for dealing with strings.
 * <p>
 * 
 * @author Dan Borza [dborza@kno.com]
 * @since Jul 4, 2012
 */
public class StringUtils {

	public boolean stringsMightMatch(final String s1, final String s2) { 
		return s1.equalsIgnoreCase(s2) || s2.contains(s1) || s1.contains(s2);
	}
	
}
