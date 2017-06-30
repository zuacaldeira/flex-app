/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import ui.news.UITest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import utils.ServiceLocator;

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
        
        assertTrue(countSources1 <= countSources2);
        assertTrue(countAuthors1 <= countAuthors2);
        assertTrue(countArticles1 <= countArticles2);
    }
}
