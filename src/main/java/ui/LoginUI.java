package ui;

import com.auth0.AuthenticationController;
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
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ngutu.ui.auth0.AuthenticationControllerProvider;

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
public class LoginUI extends SecuredUI {

    private static final long serialVersionUID = -484103282643769272L;

    private Navigator navigator;

    @Override
    public void init(VaadinRequest request) {
        getPage().setTitle("Login into Ngutu. Your portal to the world.");
        setContent(new Label("Login, sweet login"));
    }

    @WebServlet(urlPatterns = "/login", name = "LoginUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = LoginUI.class, productionMode = false, widgetset = "ui.AppWidgetSet")
    public static class LoginUIServlet extends VaadinServlet {

        private static final long serialVersionUID = -5482443374120131558L;
        private AuthenticationController authenticationController;
        private String domain;

        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);
            domain = config.getServletContext().getInitParameter("com.auth0.domain");
            try {
                authenticationController = AuthenticationControllerProvider.getInstance(config);
            } catch (UnsupportedEncodingException e) {
                throw new ServletException("Couldn't create the AuthenticationController instance. Check the configuration.", e);
            }
        }

        @Override
        protected void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
            String redirectUri = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/callback";

            String authorizeUrl = authenticationController.buildAuthorizeUrl(req, redirectUri)
                    .withAudience(String.format("https://%s/userinfo", domain))
                    .build();
            res.sendRedirect(authorizeUrl);
        }

    }

}
