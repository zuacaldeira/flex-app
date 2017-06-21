/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import flex.backend.news.db.NewsArticle;
import flex.backend.news.services.NewsArticleService;
import flex.frontend.ui.crud.CrudBody;
import java.util.LinkedList;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class NewsPublisherArticlesBody extends CrudBody<NewsArticle> {

    private final String publisher;

    public NewsPublisherArticlesBody(String publisher) {
        this.publisher = publisher;
        forceSplit();
    }

    @Override
    protected NewsArticleService getService() {
        return ServiceLocator.getInstance().findArticlesService();
    }

    @Override
    public Iterable<NewsArticle> loadItems() {
        if(publisher != null) {
            return getService().findArticlesWithSource(publisher);
        }
        System.out.println("Something wrong here");
        return new LinkedList<>();
    }
    
    
    
}
