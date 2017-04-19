package flex.frontend.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;

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

    private AbsoluteLayout rootLayout;
    private SourcesInfoView sourcesInfoView;
    private ArticlesInfoView articlesInfoView;
    private MapView mapView;


    @Override
    protected void init(VaadinRequest request) {
        
        // Load new news
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
        base.setStyleName("base-layout");
        base.setSizeFull();
        base.setMargin(true);

        /* Full-size CSS Layout root component */

        rootLayout = new AbsoluteLayout();
        rootLayout.setSizeFull();
        rootLayout.addComponent(base);
        setContent(rootLayout);


        /* Start the actor system and managing the information to display in the ui */
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}