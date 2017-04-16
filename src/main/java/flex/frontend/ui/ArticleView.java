package flex.frontend.ui;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import flex.backend.db.ApiArticle;
import flex.backend.db.ApiSource;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends VerticalLayout implements Button.ClickListener {
    private final ApiSource source;
    private final ApiArticle article;
    private final Label title;
    private final Label author;
    private final Label content;
    private final Embedded image;
    private  HorizontalLayout controls;
    private  Button addVideoButton;
    private  Button shareOnFacebookButton;
    private  Button shareOnTwitterButton;
    private List<String> videosUrls;

    public ArticleView(ApiSource source, ApiArticle article) {
        this.source = source;
        this.article = article;

        this.title = new Label(article.getTitle());
        this.title.setStyleName("article-title white-on-black");
        this.title.setWidth("100%");
        this.title.setHeight("100%");

        this.author = new Label(article.getAuthor() + ", " + source.getName());
        this.author.setStyleName("article-author white-on-black");
        this.author.setWidth("100%");
        this.author.setHeight("100%");

        String text = article.getDescription() + " " + getLinkString(article.getUrl());
        this.content = new Label(text, ContentMode.HTML);
        this.content.setStyleName("article-content");
        this.content.setWidth("100%");
        this.content.setHeight("100%");

        this.image = new Embedded("", new ExternalResource(article.getImageUrl()));
        this.image.setWidth("100%");
        this.image.setHeight("100px");


        this.videosUrls = new LinkedList<>();

        initControls(article);
        addComponents(title, author, content, image, controls);
        setSizeFull();
        setSpacing(false);
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
        this.controls.setWidth("100%");
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
            Window w = new Window("Share on Facebook");
            w.center();
            w.setModal(true);

            TextField message = new TextField("Message");
            w.setContent(message);

            UI.getCurrent().addWindow(w);
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
                videosUrls.add(text.getValue());
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
}
