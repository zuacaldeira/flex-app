
package flex.frontend.ui.news.author;

import flex.backend.news.db.NewsAuthor;
import flex.backend.news.services.AbstractDBService;
import flex.frontend.ui.crud.CrudBody;
import org.utils.ServiceLocator;

/**
 * Created by zua on 13/04/17.
 */
public class AuthorsBody extends CrudBody<NewsAuthor> {

    public AuthorsBody() {
    }

    @Override
    protected AbstractDBService<NewsAuthor> getService() {
        return ServiceLocator.getInstance().findAuthorsService();
    }

}
