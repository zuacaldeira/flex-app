package flex.frontend.ui.news;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import flex.backend.news.db.ApiArticle;
import flex.frontend.ui.FlexButton;


/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends VerticalLayout {
    private final ApiArticle article;
    private Label title;
    private Label author;
    private Label content;
    private Image image;
    private Link link;
    private VerticalLayout info;
    private HorizontalLayout controls;
    private FlexButton commentButton;
    private FlexButton shareButton;
    private FlexButton location;
    private FlexButton time;
    private FlexButton category;

    public ArticleView(ApiArticle article) {
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
        this.author = new Label(article.getAuthor());
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

        info = new VerticalLayout(image, title, author, content, link, controls);
        info.setWidth("100%"); 
    }
    
    public void initTimeLabel() {
        String t = article.getPublishedAt();
        time = new FlexButton(t, VaadinIcons.CLOCK);
        time.setEnabled(false);
    }
}
