package ui;

import db.histories.FlexVideo;
import db.news.GraphEntity;
import ui.news.article.ArticleView;
import ui.news.source.SourceView;
import db.news.NewsArticle;
import db.news.NewsSource;
import db.news.NewsAuthor;
import ui.histories.videos.FlexVideoView;
import ui.news.author.AuthorView;


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
    
    public  GraphEntityView createView(GraphEntity entity) {
        if(entity instanceof FlexVideo) {
            return new FlexVideoView((FlexVideo) entity);
        }
        else if(entity instanceof NewsArticle) {
            return createArticleView((NewsArticle) entity);
        }
        else if(entity instanceof NewsSource) {
            return createSourceView((NewsSource) entity);
        }
        else if(entity instanceof NewsAuthor) {
            return createAuthorView((NewsAuthor) entity);
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
