package flex.frontend.ui;

import flex.frontend.ui.news.article.ArticleView;
import flex.frontend.ui.news.source.SourceView;
import flex.backend.news.db.NewsArticle;
import flex.backend.news.db.NewsSource;
import flex.backend.news.db.NewsAuthor;
import flex.frontend.ui.news.author.AuthorView;


/**
 * Created by zua on 12/04/17.
 */
public class FlexViewFactory {

    private static FlexViewFactory instance;
    
    
    private FlexViewFactory() {}
    
    public  SourceView createSourceView(NewsSource source) {
        return new SourceView(source);
    }

    public  ArticleView createArticleView(NewsArticle article) {
        return new ArticleView(article);
    }

    public  AuthorView createAuthorView(NewsAuthor author) {
        return new AuthorView(author);
    }
    
    public static  FlexViewFactory getInstance() {
        if(instance == null) {
            instance = new FlexViewFactory();
        }
        
        return instance;
    }
}
