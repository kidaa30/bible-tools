//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.07.03 at 11:25:59 PM EEST 
//


package com.dan.bibletools;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dan.bibletools package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dan.bibletools
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Bible.Book.Chapter.Verse }
     * 
     */
    public Bible.Book.Chapter.Verse createBibleBookChapterVerse() {
        return new Bible.Book.Chapter.Verse();
    }

    /**
     * Create an instance of {@link Bible.Book.Chapter }
     * 
     */
    public Bible.Book.Chapter createBibleBookChapter() {
        return new Bible.Book.Chapter();
    }

    /**
     * Create an instance of {@link Bible.Book }
     * 
     */
    public Bible.Book createBibleBook() {
        return new Bible.Book();
    }

    /**
     * Create an instance of {@link Bible }
     * 
     */
    public Bible createBible() {
        return new Bible();
    }

}
