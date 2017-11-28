package ui;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import javax.servlet.annotation.WebServlet;
import org.ngutu.ui.news.NewsView;
import org.ngutu.ui.news.NewsViewProvider;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
@Title("Ngutu Productive Reader: your portal to the world.")
@JavaScript("app://VAADIN/themes/mytheme/js/adsense.js")
@Push
public class NewsUI extends SecuredUI {

    private static final long serialVersionUID = -484103282643769272L;
    private Navigator navigator;

    @Override
    public void init(VaadinRequest request) {
        if(getSession().getAttribute("code") != null) {
            Notification.show("Authorization code = " + getSession().getAttribute("code"));
        }
        initNavigator();
    }
    
    public NewsView getMainView() {
        return (NewsView) super.getContent();
    }

    private void initNavigator() {
        navigator = new Navigator(this, this);
        navigator.addProvider(new WelcomeViewProvider());
        navigator.addProvider(new NewsViewProvider());
    }

    @WebServlet(urlPatterns = "/*", name = "NewsUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NewsUI.class, productionMode = false, widgetset = "ui.AppWidgetSet")
    public static class NewsUIServlet extends VaadinServlet {
        private static final long serialVersionUID = -3509795582956287827L;
    }
}
