/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import button.FlexActionButton;
import com.vaadin.ui.AbstractOrderedLayout;
import db.FlexUser;
import db.NewsArticle;
import db.NewsAuthor;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class ArticleViewTest {

    public ArticleViewTest() {
    }

    /**
     * Test of constructor of class ArticleView.
     */
    @Test
    public void testNew() {
        System.out.println("new()");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        ArticleView aView = new ArticleView(user, article);
        assertEquals(user, aView.getUser());
        assertEquals(article, aView.getArticle());
    }

    /**
     * Test of createInfoHeader method, of class ArticleView.
     */
    @Test
    public void testCreateInfoHeader() {
        System.out.println("createInfoHeader");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        ArticleView aView = new ArticleView(user, article);
        assertNotNull(aView.createInfoHeader());
    }

    /**
     * Test of createInfoBody method, of class ArticleView.
     */
    @Test
    public void testCreateInfoBody() {
        System.out.println("createInfoBody");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        ArticleView aView = new ArticleView(user, article);
        assertNotNull(aView.createInfoBody());
    }

    /**
     * Test of getArticle method, of class ArticleView.
     */
    @Test
    public void testGetArticle() {
        System.out.println("getArticle");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        ArticleView aView = new ArticleView(user, article);
        assertEquals(article, aView.getArticle());
    }

    /**
     * Test of getTitle method, of class ArticleView.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        ArticleView aView = new ArticleView(user, article);
        assertEquals("title", aView.getTitle().getValue());
    }

    /**
     * Test of getAuthors method, of class ArticleView.
     */
    @Test
    public void testGetAuthors() {
        System.out.println("getAuthors");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        article.setTitle("title");
        NewsAuthor author = new NewsAuthor("Author");
        author.addArticle(article);
        ArticleView aView = new ArticleView(user, article);
        assertNull(aView.getAuthors());
        //assertEquals(1, aView.getAuthors().getComponentCount());
    }

    /**
     * Test of getContent method, of class ArticleView.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        article.setTitle("Title");
        article.setDescription("Description");
        NewsAuthor author = new NewsAuthor("Author");
        author.addArticle(article);
        ArticleView aView = new ArticleView(user, article);
        assertNotNull(aView.getContent());
        assertEquals("Description", aView.getContent().getValue());
    }

    /**
     * Test of getImage method, of class ArticleView.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
        NewsAuthor author = new NewsAuthor("Author");
        author.addArticle(article);
        ArticleView aView = new ArticleView(user, article);
        assertNotNull(aView.getImage());
    }

    /**
     * Test of getPublishedAt method, of class ArticleView.
     */
    @Test
    public void testGetPublishedAt() {
        System.out.println("getPublishedAt");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        article.setTitle("Title");
        article.setDescription("Description");
        Date date = new Date();
        article.setPublishedAt(date);
        NewsAuthor author = new NewsAuthor("Author");
        author.addArticle(article);
        ArticleView aView = new ArticleView(user, article);
        assertNotNull(aView.getPublishedAt().getValue());
    }

    /**
     * Test of getItem method, of class ArticleView.
     */
    @Test
    public void testGetItem() {
        System.out.println("getItem");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
        Date date = new Date();
        article.setPublishedAt(date);
        NewsAuthor author = new NewsAuthor("Author");
        author.addArticle(article);
        ArticleView aView = new ArticleView(user, article);
        assertEquals(article, aView.getItem());
    }

    /**
     * Test of getService method, of class ArticleView.
     */
    @Test
    public void testGetService() {
        System.out.println("getService");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        article.setTitle("Title");
        article.setDescription("Description");
        Date date = new Date();
        article.setPublishedAt(date);
        NewsAuthor author = new NewsAuthor("Author");
        author.addArticle(article);
        ArticleView aView = new ArticleView(user, article);
        assertNotNull(aView.getService());
    }

    /**
     * Test of maximize method, of class ArticleView.
     */
    @Test
    public void testMaximize() {
        System.out.println("maximize");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        article.setTitle("Title");
        article.setDescription("Description");
        Date date = new Date();
        article.setPublishedAt(date);
        NewsAuthor author = new NewsAuthor("Author");
        author.addArticle(article);
        ArticleView aView = new ArticleView(user, article);
        aView.maximize();
        assertTrue(aView.getImage().isVisible());
        assertTrue(aView.getPublishedAt().isVisible());
        assertTrue(aView.getTitle().isVisible());
    }

    /**
     * Test of minimize method, of class ArticleView.
     */
    @Test
    public void testMinimize() {
        System.out.println("minimize");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        NewsAuthor author = new NewsAuthor("Author");
        author.addArticle(article);
        ArticleView aView = new ArticleView(user, article);
        aView.minimize();
        assertTrue(aView.getImage().isVisible());
        assertTrue(aView.getPublishedAt().isVisible());
        assertTrue(aView.getTitle().isVisible());
    }

    /**
     * Test of createInfoActions method, of class ArticleView.
     */
    @Test
    public void testCreateInfoActions() {
        System.out.println("createInfoActions");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        article.setTitle("Title");
        article.setDescription("Description");
        Date date = new Date();
        article.setPublishedAt(date);
        NewsAuthor author = new NewsAuthor("Author");
        author.addArticle(article);
        ArticleView aView = new ArticleView(user, article);
        AbstractOrderedLayout actions = aView.createInfoActions();
        assertNotNull(actions);
    }

    /**
     * Test of buttonClick method, of class ArticleView.
     */
    @Test
    public void testButtonClick() {
        System.out.println("buttonClick");
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        article.setTitle("Title");
        article.setDescription("Description");
        Date date = new Date();
        article.setPublishedAt(date);
        NewsAuthor author = new NewsAuthor("Author");
        author.addArticle(article);
        ArticleView aView = new ArticleView(user, article);
        AbstractOrderedLayout actions = aView.createInfoActions();
        ((FlexActionButton)actions.getComponent(1)).click();
    }

}
