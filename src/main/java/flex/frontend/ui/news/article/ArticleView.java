package flex.frontend.ui.news.article;

import flex.frontend.ui.news.NewsBody;
import com.vaadin.event.LayoutEvents;
import flex.frontend.ui.GraphEntityView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;
import flex.backend.news.db.NewsArticle;
import flex.backend.news.db.NewsSource;
import flex.frontend.ui.FlexButton;
import flex.frontend.ui.news.FlexWindow;
import java.text.SimpleDateFormat;
import org.utils.ServiceLocator;


/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends GraphEntityView implements ClickListener, LayoutEvents.LayoutClickListener {
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
        //super.setMargin(true);
        //super.setMargin(new MarginInfo(false, true, true, true));
        super.setStyleName("article");
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
        this.title.setStyleName("title");
        this.title.setSizeFull();
    }

    private void initContent() {
        this.content = new Label(article.getDescription());
        this.content.setStyleName("content");
        this.content.setSizeFull();
    }

    private void initImage() {
        if(article.getImageUrl() != null) {
            this.image = new Image("", new ExternalResource(article.getImageUrl()));
            this.image.setStyleName("image");
            if(this.image.getWidth() >= this.image.getHeight()) {
                this.image.setWidth("100%");
                this.image.setHeightUndefined();
            } else {
                this.image.setHeight("100%");
                this.image.setWidthUndefined();
            }
        }
    }

    public Image getImage() {
        return image;
    }
    
    private void initAuthor() {
        authors = new HorizontalLayout();
        authors.setSizeFull();
        authors.setMargin(false);
        authors.setSpacing(false);
        authors.addLayoutClickListener((LayoutEvents.LayoutClickListener) this);
        
        article.getAuthors().forEach(a -> {
            Label author = new Label(a.getName());
            author.setSizeUndefined();
            author.setStyleName("author");
            authors.addComponents(author);
            authors.setComponentAlignment(author, Alignment.MIDDLE_LEFT);
        });
    }

    private void initControls() {
        commentButton = new FlexButton("", VaadinIcons.COMMENT);
        commentButton.setDescription("Comment");
        
        shareButton = new FlexButton((""), VaadinIcons.CONNECT);
        shareButton.setDescription("Share");
        
        youtubeButton = new FlexButton((""), VaadinIcons.YOUTUBE);
        youtubeButton.setDescription("Add YouTube video link");
        youtubeButton.addClickListener((Button.ClickListener) this);

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
        info = new VerticalLayout();

        if(sourceName != null) {
            info.addComponent(sourceName);
        }
        
        if(title != null) {
            info.addComponent(title);
        }
        
        if(image != null) {
            info.addComponent(image);
        }
        if(content != null) {
            info.addComponent(content);
        }
        if(controls != null && controls.getComponentCount() > 0) {
            info.addComponent(controls);
        }
        info.setStyleName("info");
        info.setSpacing(false);
        info.setMargin(false);
    }
    
    private void initSourceName() {
        NewsSource source = ServiceLocator.getInstance().findSourcesService().findSourceBySourceId(article.getSourceId());
        if(source != null) {
            sourceName = new Label(source.getName());
        }
        else {
            sourceName = new Label("Uknown");
        }
        sourceName.setStyleName(ValoTheme.LABEL_LIGHT + " " + "source");
    }

    private void initTimeLabel() {
        SimpleDateFormat format = new SimpleDateFormat("d MMM HH:mm");
        String t = format.format(article.getPublishedAt());
        publishedAt = new Label(t);
        publishedAt.setSizeUndefined();
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

    public void minimize() {
        setStyleName("article");
        content.setVisible(false);
        controls.setVisible(false);
    }

    public void maximizeInfo() {
        setStyleName("article-selected");
        content.setVisible(true);
        controls.setVisible(true);
    }

    private NewsBody getArticlesBody() {
        return getArticlesBody(this);
    }

    private NewsBody getArticlesBody(Component component) {
        if(component == null) {
            throw new IllegalArgumentException("Component cannot be null");
        }
        else if(component instanceof NewsBody) {
            return (NewsBody) component;
        }
        else {
            return getArticlesBody(component.getParent());
        }
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if(event.getButton() == youtubeButton) {
            SingleFieldDialog dialog = new SingleFieldDialog("Youtube Url");
            Window w = new  FlexWindow("Adding a video url", dialog);
            UI.getCurrent().addWindow(w);
        }
    }

    @Override
    public void layoutClick(LayoutEvents.LayoutClickEvent event) {
        if(event.getClickedComponent() instanceof Label) {
            Label label = (Label) event.getClickedComponent();
            String url = (String) label.getData();
            if(url != null) {
                getArticlesBody().getBrowserFrame().setSource(new ExternalResource(url));
                maximizeInfo();
            }
        }
    }
    
    
    

}
