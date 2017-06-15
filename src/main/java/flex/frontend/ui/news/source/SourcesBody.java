package flex.frontend.ui.news.source;

import flex.backend.news.db.NewsSource;
import flex.backend.news.services.AbstractDBService;
import flex.frontend.ui.crud.CrudBody;
import org.utils.ServiceLocator;

/**
 * Created by zua on 12/04/17.
 */
public class SourcesBody extends CrudBody<NewsSource> {

    public SourcesBody() {
        super();
    }

    @Override
    protected AbstractDBService<NewsSource> getService() {
        return ServiceLocator.getInstance().findSourcesService();
    }

}
