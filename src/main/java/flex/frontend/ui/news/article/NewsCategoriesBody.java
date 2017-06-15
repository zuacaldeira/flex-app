/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.article;

import flex.backend.news.db.NewsArticle;
import flex.frontend.ui.news.NewsBody;

/**
 *
 * @author zua
 */
public class NewsCategoriesBody extends NewsBody {

    private final String category;

    public NewsCategoriesBody(String category) {
        super();
        this.category = category;
    }

    @Override
    public Iterable<NewsArticle> loadItems() {
        if(category != null) {
            return getService().findAll(category);
        }
        else {
            return super.loadItems();
        }
    }
}
