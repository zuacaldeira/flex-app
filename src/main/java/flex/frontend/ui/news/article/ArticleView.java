package flex.frontend.ui.news.article;

import flex.frontend.ui.news.NewsBody;
import flex.frontend.ui.GraphEntityView;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;
import flex.backend.news.db.NewsArticle;
import flex.backend.news.db.NewsSource;
import org.utils.ServiceLocator;


/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends GraphEntityView implements ClickListener {
    // Info components
    private Label title;
    private Label sourceName;
    private Label content;
    private Image image;
    private Label publishedAt;
    
    private AbstractOrderedLayout authors;

    public ArticleView(NewsArticle article) {
        super(article);
    }
    
    
    @Override
    public AbstractOrderedLayout createInfoHeader() {
        initSourceName();
        initTimeLabel();
        initTitle();
        initImage();
        HorizontalLayout firstLine = new HorizontalLayout(sourceName, publishedAt);
        firstLine.setWidth("100%");
        firstLine.setComponentAlignment(sourceName, Alignment.MIDDLE_LEFT);
        firstLine.setComponentAlignment(publishedAt, Alignment.MIDDLE_RIGHT);
        VerticalLayout view = new VerticalLayout(firstLine);
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
        sourceName.setStyleName(ValoTheme.LABEL_LIGHT + " " + "source");
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
