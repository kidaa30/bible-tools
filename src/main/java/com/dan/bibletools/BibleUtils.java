/*
 * BibleUtils.java
 * Copyright (c) 2011-2012 Kno, Inc. All rights reserved.
 */
package com.dan.bibletools;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Utils for dealing with {@link Bible} objects.
 * <p>
 * 
 * @author Dan Borza [dborza@kno.com]
 * @since Jul 10, 2012
 */
public class BibleUtils {

	public Bible readBible(final File file) throws JAXBException {
		final JAXBContext ctx = JAXBContext.newInstance(new Class[] {Bible.class});
		final Unmarshaller um = ctx.createUnmarshaller();
		final Bible bible = (Bible) um.unmarshal(file);
		return bible;
	}
	
}
