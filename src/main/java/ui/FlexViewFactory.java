package ui;

import db.histories.FlexEvent;
import db.histories.FlexVideo;
import db.news.FlexUser;
import db.news.GraphEntity;
import ui.news.article.ArticleView;
import ui.news.source.SourceView;
import db.news.NewsArticle;
import db.news.NewsSource;
import db.news.NewsAuthor;
import ui.histories.videos.EventView;
import ui.histories.videos.FlexVideoView;
import ui.news.author.AuthorView;


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
    
    public  EventView createEventView(FlexUser user, FlexEvent event) {
        return new EventView(user, event);
    }

    public  GraphEntityView createView(FlexUser user, GraphEntity entity) {
        if(entity instanceof FlexVideo) {
            return new FlexVideoView(user, (FlexVideo) entity);
        }
        else if(entity instanceof NewsArticle) {
            return createArticleView(user, (NewsArticle) entity);
        }
        else if(entity instanceof NewsSource) {
            return createSourceView(user, (NewsSource) entity);
        }
        else if(entity instanceof NewsAuthor) {
            return createAuthorView(user, (NewsAuthor) entity);
        }
        else if(entity instanceof FlexEvent) {
            return createEventView(user, (FlexEvent) entity);
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
