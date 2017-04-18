package flex.frontend.ui;

import com.vaadin.ui.GridLayout;
import flex.backend.db.ApiArticle;
import flex.backend.db.ApiSource;
import flex.backend.services.NewsLoaderService;
import java.util.List;
import java.util.Map;
import org.utils.ServiceLocator;

/**
 * Created by zua on 13/04/17.
 */
public class ArticlesInfoView extends GridLayout {
    private NewsLoaderService service;

    public ArticlesInfoView() {
        super(3, 1);
        setSizeFull();
        setHeightUndefined();
        initArticles();
    }

    private void initArticles() {
        service = ServiceLocator.findNewsLoaderService();
        System.out.print("Found Service? " + (service != null));
        Map<ApiSource, List<ApiArticle>> articles = service.loadLatestNews(10);
        for(ApiSource source: articles.keySet()) {
            for(ApiArticle article: articles.get(source)) {
                addComponent(FlexViewFactory.createArticleView(source, article));
            }
        }
    }


}
