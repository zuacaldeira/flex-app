/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.bantu.videos;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;
import flex.backend.bantus.db.FlexVideo;
import flex.backend.bantus.services.FlexVideoService;
import flex.backend.news.services.AbstractDBService;
import flex.frontend.ui.GraphEntityView;
import flex.frontend.ui.TitleLabel;
import java.text.SimpleDateFormat;

/**
 *
 * @author zua
 */
public class FlexVideoView extends GraphEntityView<FlexVideo> {

    private Label title;
    private Link author;
    private Link url;
    private Label language;
    private HorizontalLayout poster;
    private Label postedAt;
    private Embedded video;
    private Label videoDescription;

    public FlexVideoView(FlexVideo flexVideo) {
        super(flexVideo);
    }
    
    private void initTitle() {
        this.title = new TitleLabel(getItem().getTitle());
        this.title.setHeightUndefined();
    }
    
    
    private void initAuthor() {
        author = new Link(null, new ExternalResource(getItem().getAuthor()));
    }
    
    private void initLanguage() {
        language = new Label(getItem().getLanguage());
    }
    
    private void initPostedAt() {
       SimpleDateFormat format = new SimpleDateFormat("HH:mm, ddMMMyy");
       postedAt = new Label(format.format(getItem().getPostedAt()));
       postedAt.setStyleName("date");
    }
    
    private void initVideo() {
        ExternalResource resource = new ExternalResource(getItem().getUrl());
        video = new Embedded(null, resource);
        video.setMimeType("application/x-shockwave-flash");
        video.setParameter("allowFullScreen", "true");
        video.setWidth("100%");
        video.setHeightUndefined();
    }
    

    
    private String extractIdFromUrl(String url) {
        String id = url.split("=")[1];
        return id;
    }
    private void initPoster() {
        initAuthor();
        initLanguage();
        initPostedAt();
        poster = new HorizontalLayout();
        if(author != null) {
            poster.addComponent(author);
        }
        if(language != null) {
            poster.addComponent(language);
        }
   }


    @Override
    public AbstractOrderedLayout createInfoHeader() {
        initTitle();
        initVideo();
        VerticalLayout header = new VerticalLayout(title, video);
        header.setSpacing(false);
        header.setMargin(false);
        return header;
    }

    @Override
    public AbstractOrderedLayout createInfoBody() {
        initVideoDescription();
        VerticalLayout body = new VerticalLayout(videoDescription);
        body.setSpacing(false);
        body.setMargin(false);
        return body;
    }

    private void initVideoDescription() {
        videoDescription = new Label(getItem().getDescription());
        videoDescription.setWidth("100%");
        videoDescription.setHeightUndefined();
    }

    @Override
    public AbstractDBService<FlexVideo> getService() {
        return new FlexVideoService();
    }

}
