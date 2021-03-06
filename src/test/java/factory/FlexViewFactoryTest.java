/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import db.auth.FlexUser;
import db.news.NewsArticle;
import db.news.NewsAuthor;
import db.news.NewsSource;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class FlexViewFactoryTest {
    
    public FlexViewFactoryTest() {
    }

    /**
     * Test of createSourceView method, of class FlexViewFactory.
     */
    @Test
    public void testCreateSourceView() {
        System.out.println("createSourceView");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsSource source = new NewsSource();
        source.setSourceId("souceID");
        assertNotNull(FlexViewFactory.getInstance().createSourceView(user,source));
    }

    /**
     * Test of createArticleView method, of class FlexViewFactory.
     */
    @Test
    public void testCreateArticleView() {
        System.out.println("createArticleView");
        FlexUser user = new FlexUser();
        
        NewsArticle article = new NewsArticle();
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
         article.setSourceId("sourceId");
       
        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();
        
        assertNotNull(FlexViewFactory.getInstance().createArticleView(user,article));
    }

    /**
     * Test of createAuthorView method, of class FlexViewFactory.
     */
    @Test
    public void testCreateAuthorView() {
        System.out.println("createAuthorView");
        FlexUser user = new FlexUser();
        
        NewsArticle article = new NewsArticle();
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
        
        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();
        
        assertNotNull(FlexViewFactory.getInstance().createAuthorView(user,author));
    }

    /**
     * Test of createView method, of class FlexViewFactory.
     */
    @Test
    public void testCreateView() {
        System.out.println("createView");
        FlexUser user = new FlexUser();
        
        NewsArticle article = new NewsArticle();
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
        
        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();
        
        assertNotNull(FlexViewFactory.getInstance().createView(user,author));
    }

    /**
     * Test of getInstance method, of class FlexViewFactory.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        assertNotNull(FlexViewFactory.getInstance());
    }
    
}
