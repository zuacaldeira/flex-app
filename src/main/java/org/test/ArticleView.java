package org.test;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;

/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends VerticalLayout {
    private final ApiSource source;
    private final ApiArticle article;
    private final Label title;
    private final Label author;
    private final Label content;

    public ArticleView(ApiSource source, ApiArticle article) {
        this.source = source;
        this.article = article;

        this.title = new Label(article.getTitle());
        this.title.setStyleName("article-title white-on-black");
        this.title.setWidth("100%");
        this.title.setHeight("100%");

        this.author = new Label(article.getAuthor() + ", " + source.getName());
        this.author.setStyleName("article-author white-on-black");
        this.author.setWidth("100%");
        this.author.setHeight("100%");

        this.content = new Label(article.getDescription());
        this.content.setStyleName("article-content");
        this.content.setWidth("100%");
        this.content.setHeight("100%");

        addComponents(title, author, content);
        setSizeFull();
        setSpacing(false);
    }


    public ApiSource getSource() {
        return source;
    }

    public ApiArticle getArticle() {
        return article;
    }

    public Label getTitle() {
        return title;
    }

    public Label getAuthor() {
        return author;
    }

    public Label getContent() {
        return content;
    }
}
