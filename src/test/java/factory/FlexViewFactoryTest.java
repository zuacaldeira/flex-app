/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import db.FlexUser;
import db.NewsArticle;
import db.NewsAuthor;
import db.NewsSource;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

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
        NewsSource source = new NewsSource("sourceId", "name", "description", "url", "category", "language", "country");
        assertNotNull(FlexViewFactory.getInstance().createSourceView(user,source));
    }

    /**
     * Test of createArticleView method, of class FlexViewFactory.
     */
    @Test
    public void testCreateArticleView() {
        System.out.println("createArticleView");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsArticle article = new NewsArticle("test:title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        assertNotNull(FlexViewFactory.getInstance().createArticleView(user,article));
    }

    /**
     * Test of createAuthorView method, of class FlexViewFactory.
     */
    @Test
    public void testCreateAuthorView() {
        System.out.println("createAuthorView");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsAuthor author = new NewsAuthor("test:author");
        assertNotNull(FlexViewFactory.getInstance().createAuthorView(user,author));
    }

    /**
     * Test of createView method, of class FlexViewFactory.
     */
    @Test
    public void testCreateView() {
        System.out.println("createView");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsAuthor author = new NewsAuthor("test:author");
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
