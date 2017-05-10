package flex.frontend.ui.news.article;

import flex.backend.news.db.NewsArticle;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexViewFactory;
import java.util.Collection;
import org.utils.ServiceLocator;

/**
 * Created by zua on 13/04/17.
 */
public class ArticlesBody extends FlexBody {
    
    public ArticlesBody() {
        initArticles();
    }

    private void initArticles() {
        ServiceLocator.getInstance().findArticlesService().findAll()
            .forEach((article) -> {
                addView(FlexViewFactory.getInstance().createArticleView(article));
            }
        );
    }

    public void addArticles(Collection<NewsArticle> articles) {
        articles.stream().forEach((article) -> {
            addView(FlexViewFactory.getInstance().createArticleView(article));
        });
    }
}
