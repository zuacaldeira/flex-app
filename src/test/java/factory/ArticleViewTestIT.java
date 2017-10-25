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
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        ArticleView aView = new ArticleView(user, article);
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
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        ArticleView aView = new ArticleView(user, article);
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
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        ArticleView aView = new ArticleView(user, article);
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
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        ArticleView aView = new ArticleView(user, article);
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
        NewsSource source = new NewsSource("sourceId", "name", "description", "url", "category", "en", "GB");
        source.setLogoUrl("logoUrl");
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date(), "sourceId", "language", "country");
        NewsAuthor author = new NewsAuthor("author");
        author.addArticle(article);
        source.addCorrespondent(author);
        ServiceLocator.getInstance().findSourcesService().save(source);

        FlexUser user = new FlexUser("test:username", "test:password");
        ServiceLocator.getInstance().findUserService().register("test:username", "test:password");

        ServiceLocator.getInstance().findArticlesService().markAsFake("test:username", article);
        ServiceLocator.getInstance().findArticlesService().markAsFavorite("test:username", article);
        ServiceLocator.getInstance().findArticlesService().markAsRead("test:username", article);
    }

}
