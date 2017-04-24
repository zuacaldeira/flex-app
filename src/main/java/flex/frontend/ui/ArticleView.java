package flex.frontend.ui;

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
    private  Label title;
    private  Label author;
    private  Label content;
    private  Image image;
    private Link link;
    private final VerticalLayout text;

    public ArticleView(ApiSource source, ApiArticle article) {
        this.source = source;
        this.article = article;
        
        setWidth("100%");

        initImage(article);

        initTitle(article);
        initAuthor(source, article);
        initContent(article);
        initLink(article);
        
        text = new VerticalLayout(title, author, content, link, image);
        text.setWidth("100%");
        text.setStyleName("article");
        
        addComponent(text);
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
        this.image = new Image("", new ExternalResource(article.getImageUrl()));
        this.image.setWidth("100%");
    }

    public Image getImage() {
        return image;
    }
}
