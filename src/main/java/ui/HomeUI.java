package ui;

import com.auth0.SessionUtils;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
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
public class HomeUI extends SecuredUI {

    private static final long serialVersionUID = -484103282643769272L;
    private Navigator navigator;

    @Override
    public void init(VaadinRequest request) {
        getPage().setTitle("Ngutu Home. Your portal to the world.");
        setContent(new Label("Home, sweet home"));
    }

    @Override
    public Component getContent() {
        return super.getContent();
    }

    @WebServlet(urlPatterns = "/*", name = "HomeUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = HomeUI.class, productionMode = false, widgetset = "ui.AppWidgetSet")
    public static class HomeUIServlet extends VaadinServlet {

        private static final long serialVersionUID = 6335912660173166675L;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            final String accessToken = (String) SessionUtils.get(req, "accessToken");
            final String idToken = (String) SessionUtils.get(req, "idToken");
            if (accessToken != null) {
                req.setAttribute("userId", accessToken);
            } else if (idToken != null) {
                req.setAttribute("userId", idToken);
            }
            req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, res);
        }
    }

}
