package org.test;

import javax.servlet.annotation.WebServlet;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.*;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;

import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */

@Theme("mytheme")
@Push
public class MyUI extends UI {

    private ActorSystem actorSystem;
    private AbsoluteLayout rootLayout;
    private SourcesInfoView sourcesInfoView;
    private ArticlesInfoView articlesInfoView;
    private MapView mapView;


    @Override
    protected void init(VaadinRequest request) {
        sourcesInfoView = new SourcesInfoView();
        articlesInfoView = new ArticlesInfoView();
        mapView = new MapView();

        /* World News tab */
        /* Data presentation is provided in a tabsheet */
        TabSheet tabs = new TabSheet();
        tabs.setSizeFull();
        tabs.addTab(mapView, "The World News");
        tabs.addTab(sourcesInfoView, "News Sources");
        tabs.addTab(articlesInfoView, "Latest News");

        VerticalLayout base = new VerticalLayout(tabs);
        base.setSizeFull();
        base.setMargin(true);

        /* Full-size CSS Layout root component */

        rootLayout = new AbsoluteLayout();
        rootLayout.setSizeFull();
        rootLayout.addComponent(base);
        setContent(rootLayout);


        /* Start the actor system and managing the information to display in the ui */
        actorSystem = ActorSystem.create("Flex_Actor_System");
        new Thread(new Runnable() {
            @Override
            public void run() {
                ActorRef mapManager = actorSystem.actorOf(Props.create(NewsManager.class));
                mapManager.tell(new LoadAllMessage(MyUI.this), ActorRef.noSender());
            }
        }).start();
    }




    public void addArticleMarker(ApiSource source, ApiArticle article) {

        access(new Runnable() {
            @Override
            public void run() {
                //GoogleMapMarker marker = createGoogleMapMarker(source, article);
                //googleMap.addMarker(marker);
                articlesInfoView.addComponent(FlexViewFactory.createArticleView(source, article));
            }
        });

    }

    private GoogleMapMarker createGoogleMapMarker(ApiSource source, ApiArticle article) {
        CountryData cdata = new GeoInfo(source).getCountryData();

        GoogleMapMarker marker = new GoogleMapMarker(
                article.getTitle(),
                new LatLon(cdata.getLatitude(), cdata.getLongitude()),
                false);

        marker.setCaption(article.getTitle());
        marker.setIconUrl(article.getImageUrl());

        GoogleMapInfoWindow info = new GoogleMapInfoWindow(article.getTitle(), marker);
        info.setContent(article.getDescription());
        info.setWidth("100px");
        info.setHeight("60px");

        return marker;
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
