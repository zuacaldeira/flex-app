
package flex.frontend.ui.news.author;

import flex.backend.news.db.NewsAuthor;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexViewFactory;
import org.utils.ServiceLocator;

/**
 * Created by zua on 13/04/17.
 */
public class AuthorsBody extends FlexBody {

    public AuthorsBody() {
        initAuthors();
    }

    private void initAuthors() {
        addAuthors(ServiceLocator.getInstance().findAuthorsService().findAll());
    }

    public void addAuthors(Iterable<NewsAuthor> authors) {
        authors.forEach((author) -> {
            addView(FlexViewFactory.getInstance().createAuthorView(author));
        });
    }
}
