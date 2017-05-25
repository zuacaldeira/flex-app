package flex.frontend.ui.news;


import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;

import com.vaadin.ui.*;
import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */

@Theme("mytheme")
@Push
@PreserveOnRefresh
public class NewsUI extends UI {
    private NewsView newsView;


    @Override
    protected void init(VaadinRequest request) {
        
        // Load new news
        newsView = new NewsView();
        setContent(newsView);
    }

    public NewsView getNewsView() {
        return newsView;
    }





    @WebServlet(urlPatterns = "/news/*", name = "NewsUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NewsUI.class, productionMode = false)
    public static class NewsUIServlet extends VaadinServlet {
    }

}