/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import db.FlexUser;
import db.NewsArticle;

/**
 *
 * @author zua
 */
public class ArticleViewHeader extends VerticalLayout {

    private static final long serialVersionUID = 1097524303616344971L;
    
    private final FlexUser user;
    private final NewsArticle article;
    private Image image;
    private SourceInfoView sourceInfo;
    

    public ArticleViewHeader(FlexUser user, NewsArticle article) {
        this.user = user;
        this.article = article;
        initSourceInfo();        
        if(sourceInfo != null) {
            super.addComponent(sourceInfo);
        }
        initImage();
        if(image != null) {
            super.addComponent(image);
        }
        super.setStyleName("article-header");
        super.setSizeFull();
        super.setMargin(false);
        setSpacing(false);
    }
    
    private void initSourceInfo() {
        sourceInfo = new SourceInfoView(article);
    }
    
    private void initImage() {
        // Initializes the image and sizes, style name and caption
        buildImage();
        if(image != null) {
            configureImageSize();
            this.image.setStyleName("image");
            this.image.setCaption(null);
        }
    }
    
    public Image getImage() {
        return image;
    }

    public SourceInfoView getSourceInfo() {
        return sourceInfo;
    }



    private void buildImage() {
        if(article.getImageUrl() != null) {
            image = new Image(null, new ExternalResource(article.getImageUrl()));
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

    void full() {
        image.setVisible(true);
        sourceInfo.setVisible(true);
    }
    
    void imagesOnly() {
        image.setVisible(true);
        sourceInfo.setVisible(true);
    }
    
    void titlesOnly() {
        image.setVisible(false);
        sourceInfo.setVisible(true);
    }
    
    
    
}
