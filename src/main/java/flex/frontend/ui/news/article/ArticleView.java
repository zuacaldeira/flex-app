package flex.frontend.ui.news.article;

import flex.frontend.ui.GraphEntityView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import flex.backend.news.db.NewsArticle;
import flex.frontend.ui.FlexButton;


/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends GraphEntityView {
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
    private Label publishedAt;
    private FlexButton readButton;
    private FlexButton youtubeButton;
    private Label sourceName;

    public ArticleView(NewsArticle article) {
        this.article = article;
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
    
    private void initAuthor() {
        author = new Label(article.getAuthor().getName());
        author.setSizeFull();
    }

    private void initControls() {
        initTimeLabel();
        commentButton = new FlexButton("", VaadinIcons.COMMENT);
        commentButton.setDescription("Comment");
        
        shareButton = new FlexButton((""), VaadinIcons.CONNECT);
        shareButton.setDescription("Share");
        
        readButton = new FlexButton((""), VaadinIcons.EYE);
        readButton.setDescription("Mark as read");
        
        youtubeButton = new FlexButton((""), VaadinIcons.YOUTUBE);
        youtubeButton.setDescription("Add YouTube video link");

        controls = new HorizontalLayout(commentButton, shareButton, readButton, youtubeButton);
        controls.setSizeFull();
    }

    private void initInfo() {
        initSourceName();
        initTitle();
        initAuthor();
        initContent();
        initLink();
        initImage();
        initControls();
        initTimeLabel();
        info = new VerticalLayout(sourceName, image, title, publishedAt, author, content, url, controls);
        info.setWidth("100%"); 
    }
    
    private void initSourceName() {
        if(article.getAuthor().getSource() != null) {
            sourceName = new Label(article.getAuthor().getSource().toString());
        }
        else {
            sourceName = new Label("Uknown");
            sourceName.setStyleName(ValoTheme.LABEL_FAILURE);
        }
    }

    private void initTimeLabel() {
        String t = article.getPublishedAt();
        publishedAt = new Label(t);
    }

    public Link getUrl() {
        return url;
    }

    public Label getPublishedAt() {
        return publishedAt;
    }

    public VerticalLayout getInfo() {
        return info;
    }

    public HorizontalLayout getControls() {
        return controls;
    }

    public FlexButton getCommentButton() {
        return commentButton;
    }

    public FlexButton getShareButton() {
        return shareButton;
    }

    public FlexButton getReadButton() {
        return readButton;
    }

    public FlexButton getYoutubeButton() {
        return youtubeButton;
    }
    
    
}
