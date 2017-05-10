/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.article;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.vaadin.server.ExternalResource;
import flex.backend.news.db.NewsArticle;
import flex.backend.news.db.NewsAuthor;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class ArticleViewTest {
    public ArticleViewTest() {
    }




    @DataProvider
    public static Object[][] articlesProvider() {
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", "publishedAt");
        article.setAuthor(new NewsAuthor("name"));
        ArticleView view = new ArticleView(article);
        return new Object[][] {
                {view, article}
        };
    }

    /**
     * Test of getArticle method, of class ArticleView.
     */
    @Test
    @UseDataProvider("articlesProvider")
    public void testGetArticle(ArticleView view, NewsArticle expectedArticle) {
        System.out.println("getArticle");
        assertEquals(expectedArticle, view.getArticle());
    }

    /**
     * Test of getTitle method, of class ArticleView.
     */
    @Test
    @UseDataProvider("articlesProvider")
    public void testGetTitle(ArticleView view, NewsArticle expectedArticle) {
        System.out.println("getTitle");
        assertEquals(expectedArticle.getTitle(), view.getTitle().getValue());
    }

    /**
     * Test of getAuthor method, of class ArticleView.
     */
    @Test
    @UseDataProvider("articlesProvider")
    public void testGetAuthor(ArticleView view, NewsArticle expectedArticle) {
        System.out.println("getAuthor");
        assertEquals(expectedArticle.getAuthor().getName(), view.getAuthor().getValue());
    }

    /**
     * Test of getContent method, of class ArticleView.
     */
    @Test
    @UseDataProvider("articlesProvider")
    public void testGetContent(ArticleView view, NewsArticle expectedArticle) {
        System.out.println("getContent");
        assertEquals(expectedArticle.getDescription(), view.getContent().getValue());
    }

    /**
     * Test of getImage method, of class ArticleView.
     */
    @Test
    @UseDataProvider("articlesProvider")
    public void testGetImage(ArticleView view, NewsArticle expectedArticle) {
        System.out.println("getImage");
        assertEquals(expectedArticle.getImageUrl(), ((ExternalResource)view.getImage().getSource()).getURL());
    }


    /**
     * Test of getUrl method, of class ArticleView.
     */
    @Test
    @UseDataProvider("articlesProvider")
    public void testGetUrl(ArticleView view, NewsArticle expectedArticle) {
        System.out.println("getUrl");
        assertEquals(expectedArticle.getUrl(), ((ExternalResource)view.getUrl().getResource()).getURL());
    }

    /**
     * Test of getPublishedAt method, of class ArticleView.
     */
    @Test
    @UseDataProvider("articlesProvider")
    public void testGetPublishedAt(ArticleView view, NewsArticle expectedArticle) {
        System.out.println("getPublishedAt");
        assertEquals(expectedArticle.getPublishedAt(), view.getPublishedAt().getValue());
    }
    
    
    
    
    @Test
    @UseDataProvider("articlesProvider")
    public void testControls(ArticleView view, NewsArticle expectedArticle) {
        assertNotNull(view.getReadButton());
        assertNotNull(view.getCommentButton());
        assertNotNull(view.getShareButton());
        assertNotNull(view.getYoutubeButton());
    }
    
}
