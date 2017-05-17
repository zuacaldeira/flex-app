package flex.frontend.ui.news.article;

import flex.frontend.ui.GraphEntityView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import flex.backend.news.db.NewsArticle;
import flex.frontend.ui.FlexButton;
import java.util.Set;
import org.utils.FlexUtils;


/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends GraphEntityView {
    // The associated article
    private final NewsArticle article;

    // The two main layout parts: info and control
    private VerticalLayout info;
    private HorizontalLayout controls;

    // Info components
    private Label title;
    private Label sourceName;
    private Label content;
    private Image image;
    private Label publishedAt;
    
    // Control buttons
    private FlexButton commentButton;
    private FlexButton shareButton;
    private FlexButton youtubeButton;
    private AbstractOrderedLayout authors;

    public ArticleView(NewsArticle article) {
        this.article = article;
        initInfo();
        super.addComponent(info);
        super.setSizeFull();
        super.setSpacing(false);
        super.setStyleName("article-minimized");
    }

    public NewsArticle getArticle() {
        return article;
    }

    public Label getTitle() {
        return title;
    }

    public AbstractOrderedLayout getAuthors() {
        return authors;
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
            if(this.image.getWidth() >= this.image.getHeight()) {
                this.image.setWidth("100%");
            } else {
                this.image.setHeight("100%");
            }
        }
        else {
            this.image = new Image();
        }
    }

    public Image getImage() {
        return image;
    }
    
    private void initAuthor() {
        authors = new VerticalLayout();
        authors.setSizeFull();
        authors.setMargin(false);
        authors.setSpacing(false);
        
        if(article.getAuthor() != null) {
            Set<String> allAuthorsNames = FlexUtils.getInstance().extractAuthorsNames(article.getAuthor().getName());
            for(String authorName: allAuthorsNames) {
                //author.setStyleName("article-author");
                Component author = null;
                if(FlexUtils.getInstance().isUrl(authorName)) {
                    author = new Label(FlexUtils.getInstance().extractNameFromUrl(authorName));
                    author.setSizeUndefined();
                    /*((Label)author).addClickListener(event -> {
                        ArticlesBody body = getArticlesBody();
                        body.getBrowserFrame().setSource(new ExternalResource(authorName));
                    });*/
                }
                else {
                    System.out.println("Not an url... " + authorName);
                    author = new Label(authorName);
                    author.setSizeUndefined();
                    //author.setStyleName(ValoTheme.BUTTON_LINK);
                }
                authors.addComponent(author);
                authors.setComponentAlignment(author, Alignment.MIDDLE_LEFT);
            }
        }
    }

    private void initControls() {
        initTimeLabel();
        commentButton = new FlexButton("", VaadinIcons.COMMENT);
        commentButton.setDescription("Comment");
        
        shareButton = new FlexButton((""), VaadinIcons.CONNECT);
        shareButton.setDescription("Share");
        
        youtubeButton = new FlexButton((""), VaadinIcons.YOUTUBE);
        youtubeButton.setDescription("Add YouTube video link");

        controls = new HorizontalLayout(commentButton, shareButton, youtubeButton);
        controls.setSizeFull();
        controls.setStyleName("controls");
    }

    private void initInfo() {
        initSourceName();
        initTitle();
        initAuthor();
        initContent();
        initImage();
        initTimeLabel();
        initControls();
        info = new VerticalLayout(title, image, content, authors, controls);
        info.setStyleName("info");
        info.setSpacing(false);
        info.setMargin(false);
        info.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
        minimizeInfo();
    }
    
    private void initSourceName() {
        if(article.getAuthor().getSource() != null) {
            sourceName = new Label(article.getAuthor().getSource().getName());
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

    public FlexButton getYoutubeButton() {
        return youtubeButton;
    }

    public void minimizeInfo() {
        // No controls
        controls.setVisible(false);
        // Only title and authors are left visible
        image.setVisible(false);
        publishedAt.setVisible(false);
        authors.setVisible(false);
        image.setVisible(false);
        setStyleName("article-minimized");
    }

    public void maximizeInfo() {
        // No controls
        controls.setVisible(true);
        
        // Only title and authors are left visible
        content.setVisible(true);
        image.setVisible(true);
        publishedAt.setVisible(true);   
        authors.setVisible(true);
        image.setVisible(true);
        content.setVisible(true);
        setStyleName("article-maximized");
    }

    private ArticlesBody getArticlesBody() {
        return getArticlesBody(this);
    }

    private ArticlesBody getArticlesBody(Component component) {
        if(component == null) {
            throw new IllegalArgumentException("Component cannot be null");
        }
        else if(component instanceof ArticlesBody) {
            return (ArticlesBody) component;
        }
        else {
            return getArticlesBody(component.getParent());
        }
    }

}
