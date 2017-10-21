/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import db.FlexUser;
import db.NewsArticle;

/**
 *
 * @author zua
 */
public class ArticleViewHeader extends VerticalLayout {
    
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
        super.addComponent(title);
        super.setStyleName("article-header");
        super.setSizeFull();
        super.setMargin(false);
    }
    
    private void initImage() {
        // Initializes the image and sizes, style name and caption
        buildImage();
        configureImageSize();
        this.image.setStyleName("image");
        this.image.setCaption(null);
    }
    
    private void initTitle() {
        this.title = new Label(article.getTitle());
        this.title.setSizeFull();
        this.title.setStyleName("title");
    }

    public Image getImage() {
        return image;
    }

    public Label getTitle() {
        return title;
    }

    private void buildImage() {
        if(article.getImageUrl() != null) {
            image = new Image(null, new ExternalResource(article.getImageUrl()));
        }
        else {
            Notification.show("ImageNotFound for article " + article.getTitle());
            image = new Image();
        }
    }

    private void configureImageSize() {
        if (this.image.getWidth() >= this.image.getHeight()) {
            this.image.setWidth("100%");
            this.image.setHeightUndefined();
        } 
        else {
            this.image.setHeight("100%");
            this.image.setWidthUndefined();
        }
    }
    
    
    
}
