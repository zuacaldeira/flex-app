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

        NewsArticle article = new NewsArticle();
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
        article.setSourceId("sourceId");

        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();

        ArticleView aView = new ArticleView(user, article);
        assertNotNull(aView.getItem());
    }

    /**
     * Test of getArticle method, of class ArticleView.
     */
    @Test
    public void testGetArticle() {
        System.out.println("getArticle");
        FlexUser user = new FlexUser();

        NewsArticle article = new NewsArticle();
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
        article.setSourceId("sourceId");

        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();

        ArticleView aView = new ArticleView(user, article);
        assertEquals(aView.getItem(), article);
    }

    /**
     * Test of getTitle method, of class ArticleView.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        FlexUser user = new FlexUser();

        NewsArticle article = new NewsArticle();
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
        article.setSourceId("sourceId");

        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();

        ArticleView aView = new ArticleView(user, article);
        assertEquals("Title", aView.getArticle().getTitle());
    }

    /**
     * Test of getAuthors method, of class ArticleView.
     */
    @Test
    public void testGetAuthors() {
        System.out.println("getAuthors");
        FlexUser user = new FlexUser();

        NewsArticle article = new NewsArticle();
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
        article.setSourceId("sourceId");

        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();

        ArticleView aView = new ArticleView(user, article);
    }

    /**
     * Test of getContent method, of class ArticleView.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        FlexUser user = new FlexUser();

        NewsArticle article = new NewsArticle();
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
        article.setSourceId("sourceId");

        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();

        ArticleView aView = new ArticleView(user, article);
        assertEquals("Description", aView.getArticle().getDescription());
    }

    /**
     * Test of getArticleViewHeader method, of class ArticleView.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        FlexUser user = new FlexUser();

        NewsArticle article = new NewsArticle();
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
        article.setSourceId("sourceId");

        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();
        source.setSourceId("sourceId");

        ArticleView aView = new ArticleView(user, article);
    }

    /**
     * Test of getPublishedAt method, of class ArticleView.
     */
    @Test
    public void testGetPublishedAt() {
        System.out.println("getPublishedAt");
        FlexUser user = new FlexUser();

        Date date = new Date();
        NewsArticle article = new NewsArticle();
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
        article.setPublishedAt(date);
        article.setSourceId("sourceId");

        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();

        ArticleView aView = new ArticleView(user, article);
        assertEquals(date, aView.getArticle().getPublishedAt());
    }

    /**
     * Test of getItem method, of class ArticleView.
     */
    @Test
    public void testGetItem() {
        System.out.println("getItem");
        FlexUser user = new FlexUser();

        NewsArticle article = new NewsArticle();
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
        article.setSourceId("sourceId");

        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();

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

        NewsArticle article = new NewsArticle();
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
        article.setSourceId("sourceId");

        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();

        ArticleView aView = new ArticleView(user, article);
        assertEquals(article, aView.getArticle());
        aView.select();
    }

    /**
     * Test of minimize method, of class ArticleView.
     */
    @Test
    public void testMinimize() {
        System.out.println("minimize");
        FlexUser user = new FlexUser();

        NewsArticle article = new NewsArticle();
        article.setTitle("Title");
        article.setDescription("Description");
        article.setImageUrl("imageUrl");
        
        article.setSourceId("sourceId");
        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();

        ArticleView aView = new ArticleView(user, article);
        assertEquals("Description", aView.getArticle().getDescription());
        aView.unselect();
    }

}
