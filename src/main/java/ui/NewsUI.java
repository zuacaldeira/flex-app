package ui;

import com.auth0.json.auth.TokenHolder;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.AuthRequest;
import com.auth0.net.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class NewsUI extends SecuredUI {

    @WebServlet(urlPatterns = "/*", name = "NewsUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NewsUI.class, productionMode = false, widgetset = "ui.AppWidgetSet")
    public static class NewsUIServlet extends VaadinServlet {
        private static final long serialVersionUID = -3509795582956287827L;
    }

    private static final long serialVersionUID = -484103282643769272L;
    private Navigator navigator;

    private void initNavigator() {
        navigator = new Navigator(this, this);
        navigator.addProvider(new WelcomeViewProvider());
        navigator.addProvider(new NewsViewProvider());
    }

    public NewsView getMainView() {
        return (NewsView) super.getContent();
    }

    @Override
    public void init(VaadinRequest request) {
        
        if (request.getParameter("code") != null) {
            Notification.show("Authorization code = " + request.getParameter("code"));
            try {
                NgutuAuthAPI api = new NgutuAuthAPI();
                AuthRequest authRequest = api.requestToken(request.getParameter("code"));
                TokenHolder tokenHolder = authRequest.execute();
                Request<UserInfo> req = api.userInfo(tokenHolder.getAccessToken());
                UserInfo userInfo = req.execute();
                System.out.println("EMAIL --> " + userInfo.getValues().get("email"));
            } catch (Exception ex) {
                Logger.getLogger(NewsUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            initNavigator();
        }
    }

  

    private Auth0CallbackResponse extractResponse(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Auth0CallbackResponse value = objectMapper.readValue(response, Auth0CallbackResponse.class);
            return value;
        } catch (IOException ex) {
            Logger.getLogger(NewsUI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private Auth0UserInfoResponse extractUserInfo(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Auth0UserInfoResponse value = objectMapper.readValue(response, Auth0UserInfoResponse.class);
            return value;
        } catch (IOException ex) {
            Logger.getLogger(NewsUI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void printAttributes(VaadinRequest request) {
        System.out.println("Auth type" + request.getAuthType());
        System.out.println("Parameter map " + request.getParameterMap());
        System.out.println("Has code parameter " + request.getParameter("code"));
        
    }
}
