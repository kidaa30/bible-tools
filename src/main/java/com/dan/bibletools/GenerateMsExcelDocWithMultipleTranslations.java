/*
 * GenerateMsOfficeDocWithMultipleTranslations.java
 * Copyright (c) 2011-2012 Kno, Inc. All rights reserved.
 */
package com.dan.bibletools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dan.bibletools.Bible.Book;
import com.dan.bibletools.Bible.Book.Chapter;
import com.dan.bibletools.Bible.Book.Chapter.Verse;

/**
 * Generate a Ms Excel Document with multiple parallel translations.
 * <p>
 * 
 * @author Dan Borza [dborza@kno.com]
 * @since Jul 10, 2012
 */
public class GenerateMsExcelDocWithMultipleTranslations {

	public static void main(String[] args) throws IOException, JAXBException {
		new GenerateMsExcelDocWithMultipleTranslations().generate(new File("/var/tmp/table1.doc"), BookEnum.Joshua, Translation.NIV, Translation.KJV);
	}

	private final BibleUtils bibleUtils = new BibleUtils();
	
	public void generate(final File outputFile, final BookEnum book, final Translation ... translations) throws IOException, JAXBException {
		
		if (translations == null || translations.length == 0) {
			System.err.println("Not generating document because no translations were provided.");
			return;
		}
		
		final Map<Translation, Book> bibleMap = new HashMap<Translation, Book>();
		
		for (final Translation t : translations) {
			System.out.println("Reading translation " + t + " in memory.");
			final File f = new File(t.getPath());
			if (!f.exists()) {
				System.err.println("Could not find file '" + f.getAbsolutePath() + "'");
			} else {
				final Bible bible = bibleUtils.readBible(f);
				for (final Book b : bible.getBook()) {
					if (b.getName().equalsIgnoreCase(book.getName())) {
						bibleMap.put(t, b);
					}
				}
			}
		}

		System.out.println("read bible map: " + bibleMap);

		XSSFWorkbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();

        XSSFSheet sheet = wb.createSheet();
        
        createVerseColumn(1, 0, sheet, bibleMap.get(translations[0]));
        
        for (final Translation t : translations) {
        	
        }
        
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("/var/tmp/xssf-richtext.xlsx");
        wb.write(fileOut);
        fileOut.close();
        
	}

	private void createVerseColumn(final int startRow, final int columnIndex, final XSSFSheet sheet, final Book book) {
		XSSFRow row0 = sheet.createRow(startRow - 1);
		row0.createCell(columnIndex).setCellValue("Ref");
		int rowNumber = startRow;
		for (Chapter c : book.getChapter()) {
			for (Verse v : c.getVerse()) {
				XSSFRow row = sheet.createRow(rowNumber);
				XSSFCell cell = row.createCell(columnIndex);
				cell.setCellValue(c.getName() + ":" + v.getName());
				rowNumber ++;
			}
        }
	}

	private int getTotalVerseNumbers(Book book) {
		if (book == null) {
			return 0;
		}
		int rowNumber = 0;
		for (final Chapter c : book.getChapter()) {
			rowNumber += c.getVerse().size();			
		}
		return rowNumber;
	}
	
}
