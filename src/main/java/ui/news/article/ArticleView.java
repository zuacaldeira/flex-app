package ui.news.article;

import ui.GraphEntityView;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import db.news.FlexUser;
import db.news.NewsArticle;
import db.news.NewsSource;
import services.news.NewsArticleService;
import utils.ServiceLocator;


/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends GraphEntityView<NewsArticle>  {
    // Info components
    private Label title;
    private HorizontalLayout header;
    private Label content;
    private Image image;
    private Image logoImage;
    private Label publishedAt;
    
    private AbstractOrderedLayout authors;
    private Label sourceNameLabel;

    public ArticleView(FlexUser user, NewsArticle article) {
        super(user, article);
    }
    
    
    @Override
    public AbstractOrderedLayout createInfoHeader() {
        initSourceInfo();
        initTimeLabel();
        
        VerticalLayout sourceAndDate = new VerticalLayout(sourceNameLabel, publishedAt);
        sourceAndDate.setSpacing(false);
        sourceAndDate.setMargin(false);
        sourceAndDate.setComponentAlignment(sourceNameLabel, Alignment.MIDDLE_LEFT);
        sourceAndDate.setComponentAlignment(publishedAt, Alignment.MIDDLE_LEFT);
        header = new HorizontalLayout(logoImage, sourceAndDate);
        header.setMargin(false);
        header.setSpacing(false);
        header.setSizeUndefined();
        //header.setComponentAlignment(logoImage, Alignment.MIDDLE_CENTER);
        //header.setComponentAlignment(sourceAndDate, Alignment.MIDDLE_CENTER);
        return header;
    }

    @Override
    public AbstractOrderedLayout createInfoBody() {
        initTitle();
        initImage();
        initContent();
        VerticalLayout view = new VerticalLayout(title);
        if(image != null) {
            view.addComponent(image);
        }   
        view.addComponent(content);
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
    
    private void initSourceInfo() {
        NewsSource source = ServiceLocator.getInstance().findSourcesService().findSourceBySourceId(getItem().getSourceId());        
        logoImage = null;
        if(source.getLogoUrl() != null) {
            logoImage = new Image("", new ExternalResource(source.getLogoUrl()));
            logoImage.setHeight("64px");
            logoImage.setWidth("64px");
        } else {
            logoImage = new Image();
            logoImage.setSizeUndefined();
        }
        logoImage.addStyleName("circle");
        
        sourceNameLabel = new Label(source.getName());
        sourceNameLabel.setSizeUndefined();
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
