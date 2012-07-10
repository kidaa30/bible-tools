/*
 * GenerateMsOfficeDocWithMultipleTranslations.java
 * Copyright (c) 2011-2012 Kno, Inc. All rights reserved.
 */
package com.dan.bibletools;

import java.io.File;

/**
 * Generate a Ms Office Document with multiple parallel translations.
 * <p>
 * 
 * @author Dan Borza [dborza@kno.com]
 * @since Jul 10, 2012
 */
public class GenerateMsOfficeDocWithMultipleTranslations {

	public static void main(String[] args) {
		new GenerateMsOfficeDocWithMultipleTranslations().generate(new File("x"), Translation.NIV, Translation.KJV);
	}
	
	public void generate(final File outputFile, final Translation ... translations) {
		if (translations == null || translations.length == 0) {
			System.err.println("Not generating document because no translations were provided.");
			return;
		}
		for (final Translation t : translations) {
			final File f = new File(t.getPath());
			if (!f.exists()) {
				System.err.println("Could not find file '" + f.getAbsolutePath() + "'");
			}
		}
	}
	
}
