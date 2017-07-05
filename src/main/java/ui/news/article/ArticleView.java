package ui.news.article;

import ui.GraphEntityView;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import db.news.FlexUser;
import db.news.NewsArticle;
import db.news.NewsSource;
import services.news.NewsArticleService;
import ui.FlexBody;
import utils.ServiceLocator;


/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends GraphEntityView<NewsArticle>  {
    // Info components
    private Label title;
    private Label sourceName;
    private Label content;
    private Image image;
    private Label publishedAt;
    
    private AbstractOrderedLayout authors;

    public ArticleView(FlexUser user, NewsArticle article) {
        super(user, article);
    }
    
    
    @Override
    public AbstractOrderedLayout createInfoHeader() {
        initSourceName();
        initTimeLabel();
        initTitle();
        initImage();
        VerticalLayout view = new VerticalLayout(sourceName, publishedAt);
        if(title != null) {
            view.addComponent(title);
        }
        if(image != null) {
            view.addComponent(image);
        }
        view.setSpacing(false);
        view.setMargin(false);
        return view;
    }

    @Override
    public AbstractOrderedLayout createInfoBody() {
        initImage();
        initContent();
        VerticalLayout view = new VerticalLayout(content);   
        view.setSpacing(false);
        view.setMargin(false);
        return view;
    }
    
    public NewsArticle getArticle() {
        return getItem();
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
        this.title = new Label(getItem().getTitle());
        this.title.setStyleName("title");
        this.title.setSizeFull();
    }

    private void initContent() {
        this.content = new Label(getItem().getDescription());
        this.content.setStyleName("content");
        this.content.setSizeFull();
    }

    private void initImage() {
        if(getItem().getImageUrl() != null) {
            this.image = new Image("", new ExternalResource(getItem().getImageUrl()));
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
    
    private void initSourceName() {
        NewsSource source = ServiceLocator.getInstance().findSourcesService().findSourceBySourceId(getItem().getSourceId());
        if(source != null) {
            sourceName = new Label(source.getName());
        }
        else {
            sourceName = new Label("Uknown");
        }
        sourceName.setStyleName("source");
    }

    private void initTimeLabel() {
        publishedAt = new DateLabel(getItem().getPublishedAt());
        publishedAt.setSizeUndefined();
    }

    public Label getPublishedAt() {
        return publishedAt;
    }

    @Override
    public NewsArticle getItem() {
        return (NewsArticle) super.getItem();
    }

    
    private FlexBody getArticlesBody() {
        return getArticlesBody(this);
    }

    private FlexBody getArticlesBody(Component component) {
        if(component == null) {
            throw new IllegalArgumentException("Component cannot be null");
        }
        else if(component instanceof FlexBody) {
            return (FlexBody) component;
        }
        else {
            return getArticlesBody(component.getParent());
        }
    }

    @Override
    public NewsArticleService getService() {
        return ServiceLocator.getInstance().findArticlesService();
    }

    @Override
    public void maximize() {
        super.maximize();
    }

    @Override
    public void minimize() {
        super.minimize();
    }

    
    
}
