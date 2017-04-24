package flex.frontend.ui;

import com.vaadin.ui.GridLayout;
import flex.backend.db.ApiArticle;
import flex.backend.db.ApiSource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.utils.ServiceLocator;

/**
 * Created by zua on 13/04/17.
 */
public class ArticlesInfoView extends GridLayout {
    private Map<ApiSource, List<ApiArticle>> news;
    public ArticlesInfoView() {
        super(1, 1);
        setWidth("100%");
        setHeightUndefined();
        setSpacing(true);
        setMargin(true);
        setStyleName("articles");
        initArticles();
    }

    public void initArticles() {
        news = ServiceLocator.findNewsLoaderService().loadArticles(MyUI.MAX_ARTICLES);
        for(ApiSource source: news.keySet()) {
            for(ApiArticle article: news.get(source)) {
                addComponent(FlexViewFactory.createArticleView(source, article));
            }
        }
    }

    public void addArticles(ApiSource source, Collection<ApiArticle> articles) {
        for(ApiArticle article: articles) {
            addComponent(FlexViewFactory.createArticleView(source, article));
        }
    }



}
