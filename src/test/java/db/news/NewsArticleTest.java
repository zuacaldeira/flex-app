/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.news;

import db.news.NewsArticle;
import db.news.NewsAuthor;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.util.Date;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class NewsArticleTest  extends TestCase {
    
    
    public NewsArticleTest() {}

    /**
     * Test of getTitle method, of class NewsArticle.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        NewsArticle instance = new NewsArticle();
        assertNull(instance.getTitle());
        
        String expResult = "";
        instance.setTitle(expResult);
        
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class NewsArticle.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        NewsArticle instance = new NewsArticle();
        assertNull(instance.getDescription());
        
        String expResult = "";
        instance.setDescription(expResult);
        
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUrl method, of class NewsArticle.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        NewsArticle instance = new NewsArticle();
        assertNull(instance.getUrl());
        
        String expResult = "";
        instance.setUrl(expResult);
        
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImageUrl method, of class NewsArticle.
     */
    @Test
    public void testGetImageUrl() {
        System.out.println("getImageUrl");
        NewsArticle instance = new NewsArticle();
        assertNull(instance.getImageUrl());
        
        String expResult = "";
        instance.setImageUrl(expResult);
        
        String result = instance.getImageUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPublishedAt method, of class NewsArticle.
     */
    @Test
    public void testGetPublishedAt() {
        System.out.println("getPublishedAt");
        NewsArticle instance = new NewsArticle();
        assertNull(instance.getPublishedAt());
        
        Date expResult = new Date();
        instance.setPublishedAt(expResult);
        
        Date result = instance.getPublishedAt();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class NewsArticle.
     */
    @Test
    @UseDataProvider("titlesProvider")
    public void testSetTitle(String title) {
        System.out.println("setTitle");
        
        NewsArticle instance = new NewsArticle();
        assertNull(instance.getTitle());
        
        instance.setTitle(title);
        assertEquals(title, instance.getTitle());
    }
    
    @DataProvider
    public static Object[][] titlesProvider() {
        return new Object[][] {
            {""}, 
            {"A title"}, 
            {"A title, with punctuation!"}, 
            {"A title with it's ~§¥ special characters"}
        };    
    }

    @DataProvider
    public static Object[][] datesProvider() {
        return new Object[][] {
            {new Date()}
        };    
    }

    /**
     * Test of setDescription method, of class NewsArticle.
     * @param description
     */
    @Test
    @UseDataProvider("titlesProvider")
    public void testSetDescription(String description) {
        System.out.println("setDescription");

        NewsArticle instance = new NewsArticle();
        assertNull(instance.getDescription());
        
        instance.setDescription(description);
        assertEquals(description, instance.getDescription());
    }

    /**
     * Test of setUrl method, of class NewsArticle.
     * @param url
     */
    @Test
    @UseDataProvider("titlesProvider")
    public void testSetUrl(String url) {
        System.out.println("setUrl");
        
        NewsArticle instance = new NewsArticle();
        assertNull(instance.getUrl());
        
        instance.setUrl(url);
        assertEquals(url, instance.getUrl());
    }

    /**
     * Test of setImageUrl method, of class NewsArticle.
     * @param imageUrl
     */
    @Test
    @UseDataProvider("titlesProvider")
    // TODO: Test is a link
    public void testSetImageUrl(String imageUrl) {
        System.out.println("setImageUrl");

        NewsArticle instance = new NewsArticle();
        assertNull(instance.getImageUrl());
        
        instance.setImageUrl(imageUrl);
        assertEquals(imageUrl, instance.getImageUrl());
    }

    /**
     * Test of setPublishedAt method, of class NewsArticle.
     * @param publishedAt
     */
    @Test
    @UseDataProvider("datesProvider")
    // TODO: Test is a date
    public void testSetPublishedAt(Date publishedAt) {
        System.out.println("setPublishedAt");
        
        NewsArticle instance = new NewsArticle();
        assertNull(instance.getPublishedAt());
        
        instance.setPublishedAt(publishedAt);
        assertEquals(publishedAt, instance.getPublishedAt());
    }

    /**
     * Test of getSourceId method, of class NewsArticle.
     * @param sourceId
     */
    @Test
    @UseDataProvider("titlesProvider")
    public void testGetSourceId(String sourceId) {
        System.out.println("getSourceId");
        
        NewsArticle instance = new NewsArticle();
        assertNull(instance.getSourceId());
        
        instance.setSourceId(sourceId);
        assertEquals(sourceId, instance.getSourceId());
    }

    /**
     * Test of setSourceId method, of class NewsArticle.
     * @param sourceId
     */
    @Test
    @UseDataProvider("titlesProvider")
    public void testSetSourceId(String sourceId) {
        System.out.println("setSourceId");

        NewsArticle instance = new NewsArticle();
        assertNull(instance.getSourceId());
        
        instance.setSourceId(sourceId);
        assertEquals(sourceId, instance.getSourceId());

    }

    /**
     * Test of hashCode method, of class NewsArticle.
     * @param first
     * @param second
     */
    @Test
    @UseDataProvider("equalsProvider")
    public void testHashCode(NewsArticle first, NewsArticle second, boolean expected) {
        System.out.println("hashCode");
        assertEquals(expected, first.hashCode() == second.hashCode());
    }
    
    @DataProvider
    public static Object[][] equalsProvider() {
        NewsArticle article1 = new NewsArticle("title", "description", "url", "imageUrl", new Date());
        NewsArticle article2 = new NewsArticle("title", "description", "url", "imageUrl", new Date());
        return new Object[][] {
            {new NewsArticle(), new NewsArticle(), true},
            {article1, article1, true},
            {article1, article2, true}
        };
    }

    @DataProvider
    public static Object[][] equalsNegativeProvider() {
        NewsArticle article1 = new NewsArticle("title1", "description", "url", "imageUrl", new Date());
        NewsArticle article2 = new NewsArticle("title2", "description", "url", "imageUrl", new Date());
        return new Object[][] {
            {new NewsArticle(), article1, false},
            {article1, article2, false},
            {article1, null, false},
            {article1, new NewsAuthor(), false}
        };
    }

    /**
     * Test of equals method, of class NewsArticle.
     * @param first
     * @param second
     * @param expected
     */
    @Test
    @UseDataProvider("equalsProvider")
    public void testEquals(NewsArticle first, NewsArticle second, boolean expected) {
        System.out.println("equals");
        assertEquals(expected, first.equals(second));
    }

    @Test
    @UseDataProvider("equalsNegativeProvider")
    public void testEqualsNegative(NewsArticle first, Object second, boolean expected) {
        System.out.println("equals");
        assertEquals(expected, first.equals(second));
    }

    /**
     * Test of toString method, of class NewsArticle.
     * @param article
     * @param expected
     */
    @Test
    @UseDataProvider("toStringProvider")
    public void testToString(NewsArticle article, String expected) {
        System.out.println("toString");
        assertEquals(expected, article.toString());
    }
    
     @DataProvider
    public static Object[][] toStringProvider() {
        NewsArticle article1 = new NewsArticle("title1", "description", "url", "imageUrl", new Date());
        NewsArticle article2 = new NewsArticle("title2", "description", "url", "imageUrl", new Date());
        return new Object[][] {
            {new NewsArticle(), ""},
            {article1, "title1"},
            {article2, "title2"}
        };
    }
   

    /**
     * Test of compareTo method, of class NewsArticle.
     */
    @Test
    @UseDataProvider("compareToProvider")
    public void testCompareTo(NewsArticle first, NewsArticle second, int expected) {
        System.out.println("compareTo");
        assertEquals(expected, first.compareTo(second));
    }
    
    
    @DataProvider
    public static Object[][] compareToProvider() {
        NewsArticle article1 = new NewsArticle("title1", "description", "url", "imageUrl", new Date());
        NewsArticle article2 = new NewsArticle("title2", "description", "url", "imageUrl", new Date());
        return new Object[][] {
            {new NewsArticle(), new NewsArticle(), 0},
            {new NewsArticle(), article1, 1},
            {article1, new NewsArticle(), -1},
            {article1, article1, 0},
            {article2, article2, 0},
            {article1, article2, -1},
            {article2, article1, 1}
        };
    }
    
}
