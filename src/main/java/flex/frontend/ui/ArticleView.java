package flex.frontend.ui;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import flex.backend.db.ApiArticle;
import flex.backend.db.ApiSource;


/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends AbsoluteLayout {
    private final ApiSource source;
    private final ApiArticle article;
    private  Label title;
    private  Label author;
    private  Label content;
    private  Image image;
    private Link link;
    private final HorizontalLayout text;

    public ArticleView(ApiSource source, ApiArticle article) {
        this.source = source;
        this.article = article;
        
        setWidth("100%");
        setHeight("720px");
        
        initTitle(article);
        initAuthor(source, article);
        initContent(article);
        initImage(article);
        initLink(article);
        
        VerticalLayout left = new VerticalLayout(title, author);
        VerticalLayout right = new VerticalLayout(content, link);
        text = new HorizontalLayout(left, right);
        text.setWidth("100%");
        text.setSpacing(true);
        text.setStyleName("article");
        
        addComponents(image, text);
        ComponentPosition textPosition = new ComponentPosition();
        textPosition.setBottom(0f, Unit.PERCENTAGE);
        setPosition(text, textPosition);
    }

    private void initLink(ApiArticle article) {
        this.link = new Link("Read Full Story", new ExternalResource(article.getUrl()));
        link.setTargetName("_blank"); 
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

    private void initTitle(ApiArticle article) {
        this.title = new Label(article.getTitle());
        this.title.setStyleName("article-title white-on-black");
        this.title.setSizeFull();
    }

    private void initAuthor(ApiSource source, ApiArticle article) {
        this.author = new Label(article.getAuthor() + ", " + source.getName());
        this.author.setStyleName("article-author white-on-black");
        this.author.setSizeFull();
    }

    private void initContent(ApiArticle article) {
        this.content = new Label(article.getDescription());
        this.content.setStyleName("article-content");
        this.content.setSizeFull();
    }

    private void initImage(ApiArticle article) {
        this.image = new Image(article.getTitle(), new ExternalResource(article.getImageUrl()));
        this.image.setSizeFull();
    }

    public Image getImage() {
        return image;
    }
}
