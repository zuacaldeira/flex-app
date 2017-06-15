package flex.frontend.ui.news;


import flex.frontend.ui.news.article.NewsCategoriesView;
import flex.frontend.ui.news.author.NewsAuthorsView;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import flex.frontend.ui.FlexMainView;

import flex.frontend.ui.SecuredUI;
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
public class NewsUI extends SecuredUI {

    @Override
    protected String getPageLocation() {
        return "/flex-app/news";
    }

    @Override
    protected FlexMainView createMainView() {
        String currentLocation = Page.getCurrent().getLocation().getPath();
        System.out.println("currentLocation -> " + currentLocation);
        if(currentLocation.endsWith("categories")) {
            return new NewsCategoriesView();
        }
        else if(currentLocation.endsWith("publishers")) {
            return new NewsPublishersView();
        }
        else if(currentLocation.endsWith("authors")) {
            return new NewsAuthorsView();
        }
        else {
            return new NewsView();
        }
    }

    @Override
    public NewsView getContent() {
        return (NewsView) super.getContent();
    }

    @WebServlet(urlPatterns = "/news/*", name = "NewsUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NewsUI.class, productionMode = false)
    public static class NewsUIServlet extends VaadinServlet {
    }

}