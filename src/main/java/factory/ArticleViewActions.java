/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.ui.Alignment;
import components.CommentButton;
import components.FakeButton;
import components.FavoriteButton;
import components.HideButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import db.auth.FlexUser;
import db.news.NewsArticle;
import db.opinion.Fake;
import db.opinion.Favorite;
import db.opinion.Read;
import java.util.Date;
import org.ngutu.ui.share.ShareOnFacebook;
import services.auth.FlexUserService;
import services.news.NewsArticleService;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class ArticleViewActions extends HorizontalLayout implements Button.ClickListener {

    private static final long serialVersionUID = 5885947852356212404L;

    private CommentButton commentButton;
    private FavoriteButton favoriteButton;
    private FakeButton fakeButton;
    private HideButton hideButton;
    private FacebookShareButton facebookButton;
    private final FlexUser user;
    private final NewsArticle article;

    public ArticleViewActions(FlexUser user, NewsArticle article) {
        this.user = user;
        this.article = article;
        super.setSizeFull();
        super.setHeight("21px");
        super.setStyleName("actions");
        super.setSpacing(false);
        initActions();
        super.addComponents(facebookButton, favoriteButton, fakeButton, hideButton);
        super.setComponentAlignment(facebookButton, Alignment.MIDDLE_CENTER);
        if(user == null) {
            setDescription("You have to login to enable social actions");
            disableActions();
        }
    }

    private void disableActions() {
        facebookButton.setEnabled(false);
        hideButton.setEnabled(false);
        fakeButton.setEnabled(false);
        favoriteButton.setEnabled(false);
    }

    private void initActions() {
        commentButton = new CommentButton();
        commentButton.addClickListener(this);
        
        facebookButton = new FacebookShareButton(article.getUrl());
        facebookButton.addClickListener(event -> {
            ShareOnFacebook shareOnFacebook = new ShareOnFacebook();
            shareOnFacebook.share(article, "Check this out!");
        });
        
        
        favoriteButton = new FavoriteButton();
        favoriteButton.addClickListener(this);
        if (user != null && user.getFavorite().contains(article)) {
            favoriteButton.addStyleName("yellow");
        }

        fakeButton = new FakeButton();
        fakeButton.addClickListener(this);
        if (user != null && user.getFake().contains(article)) {
            fakeButton.addStyleName("red");
        }

        hideButton = new HideButton();
        hideButton.addClickListener(this);
        if (user != null && user.getRead().contains(article)) {
            hideButton.addStyleName("purple");
        }

    }

    public NewsArticleService getArticlesService() {
        return ServiceLocator.getInstance().findArticlesService();
    }

    public FlexUserService getUserService() {
        return ServiceLocator.getInstance().findUserService();
    }

    protected void handleHideClick(HideButton button) {
        if (!button.getStyleName().contains("purple")) {
            Read read = new Read();
            read.setArticle(article);
            read.setUser(user);
            read.setCreatedAt(new Date());
            getUserService().save(user);
            button.addStyleName("purple");
            button.setDescription("Mark as Read");
        } else {
            Read read = new Read();
            read.setArticle(article);
            read.setUser(user);
            user.getRead().remove(read);
            getUserService().save(user);
            button.removeStyleName("purple");
            button.setDescription("Mark as Unread");
        }
        if (getParent() != null && getParent().getParent() != null) {
            ((VerticalLayout) getParent().getParent()).removeComponent(getParent());
        }
    }

    protected void handleFavouriteClick(FavoriteButton button) {
        if (!button.getStyleName().contains("yellow")) {
            Favorite favorite = new Favorite();
            favorite.setArticle(article);
            favorite.setUser(user);
            favorite.setCreatedAt(new Date());
            getUserService().save(user);
            button.addStyleName("yellow");
            button.setDescription("Unmark Favorite");
        } else {
            Favorite favorite = new Favorite();
            favorite.setArticle(article);
            favorite.setUser(user);
            user.getFavorite().remove(favorite);
            getUserService().save(user);
            button.removeStyleName("yellow");
            button.setDescription("Mark as Favorite");
        }
    }

    protected void handleFakeClick(FakeButton button) {
        if (!button.getStyleName().contains("red")) {
            Fake fake = new Fake();
            fake.setArticle(article);
            fake.setUser(user);
            fake.setCreatedAt(new Date());
            getUserService().save(user);
            button.addStyleName("red");
            button.setDescription("Unmark Fake");
        } else {
            Fake fake = new Fake();
            fake.setArticle(article);
            fake.setUser(user);
            user.getFavorite().remove(fake);
            getUserService().save(user);
            button.removeStyleName("red");
            button.setDescription("Mark as Fake");
        }
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton() == hideButton) {
            handleHideClick((HideButton) event.getButton());
        } else if (event.getButton() == favoriteButton) {
            handleFavouriteClick((FavoriteButton) event.getButton());
        } else if (event.getButton() == fakeButton) {
            handleFakeClick((FakeButton) event.getButton());
        }
    }

    public CommentButton getCommentButton() {
        return commentButton;
    }

    public FavoriteButton getFavoriteButton() {
        return favoriteButton;
    }

    public FakeButton getFakeButton() {
        return fakeButton;
    }

    public HideButton getHideButton() {
        return hideButton;
    }

    void full() {
        setVisible(true);
    }

    void imagesOnly() {
        setVisible(false);
    }
    
    void titlesOnly() {
        setVisible(false);
    }
}
