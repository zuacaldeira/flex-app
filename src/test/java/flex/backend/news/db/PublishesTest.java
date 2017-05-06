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
public class PublishesTest {
    
    public PublishesTest() {
    }

    /**
     * Test of getSource method, of class Publishes.
     * @param publishes
     * @param expected
     */
    @Test
    @UseDataProvider("sourceProvider")
    public void testGetSource(Publishes publishes, NewsSource expected) {
        System.out.println("getSource");
        assertEquals(expected, publishes.getSource());
    }
    
    @DataProvider
    public static Object[][] sourceProvider() {
        NewsSource source = new NewsSource();
        Publishes publishes = new Publishes();
        publishes.setSource(source);
        return new Object[][] {
            {publishes, source}
        };
    }

    /**
     * Test of setSource method, of class Publishes.
     * @param publishes
     * @param oldSource
     * @param newSource
     */
    @Test
    @UseDataProvider("sourceProvider2")
    public void testSetSource(Publishes publishes, NewsSource oldSource, NewsSource newSource) {
        System.out.println("setSource");
        assertEquals(oldSource, publishes.getSource());
        publishes.setSource(newSource);
        assertEquals(newSource, publishes.getSource());
    }
    
    @DataProvider
    public static Object[][] sourceProvider2() {
        NewsSource source = new NewsSource();
        source.setName("OLD");
        
        NewsSource newSource = new NewsSource();
        newSource.setName("NEW");
        
        Publishes publishes = new Publishes();
        publishes.setSource(source);
        
        return new Object[][] {
            {publishes, source, newSource}
        };
    }


    /**
     * Test of getAuthor method, of class Publishes.
     * @param publishes
     * @param expected
     */
    @Test
    @UseDataProvider("authorProvider")
    public void testGetAuthor(Publishes publishes, NewsAuthor expected) {
        System.out.println("getAuthor");
        assertEquals(expected, publishes.getAuthor());
    }

    @DataProvider
    public static Object[][] authorProvider() {
        NewsAuthor author = new NewsAuthor();
        author.setName("OLD");
        
        Publishes publishes = new Publishes();
        publishes.setAuthor(author);
        
        return new Object[][] {
            {publishes, author}
        };
    }
    
    /**
     * Test of setAuthor method, of class Publishes.
     * @param publishes
     * @param oldAuthor
     * @param newAuthor
     */
    @Test
    @UseDataProvider("authorProvider2")
    public void testSetAuthor(Publishes publishes, NewsAuthor oldAuthor, NewsAuthor newAuthor) {
        System.out.println("setAuthor");
        assertEquals(oldAuthor, publishes.getAuthor());
        publishes.setAuthor(newAuthor);
        assertEquals(newAuthor, publishes.getAuthor());
    }

    @DataProvider
    public static Object[][] authorProvider2() {
        NewsAuthor author = new NewsAuthor();
        author.setName("OLD");
        
        NewsAuthor newAuthor = new NewsAuthor();
        newAuthor.setName("NEW");
        
        Publishes publishes = new Publishes();
        publishes.setAuthor(author);
        
        return new Object[][] {
            {publishes, author, newAuthor}
        };
    }

    /**
     * Test of hashCode method, of class Publishes.
     * @param publishes
     * @param other
     * @param expected
     */
    @Test
    @UseDataProvider("equalsProvider")
    public void testHashCode(Publishes publishes, Publishes other, boolean expected) {
        System.out.println("hashCode");
        assertEquals(expected, publishes.hashCode() == other.hashCode());
    }

    /**
     * Test of equals method, of class Publishes.
     * @param publishes
     * @param other
     * @param expected
     */
    @Test
    @UseDataProvider("equalsProvider")
    public void testEquals(Publishes publishes, Publishes other, boolean expected) {
        System.out.println("equals");
        assertEquals(expected, publishes.equals(other));
    }
    
    @DataProvider
    public static Object[][] equalsProvider() {
        NewsAuthor author1 = new NewsAuthor();
        author1.setName("Author 1");
        
        NewsAuthor author2 = new NewsAuthor();
        author2.setName("Author 2");
        
        Publishes publishes = new Publishes();
        publishes.setAuthor(author1);
        
        Publishes other = new Publishes();
        publishes.setAuthor(author2);

        return new Object[][] {
            {publishes, other, false},
            {publishes, publishes, true},
            {other, other, true}
        };
    }
}
