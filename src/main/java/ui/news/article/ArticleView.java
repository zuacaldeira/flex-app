package ui.news.article;

import ui.GraphEntityView;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import db.news.FlexUser;
import db.news.NewsArticle;
import db.news.NewsSource;
import services.news.NewsArticleServiceInterface;
import ui.CommentButton;
import ui.FakeButton;
import ui.FavoriteButton;
import ui.FlexButton;
import ui.HideButton;
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
    private static final String HEADER_HEIGHT = "48px";

    public ArticleView(FlexUser user, NewsArticle article) {
        super(user, article);
    }
    
    
    @Override
    public AbstractOrderedLayout createInfoHeader() {
        initSourceInfo();
        initTimeLabel();
        
        VerticalLayout sourceAndDate = new VerticalLayout();
        sourceAndDate.setSpacing(false);
        sourceAndDate.setMargin(false);
        if(sourceNameLabel != null) {
            sourceAndDate.addComponent(sourceNameLabel);
            sourceAndDate.setComponentAlignment(sourceNameLabel, Alignment.MIDDLE_LEFT);
        }
        if(publishedAt != null) {
            sourceAndDate.addComponent(publishedAt);
            sourceAndDate.setComponentAlignment(publishedAt, Alignment.MIDDLE_LEFT);
        }
        header = new HorizontalLayout(logoImage, sourceAndDate);
        header.setExpandRatio(logoImage, .25f);
        header.setExpandRatio(sourceAndDate, .75f);
        header.setMargin(false);
        header.setSpacing(true);
        //header.setHeight(HEADER_HEIGHT);
        header.setWidthUndefined();
        header.setComponentAlignment(logoImage, Alignment.MIDDLE_CENTER);
        header.setComponentAlignment(sourceAndDate, Alignment.BOTTOM_LEFT);
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
        logoImage = new Image("");
        logoImage.addStyleName("circle");
        NewsSource source = ServiceLocator.getInstance().findSourcesService().findSourceWithSourceId(getItem().getSourceId());
        if(source != null) {
            if(source.getLogoUrl() != null) {
                logoImage = new Image("", new ExternalResource(source.getLogoUrl()));
                logoImage.setHeight(HEADER_HEIGHT);
                logoImage.setWidth(HEADER_HEIGHT);
            } 
            sourceNameLabel = new Label(source.getName());
            sourceNameLabel.setSizeUndefined();
        }
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
    
    public NewsArticleServiceInterface getService() {
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

    
    public  AbstractOrderedLayout createInfoActions() {
        FlexButton commentButton = new CommentButton();
        commentButton.addClickListener(this);

        FlexButton favoriteButton = new FavoriteButton();
        FlexButton fakeButton = new FakeButton();
        FlexButton hideButton = new HideButton();

        hideButton.addClickListener(this);
        fakeButton.addClickListener(this);
        favoriteButton.addClickListener(this);
        
        if(getUser() != null && getService().isFavorite(getUser().getUsername(), getItem())) {
            favoriteButton.addStyleName("yellow");
        }

        if(getUser() != null && getService().isFake(getUser().getUsername(), getItem())) {
            fakeButton.addStyleName("red");
        }

        if(getUser() != null && getService().isRead(getUser().getUsername(), getItem())) {
            hideButton.addStyleName("purple");
        }


        HorizontalLayout actions = new HorizontalLayout(commentButton, favoriteButton, fakeButton, hideButton);
        actions.setSizeUndefined();
        actions.setStyleName("actions");
        actions.setSpacing(false);
        return actions;
    }
    
    private void handleHideClick(HideButton button) {
        if(!button.getStyleName().contains("purple")) {
            getService().markAsRead(getUser().getUsername(), this.getItem());
            button.addStyleName("purple");
            button.setDescription("Mark as Read");
        }
        else {
            getService().removeMarkAsRead(getUser().getUsername(), this.getItem());
            button.removeStyleName("purple");
            button.setDescription("Mark as Unread");
        }        
        ((VerticalLayout)getParent()).removeComponent(this);
    }

    private void handleFavouriteClick(FavoriteButton button) {
        if(!button.getStyleName().contains("yellow")) {
            getService().markAsFavorite(getUser().getUsername(), this.getItem());
            button.addStyleName("yellow");
            button.setDescription("Unmark Favorite");
        }
        else {
            getService().removeMarkAsFavorite(getUser().getUsername(), this.getItem());
            button.removeStyleName("yellow");
            button.setDescription("Mark as Favorite");
        }
    }

    private void handleFakeClick(FakeButton button) {
        if(!button.getStyleName().contains("red")) {
            getService().markAsFake(getUser().getUsername(), this.getItem());
            button.addStyleName("red");
            button.setDescription("Unmark Fake");
        }
        else {
            getService().removeMarkAsFake(getUser().getUsername(), this.getItem());
            button.removeStyleName("red");
            button.setDescription("Mark as Fake");
        }
    }
    
    @Override
    public void buttonClick(Button.ClickEvent event) {
        if(getUI() != null && getUI().isAttached()){
            if(event.getButton() instanceof HideButton) {
                handleHideClick((HideButton) event.getButton());
            }
            else if(event.getButton() instanceof FavoriteButton) {
                handleFavouriteClick((FavoriteButton) event.getButton());            
            }

            else if(event.getButton() instanceof FakeButton) {
                handleFakeClick((FakeButton) event.getButton());
            }
        }
    }
    
}
