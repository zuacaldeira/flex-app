/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.news.article;

import ui.news.article.ArticleView;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.vaadin.server.ExternalResource;
import db.news.NewsArticle;
import db.news.NewsAuthor;
import ui.news.UITest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class ArticleViewTestIT extends UITest {
    public ArticleViewTestIT() {
    }




    @DataProvider
    public static Object[][] articlesProvider() {
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date());
        Set<NewsAuthor> authors = new HashSet<>();
        authors.add(new NewsAuthor("name"));
        article.setAuthors(authors);
        ArticleView view = new ArticleView(article);
        return new Object[][] {
                {view, article}
        };
    }

    /**
     * Test of getArticle method, of class ArticleView.
     * @param view
     * @param expectedArticle
     */
    @Test
    @UseDataProvider("articlesProvider")
    public void testGetArticle(ArticleView view, NewsArticle expectedArticle) {
        System.out.println("getArticle");
        assertEquals(expectedArticle, view.getArticle());
    }

    /**
     * Test of getTitle method, of class ArticleView.
     * @param view
     * @param expectedArticle
     */
    @Test
    @UseDataProvider("articlesProvider")
    public void testGetTitle(ArticleView view, NewsArticle expectedArticle) {
        System.out.println("getTitle");
        assertEquals(expectedArticle.getTitle(), view.getTitle().getValue());
    }

    /**
     * Test of getAuthors method, of class ArticleView.
     * @param view
     * @param expectedArticle
     */
    @Test
    @UseDataProvider("articlesProvider")
    @Ignore
    public void testGetAuthor(ArticleView view, NewsArticle expectedArticle) {
        System.out.println("getAuthor");
        //assertTrue(expectedArticle.getAuthors().getName().contains(view.getAuthors().getComponent(0).getCaption()));
    }

    /**
     * Test of getContent method, of class ArticleView.
     * @param view
     * @param expectedArticle
     */
    @Test
    @UseDataProvider("articlesProvider")
    public void testGetContent(ArticleView view, NewsArticle expectedArticle) {
        System.out.println("getContent");
        assertEquals(expectedArticle.getDescription(), view.getContent().getValue());
    }

    /**
     * Test of getImage method, of class ArticleView.
     * @param view
     * @param expectedArticle
     */
    @Test
    @UseDataProvider("articlesProvider")
    public void testGetImage(ArticleView view, NewsArticle expectedArticle) {
        System.out.println("getImage");     
        assertEquals(expectedArticle.getImageUrl(), ((ExternalResource)view.getImage().getSource()).getURL());
    }


    /**
     * Test of getPublishedAt method, of class ArticleView.
     * @param view
     * @param expectedArticle
     */
    @Test
    @UseDataProvider("articlesProvider")
    public void testGetPublishedAt(ArticleView view, NewsArticle expectedArticle) throws ParseException {
        System.out.println("getPublishedAt");
        //assertTrue(expectedArticle.getPublishedAt().equals(DateFormat.getDateTimeInstance().parse(view.getPublishedAt().getValue())));
    }
    
    
    
    
    @Test
    @UseDataProvider("articlesProvider")
    public void testControls(ArticleView view, NewsArticle expectedArticle) {
        assertNotNull(view.getInfoActions());
    }
    
}
