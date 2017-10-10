/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import db.FlexUser;
import db.NewsArticle;
import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class ArticlesRepositoryTest {

    private final String TEST_USERNAME = "test:username";
    private final String TEST_PASSWORD = "test:password";
    
    
    public ArticlesRepositoryTest() {
    }

    /**
     * Test of loadNodes method, of class ArticlesRepository.
     */
    @Test
    public void testLoadNodes() {
        System.out.println("loadNodes");
        ArticlesRepository instance = new ArticlesRepository();
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        Collection<NewsArticle> result = instance.loadNodes(DataProviderType.LATEST, null, user);
        assertNotNull(result);
    }

    /**
     * Test of getCategoryDBCaption method, of class ArticlesRepository.
     */
    @Test
    public void testGetCategoryDBCaption() {
        System.out.println("getCategoryDBCaption");
        assertEquals("cat", ArticlesRepository.getCategoryDBCaption("Cat"));
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
