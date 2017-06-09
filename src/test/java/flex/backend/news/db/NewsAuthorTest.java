/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.db;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class NewsAuthorTest {
    
    public NewsAuthorTest() {
    }

    /**
     * Test of getName method, of class NewsAuthor.
     * @param author
     * @param name
     */
    @Test
    @UseDataProvider("nameProvider")
    public void testGetName(NewsAuthor author, String name) {
        System.out.println("getName");
        assertEquals(name, author.getName());
    }
    
    @DataProvider
    public static Object[][] nameProvider() {
        NewsAuthor author1 = new NewsAuthor();
        author1.setName("John");
        return new Object[][] {
            {author1, "John"},
            {new NewsAuthor(), NewsAuthor.UNKNOWN.getName()},
        };
    }

    /**
     * Test of setName method, of class NewsAuthor.
     * @param author
     * @param oldName
     * @param newName
     */
    @Test
    @UseDataProvider("nameProvider2")    
    public void testSetName(NewsAuthor author, String oldName, String newName) {
        System.out.println("setName");
        assertEquals(oldName, author.getName());
    }
    
    @DataProvider
    public static Object[][] nameProvider2() {
        NewsAuthor author1 = new NewsAuthor();
        author1.setName("John A");
        return new Object[][] {
            {author1, "John A", "John B"},
            {new NewsAuthor(), NewsAuthor.UNKNOWN.getName(), "John B"},
        };
    }


    /**
     * Test of compareTo method, of class NewsAuthor.
     * @param author
     * @param other
     * @param expected
     */
    @Test
    @UseDataProvider("compareToProvider")
    public void testCompareTo(NewsAuthor author, NewsAuthor other, int expected) {
        System.out.println("compareTo");
        assertEquals(expected, author.compareTo(other));
    }
    
    
    @DataProvider
    public static Object[][] compareToProvider() {
        NewsAuthor author1 = new NewsAuthor();
        author1.setName("John A");
        
        NewsAuthor author2 = new NewsAuthor();
        author2.setName("John B");
        return new Object[][] {
            {author1, author2, -1},
            {author2, author1, 1},
            {author1, author1, 0},
            {author2, author2, 0}
        };
    }

    /**
     * Test of hashCode method, of class NewsAuthor.
     * @param author
     * @param other
     * @param expected
     */
    @Test
    @UseDataProvider("equalsProvider")
    public void testHashCode(NewsAuthor author, NewsAuthor other, boolean expected) {
        System.out.println("hashCode");
        assertEquals(expected, author.hashCode() == other.hashCode());
    }

    /**
     * Test of equals method, of class NewsAuthor.
     * @param author
     * @param other
     * @param expected
     */
    @Test
    @UseDataProvider("equalsProvider")
    public void testEquals(NewsAuthor author, NewsAuthor other, boolean expected) {
        System.out.println("equals");
        assertEquals(expected, author.equals(other));
    }
    
    @Test
    @UseDataProvider("equalsNegativeProvider")
    public void testEqualsNegative(NewsAuthor author, Object other, boolean expected) {
        System.out.println("equals");
        assertEquals(expected, author.equals(other));
    }

    @DataProvider
    public static Object[][] equalsProvider() {
        NewsAuthor author1 = new NewsAuthor();
        author1.setName("John A");
        
        NewsAuthor author2 = new NewsAuthor();
        author2.setName("John B");
        return new Object[][] {
            {author1, author2, false},
            {author2, author1, false},
            {author1, author1, true},
            {author2, author2, true}
        };
    }

    @DataProvider
    public static Object[][] equalsNegativeProvider() {
        NewsAuthor author1 = new NewsAuthor();
        author1.setName("John A");
        
        NewsAuthor author2 = new NewsAuthor();
        author2.setName("John B");
        return new Object[][] {
            {author1, author2, false},
            {author2, author1, false},
            {author1, null, false},
            {author2, new NewsArticle(), false}
        };
    }




    /**
     * Test of toString method, of class NewsAuthor.
     * @param author
     * @param expected
     */
    @Test
    @UseDataProvider("toStringProvider")
    public void testToString(NewsAuthor author, String expected) {
        System.out.println("toString");
        assertEquals(expected, author.toString());
    }
    
    @DataProvider
    public static Object[][] toStringProvider() {
        NewsAuthor author1 = new NewsAuthor();
        author1.setName("John A");
        
        NewsAuthor author2 = new NewsAuthor();
        author2.setName("John B");
        return new Object[][] {
            {author1, "John A"},
            {author2, "John B"},
            {new NewsAuthor(), NewsAuthor.UNKNOWN.getName()},
        };
    }

    /**
     * Test of getArticles method, of class NewsAuthor.
     */
    @Test
    public void testGetArticles() {
        System.out.println("getArticles");
        NewsAuthor instance = new NewsAuthor();
        Set<NewsArticle> result = instance.getArticles();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Test of setArticles method, of class NewsAuthor.
     */
    @Test
    public void testSetArticles() {
        System.out.println("setArticles");
        NewsAuthor instance = new NewsAuthor();
        assertTrue(instance.getArticles().isEmpty());

        Set<NewsArticle> articles = new HashSet<>();
        articles.add(new NewsArticle());
        instance.setArticles(articles);
        
        assertFalse(instance.getArticles().isEmpty());
        
    }
    
    @Test
    public void testAddAuthorWithArticle() {
        NewsArticle article = new NewsArticle();
        article.setTitle("title");
        
        NewsAuthor author = NewsAuthor.UNKNOWN;        
        author.addArticle(article);
        
        
        assertNotNull(article.getAuthors());
        assertFalse(author.getArticles().isEmpty());
        assertTrue(author.getArticles().contains(article));
        
    }
    
}
