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
import db.opinion.Read;
import db.relationships.AuthoredBy;
import db.relationships.EditedBy;
import db.relationships.PublishedBy;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class ArticleViewTestIT {

    public ArticleViewTestIT() {
    }

    /**
     * Test of createInfoHeader method, of class ArticleView.
     */
    @Test
    public void testGetArticleViewHeader() {
        System.out.println("testGetArticleViewHeader");
        initMinimalScenario();
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle();
        article.setTitle("title");
        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();
        
        AuthoredBy authoredBy = new AuthoredBy();
        authoredBy.setArticle(article);
        authoredBy.setAuthor(author);
        
        PublishedBy publishedBy = new PublishedBy();
        publishedBy.setArticle(article);
        publishedBy.setSource(source);
        
        ArticleView aView = new ArticleView(user, publishedBy);
        assertNotNull(aView.getArticleViewHeader());
    }

    /**
     * Test of createInfoBody method, of class ArticleView.
     */
    @Test
    public void testGetArticleViewBody() {
        System.out.println("testGetArticleViewBody");
        initMinimalScenario();
        FlexUser user = new FlexUser();
        NewsArticle article = new NewsArticle();
        article.setTitle("title");
        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();
        
        AuthoredBy authoredBy = new AuthoredBy();
        authoredBy.setArticle(article);
        authoredBy.setAuthor(author);
        
        PublishedBy publishedBy = new PublishedBy();
        publishedBy.setArticle(article);
        publishedBy.setSource(source);
        
        ArticleView aView = new ArticleView(user, publishedBy);
        assertNotNull(aView.getArticleViewBody());
    }


    /**
     * Test of createInfoActions method, of class ArticleView.
     */
    @Test
    public void testGetArticleViewActions() {
        System.out.println("testGetArticleViewActions");
        initMinimalScenario();
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsArticle article = new NewsArticle();
        article.setTitle("title");
        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();
        
        AuthoredBy authoredBy = new AuthoredBy();
        authoredBy.setArticle(article);
        authoredBy.setAuthor(author);
        
        PublishedBy publishedBy = new PublishedBy();
        publishedBy.setArticle(article);
        publishedBy.setSource(source);
        
        ArticleView aView = new ArticleView(user, publishedBy);
        assertNotNull(aView.getArticleViewActions());
    }

    /**
     * Test of buttonClick method, of class ArticleView.
     */
    @Test
    public void testButtonClick() {
        System.out.println("buttonClick");
        initMinimalScenario();
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsArticle article = new NewsArticle();
        article.setTitle("title");
        NewsAuthor author = new NewsAuthor("Author");
        NewsSource source = new NewsSource();
        
        AuthoredBy authoredBy = new AuthoredBy();
        authoredBy.setArticle(article);
        authoredBy.setAuthor(author);
        
        PublishedBy publishedBy = new PublishedBy();
        publishedBy.setArticle(article);
        publishedBy.setSource(source);
        
        ArticleView aView = new ArticleView(user, publishedBy);
        ArticleViewActions actions = aView.getArticleViewActions();
        assertNotNull(actions);
        assertEquals(4, actions.getComponentCount());
        
        actions.handleHideClick(actions.getHideButton());
        actions.handleFakeClick(actions.getFakeButton());
        actions.handleFavouriteClick(actions.getFavoriteButton());
        //assertTrue(((FlexActionButton) actions.getComponent(0)).getStyleName().contains("purple"));
        assertFalse(actions.getFavoriteButton().getStyleName().contains("yellow"));
        assertFalse(actions.getFakeButton().getStyleName().contains("red"));
        assertFalse(actions.getHideButton().getStyleName().contains("purple"));

        actions.handleHideClick(actions.getHideButton());
        actions.handleFakeClick(actions.getFakeButton());
        actions.handleFavouriteClick(actions.getFavoriteButton());
    }

    private void initMinimalScenario() {
        NewsSource source = new NewsSource();
        source.setName("Name");
        source.setLogoUrl("logoUrl");

        NewsArticle article = new NewsArticle();
        article.setTitle("title");

        NewsAuthor author = new NewsAuthor("author");
        
        EditedBy editedBy = new EditedBy();
        editedBy.setAuthor(author);
        editedBy.setSource(source);

        AuthoredBy authoredBy = new AuthoredBy();
        authoredBy.setArticle(article);
        authoredBy.setAuthor(author);
        
        PublishedBy publishedBy = new PublishedBy();
        publishedBy.setArticle(article);
        publishedBy.setSource(source);
        
        FlexUser user = new FlexUser("test:username", "test:password");
        Read read = new Read();
        read.setArticle(article);
        read.setUser(user);
        
        ServiceLocator.getInstance().findUserService().save(user);
    }
}
