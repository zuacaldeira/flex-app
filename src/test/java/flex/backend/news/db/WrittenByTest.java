/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.db;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class WrittenByTest {
    
    public WrittenByTest() {
    }

    /**
     * Test of getArticle method, of class WrittenBy.
     * @param writtenBy
     * @param expected
     */
    @Test
    @UseDataProvider("articleProvider")
    public void testGetArticle(WrittenBy writtenBy, NewsArticle expected) {
        System.out.println("getArticle");
        assertEquals(expected, writtenBy.getArticle());
    }
    
    @DataProvider
    public static Object[][] articleProvider() {
        NewsArticle article = new NewsArticle();
        WrittenBy writtenBy = new WrittenBy();
        writtenBy.setArticle(article);
        return new Object[][] {
            {writtenBy, article}
        };
    }

    /**
     * Test of SetArticle method, of class WrittenBy.
     * @param writtenBy
     * @param oldArticle
     * @param newArticle
     */
    @Test
    @UseDataProvider("articleProvider2")
    public void testSetArticle(WrittenBy writtenBy, NewsArticle oldArticle, NewsArticle newArticle) {
        System.out.println("setArticle");
        assertEquals(oldArticle, writtenBy.getArticle());
        writtenBy.setArticle(newArticle);
        assertEquals(newArticle, writtenBy.getArticle());
    }
    
    @DataProvider
    public static Object[][] articleProvider2() {
        NewsArticle article = new NewsArticle();
        article.setTitle("OLD");
        
        NewsArticle newArticle = new NewsArticle();
        newArticle.setTitle("NEW");
        
        WrittenBy writtenBy = new WrittenBy();
        writtenBy.setArticle(article);
        
        return new Object[][] {
            {writtenBy, article, newArticle}
        };
    }


    /**
     * Test of getAuthor method, of class Publishes.
     * @param writtenBy
     * @param expected
     */
    @Test
    @UseDataProvider("authorProvider")
    public void testGetAuthor(WrittenBy writtenBy, NewsAuthor expected) {
        System.out.println("getAuthor");
        assertEquals(expected, writtenBy.getAuthor());
    }

    @DataProvider
    public static Object[][] authorProvider() {
        NewsAuthor author = new NewsAuthor();
        author.setName("OLD");
        
        WrittenBy writtenBy = new WrittenBy();
        writtenBy.setAuthor(author);
        
        return new Object[][] {
            {writtenBy, author}
        };
    }
    
    /**
     * Test of setAuthor method, of class WrittenBy.
     * @param writtenBy
     * @param oldAuthor
     * @param newAuthor
     */
    @Test
    @UseDataProvider("authorProvider2")
    public void testSetAuthor(WrittenBy writtenBy, NewsAuthor oldAuthor, NewsAuthor newAuthor) {
        System.out.println("setAuthor");
        assertEquals(oldAuthor, writtenBy.getAuthor());
        writtenBy.setAuthor(newAuthor);
        assertEquals(newAuthor, writtenBy.getAuthor());
    }

    @DataProvider
    public static Object[][] authorProvider2() {
        NewsAuthor author = new NewsAuthor();
        author.setName("OLD");
        
        NewsAuthor newAuthor = new NewsAuthor();
        newAuthor.setName("NEW");
        
        WrittenBy writtenBy = new WrittenBy();
        writtenBy.setAuthor(author);
        
        return new Object[][] {
            {writtenBy, author, newAuthor}
        };
    }

    /**
     * Test of hashCode method, of class WrittenBy.
     * @param writtenBy
     * @param other
     * @param expected
     */
    @Test
    @UseDataProvider("equalsProvider")
    public void testHashCode(WrittenBy writtenBy, WrittenBy other, boolean expected) {
        System.out.println("hashCode");
        assertEquals(expected, writtenBy.hashCode() == other.hashCode());
    }

    /**
     * Test of equals method, of class WrittenBy.
     * @param writtenBy
     * @param other
     * @param expected
     */
    @Test
    @UseDataProvider("equalsProvider")
    public void testEquals(WrittenBy writtenBy, WrittenBy other, boolean expected) {
        System.out.println("equals");
        assertEquals(expected, writtenBy.equals(other));
    }
    
    @DataProvider
    public static Object[][] equalsProvider() {
        NewsAuthor author1 = new NewsAuthor();
        author1.setName("Author 1");
        
        NewsAuthor author2 = new NewsAuthor();
        author2.setName("Author 2");
        
        WrittenBy writtenBy = new WrittenBy();
        writtenBy.setAuthor(author1);
        
        WrittenBy other = new WrittenBy();
        writtenBy.setAuthor(author2);

        return new Object[][] {
            {writtenBy, other, false},
            {writtenBy, writtenBy, true},
            {other, other, true}
        };
    }
}
