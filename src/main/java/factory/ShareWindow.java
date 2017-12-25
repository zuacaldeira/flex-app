/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import components.FlexWindow;
import components.SaveButton;
import db.news.NewsArticle;
import org.ngutu.ui.share.ShareOnFacebook;

/**
 *
 * @author zua
 */
public class ShareWindow extends FlexWindow {

    private static final long serialVersionUID = -5084704367793654761L;
    private VerticalLayout form;
    private TextArea textArea;
    private SaveButton saveButton;
    private final NewsArticle article;

    public ShareWindow(NewsArticle article) {
        super("Post Composer");
        this.article = article;
        initForm();
        super.setContent(form);
        super.setHeightUndefined();
        super.setWidth("35%");
    }

    private void initForm() {
        textArea = new TextArea("Message:");
        textArea.setHeight(3, Unit.CM);
        textArea.setWidth("100%");

        saveButton = new SaveButton();
        saveButton.setCaption("Post to Facebook");
        saveButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        saveButton.setWidth("100%");
        saveButton.addClickListener(click -> {
            postToFacebook();
        });

        form = new VerticalLayout(textArea, saveButton);
        form.setWidth("100%");
        form.setHeightUndefined();
    }

    private void postToFacebook() {
        String message = textArea.getValue();
        ShareOnFacebook shareOnFacebook = new ShareOnFacebook();
        shareOnFacebook.share(article, message);
        Notification.show("Success! You're message was posted in your timeline", Notification.Type.TRAY_NOTIFICATION);
        this.close();
    }

}
