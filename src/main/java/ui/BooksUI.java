package ui;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import javax.servlet.annotation.WebServlet;

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
@Title("Ngutu Productive Reader")
@JavaScript("app://VAADIN/themes/mytheme/js/adsense.js")
//@HtmlImport("app://VAADIN/themes/mytheme/html/adsense.html")
@Push
public class BooksUI extends SecuredUI {

    private static final long serialVersionUID = -484103282643769272L;
    private Navigator navigator;
    
    @Override
    public void init(VaadinRequest request) {
        getPage().setTitle("Ngutu. Your portal to the world.");
    }

    @Override
    public Component getContent() {
        return super.getContent();
    }
    
    @WebServlet(urlPatterns = "/books/*", name = "BooksUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = BooksUI.class, productionMode = false, widgetset = "ui.AppWidgetSet")
    public static class BooksUIServlet extends VaadinServlet {
        private static final long serialVersionUID = 6335912660173166675L;
    }
    
}
