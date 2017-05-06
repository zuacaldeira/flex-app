package flex.frontend.ui;

import flex.frontend.ui.news.ArticleView;
import flex.frontend.ui.news.SourceView;
import flex.backend.news.db.NewsArticle;
import flex.backend.news.db.NewsSource;
import flex.backend.news.db.NewsAuthor;
import flex.frontend.ui.news.AuthorView;


/**
 * Created by zua on 12/04/17.
 */
public class FlexViewFactory {
    public static SourceView createSourceView(NewsSource source) {
        return new SourceView(source);
    }

    public static ArticleView createArticleView(NewsArticle article) {
        return new ArticleView(article);
    }

    public static AuthorView createAuthorView(NewsAuthor author) {
        return new AuthorView(author);
    }
}
