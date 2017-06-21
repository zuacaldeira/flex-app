/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import flex.backend.news.db.NewsSource;
import flex.backend.news.services.NewsSourceService;
import flex.frontend.ui.crud.CrudBody;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class NewsPublishersBody extends CrudBody<NewsSource> {

    public NewsPublishersBody() {
        forceGrid();
    }

    @Override
    protected NewsSourceService getService() {
        return ServiceLocator.getInstance().findSourcesService();
    }
    
}
