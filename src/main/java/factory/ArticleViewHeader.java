/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import db.auth.FlexUser;
import db.relationships.PublishedBy;

/**
 *
 * @author zua
 */
public class ArticleViewHeader extends VerticalLayout {

    private static final long serialVersionUID = 1097524303616344971L;
    
    private final FlexUser user;
    private final PublishedBy publishedBy;
    private Image image;
    private SourceInfoView sourceInfo;
    

    public ArticleViewHeader(FlexUser user, PublishedBy publishedBy) {
        this.user = user;
        this.publishedBy = publishedBy;
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
        sourceInfo = new SourceInfoView(publishedBy);
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
        if(publishedBy.getArticle().getImageUrl() != null) {
            image = new Image(null, new ExternalResource(publishedBy.getArticle().getImageUrl()));
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
        if(image != null) {
            image.setVisible(true);
        }
        sourceInfo.setVisible(true);
    }
    
    void imagesOnly() {
        if(image != null) {
            image.setVisible(true);
        }
        sourceInfo.setVisible(true);
    }
    
    void titlesOnly() {
        if(image != null) {
            image.setVisible(false);
        }
        sourceInfo.setVisible(true);
    }
    
    
    
}
