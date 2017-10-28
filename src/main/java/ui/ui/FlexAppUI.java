package ui.ui;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import ui.view.main.FlexMainView;
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
@Push
public class FlexAppUI extends SecuredUI {

    private static final long serialVersionUID = -484103282643769272L;

    @Override
    public FlexMainView getContent() {
        return (FlexMainView) super.getContent();
    }

    @WebServlet(urlPatterns = "/*", name = "NewsUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = FlexAppUI.class, productionMode = true, widgetset = "ui.ui.AppWidgetSet")
    public static class NewsUIServlet extends VaadinServlet {

        private static final long serialVersionUID = -3509795582956287827L;
    }

}
