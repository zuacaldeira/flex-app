package flex.frontend.ui.news;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import flex.backend.news.db.NewsArticle;
import flex.frontend.ui.FlexButton;


/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends VerticalLayout {
    private final NewsArticle article;
    private Label title;
    private Label author;
    private Label content;
    private Image image;
    private Link url;
    private VerticalLayout info;
    private HorizontalLayout controls;
    private FlexButton commentButton;
    private FlexButton shareButton;
    private FlexButton publishedAt;

    public ArticleView(NewsArticle article) {
        this.article = article;

        setSizeFull();
        setStyleName("article");
        setMargin(false);
        initInfo();

        addComponent(info);
    }

    private void initLink() {
        this.url = new Link("Read Full Story", new ExternalResource(article.getUrl()));
        url.setTargetName("_blank"); 
    }

    public NewsArticle getArticle() {
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

    private void initTitle() {
        this.title = new Label(article.getTitle());
        this.title.setStyleName("article-title white-on-black");
        this.title.setSizeFull();
    }

    private void initContent() {
        this.content = new Label(article.getDescription());
        this.content.setStyleName("article-content");
        this.content.setSizeFull();
    }

    private void initImage() {
        if(article.getImageUrl() != null) {
            this.image = new Image("", new ExternalResource(article.getImageUrl()));
            this.image.setWidth("100%");
        }
        else {
            this.image = new Image();
        }
    }

    public Image getImage() {
        return image;
    }
    
    public void initAuthor() {
        author = new Label(article.getAuthor().getName());
        author.setSizeFull();
    }

    private void initControls() {
        initTimeLabel();
        commentButton = new FlexButton("Comment", VaadinIcons.COMMENT);
        shareButton = new FlexButton(("Share"), VaadinIcons.SHARE_SQUARE);
        controls = new HorizontalLayout(commentButton, shareButton);
        controls.setSizeFull();
    }

    private void initInfo() {
        initTitle();
        initAuthor();
        initContent();
        initLink();
        initImage();
        initControls();
        info = new VerticalLayout(image, title, author, content, url, controls);
        info.setWidth("100%"); 
    }
    
    public void initTimeLabel() {
        String t = article.getPublishedAt();
        publishedAt = new FlexButton(t, VaadinIcons.CLOCK);
        publishedAt.setEnabled(false);
    }

    public Link getUrl() {
        return url;
    }

    public FlexButton getPublishedAt() {
        return publishedAt;
    }
}
