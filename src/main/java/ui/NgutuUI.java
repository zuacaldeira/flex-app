package ui;

import com.auth0.exception.Auth0Exception;
import org.ngutu.ui.auth0.NgutuAuthAPI;
import org.ngutu.ui.auth0.Auth0CallbackResponse;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.AuthRequest;
import com.auth0.net.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import db.Auth0UserInfo;
import db.FlexUser;
import db.Gender;
import db.Neo4jSessionFactory;
import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import org.ngutu.ui.news.NewsView;
import services.FlexUserServiceInterface;
import utils.MyDateUtils;
import utils.ServiceLocator;

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
@Title("Ngutu °°° Connecting the Unconnected Dots")
@PushStateNavigation
@Push
@JavaScript("app://VAADIN/themes/mytheme/js/adsense.js")
public class NgutuUI extends SecuredUI {

    private static final long serialVersionUID = -484103282643769272L;

    public NewsView getMainView() {
        return (NewsView) super.getContent();
    }

    @Override
    public void init(VaadinRequest request) {
        if (request != null) {
            String fragment = getNavigator().getState();
            String authorizationCode = request.getParameter("code");
            System.out.println("State = " + getNavigator().getState() + " View = " + getNavigator().getCurrentView());
            System.out.println("Code = " + authorizationCode);
            if (authorizationCode != null) {
                Notification.show("Authorization code ", authorizationCode, Notification.Type.HUMANIZED_MESSAGE);
                try {
                    UserInfo userInfo = extractUserInfo(fragment, authorizationCode);
                    printUserInfo(userInfo);
                    updateSession(userInfo);
                } catch (Exception ex) {
                    Logger.getLogger(NgutuUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        // TODO: Check navigation
    }

    private UserInfo extractUserInfo(String fragment, String authorizationCode) throws Auth0Exception {
        NgutuAuthAPI api = new NgutuAuthAPI(fragment);
        AuthRequest authRequest = api.exchangeCode(authorizationCode, api.getRedirectUrl());
        TokenHolder tokenHolder = authRequest.execute();

        System.out.println("ACCESS_TOKEN --> " + tokenHolder.getAccessToken());

        Request<UserInfo> req = api.userInfo(tokenHolder.getAccessToken());
        UserInfo userInfo = req.execute();

        System.out.println("NAME --> " + userInfo.getValues().get("name"));
        return userInfo;
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

    private void printUserInfo(UserInfo userInfo) {
        for(Object k: userInfo.getValues().keySet()) {
            System.out.printf("(k, v) = (%s, %s)\n", k, userInfo.getValues().get(k));
        }
    }

    private void updateSession(UserInfo userInfo) throws ParseException {
        Auth0UserInfo auth0UserInfo = new Auth0UserInfo();
        auth0UserInfo.setSub((String) userInfo.getValues().get("sub"));
        auth0UserInfo.setGender(Gender.valueOf( ((String) userInfo.getValues().get("gender")).toUpperCase()));
        auth0UserInfo.setEmailVerified((Boolean) userInfo.getValues().get("email_verified"));
        auth0UserInfo.setUpdatedAt(MyDateUtils.parseDate((String) userInfo.getValues().get("updated_at")));
        auth0UserInfo.setNickname((String)userInfo.getValues().get("nickname"));
        auth0UserInfo.setName((String)userInfo.getValues().get("name"));
        auth0UserInfo.setFamilyName((String)userInfo.getValues().get("family_name"));
        auth0UserInfo.setGivenName((String)userInfo.getValues().get("given_name"));
        auth0UserInfo.setLocale(new Locale((String)userInfo.getValues().get("locale")));
        auth0UserInfo.setPicture((String)userInfo.getValues().get("picture"));

        FlexUser user = new FlexUser(auth0UserInfo.getSub(), null);
        user.setUserInfo(auth0UserInfo);
        System.out.println("USER  --- " + user);
        System.out.println("USER INFO --- " + user);
        
        FlexUserServiceInterface service = ServiceLocator.getInstance().findUserService();
        service.save(user);
        service.login(user.getUsername(), "null");
        System.out.println("USER SAVED --- " + user);
        System.out.println("USER INFO  SAVED --- " + user);
        getSession().setAttribute("user", user);
        System.out.println("SET ATTRIBUTE USER " + getSession().getAttribute("user"));
    }

    @WebServlet(urlPatterns = "/*", name = "NgutuUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NgutuUI.class, productionMode = false, widgetset = "ui.AppWidgetSet")
    public static class NgutuUIServlet extends VaadinServlet {

        private static final long serialVersionUID = -3509795582956287827L;
        private static Neo4jSessionFactory sessionfactory = Neo4jSessionFactory.getInstance();
    }

}
