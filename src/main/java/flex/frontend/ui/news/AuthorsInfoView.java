
package flex.frontend.ui.news;

import flex.backend.news.db.NewsAuthor;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexViewFactory;
import org.utils.ServiceLocator;

/**
 * Created by zua on 13/04/17.
 */
public class AuthorsInfoView extends FlexBody {

    public AuthorsInfoView() {
        getGrid().setColumns(2);
        initAuthors();
        
    }

    private void initAuthors() {
        addAuthors(ServiceLocator.findAuthorsService().findAll());
    }

    public void addAuthors(Iterable<NewsAuthor> authors) {
        authors.forEach((author) -> {
            getGrid().addComponent(FlexViewFactory.createAuthorView(author));
        });
    }
}
