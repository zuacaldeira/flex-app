/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.frontend.ui.news.UITest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class NewsApiServiceTest extends UITest {
    
    public NewsApiServiceTest() {
    }

    @Test
    public void testLoadData() {
        ServiceLocator.findNewsApiService().loadData();
    }
    
    @Test
    public void testLoadDataTwice() {
        ServiceLocator.findNewsApiService().loadData();
        long countSources1 = ServiceLocator.findSourcesService().count();
        long countAuthors1 = ServiceLocator.findAuthorsService().count();
        long countArticles1 = ServiceLocator.findArticlesService().count();
        
        ServiceLocator.findNewsApiService().loadData();
        long countSources2 = ServiceLocator.findSourcesService().count();
        long countAuthors2 = ServiceLocator.findAuthorsService().count();
        long countArticles2 = ServiceLocator.findArticlesService().count();
        
        assertEquals(countSources1, countSources2);
        assertEquals(countAuthors1, countAuthors2);
        assertEquals(countArticles1, countArticles2);
    }
}
