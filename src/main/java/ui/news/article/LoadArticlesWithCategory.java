/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.news.article;

import ui.FlexMainView;
import ui.news.LoadArticles;

/**
 *
 * @author zua
 */
public class LoadArticlesWithCategory extends LoadArticles {

    private final String category;

    public LoadArticlesWithCategory(int limit, String category, FlexMainView view) {
        super(limit, view);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    
}
