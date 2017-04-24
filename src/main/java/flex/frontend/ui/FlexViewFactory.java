package flex.frontend.ui;

import flex.backend.db.ApiArticle;
import flex.backend.db.ApiSource;


/**
 * Created by zua on 12/04/17.
 */
public class FlexViewFactory {
    public static SourceView createSourceView(ApiSource source) {
        return new SourceView(source);
    }

    public static ArticleView createArticleView(ApiSource source, ApiArticle article) {
        return new ArticleView(source, article);
    }
}
