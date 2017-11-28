package ui;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class LogoutUI extends SecuredUI {

    private static final long serialVersionUID = -484103282643769272L;
    private Navigator navigator;

    @Override
    public void init(VaadinRequest request) {
        getPage().setTitle("Logout from Ngutu. Your portal to the world.");
        setContent(new Label("Logout, sweet logout"));
    }

    @WebServlet(urlPatterns = "/logout", name = "LogoutUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = LogoutUI.class, productionMode = false, widgetset = "ui.AppWidgetSet")
    public static class LogoutUIServlet extends VaadinServlet {

        private static final long serialVersionUID = -8309707580707119681L;

        @Override
        protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
            if (request.getSession() != null) {
                request.getSession().invalidate();
            }
            response.sendRedirect("/login");
        }
    }

}
