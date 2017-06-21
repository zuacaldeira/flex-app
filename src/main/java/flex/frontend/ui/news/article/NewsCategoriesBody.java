/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.article;

import flex.backend.news.db.NewsArticle;
import flex.backend.news.services.NewsArticleService;
import flex.frontend.ui.crud.CrudBody;
import java.util.LinkedList;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class NewsCategoriesBody extends CrudBody<NewsArticle> {

    private final String category;

    public NewsCategoriesBody(String category) {
        super();
        this.category = category;
        forceSplit();
    }

    @Override
    public Iterable<NewsArticle> loadItems() {
        if(category != null) {
            System.out.println("Load items with category " + category);
            return getService().findArticlesWithCategory(category, 25);
        }
        return new LinkedList<>();
    }

    @Override
    protected NewsArticleService getService() {
        return ServiceLocator.getInstance().findArticlesService();
    }
}
