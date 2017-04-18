/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.GraphResponse;
import com.restfb.types.User;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import flex.backend.db.ApiArticle;
import flex.backend.db.ApiSource;



/**
 *
 * @author zua
 */
public class ShareOnFacebookWindow extends Window implements Button.ClickListener {
    private final ArticleView articleView;
    private final TextArea messageView;
    private final SaveButton saveButton;
    private final VerticalLayout contentLayout;
    private final CancelButton cancelButton;
    private final HorizontalLayout controls;
    private FacebookClient facebookClient;

    public ShareOnFacebookWindow(ApiSource source, ApiArticle article) {
        super("Share on Facebook");
        
        this.articleView = FlexViewFactory.createArticleView(source, article);
        articleView.removeComponent(articleView.getControls());
        //this.articleView.setSizeFull();
        
        this.messageView = new TextArea("My message");
        this.messageView.setSizeFull();
        
        this.saveButton = new SaveButton();
        this.saveButton.addClickListener(this);
        
        this.cancelButton = new CancelButton();
        this.cancelButton.addClickListener(this);

        this.controls = new HorizontalLayout(cancelButton, saveButton);
        this.controls.setSizeUndefined();
        
        VerticalLayout myData = new VerticalLayout(messageView, controls);
        myData.setSizeFull();
        myData.setComponentAlignment(controls, Alignment.MIDDLE_RIGHT);
        
        this.contentLayout = new VerticalLayout(this.articleView, myData);
        this.contentLayout.setExpandRatio(articleView, .5f);
        this.contentLayout.setExpandRatio(myData, .5f);
        
        this.setContent(contentLayout);
        this.setModal(true);
        this.center();
        this.setWidth("50%");
        this.setHeight("90%");
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if(event.getButton() == saveButton) {
            postOnFacebook();
        }
        this.close();
    }

    private void postOnFacebook() {
        facebookClient = new DefaultFacebookClient(FacebookRestApi.getAccessToken(), FacebookRestApi.getAppSecret(), Version.LATEST);

        // Request will include appsecret_proof
        User me = facebookClient.fetchObject("me", User.class);
        Notification.show("I am " + me.getName(), Notification.Type.TRAY_NOTIFICATION);
        
        String message = "Good Morning " + me.getName();
        
        // Publish Good Morning! Message
        GraphResponse publishMessageResponse = 
                facebookClient.publish("me/feed", GraphResponse.class, 
                        Parameter.with("message", message));
        
        System.out.println("Published message ID: " + publishMessageResponse.getId());

        // You may also generate the appsecret_proof value directly (not normally needed).
        String proof = new DefaultFacebookClient().obtainAppSecretProof(FacebookRestApi.getAccessToken(), FacebookRestApi.getAppSecret());
        
        System.out.println("Here's my proof: " + proof);
    }    
}
