package ui.ui;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import db.FlexUser;
import javax.servlet.annotation.WebServlet;
import ui.view.main.FlexMainView;

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
public class NewsUI extends SecuredUI {

    private static final long serialVersionUID = -484103282643769272L;

    @Override
    public Component getContent() {
        return super.getContent();
    }
    
    public FlexMainView getMainView() {
        return (FlexMainView) super.getContent();
    }

    @Override
    protected final FlexMainView createUIContent() {
        FlexMainView mainView = new FlexMainView((FlexUser) getSession().getAttribute("user"));
        mainView.populate();
        return mainView;
    }
    
    @WebServlet(urlPatterns = "/*", name = "NewsUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NewsUI.class, productionMode = false, widgetset = "ui.ui.AppWidgetSet")
    public static class NewsUIServlet extends VaadinServlet {
        private static final long serialVersionUID = -3509795582956287827L;
    }
}
