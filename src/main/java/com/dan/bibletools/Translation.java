/*
 * Translation.java
 * Copyright (c) 2011-2012 Kno, Inc. All rights reserved.
 */
package com.dan.bibletools;

/**
 * Model a bible translation.
 * <p>
 * 
 * @author Dan Borza [dborza@kno.com]
 * @since Jul 10, 2012
 */
public enum Translation {

	AMP("AMP", "amp.xml"),
	CEV("CEV", "cev.xml"),
	ESV("ESV", "esv.xml"),
	KJV("KJV", "kjv.xml"),
	MKJV("MKJV", "mkjv.xml"),
	MSG("MSG", "msg.xml"),
	NASB("NASB", "nasb.xml"),
	NIV("NIV", "niv.xml"),
	NKJV("NKJV", "nkjv.xml"),
	NLT("NLT", "nlt.xml"),
	NRSV("NRSV", "nrsv.xml"),
	TDC("", "");
	
	private final String name;
	
	private final String path;

	Translation(final String name, final String path) {
		this.name = name;
		this.path = "bible-translations/" + path;
	}
	
	public static Translation fromString(final String str) {
		for (final Translation t : Translation.values()) {
			if (t.name.equalsIgnoreCase(str)) {
				return t;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}
	
}
