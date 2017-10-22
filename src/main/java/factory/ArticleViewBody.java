/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import db.FlexUser;
import db.NewsArticle;
import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 *
 * @author zua
 */
public class ArticleViewBody extends VerticalLayout {

    private final NewsArticle article;
    private final FlexUser user;
    private AuthorsInfoView authors;
    private Label content;

    public ArticleViewBody(FlexUser user, NewsArticle article) {
        this.user = user;
        this.article = article;
        super.setStyleName("article-body");
        initContent();
        initAuthors();
        super.addComponents(content);
        super.setSpacing(false);
        super.setMargin(false);
    }

    private void initContent() {
        String value = VaadinIcons.CLOCK.getHtml()
                + " <strong>" + toText(article.getPublishedAt()) + "</strong>: "
                + article.getDescription();
        this.content = new Label(value, ContentMode.HTML);
        this.content.setStyleName("value");
        this.content.setWidth("100%");
        //this.content.setHeightUndefined();
    }

    private String toText(Date date) {
        if (date != null) {
            return DateFormatUtils.format(date, "dd MMM yyyy, HH:mm:ss");
        } else {
            return DateFormatUtils.format(new Date(), "dd MMM yyyy, HH:mm:ss");
        }
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
