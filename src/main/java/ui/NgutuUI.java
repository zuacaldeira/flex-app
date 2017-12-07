package ui;

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
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import org.ngutu.ui.share.NgutuFacebookAPI;
import services.FlexUserServiceInterface;
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
    private NgutuFacebookAPI facebookAPI = new NgutuFacebookAPI("");

    public NgutuFacebookAPI getFacebookAPI() {
        return facebookAPI;
    }

    @Override
    public void init(VaadinRequest request) {
        if (request != null) {
            printRequest(request);
            if (request.getParameterMap().containsKey("code")) {
                handleCodeRequest(request);
            }
        }
        // TODO: Check navigation
    }

    private void handleCodeRequest(VaadinRequest request) {
        String fragment = getNavigator().getState();
        String code = request.getParameter("code");
        System.out.println("Code -> " + code);
        if (code != null) {
            NgutuFacebookAPI api = new NgutuFacebookAPI("");
            User user = api.fetchUserWithCode(code);
            printUserInfo(user);
            try {
                updateSession(convert2FlexUser(user));
            } catch (ParseException ex) {
                Logger.getLogger(NgutuUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void printRequest(VaadinRequest request) {
        for (Object k : request.getParameterMap().keySet()) {
            System.out.printf("(k, v) = (%s, %s)\n", k, request.getParameterMap().get(k));
        }
    }

    private void printUserInfo(User user) {
        System.out.printf("(%s, %s)\n", "About", user.getAbout());
        System.out.printf("(%s, %s)\n", "Email", user.getEmail());
        System.out.printf("(%s, %s)\n", "First Name", user.getFirstName());
        System.out.printf("(%s, %s)\n", "Bio", user.getBio());
        System.out.printf("(%s, %s)\n", "Birthday", user.getBirthday());
        System.out.printf("(%s, %s)\n", "Context", user.getContext());
        System.out.printf("(%s, %s)\n", "Cover Photo", user.getCover());
        System.out.printf("(%s, %s)\n", "Id", user.getId());
        System.out.printf("(%s, %s)\n", "Picture", user.getPicture().getUrl());
    }

    private void updateSession(FlexUser user) throws ParseException {

        FlexUserServiceInterface service = ServiceLocator.getInstance().findUserService();
        service.save(user);
        getSession().setAttribute("user", user);
    }

    private FlexUser convert2FlexUser(User user) {
        AuthUserInfo authUserInfo = convertFacebookUser2AuthUserInfo(user);
        FlexUser fuser = new FlexUser(authUserInfo.getSub(), null);
        fuser.setUserInfo(authUserInfo);
        return fuser;
    }

    private AuthUserInfo convertFacebookUser2AuthUserInfo(User userInfo) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        authUserInfo.setSub(userInfo.getEmail());
        authUserInfo.setUpdatedAt(userInfo.getUpdatedTime());
        authUserInfo.setNickname(userInfo.getShortName());
        authUserInfo.setName(userInfo.getName());
        authUserInfo.setFamilyName(userInfo.getLastName());
        authUserInfo.setGivenName(userInfo.getFirstName());
        authUserInfo.setLocale(userInfo.getLocale());
        authUserInfo.setPicture(userInfo.getPicture().getUrl());
        return authUserInfo;
    }

    @WebServlet(urlPatterns = "/*", name = "NgutuUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NgutuUI.class, productionMode = false, widgetset = "ui.AppWidgetSet")
    public static class NgutuUIServlet extends VaadinServlet {

        private static final long serialVersionUID = -3509795582956287827L;

    }

}
