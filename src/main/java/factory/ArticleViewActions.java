/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import org.ngutu.ui.common.CommentButton;
import org.ngutu.ui.common.FakeButton;
import org.ngutu.ui.common.FavoriteButton;
import org.ngutu.ui.common.HideButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import db.auth.FlexUser;
import db.news.NewsArticle;
import backend.services.auth.FlexUserService;
import backend.services.news.NewsArticleService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
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

    public ArticleViewActions(NewsArticle article) {
        this.user = getUser();
        this.article = article;
        super.setSizeFull();
        super.setHeightUndefined();
        super.setStyleName("actions");
        super.setSpacing(false);
        initActions();
        if (user != null && user.isAdmin()) {
            super.addComponent(facebookButton);
            super.setComponentAlignment(facebookButton, Alignment.MIDDLE_CENTER);
        }
        super.addComponents(favoriteButton, fakeButton, hideButton);
        super.setComponentAlignment(favoriteButton, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(fakeButton, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(hideButton, Alignment.MIDDLE_CENTER);
        if (user == null) {
            super.setDescription("You have to login to enable social actions");
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
        facebookButton = new FacebookShareButton();
        facebookButton.addClickListener(event -> {
            facebookButton.addStyleName("scale-in-out");
            getUI().addWindow(new ShareWindow(article));
        });
        commentButton = new CommentButton();
        commentButton.addClickListener(this);

        favoriteButton = new FavoriteButton();
        favoriteButton.addClickListener(this);
        if (user != null && isFavorite(user, article)) {
            favoriteButton.addStyleName("yellow");
        }

        fakeButton = new FakeButton();
        fakeButton.addClickListener(this);
        if (user != null && isFake(user, article)) {
            fakeButton.addStyleName("red");
        }

        hideButton = new HideButton();
        hideButton.addClickListener(this);
        if (user != null && isRead(user, article)) {
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
            user.getRead().add(article);
            getUserService().save(user);
            button.addStyleName("purple");
            button.setDescription("Mark as Read");
        } else {
            user.getRead().remove(article);
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
            user.getFavorite().add(article);
            getUserService().save(user);
            button.addStyleName("yellow");
            button.setDescription("Unmark Favorite");
        } else {
            user.getFavorite().remove(article);
            getUserService().save(user);
            button.removeStyleName("yellow");
            button.setDescription("Mark as Favorite");
        }
    }

    protected void handleFakeClick(FakeButton button) {
        if (!button.getStyleName().contains("red")) {
            user.getFake().add(article);
            getUserService().save(user);
            button.addStyleName("red");
            button.setDescription("Unmark Fake");
        } else {
            user.getFake().remove(article);
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

    private boolean isFavorite(FlexUser user, NewsArticle article) {
        return user.getFavorite().contains(article);
    }

    private boolean isFake(FlexUser user, NewsArticle article) {
        return user.getFake().contains(article);
    }

    private boolean isRead(FlexUser user, NewsArticle article) {
        return user.getRead().contains(article);
    }

    public NewsArticle getArticle() {
        return article;
    }

    public FacebookShareButton getFacebookButton() {
        return facebookButton;
    }

    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    private FlexUserService getUsersService() {
        return ServiceLocator.getInstance().findUserService();
    }
}
