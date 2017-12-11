/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import db.news.NewsArticle;
import db.news.NewsSource;
import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 *
 * @author zua
 */
public class SourceInfoView extends HorizontalLayout {

    private static final long serialVersionUID = -6727614163768414373L;

    private final String LOGO_HEIGHT = "36px";
    private final NewsArticle article;
    private NewsSource source;
    private Image logoImage;
    private Label sourceName;
    private Label publishedAt;

    public SourceInfoView(NewsArticle article) {
        this.article = article;
        initSource();
        initSourceLogo();
        initSourceName();
        initPublishedAt();
        VerticalLayout info = createInfo();
        super.addComponents(logoImage, info);
        super.setExpandRatio(info, 1);
        super.setComponentAlignment(logoImage, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(info, Alignment.MIDDLE_LEFT);
        super.setSizeUndefined();
        super.setWidth("100%");
        super.setMargin(new MarginInfo(false, true));
        super.setStyleName("source-info");
    }

    /*
     * @TODO: Replace sourceId in NewsArticle with a reference to a NewsSource 
     * object.
     */
    private void initSource() {
        source = article.getPublishedBy().getSource();
    }

    private void initSourceLogo() {
        if (source != null) {
            if (source.getLogoUrl() != null) {
                logoImage = new Image(null, new ExternalResource(source.getLogoUrl()));
            } else {
                logoImage = new Image();
            }
            logoImage.addStyleName("circle");
            logoImage.setHeight(LOGO_HEIGHT);
            logoImage.setWidth(LOGO_HEIGHT);
        } else {
            logoImage = new Image();
        }
    }

    private void initSourceName() {
        if (source != null) {
            sourceName = new Label(source.getName());
            sourceName.setSizeUndefined();
            //sourceName.setIcon(new ExternalResource(source.getLogoUrl()));
        }
        else {
            sourceName = new Label();
        }
    }

    private void initPublishedAt() {
        publishedAt = new Label(VaadinIcons.CLOCK.getHtml()
                + " <strong>" + toText(article.getPublishedAt()) + "</strong>", ContentMode.HTML);
    }

    private String toText(Date date) {
        if (date != null) {
            return DateFormatUtils.format(date, "dd MMM yyyy, HH:mm:ss");
        } else {
            return DateFormatUtils.format(new Date(), "dd MMM yyyy, HH:mm:ss");
        }
    }

    private VerticalLayout createInfo() {
        VerticalLayout info = new VerticalLayout();
        info.setSpacing(false);
        info.setMargin(false);
        if (sourceName != null) {
            info.addComponent(sourceName);
        }
        if (publishedAt != null) {
            info.addComponent(publishedAt);
        }
        return info;
    }

}
