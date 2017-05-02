package flex.frontend.ui;

import flex.frontend.ui.news.ArticleView;
import flex.frontend.ui.news.SourceView;
import flex.backend.news.db.ApiArticle;
import flex.backend.news.db.ApiSource;


/**
 * Created by zua on 12/04/17.
 */
public class FlexViewFactory {
    public static SourceView createSourceView(ApiSource source) {
        return new SourceView(source);
    }

    public static ArticleView createArticleView(ApiArticle article) {
        return new ArticleView(article);
    }
}
