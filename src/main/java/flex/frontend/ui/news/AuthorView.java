package flex.frontend.ui.news;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import flex.backend.news.db.NewsAuthor;


/**
 * Created by zua on 13/04/17.
 */
public class AuthorView extends VerticalLayout {
    private final NewsAuthor author;
    private Label name;
    private Link facebookUrl;
    private VerticalLayout info;
    private HorizontalLayout controls;

    public AuthorView(NewsAuthor author) {
        this.author = author;
        initName();
        initFacebookUrl();
        initInfo();
        initControls();
        setSizeFull();
        addComponents(info);
        setStyleName("article");
        setMargin(false);
    }

    private void initName() {
        if(author.getName() != null) {
            name = new Label(author.getName());
        }
    }

    private void initFacebookUrl() {
        if(author.getUrl() != null) {
            facebookUrl = new Link(author.getUrl(), new ExternalResource(author.getUrl()));
            facebookUrl.setTargetName("_blank");
        }
    }

    private void initInfo() {
        info = new VerticalLayout();
        if(name != null) {
            info.addComponent(name);
        }
        if(facebookUrl != null) {
            info.addComponent(facebookUrl);
        }
    }

    private void initControls() {
        controls = new HorizontalLayout();
    }

}
