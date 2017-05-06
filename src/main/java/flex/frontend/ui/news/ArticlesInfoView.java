package flex.frontend.ui.news;

import flex.backend.news.db.NewsArticle;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexViewFactory;
import java.util.Collection;
import org.utils.ServiceLocator;

/**
 * Created by zua on 13/04/17.
 */
public class ArticlesInfoView extends FlexBody {

    public ArticlesInfoView() {
        initArticles();
    }

    private void initArticles() {
        ServiceLocator.findNewsArticleService().findAll()
            .forEach((article) -> {
                getGrid().addComponent(FlexViewFactory.createArticleView(article));
            }
        );
    }

    public void addArticles(Collection<NewsArticle> articles) {
        articles.stream().forEach((article) -> {
            getGrid().addComponent(FlexViewFactory.createArticleView(article));
        });
    }
}
