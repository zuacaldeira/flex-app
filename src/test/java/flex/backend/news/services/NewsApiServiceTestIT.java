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
public class NewsApiServiceTestIT extends UITest {
    
    public NewsApiServiceTestIT() {
    }

    @Test
    public void testLoadData() {
        ServiceLocator.getInstance().findNewsApiService().loadData();
    }
    
    @Test
    public void testLoadDataTwice() {
        ServiceLocator.getInstance().findNewsApiService().loadData();
        long countSources1 = ServiceLocator. getInstance().findSourcesService().count();
        long countAuthors1 = ServiceLocator.getInstance().findAuthorsService().count();
        long countArticles1 = ServiceLocator.getInstance().findArticlesService().count();
        
        ServiceLocator.getInstance().findNewsApiService().loadData();
        long countSources2 = ServiceLocator.getInstance().findSourcesService().count();
        long countAuthors2 = ServiceLocator.getInstance().findAuthorsService().count();
        long countArticles2 = ServiceLocator.getInstance().findArticlesService().count();
        
        assertEquals(countSources1, countSources2);
        assertEquals(countAuthors1, countAuthors2);
        assertEquals(countArticles1, countArticles2);
    }
}
