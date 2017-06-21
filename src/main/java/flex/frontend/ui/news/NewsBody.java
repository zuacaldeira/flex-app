package flex.frontend.ui.news;

import flex.backend.news.db.NewsArticle;
import flex.backend.news.services.NewsArticleService;
import flex.frontend.ui.crud.CrudBody;
import org.utils.ServiceLocator;

/**
 * Created by zua on 13/04/17.
 */
public class NewsBody extends CrudBody<NewsArticle> {

    
    public NewsBody() {
        forceSplit();
    }

    @Override
    public NewsArticleService getService() {
        return ServiceLocator.getInstance().findArticlesService();
    }

}
