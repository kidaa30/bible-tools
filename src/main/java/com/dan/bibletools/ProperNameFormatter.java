/*
 * ProperNameFormatter.java
 * Copyright (c) 2011-2012 Kno, Inc. All rights reserved.
 */
package com.dan.bibletools;

/**
 * Format a proper name that is found in the text by eliminating wrong characters that might be in there after the parsing.
 * <p>
 * 
 * @author Dan Borza [dborza@kno.com]
 * @since Jul 4, 2012
 */
public class ProperNameFormatter {

	public String formatName(final String inputName) {
		return inputName.trim().
				replaceAll(",", "").
				replaceAll("\\.", "").
				replaceAll(":", "").
				replaceAll(";", "").
				replaceAll("\\)", "").
				replaceAll("\"", "");
				
	}
	
}
