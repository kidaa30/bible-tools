/*
 * CollectionUtils.java
 * Copyright (c) 2011-2012 Kno, Inc. All rights reserved.
 */
package com.dan.bibletools;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Utility class for dealing with collecitons.
 * <p>
 * 
 * @author Dan Borza [dborza@kno.com]
 * @since Jul 4, 2012
 */
public class CollectionUtils {

	public Collection<Integer> buildIntervalCollection(final int start, final int stop) {
		final Collection<Integer> col = new ArrayList<Integer>();
		for (int i = start; i <= stop; i ++) {
			col.add(i);
		}
		return col;
	}
	
}
