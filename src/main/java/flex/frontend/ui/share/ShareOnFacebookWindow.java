/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.share;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import flex.backend.db.ApiArticle;
import flex.backend.db.ApiSource;
import flex.frontend.ui.ArticleView;
import flex.frontend.ui.CancelButton;
import flex.frontend.ui.FlexViewFactory;
import flex.frontend.ui.SaveButton;



/**
 *
 * @author zua
 */
public class ShareOnFacebookWindow extends Window implements Button.ClickListener {
     private static final String NETWORK_NAME = "Facebook";
     private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/v2.9/me";

    
    private ArticleView articleView;
    private TextArea messageView;
    private SaveButton saveButton;
    private VerticalLayout contentLayout;
    private CancelButton cancelButton;
    private HorizontalLayout controls;
    
    public ShareOnFacebookWindow(ApiSource source, ApiArticle article) {
        super("Share on Facebook");
        
        this.articleView = FlexViewFactory.createArticleView(source, article);

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
        }
        this.close();
    }

}
