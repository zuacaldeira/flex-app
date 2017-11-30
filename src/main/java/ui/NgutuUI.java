package ui;

import org.ngutu.ui.auth0.Auth0UserInfoResponse;
import org.ngutu.ui.auth0.NgutuAuthAPI;
import org.ngutu.ui.auth0.Auth0CallbackResponse;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.AuthRequest;
import com.auth0.net.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import org.ngutu.ui.news.NewsView;

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
public class NgutuUI extends SecuredUI {

    private static final long serialVersionUID = -484103282643769272L;

    public NewsView getMainView() {
        return (NewsView) super.getContent();
    }

    @Override
    public void init(VaadinRequest request) {
        if (request != null && request.getParameter("code") != null) {
            Notification.show("Authorization code = " + request.getParameter("code"));
            try {
                NgutuAuthAPI api = new NgutuAuthAPI();
                AuthRequest authRequest = api.exchangeCode(request.getParameter("code"), "https://ngutu.herokuapp.com/news");
                TokenHolder tokenHolder = authRequest.execute();
                System.out.println("ACCESS_TOKEN --> " + tokenHolder.getAccessToken());
                Request<UserInfo> req = api.userInfo(tokenHolder.getAccessToken());
                UserInfo userInfo = req.execute();
                System.out.println("EMAIL --> " + userInfo.getValues().keySet());
            } catch (Exception ex) {
                Logger.getLogger(NgutuUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // TODO: Check navigation
    }

    private Auth0CallbackResponse extractResponse(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Auth0CallbackResponse value = objectMapper.readValue(response, Auth0CallbackResponse.class);
            return value;
        } catch (IOException ex) {
            Logger.getLogger(NgutuUI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private Auth0UserInfoResponse extractUserInfo(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Auth0UserInfoResponse value = objectMapper.readValue(response, Auth0UserInfoResponse.class);
            return value;
        } catch (IOException ex) {
            Logger.getLogger(NgutuUI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void printAttributes(VaadinRequest request) {
        System.out.println("Auth type" + request.getAuthType());
        System.out.println("Parameter map " + request.getParameterMap());
        System.out.println("Has code parameter " + request.getParameter("code"));

    }

    @WebServlet(urlPatterns = "/*", name = "NgutuUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NgutuUI.class, productionMode = false, widgetset = "ui.AppWidgetSet")
    public static class NgutuUIServlet extends VaadinServlet {

        private static final long serialVersionUID = -3509795582956287827L;
    }

}
