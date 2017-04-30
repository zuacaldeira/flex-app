package flex.frontend.ui;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addon.oauthpopup.OAuthListener;
import org.vaadin.addon.oauthpopup.OAuthPopupButton;
import org.vaadin.addon.oauthpopup.OAuthPopupOpener;
import org.vaadin.addon.oauthpopup.buttons.FacebookButton;
import org.vaadin.addon.oauthpopup.buttons.GitHubButton;
import org.vaadin.addon.oauthpopup.buttons.LinkedInButton;
import org.vaadin.addon.oauthpopup.buttons.TwitterButton;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@Push
@Theme("mytheme")
@SuppressWarnings("serial")
public class LoginUI extends UI {
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = LoginUI.class)
	public static class Servlet extends VaadinServlet {
	}
	


	private final VerticalLayout layout = new VerticalLayout();

	@Override
	protected void init(VaadinRequest request) {

		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);

		addTwitterButton();
		addFacebookButton();
		addLinkedInButton();
		addGitHubButton();
		
		addTwitterNativeButton();
		
		layout.addComponent(new Link("Add-on at Vaadin Directory", new ExternalResource("http://vaadin.com/addon/oauth-popup-add-on")));
		layout.addComponent(new Link("Source code at GitHub", new ExternalResource("https://github.com/ahn/vaadin-oauthpopup")));
	}
	
	private void addTwitterButton() {
		ApiInfo api = ApiInfo.TWITTER_API;
		OAuthPopupButton button = new TwitterButton(api.apiKey, api.apiSecret);
		addButton(api, button);
	}
	
	private void addFacebookButton() {
		ApiInfo api = ApiInfo.FACEBOOK_API;
		OAuthPopupButton button = new FacebookButton(api.apiKey, api.apiSecret);
		addButton(api, button);
	}
	
	private void addLinkedInButton() {
		ApiInfo api = ApiInfo.LINKEDIN_API;
		OAuthPopupButton button = new LinkedInButton(api.apiKey, api.apiSecret);
		addButton(api, button);
	}
	
	private void addGitHubButton() {
		ApiInfo api = ApiInfo.GITHUB_API;
		OAuthPopupButton button = new GitHubButton(api.apiKey, api.apiSecret);
		addButton(api, button);
	}
	
	private void addTwitterNativeButton() {
		final NativeButton b = new NativeButton("Another Twitter Auth Button");
		
		OAuthPopupOpener opener = new OAuthPopupOpener(
				ApiInfo.TWITTER_API.scribeApi, 
				ApiInfo.TWITTER_API.apiKey,
				ApiInfo.TWITTER_API.apiSecret);
		opener.extend(b);
		opener.addOAuthListener(new OAuthListener() {
			@Override
			public void authSuccessful(String accessToken,
					String accessTokenSecret, String oauthRawResponse) {
				Notification.show("authSuccessful");
			}
			
			@Override
			public void authDenied(String reason) {
				Notification.show("authDenied");
			}
		});
		
		layout.addComponent(b);
	}

	private void addButton(final ApiInfo service, OAuthPopupButton button) {

		// In most browsers "resizable" makes the popup
		// open in a new window, not in a tab.
		// You can also set size with eg. "resizable,width=400,height=300"
		button.setPopupWindowFeatures("resizable,width=900,height=600");

		HorizontalLayout hola = new HorizontalLayout();
		hola.setSpacing(true);
		hola.addComponent(button);

		layout.addComponent(hola);

		button.addOAuthListener(new Listener(service, hola));
	}

	private class Listener implements OAuthListener {

		private final ApiInfo service;
		private final HorizontalLayout hola;

		private Listener(ApiInfo service, HorizontalLayout hola) {
			this.service = service;
			this.hola = hola;
		}

		@Override
		public void authSuccessful(final String accessToken,
				final String accessTokenSecret, String oauthRawResponse) {
			hola.addComponent(new Label("Authorized."));
			Button testButton = new Button("Test " + service.name + " API");
			testButton.addStyleName(ValoTheme.BUTTON_LINK);
			hola.addComponent(testButton);
			testButton.addClickListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					GetTestComponent get = new GetTestComponent(service,
							accessToken, accessTokenSecret);
					Window w = new Window(service.name, get);
					w.center();
					w.setWidth("75%");
					w.setHeight("75%");
					addWindow(w);
				}
			});
		}

		@Override
		public void authDenied(String reason) {
			hola.addComponent(new Label("Auth failed."));
		}
                
                
	}

}