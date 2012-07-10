/*
 * GenerateMsOfficeDocWithMultipleTranslations.java
 * Copyright (c) 2011-2012 Kno, Inc. All rights reserved.
 */
package com.dan.bibletools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
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
		new GenerateMsExcelDocWithMultipleTranslations().generate(
				new File("/var/tmp/xssf-richtext.xlsx"), 
				BookEnum.Joshua, 
				Translation.NIV,
				Translation.NASB,
				Translation.NRSV,
				Translation.KJV);
	}

	private final BibleUtils bibleUtils = new BibleUtils();
	
	private final byte bibleTranslationColumnIndexCharWidth = 30;
	
	public void generate(final File outputFile, final BookEnum book, final Translation ... translations) throws IOException, JAXBException {
		
		if (translations == null || translations.length == 0) {
			System.err.println("Not generating document because no translations were provided.");
			return;
		}
		
		final Map<Translation, Book> bibleMap = new LinkedHashMap<Translation, Book>();
		
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

		System.out.println("Read bible map: " + bibleMap);

		final XSSFWorkbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
		
		final XSSFCellStyle cs = wb.createCellStyle();
        cs.setWrapText(true);
        
		final XSSFSheet sheet = wb.createSheet();
        
        int columnIndex = 0;
        
        addVerseColumn(1, columnIndex, sheet, bibleMap.get(translations[0]));
        
        for (final Map.Entry<Translation, Book> translationAndBible : bibleMap.entrySet()) {
        	System.out.println("Adding translation " + translationAndBible.getKey() + " to excel sheet.");
        	columnIndex ++;
        	addTranslation(1, columnIndex, sheet, cs, translationAndBible.getKey(), translationAndBible.getValue());
        }
        
        columnIndex ++;
        addCommentsColumn(1, columnIndex, sheet, cs, bibleMap.get(translations[0]));
        
        // Write the output to a file
        FileOutputStream fileOut = null;
        try {
        	fileOut = new FileOutputStream(outputFile);
        	wb.write(fileOut);
        } catch (final IOException e) {
        	System.err.println("Oops!");
        	e.printStackTrace();
        } finally { 
        	if (fileOut != null) {
        		fileOut.close();
        	}
        }
        
        System.out.println("Done!");
	}

	private void addVerseColumn(final int startRow, final int columnIndex, final XSSFSheet sheet, final Book book) {
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
	
	private void addTranslation(final int startRow, int columnIndex, final XSSFSheet sheet, final XSSFCellStyle cs, final Translation t, final Book book) {
		sheet.setColumnWidth(columnIndex, bibleTranslationColumnIndexCharWidth*256);
		XSSFRow row0 = sheet.getRow(startRow - 1);
		row0.createCell(columnIndex).setCellValue(t.getName());
		int rowNumber = startRow;
		for (Chapter c : book.getChapter()) {
			for (Verse v : c.getVerse()) {
				XSSFRow row = sheet.getRow(rowNumber);
				XSSFCell cell = row.createCell(columnIndex);
				cell.setCellStyle(cs);
				cell.setCellValue(v.getValue());
				rowNumber ++;
			}
        }
	}

	private void addCommentsColumn(int startRow, int columnIndex, XSSFSheet sheet, XSSFCellStyle cs, final Book book) {
		sheet.setColumnWidth(columnIndex, bibleTranslationColumnIndexCharWidth*256);
		XSSFRow row0 = sheet.getRow(startRow - 1);
		row0.createCell(columnIndex).setCellValue("Comments");
		int rowNumber = startRow;
		for (Chapter c : book.getChapter()) {
			for (int i = 0; i < c.getVerse().size(); i ++) {
				XSSFRow row = sheet.getRow(rowNumber);
				XSSFCell cell = row.createCell(columnIndex);
				cell.setCellStyle(cs);
				rowNumber ++;
			}
        }
	}

	
}
