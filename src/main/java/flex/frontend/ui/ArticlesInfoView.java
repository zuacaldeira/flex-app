package flex.frontend.ui;

import com.vaadin.ui.GridLayout;
import flex.backend.db.ApiArticle;
import flex.backend.db.ApiSource;
import java.util.Collection;
import java.util.Map;
import org.utils.ServiceLocator;

/**
 * Created by zua on 13/04/17.
 */
public class ArticlesInfoView extends GridLayout {
    public ArticlesInfoView() {
        super(4, 1);
        setWidth("100%");
        setHeightUndefined();
        setSpacing(false);
        setMargin(false);
        setStyleName("articles");
        initArticles();
    }

    private void initArticles() {
        Map<ApiSource, Collection<ApiArticle>> news = ServiceLocator.findNewsLoaderService().loadArticles(MyUI.MAX_ARTICLES);
        news.keySet().stream().forEach((source) -> {
            news.get(source).stream().forEach((article) -> {
                addComponent(FlexViewFactory.createArticleView(source, article));
            });
        });
    }

    public void addArticles(ApiSource source, Collection<ApiArticle> articles) {
        articles.stream().forEach((article) -> {
            addComponent(FlexViewFactory.createArticleView(source, article));
        });
    }



}
