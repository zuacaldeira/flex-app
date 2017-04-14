package org.test;

import com.vaadin.ui.Component;

/**
 * Created by zua on 12/04/17.
 */
public class FlexViewFactory {
    public static ApiSourceView createView(Object api) {
        return new ApiSourceView((ApiSource) api);
    }

    public static ArticleView createArticleView(ApiSource source, ApiArticle article) {
        return new ArticleView(source, article);
    }
}
