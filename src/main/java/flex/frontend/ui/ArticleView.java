package flex.frontend.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import flex.backend.db.ApiArticle;
import flex.backend.db.ApiSource;


/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends VerticalLayout {
    private final ApiSource source;
    private final ApiArticle article;
    private Label title;
    private Label author;
    private Label content;
    private Image image;
    private Link link;
    private VerticalLayout info;
    private GridLayout controls;
    private FlexButton commentButton;
    private FlexButton shareButton;
    private FlexButton location;
    private FlexButton time;
    private FlexButton category;

    public ArticleView(ApiSource source, ApiArticle article) {
        this.source = source;
        this.article = article;

        setSizeFull();
        initInfo();
        addComponent(info);
        setStyleName("article");
        //addStyleName("category-"+source.getCategory().trim());
        setMargin(false);
    }

    private void initLink() {
        this.link = new Link("Read Full Story", new ExternalResource(article.getUrl()));
        link.setTargetName("_blank"); 
        link.setIcon(VaadinIcons.NEWSPAPER);
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

    private void initTitle() {
        this.title = new Label(article.getTitle());
        this.title.setStyleName("article-title white-on-black");
        this.title.setSizeFull();
    }

    private void initAuthor() {
        this.author = new Label(article.getAuthor() + ", " + source.getName());
        this.author.setStyleName("article-author white-on-black");
        this.author.setSizeFull();
    }

    private void initContent() {
        this.content = new Label(article.getDescription());
        this.content.setStyleName("article-content");
        this.content.setSizeFull();
    }

    private void initImage() {
        this.image = new Image("", new ExternalResource(article.getImageUrl()));
        this.image.setWidth("100%");
    }

    public Image getImage() {
        return image;
    }

    private void initControls() {
        initLocationLabel();
        initTimeLabel();
        initCategoryLabel();
        commentButton = new FlexButton("Comment", VaadinIcons.COMMENT);
        shareButton = new FlexButton(("Share"), VaadinIcons.SHARE_SQUARE);
        controls = new GridLayout(2, 1, location, category, commentButton, shareButton);
        controls.setSizeFull();
    }

    private void initInfo() {
        initTitle();
        initAuthor();
        initContent();
        initLink();
        initImage();
        initControls();

        info = new VerticalLayout(image, title, author, content, link, controls);
        info.setWidth("100%"); 
    }
    
    public void initLocationLabel() {
        String loc = source.getCountry();
        location = new FlexButton(loc, VaadinIcons.MAP_MARKER);
        location.setEnabled(false);
    }

    public void initTimeLabel() {
        String t = article.getPublishedAt();
        time = new FlexButton(t, VaadinIcons.CLOCK);
        time.setEnabled(false);
    }

    public void initCategoryLabel() {
        String t = source.getCategory();
        category = new FlexButton(t, VaadinIcons.TAGS);
        category.setEnabled(false);
    }
}
