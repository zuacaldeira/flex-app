/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import db.auth.FlexUser;
import db.news.NewsArticle;

/**
 *
 * @author zua
 */
public class ArticleViewBody extends VerticalLayout {

    private static final long serialVersionUID = -714857695983398055L;

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
        super.setMargin(new MarginInfo( false, true));
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

    void full() {
        title.setVisible(true);
        content.setVisible(true);
    }

    void imagesOnly() {
        title.setVisible(true);
        content.setVisible(false);
    }
    
    void titlesOnly() {
        title.setVisible(true);
        content.setVisible(false);
    }

}
