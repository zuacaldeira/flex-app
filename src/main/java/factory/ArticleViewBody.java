/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import db.FlexUser;
import db.NewsArticle;

/**
 *
 * @author zua
 */
public class ArticleViewBody extends VerticalLayout {

    private final NewsArticle article;
    private final FlexUser user;
    private AuthorsInfoView authors;
    private Label title;
    private Label content;

    public ArticleViewBody(FlexUser user, NewsArticle article) {
        this.user = user;
        this.article = article;
        super.setStyleName("article-body");
        initTitle();
        initContent();
        initAuthors();
        super.addComponents(title, content);
        super.setSpacing(false);
        super.setMargin(true);
    }

    private void initTitle() {
        this.title = new Label(article.getTitle());
        this.title.setSizeFull();
        this.title.setStyleName("title");
    }

    private void initContent() {
        String value = article.getDescription();
        this.content = new Label(value, ContentMode.HTML);
        this.content.setStyleName("value");
        this.content.setWidth("100%");
        //this.content.setHeightUndefined();
    }

    /* TODO: Implement */
    private void initAuthors() {
        // Do nothing
    }

    public NewsArticle getArticle() {
        return article;
    }

    public FlexUser getUser() {
        return user;
    }

    public AuthorsInfoView getAuthors() {
        return authors;
    }

    public Label getContent() {
        return content;
    }

}
