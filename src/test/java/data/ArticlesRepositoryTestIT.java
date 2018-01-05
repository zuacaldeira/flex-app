/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import db.auth.FlexUser;
import db.news.NewsArticle;
import io.reactivex.Observable;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class ArticlesRepositoryTestIT {

    private final String TEST_USERNAME = "test:username";
    private final String TEST_PASSWORD = "test:password";

    public ArticlesRepositoryTestIT() {
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
            {DataProviderType.LANGUAGES, "Portuguese"},
            {DataProviderType.PUBLISHER, "Publishing AG"},
            {DataProviderType.SEARCH, "a query"},
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
    @Test(dataProvider = "loadNodes")
    public void testLoadNodes(DataProviderType type, String value) {
        System.out.println("loadNodes");
        ArticlesRepository instance = new ArticlesRepository();
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        Observable<NewsArticle> result = instance.loadNodes(type, value, user);
        assertNotNull(result);
    }

    /**
     * Test of getCategoryDBCaption method, of class ArticlesRepository.
     *
     * @param category
     * @param expected
     */
    @Test(dataProvider = "captions")
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
