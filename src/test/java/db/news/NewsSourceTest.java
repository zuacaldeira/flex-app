/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.news;

import db.news.NewsSource;
import db.news.NewsAuthor;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class NewsSourceTest extends TestCase {
    
    public NewsSourceTest() {
    }

    /**
     * Test of getSourceId method, of class NewsSource.
     * @param source
     * @param expected
     */
    @Test
    @UseDataProvider("sourceIdProvider")
    public void testGetSourceId(NewsSource source, String expected) {
        System.out.println("getSourceId");
        assertEquals(expected, source.getSourceId());
    }
    
    @DataProvider
    public static Object[][] sourceIdProvider() {
        NewsSource source1 = new NewsSource("sourceId1", "name", "description", "url", "category", "language", "country");
        NewsSource source2 = new NewsSource("sourceId2", "name", "description", "url", "category", "language", "country");
        return new Object[][] {
            {source1, "sourceId1"},
            {source2, "sourceId2"},
            {new NewsSource(), null}
        };
    }

    /**
     * Test of getName method, of class NewsSource.
     * @param source
     * @param expected
     */
    @Test
    @UseDataProvider("nameProvider")
    public void testGetName(NewsSource source, String expected) {
        System.out.println("getName");
        assertEquals(expected, source.getName());
    }

    @DataProvider
    public static Object[][] nameProvider() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description", "url", "category", "language", "country");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description", "url", "category", "language", "country");
        return new Object[][] {
            {source1, "name1"},
            {source2, "name2"},
            {new NewsSource(), null}
        };
    }

    /**
     * Test of getDescription method, of class NewsSource.
     * @param source
     * @param expected
     */
    @Test
    @UseDataProvider("descriptionProvider")
    public void testGetDescription(NewsSource source, String expected) {
        System.out.println("getDescription");
        assertEquals(expected, source.getDescription());        
    }

    @DataProvider
    public static Object[][] descriptionProvider() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url", "category", "language", "country");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url", "category", "language", "country");
        return new Object[][] {
            {source1, "description1"},
            {source2, "description2"},
            {new NewsSource(), null}
        };
    }

    /**
     * Test of getUrl method, of class NewsSource.
     * @param source
     * @param expected
     */
    @Test
    @UseDataProvider("urlProvider")
    public void testGetUrl(NewsSource source, String expected) {
        System.out.println("getUrl");
        assertEquals(expected, source.getUrl());        
    }

    @DataProvider
    public static Object[][] urlProvider() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url1", "category", "language", "country");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url2", "category", "language", "country");
        return new Object[][] {
            {source1, "url1"},
            {source2, "url2"},
            {new NewsSource(), null}
        };
    }

    /**
     * Test of getCategory method, of class NewsSource.
     * @param source
     * @param expected
     */
    @Test
    @UseDataProvider("categoryProvider")
    public void testGetCategory(NewsSource source, String expected) {
        System.out.println("getCategory");
        assertEquals(expected, source.getCategory());
    }

    @DataProvider
    public static Object[][] categoryProvider() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url1", "category1", "language", "country");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url2", "category2", "language", "country");
        return new Object[][] {
            {source1, "category1"},
            {source2, "category2"},
            {new NewsSource(), null}
        };
    }

    /**
     * Test of getLanguage method, of class NewsSource.
     * @param source
     * @param expected
     */
    @Test
    @UseDataProvider("languageProvider")
    public void testGetLanguage(NewsSource source, String expected) {
        System.out.println("getLanguage");
        assertEquals(expected, source.getLanguage());
    }

    @DataProvider
    public static Object[][] languageProvider() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url1", "category1", "language1", "country");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url2", "category2", "language2", "country");
        return new Object[][] {
            {source1, "language1"},
            {source2, "language2"},
            {new NewsSource(), null}
        };
    }

    /**
     * Test of getCountry method, of class NewsSource.
     * @param source
     * @param expected
     */
    @Test
    @UseDataProvider("countryProvider")
    public void testGetCountry(NewsSource source, String expected) {
        System.out.println("getCountry");
        assertEquals(expected, source.getCountry());
    }

    @DataProvider
    public static Object[][] countryProvider() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url1", "category1", "language1", "country1");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url2", "category2", "language2", "country2");
        return new Object[][] {
            {source1, "country1"},
            {source2, "country2"},
            {new NewsSource(), null}
        };
    }

    /**
     * Test of setSourceId method, of class NewsSource.
     * @param source
     * @param oldSourceId
     * @param newSourceId
     */
    @Test
    @UseDataProvider("sourceIdProvider2")
    public void testSetSourceId(NewsSource source, String oldSourceId, String newSourceId) {
        System.out.println("setSourceId");
        assertEquals(oldSourceId, source.getSourceId());
        source.setSourceId(newSourceId);
        assertEquals(newSourceId, source.getSourceId());
    }

    @DataProvider
    public static Object[][] sourceIdProvider2() {
        NewsSource source1 = new NewsSource("sourceId1", "name", "description", "url", "category", "language", "country");
        NewsSource source2 = new NewsSource("sourceId2", "name", "description", "url", "category", "language", "country");
        return new Object[][] {
            {source1, "sourceId1", "sourceId2"},
            {source2, "sourceId2", "sourceId1"},
            {new NewsSource(), null, "sourceId1"}
        };
    }
    /**
     * Test of setName method, of class NewsSource.
     * @param source
     * @param oldName
     * @param newName
     */
    @Test
    @UseDataProvider("nameProvider2")
    public void testSetName(NewsSource source, String oldName, String newName) {
        System.out.println("setName");
        assertEquals(oldName, source.getName());
        source.setName(newName);
        assertEquals(newName, source.getName());
    }
    
    @DataProvider
    public static Object[][] nameProvider2() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description", "url", "category", "language", "country");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description", "url", "category", "language", "country");
        return new Object[][] {
            {source1, "name1", "name2"},
            {source2, "name2", "name1"},
            {new NewsSource(), null, "name1"}
        };
    }

    /**
     * Test of setDescription method, of class NewsSource.
     * @param source
     * @param oldDescription
     * @param newDescription
     */
    @Test
    @UseDataProvider("descriptionProvider2")
    public void testSetDescription(NewsSource source, String oldDescription, String newDescription) {
        System.out.println("setDescription");
        assertEquals(oldDescription, source.getDescription());
        source.setDescription(newDescription);
        assertEquals(newDescription, source.getDescription());
    }

    @DataProvider
    public static Object[][] descriptionProvider2() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url", "category", "language", "country");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url", "category", "language", "country");
        return new Object[][] {
            {source1, "description1", "description2"},
            {source2, "description2", "description1"},
            {new NewsSource(), null, "description1"}
        };
    }
    /**
     * Test of setUrl method, of class NewsSource.
     * @param source
     * @param oldUrl
     * @param newUrl
     */
    @Test
    @UseDataProvider("urlProvider2")
    public void testSetUrl(NewsSource source, String oldUrl, String newUrl) {
        System.out.println("setUrl");
        assertEquals(oldUrl, source.getUrl());
        source.setUrl(newUrl);
        assertEquals(newUrl, source.getUrl());
    }

    @DataProvider
    public static Object[][] urlProvider2() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url1", "category", "language", "country");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url2", "category", "language", "country");
        return new Object[][] {
            {source1, "url1", "url2"},
            {source2, "url2", "url1"},
            {new NewsSource(), null, "url1"}
        };
    }

    /**
     * Test of setCategory method, of class NewsSource.
     * @param source
     * @param oldCategory
     * @param newCategory
     */
    @Test
    @UseDataProvider("categoryProvider2")
    public void testSetCategory(NewsSource source, String oldCategory, String newCategory) {
        System.out.println("setCategory");
        assertEquals(oldCategory, source.getCategory());
        source.setCategory(newCategory);
        assertEquals(newCategory, source.getCategory());
    }

    @DataProvider
    public static Object[][] categoryProvider2() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url1", "category1", "language", "country");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url2", "category2", "language", "country");
        return new Object[][] {
            {source1, "category1", "category2"},
            {source2, "category2", "category1"},
            {new NewsSource(), null, "category1"}
        };
    }

    /**
     * Test of setLanguage method, of class NewsSource.
     * @param source
     * @param oldLanguage
     * @param newLanguage
     */
    @Test
    @UseDataProvider("languageProvider2")
    public void testSetLanguage(NewsSource source, String oldLanguage, String newLanguage) {
        System.out.println("setLanguage");
        assertEquals(oldLanguage, source.getLanguage());
        source.setLanguage(newLanguage);
        assertEquals(newLanguage, source.getLanguage());
    }
    
    @DataProvider
    public static Object[][] languageProvider2() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url1", "category1", "language1", "country");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url2", "category2", "language2", "country");
        return new Object[][] {
            {source1, "language1", "language2"},
            {source2, "language2", "language1"},
            {new NewsSource(), null, "language1"}
        };
    }
    /**
     * Test of setCountry method, of class NewsSource.
     * @param source
     * @param oldCountry
     * @param newCountry
     */
    @Test
    @UseDataProvider("countryProvider2")
    public void testSetCountry(NewsSource source, String oldCountry, String newCountry) {
        System.out.println("setCountry");
        assertEquals(oldCountry, source.getCountry());
        source.setCountry(newCountry);
        assertEquals(newCountry, source.getCountry());
    }
    
    @DataProvider
    public static Object[][] countryProvider2() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url1", "category1", "language1", "country1");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url2", "category2", "language2", "country2");
        return new Object[][] {
            {source1, "country1", "country2"},
            {source2, "country2", "country1"},
            {new NewsSource(), null, "country1"}
        };
    }

    /**
     * Test of toString method, of class NewsSource.
     * @param source
     * @param expected
     */
    @Test
    @UseDataProvider("toStringProvider")
    public void testToString(NewsSource source, String expected) {
        System.out.println("toString");
        assertEquals(expected, source.toString());
    }
    
    @DataProvider
    public static Object[][] toStringProvider() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url1", "category1", "language1", "country1");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url2", "category2", "language2", "country2");
        return new Object[][] {
            {source1, "sourceId1"},
            {source2, "sourceId2"},
            {new NewsSource(), ""}
        };
    }

    /**
     * Test of compareTo method, of class NewsSource.
     * @param source
     * @param other
     * @param expected
     */
    @Test
    @UseDataProvider("compareToProvider")
    public void testCompareTo(NewsSource source, NewsSource other, int expected) {
        System.out.println("compareTo");
        assertEquals(expected, source.compareTo(other));
    }
    
    @DataProvider
    public static Object[][] compareToProvider() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url1", "category1", "language1", "country1");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url2", "category2", "language2", "country2");
        return new Object[][] {
            {source1, source1, 0},
            {source2, source2, 0},
            {source1, source2, -1},
            {source2, source1, 1}
        };
    }

    /**
     * Test of hashCode method, of class NewsSource.
     * @param source
     * @param other
     * @param expected
     */
    @Test
    @UseDataProvider("equalsProvider")
    public void testHashCode(NewsSource source, NewsSource other, boolean expected) {
        System.out.println("hashCode");
        assertEquals(expected, source.hashCode() == other.hashCode());
    }
    
    
    @DataProvider
    public static Object[][] equalsProvider() {
        NewsSource source1 = new NewsSource("sourceId1", "name1", "description1", "url1", "category1", "language1", "country1");
        NewsSource source2 = new NewsSource("sourceId2", "name2", "description2", "url2", "category2", "language2", "country2");
        NewsSource source3 = new NewsSource("sourceId1", "name2", "description1", "url1", "category1", "language1", "country1");
        return new Object[][] {
            {source1, source1, true},
            {source1, source3, true},
            {source2, source2, true}
        };
    }

    /**
     * Test of equals method, of class NewsSource.
     * @param source
     * @param other
     * @param expected
     */
    @Test
    @UseDataProvider("equalsProvider")
    public void testEquals(NewsSource source, NewsSource other, boolean expected) {
        System.out.println("equals");
        assertTrue(source.equals(other));
    }
    
    /**
     * Test of equals method, of class NewsSource.
     * @param source
     * @param other
     * @param expected
     */
    @Test
    @UseDataProvider("equalsNegativeProvider")
    public void testEqualsNegative(NewsSource source, Object other, boolean expected) {
        System.out.println("equals");
        assertFalse(source.equals(other));
    }
    
        @DataProvider
    public static Object[][] equalsNegativeProvider() {
        NewsSource source1 = new NewsSource("sourceId1", "name", "description", "url", "category", "language", "country");
        NewsSource source2 = new NewsSource("sourceId2", "name", "description", "url", "category", "language", "country");
        return new Object[][] {
            {source1, source2,                  false},
            {source1, null,                     false},
            {source1, new NewsAuthor("author"), false}
        };
    }
    
    
    
    @Test
    public void testAddSourceWithAuthor() {
        NewsSource source = new NewsSource();
        source.setSourceId("sourceId");
        
        NewsAuthor author = NewsAuthor.UNKNOWN;
        source.addCorrespondent(author);
        
        assertNotNull(author.getSource());
        assertNotNull(source.getCorrespondents());
        assertFalse(source.getCorrespondents().isEmpty());
        assertTrue(source.getCorrespondents().contains(author));
        
    }
}
