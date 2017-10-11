/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import db.FlexUser;
import db.NewsArticle;
import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class ArticlesRepositoryTest {

    private final String TEST_USERNAME = "test:username";
    private final String TEST_PASSWORD = "test:password";

    public ArticlesRepositoryTest() {
    }

    @DataProvider
    public static Object[][] loadNodes() {
        return new Object[][]{
            {DataProviderType.LATEST, null},
            {DataProviderType.OLDEST, null},
            {DataProviderType.CATEGORY, "business"},
            {DataProviderType.FAKE, null},
            {DataProviderType.FAVORITE, null},
            {DataProviderType.READ, null},
            {DataProviderType.FULL, null},
            {DataProviderType.IMAGES_ONLY, null},
            {DataProviderType.TITLES_ONLY, null},
            {DataProviderType.COUNTRIES, "Portugal"},
            {DataProviderType.LANGUAGES, "Portuguese"}
        };
    }

    @DataProvider
    public static Object[][] captions() {
        return new Object[][]{
            {"Cat", "cat"},
            {"", ""}
        };
    }

    /**
     * Test of loadNodes method, of class ArticlesRepository.
     *
     * @param type
     * @param value
     */
    @Test
    @UseDataProvider("loadNodes")
    public void testLoadNodes(DataProviderType type, String value) {
        System.out.println("loadNodes");
        ArticlesRepository instance = new ArticlesRepository();
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        Collection<NewsArticle> result = instance.loadNodes(type, value, user);
        assertNotNull(result);
    }

    /**
     * Test of getCategoryDBCaption method, of class ArticlesRepository.
     *
     * @param category
     * @param expected
     */
    @Test
    @UseDataProvider("captions")
    public void testGetCategoryDBCaption(String category, String expected) {
        System.out.println("getCategoryDBCaption");
        assertEquals(expected, ArticlesRepository.getCategoryDBCaption(category));
    }

    /**
     * Test of getSourceIdForSourceName method, of class ArticlesRepository.
     */
    @Test
    public void testGetSourceIdForSourceName() {
        System.out.println("getSourceIdForSourceName");
        assertNull(ArticlesRepository.getSourceIdForSourceName("SourceName"));
    }

}
