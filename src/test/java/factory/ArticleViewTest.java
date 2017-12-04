/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

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
        assertEquals("title", aView.getArticle().getTitle());
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
        assertNull(aView.getArticleViewBody().getAuthors());
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
        assertNotNull(aView.getArticleViewBody().getContent());
        assertTrue(aView.getArticleViewBody().getContent().getValue().endsWith("Description"));
    }

    /**
     * Test of getArticleViewHeader method, of class ArticleView.
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
     * Test of select method, of class ArticleView.
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
        aView.select();
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
        aView.unselect();
    }


}
