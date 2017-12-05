package ui;

import com.auth0.exception.Auth0Exception;
import org.ngutu.ui.auth0.NgutuAuthAPI;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.AuthRequest;
import com.auth0.net.Request;
import com.restfb.types.User;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import db.AuthUserInfo;
import db.FlexUser;
import db.Gender;
import db.Neo4jSessionFactory;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import org.ngutu.ui.news.NewsView;
import org.ngutu.ui.share.NgutuFacebookAPI;
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
            if (request.getParameterMap().containsKey("code")) {
                handleAuth0Request(request);
            }
            if (request.getParameterMap().containsKey("access_token")) {
                handleFacebookRequest(request);
            }
        }
        // TODO: Check navigation
    }

    private void handleFacebookRequest(VaadinRequest request) {
        String fragment = getNavigator().getState();
        String accessToken = request.getParameter("access_token");
        if (accessToken != null) {
            try {
                User user = extractFacebookUser(fragment, accessToken);
                updateSession(convert2FlexUser(user));
            } catch (Exception ex) {
                Logger.getLogger(NgutuUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private User extractFacebookUser(String fragment, String accessToken) throws Auth0Exception {
        NgutuFacebookAPI api = new NgutuFacebookAPI(fragment);
        return api.fetchUser(accessToken);
    }
    
    private void handleAuth0Request(VaadinRequest request) {
        String fragment = getNavigator().getState();
        String authorizationCode = request.getParameter("code");
        if (authorizationCode != null) {
            try {
                UserInfo userInfo = extractUserInfo(fragment, authorizationCode);
                updateSession(convert2FlexUser(userInfo));
            } catch (Exception ex) {
                Logger.getLogger(NgutuUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    private void printUserInfo(UserInfo userInfo) {
        for (Object k : userInfo.getValues().keySet()) {
            System.out.printf("(k, v) = (%s, %s)\n", k, userInfo.getValues().get(k));
        }
    }

    private void updateSession(FlexUser user) throws ParseException {

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

    private FlexUser convert2FlexUser(UserInfo userInfo) {
        AuthUserInfo authUserInfo = convertAuth0User2AuthUserInfo(userInfo);
        FlexUser fuser = new FlexUser(authUserInfo.getSub(), null);
        fuser.setUserInfo(authUserInfo);
        return fuser;
    }

    private FlexUser convert2FlexUser(User user) {
        AuthUserInfo authUserInfo = convertFacebookUser2AuthUserInfo(user);
        FlexUser fuser = new FlexUser(authUserInfo.getSub(), null);
        fuser.setUserInfo(authUserInfo);
        return fuser;
    }

    private AuthUserInfo convertAuth0User2AuthUserInfo(UserInfo userInfo) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        authUserInfo.setSub((String) userInfo.getValues().get("sub"));
        authUserInfo.setGender(Gender.valueOf(((String) userInfo.getValues().get("gender")).toUpperCase()));
        authUserInfo.setEmailVerified((Boolean) userInfo.getValues().get("email_verified"));
        try {
            authUserInfo.setUpdatedAt(MyDateUtils.parseDate((String) userInfo.getValues().get("updated_at")));
        } catch (ParseException ex) {
            Logger.getLogger(NgutuUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        authUserInfo.setNickname((String) userInfo.getValues().get("nickname"));
        authUserInfo.setName((String) userInfo.getValues().get("name"));
        authUserInfo.setFamilyName((String) userInfo.getValues().get("family_name"));
        authUserInfo.setGivenName((String) userInfo.getValues().get("given_name"));
        authUserInfo.setLocale(new Locale((String) userInfo.getValues().get("locale")));
        authUserInfo.setPicture((String) userInfo.getValues().get("picture"));
        return authUserInfo;
    }

    private AuthUserInfo convertFacebookUser2AuthUserInfo(User userInfo) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        authUserInfo.setSub(userInfo.getEmail());
        authUserInfo.setGender(Gender.valueOf(userInfo.getGender()));
        authUserInfo.setEmailVerified(userInfo.getIsVerified());
        authUserInfo.setUpdatedAt(userInfo.getUpdatedTime());
        authUserInfo.setNickname(userInfo.getShortName());
        authUserInfo.setName(userInfo.getName());
        authUserInfo.setFamilyName(userInfo.getLastName());
        authUserInfo.setGivenName(userInfo.getFirstName());
        authUserInfo.setLocale(new Locale(userInfo.getLocale()));
        authUserInfo.setPicture(userInfo.getPicture().getUrl());
        return authUserInfo;
    }

    @WebServlet(urlPatterns = "/*", name = "NgutuUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NgutuUI.class, productionMode = false, widgetset = "ui.AppWidgetSet")
    public static class NgutuUIServlet extends VaadinServlet {

        private static final long serialVersionUID = -3509795582956287827L;
        private static Neo4jSessionFactory sessionfactory = Neo4jSessionFactory.getInstance();
    }

}
