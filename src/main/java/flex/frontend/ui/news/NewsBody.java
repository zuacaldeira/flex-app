/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import flex.frontend.ui.FlexBody;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class NewsBody extends FlexBody {

    private CountComponent sourcesCount;
    private CountComponent authorsCount;
    private CountComponent articlesCount;

    public NewsBody() {
        initSourcesCount();
        initAuthorsCount();
        initArticlesCount();
        addView(sourcesCount);
        addView(authorsCount);
        addView(articlesCount);
    }

    public CountComponent getSourcesCount() {
        return sourcesCount;
    }
    
    public CountComponent getAuthorsCount() {
        return authorsCount;
    }

    public CountComponent getArticlesCount() {
        return articlesCount;
    }

    private void initSourcesCount() {
        long nSources = ServiceLocator.getInstance().findSourcesService().count();
        sourcesCount = new CountComponent("Sources", nSources);
        sourcesCount.setSizeUndefined();
    }

    private void initAuthorsCount() {
        long nAuthors = ServiceLocator.getInstance().findAuthorsService().count();
        authorsCount = new CountComponent("Authors", nAuthors);
        authorsCount.setSizeUndefined();
    }

    private void initArticlesCount() {
        long nArticles = ServiceLocator.getInstance().findArticlesService().count();
        articlesCount = new CountComponent("Articles", nArticles);
        articlesCount.setSizeUndefined();
    }
}
