package ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import org.ngutu.ui.news.NewsView;
import org.ngutu.ui.news.NewsViewProvider;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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
    private final String USER_AGENT = "Mozilla/5.0";

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
        
        printAttributes(request);
        
        if (request.getParameter("code") != null) {
            Notification.show("Authorization code = " + request.getParameter("code"));
            try {
                String token = sendPost(request.getParameter("code").toString());
                System.out.println("TOKEN = " + token.toUpperCase());

                String email = sendGet(token);
                System.out.println("EMAIL = " + email.toUpperCase());
            } catch (Exception ex) {
                Logger.getLogger(NewsUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            initNavigator();
        }
    }

    // HTTP POST request
    private String sendPost(String code) throws Exception {

        String url = "https://ngutu.eu.auth0.com/oauth/token";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Content-type", "application/x-www-form-urlencoded");
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("client_id", "K8hEG_ew0eF4fv9tRDY1RZ72RjPK-n_Q"));
        urlParameters.add(new BasicNameValuePair("redirect_uri", "https://ngutu.herokuapp.com/#!news"));
        urlParameters.add(new BasicNameValuePair("client_secret", "oAka59gWaZ0rgnmq61geaMEpcB-RPAANal9M6seQSqeidnHWQK5JIDXeApJ0OJZ5"));
        urlParameters.add(new BasicNameValuePair("code", code));
        urlParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
        urlParameters.add(new BasicNameValuePair("scope", "openid profile email"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + post.getEntity());
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());
        return extractAccessToken(result.toString());
    }

    // HTTP GET request
    private String sendGet(String token) throws Exception {

        String url = "https://ngutu.eu.auth0.com/userinfo/?access_token=" + token;

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);

        HttpResponse response = client.execute(request);

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());
        return extractEmail(result.toString());
    }

    private String extractAccessToken(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Auth0CallbackResponse value = objectMapper.readValue(response, Auth0CallbackResponse.class);
            return value.getAccess_token();
        } catch (IOException ex) {
            Logger.getLogger(NewsUI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private String extractEmail(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Auth0UserInfoResponse value = objectMapper.readValue(response, Auth0UserInfoResponse.class);
            return value.getEmail();
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
