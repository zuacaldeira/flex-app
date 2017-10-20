/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import db.NewsArticle;
import db.NewsSource;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class SourceInfoView extends HorizontalLayout {

    private final String LOGO_HEIGHT = "36px";
    private final NewsArticle article;
    private NewsSource source;
    private Image logoImage;

    public SourceInfoView(NewsArticle article) {
        this.article = article;
        initSource();
        initSourceLogo();
        initSourceName();
    }

    /*
     * @TODO: Replace sourceId in NewsArticle with a reference to a NewsSource 
     * object.
     */
    private void initSource() {
        source = ServiceLocator.getInstance().findSourcesService().findSourceWithSourceId(article.getSourceId());
    }

    private void initSourceLogo() {
        if (source != null) {
            if (source.getLogoUrl() != null) {
                logoImage = new Image("", new ExternalResource(source.getLogoUrl()));
            } else {
                logoImage = new Image("");
            }
            logoImage.addStyleName("circle");
            logoImage.setHeight(LOGO_HEIGHT);
            logoImage.setWidth(LOGO_HEIGHT);
        }
    }

    private void initSourceName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
