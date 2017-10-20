/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import db.FlexUser;
import db.NewsArticle;

/**
 *
 * @author zua
 */
public class ArticleViewHeader extends AbsoluteLayout {
    
    private final FlexUser user;
    private final NewsArticle article;
    private Image image;
    private Label title;
    

    public ArticleViewHeader(FlexUser user, NewsArticle article) {
        this.user = user;
        this.article = article;
        initImage();
        initTitle();
        super.addComponent(image);
        super.addComponent(title, "bottom:5%");
        super.setStyleName("article-image");
    }
    
    private void initImage() {
        this.image = new Image(null, new ExternalResource(article.getImageUrl()));
        this.image.setStyleName("image");
        this.image.setSizeFull();
        /*if (this.image.getWidth() >= this.image.getHeight()) {
            this.image.setWidth("100%");
            this.image.setHeightUndefined();
        } else {
            this.image.setHeight("100%");
            this.image.setWidthUndefined();
        }
        this.image.setCaption(null);*/
    }
    
    private void initTitle() {
        this.title = new Label(article.getTitle());
        this.title.setStyleName("image-title");
    }

    public Image getImage() {
        return image;
    }

    public Label getTitle() {
        return title;
    }
    
    
    
}
