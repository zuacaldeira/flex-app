/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import flex.backend.news.db.NewsArticle;
import flex.backend.news.services.NewsArticleService;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class NewsCategoriesBody extends NewsBody {

    public NewsCategoriesBody(String category) {
        super(category);
    }

    public NewsCategoriesBody() {
    }

    @Override
    public Iterable<NewsArticle> loadArticles() {
        NewsArticleService service = ServiceLocator.getInstance().findArticlesService();
        return service.findAll((String) getMyData());
    }
    
    
}
