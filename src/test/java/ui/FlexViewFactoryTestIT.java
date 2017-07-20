/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.vaadin.server.ExternalResource;
import db.news.FlexUser;
import db.news.NewsArticle;
import db.news.NewsAuthor;
import db.news.NewsSource;
import ui.news.UITest;
import ui.news.author.AuthorView;
import ui.news.source.SourceView;
import ui.news.article.ArticleView;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class FlexViewFactoryTestIT extends UITest {
    
    public FlexViewFactoryTestIT() {
    }

    /**
     * Test of createSourceView method, of class FlexViewFactory.
     */
    @Test
    @UseDataProvider("sourcesProvider")
    public void testCreateSourceView(NewsSource source) {
        System.out.println("createSourceView");
        SourceView view = FlexViewFactory.getInstance().createSourceView(new FlexUser(), source);
        assertEquals(source, view.getItem());
        assertEquals(source.getName(), view.getName().getValue());
        assertEquals(source.getCategory(), view.getCategory().getValue());
        assertEquals(source.getDescription(), view.getDesc().getValue());
        assertEquals(source.getLanguage(), view.getLanguage().getValue());
        assertEquals(source.getCountry(), view.getCountry().getValue());
    }
    
    @DataProvider
    public static Object[][] sourcesProvider() {
        return new Object[][] {
            {new NewsSource("sourceId", "name", "description", "url", "category", "language", "country")}
        };
    }

    /**
     * Test of createArticleView method, of class FlexViewFactory.
     * @param article
     */
    @Test
    @UseDataProvider("articlesProvider")
    public void testCreateArticleView(NewsArticle article) {
        System.out.println("createArticleView");
        ArticleView view = FlexViewFactory.getInstance().createArticleView(new FlexUser(), article);
        assertEquals(article, view.getArticle());
        assertEquals(article.getDescription(), view.getContent().getValue());
        assertEquals(article.getImageUrl(), ((ExternalResource)view.getImage().getSource()).getURL());
        // @TODO: assertEquals(article.getPublishedAt(), view.getPublishedAt().getCaption());
        assertEquals(article.getSourceId(), view.getArticle().getSourceId());
        assertEquals(article.getTitle(), view.getTitle().getValue());
    }
    
    @DataProvider
    public static Object[][] articlesProvider() {
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date());
        Set<NewsAuthor> authors = new HashSet<>();
        authors.add(new NewsAuthor("name"));
        article.setAuthors(authors);
        return new Object[][] {
            {article}
        };
    }


    /**
     * Test of createAuthorView method, of class FlexViewFactory.
     */
    @Test
    @UseDataProvider("authorsProvider")
    public void testCreateAuthorView(NewsAuthor author) {
        System.out.println("createAuthorView");
        AuthorView view = FlexViewFactory.getInstance().createAuthorView(new FlexUser(), author);
        assertEquals(author, view.getAuthor());
    }
    
    @DataProvider
    public static Object[][] authorsProvider() {
        NewsAuthor author = new NewsAuthor("name");
        author.addArticle(new NewsArticle("title", "description", "url", "imageUrl", new Date()));
        
        return new Object[][] {
            {author}
        };
    }
    
}
