/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import button.CommentButton;
import button.FakeButton;
import button.FavoriteButton;
import button.HideButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import db.FlexUser;
import db.NewsArticle;
import services.NewsArticleServiceInterface;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class ArticleViewActions extends HorizontalLayout implements Button.ClickListener {

    private CommentButton commentButton;
    private FavoriteButton favoriteButton;
    private FakeButton fakeButton;
    private HideButton hideButton;
    private final FlexUser user;
    private final NewsArticle article;

    public ArticleViewActions(FlexUser user, NewsArticle article) {
        this.user = user;
        this.article = article;
        initActions();
    }

    private void initActions() {
        commentButton = new CommentButton();
        favoriteButton = new FavoriteButton();
        fakeButton = new FakeButton();
        hideButton = new HideButton();

        commentButton.addClickListener(this);
        hideButton.addClickListener(this);
        fakeButton.addClickListener(this);
        favoriteButton.addClickListener(this);

        if (user != null && getService().isFavorite(user.getUsername(), article)) {
            favoriteButton.addStyleName("yellow");
        }

        if (user != null && getService().isFake(user.getUsername(), article)) {
            fakeButton.addStyleName("red");
        }

        if (user != null && getService().isRead(user.getUsername(), article)) {
            hideButton.addStyleName("purple");
        }

        HorizontalLayout actions = new HorizontalLayout(commentButton, favoriteButton, fakeButton, hideButton);
        actions.setSizeFull();
        actions.setStyleName("actions");
        actions.setSpacing(true);
    }

    public NewsArticleServiceInterface getService() {
        return ServiceLocator.getInstance().findArticlesService();
    }

    protected void handleHideClick(HideButton button) {
        if (!button.getStyleName().contains("purple")) {
            getService().markAsRead(user.getUsername(), this.article);
            button.addStyleName("purple");
            button.setDescription("Mark as Read");
        } else {
            getService().removeMarkAsRead(user.getUsername(), this.article);
            button.removeStyleName("purple");
            button.setDescription("Mark as Unread");
        }
        if (getParent() != null) {
            ((VerticalLayout) getParent()).removeComponent(this);
        }
    }

    protected void handleFavouriteClick(FavoriteButton button) {
        if (!button.getStyleName().contains("yellow")) {
            getService().markAsFavorite(user.getUsername(), this.article);
            button.addStyleName("yellow");
            button.setDescription("Unmark Favorite");
        } else {
            getService().removeMarkAsFavorite(user.getUsername(), this.article);
            button.removeStyleName("yellow");
            button.setDescription("Mark as Favorite");
        }
    }

    protected void handleFakeClick(FakeButton button) {
        if (!button.getStyleName().contains("red")) {
            getService().markAsFake(user.getUsername(), this.article);
            button.addStyleName("red");
            button.setDescription("Unmark Fake");
        } else {
            getService().removeMarkAsFake(user.getUsername(), this.article);
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

}