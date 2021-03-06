package factory;

import db.GraphEntity;
import db.auth.FlexUser;
import db.news.NewsArticle;
import db.news.NewsAuthor;
import db.news.NewsSource;


/**
 * Created by zua on 12/04/17.
 */
public class FlexViewFactory {

    private static FlexViewFactory instance;

    private FlexViewFactory() {}
    
    public  SourceView createSourceView(FlexUser user, NewsSource source) {
        return new SourceView(user, source);
    }

    public  ArticleView createArticleView(FlexUser user, NewsArticle article) {
        return new ArticleView(user, article);
    }

    public  AuthorView createAuthorView(FlexUser user, NewsAuthor author) {
        return new AuthorView(user, author);
    }
    
    public  GraphEntityView createView(FlexUser user, GraphEntity entity) {
        if(entity instanceof NewsArticle) {
            return createArticleView(user, (NewsArticle) entity);
        }
        else if(entity instanceof NewsSource) {
            return createSourceView(user, (NewsSource) entity);
        }
        else if(entity instanceof NewsAuthor) {
            return createAuthorView(user, (NewsAuthor) entity);
        }
        return null;
    }

    public static  FlexViewFactory getInstance() {
        if(instance == null) {
            instance = new FlexViewFactory();
        }
        
        return instance;
    }
}
