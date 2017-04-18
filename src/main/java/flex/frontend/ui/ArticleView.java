package flex.frontend.ui;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import flex.backend.db.ApiArticle;
import flex.backend.db.ApiSource;


/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends VerticalLayout implements Button.ClickListener {
    private final ApiSource source;
    private final ApiArticle article;
    private  Label title;
    private  Label author;
    private  Label content;
    private  Embedded image;
    private  HorizontalLayout controls;
    private  Button addVideoButton;
    private  Button shareOnFacebookButton;
    private  Button shareOnTwitterButton;

    public ArticleView(ApiSource source, ApiArticle article) {
        this.source = source;
        this.article = article;

        setSpacing(false);
        setSizeFull();
        initTitle(article);
        initAuthor(source, article);
        initContent(article);
        initImage(article);
        initControls(article);
        addComponents(image, title, author, content, controls);
        setSizeFull();
    }

    private void initControls(ApiArticle article) {
        this.addVideoButton = new Button(FontAwesome.YOUTUBE);
        this.addVideoButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        this.addVideoButton.addClickListener(this);

        this.shareOnFacebookButton = new Button(FontAwesome.FACEBOOK_OFFICIAL);
        this.shareOnFacebookButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        this.shareOnFacebookButton.addClickListener(this);

        this.shareOnTwitterButton = new Button(FontAwesome.TWITTER);
        this.shareOnTwitterButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        this.shareOnTwitterButton.addClickListener(this);


        this.controls = new HorizontalLayout();
        this.controls.addComponents(addVideoButton, shareOnFacebookButton, shareOnTwitterButton);

    }

    private String getLinkString(String url) {
        return "<a href='" + url + "'>" + "Read Full Story" + "</a>";
    }


    public ApiSource getSource() {
        return source;
    }

    public ApiArticle getArticle() {
        return article;
    }

    public Label getTitle() {
        return title;
    }

    public Label getAuthor() {
        return author;
    }

    public Label getContent() {
        return content;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if(event.getButton() == addVideoButton) {
            interactWithVideoButton();
        }

        else if(event.getButton() == shareOnFacebookButton) {
            interactWithShareOnFacebookButton();
        }

        else if(event.getButton() == shareOnTwitterButton) {
            Window w = new Window("Share on Twitter");
            w.center();
            w.setModal(true);

            TextField message = new TextField("Message");
            w.setContent(message);

            UI.getCurrent().addWindow(w);
        }
    }

    private void interactWithVideoButton() {
        Window w = new Window("Add video url");
        w.center();
        w.setModal(true);

        TextField text = new TextField("Video URL");
        Button save = new Button("Save", FontAwesome.SAVE);
        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                //videosUrls.add(text.getValue());
            }
        });

        Button cancel = new Button("Save", FontAwesome.TIMES);
        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                w.close();
            }
        });

        HorizontalLayout windowLayout = new HorizontalLayout(text, cancel, save);
        windowLayout.setSizeFull();
        windowLayout.setSpacing(true);
        windowLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        w.setContent(windowLayout);

        UI.getCurrent().addWindow(w);
    }

    private void interactWithShareOnFacebookButton() {
        Window w = new ShareOnFacebookWindow(source, article);
        UI.getCurrent().addWindow(w);
    }

    private void initTitle(ApiArticle article) {
        this.title = new Label(article.getTitle());
        this.title.setStyleName("article-title white-on-black");
        this.title.setSizeFull();
    }

    private void initAuthor(ApiSource source, ApiArticle article) {
        this.author = new Label(article.getAuthor() + ", " + source.getName());
        this.author.setStyleName("article-author white-on-black");
        this.author.setSizeFull();
    }

    private void initContent(ApiArticle article) {
        String text = article.getDescription() + " " + getLinkString(article.getUrl());
        this.content = new Label(text, ContentMode.HTML);
        this.content.setStyleName("article-content");
        this.content.setSizeFull();
    }

    private void initImage(ApiArticle article) {
        this.image = new Embedded("", new ExternalResource(article.getImageUrl()));
        this.image.setWidth("100%");
    }

    public Embedded getImage() {
        return image;
    }

    public HorizontalLayout getControls() {
        return controls;
    }

    public Button getAddVideoButton() {
        return addVideoButton;
    }

    public Button getShareOnFacebookButton() {
        return shareOnFacebookButton;
    }

    public Button getShareOnTwitterButton() {
        return shareOnTwitterButton;
    }
    
    
    
    
}
