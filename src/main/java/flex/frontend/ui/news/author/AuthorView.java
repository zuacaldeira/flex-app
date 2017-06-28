package flex.frontend.ui.news.author;

import com.vaadin.ui.*;
import flex.backend.news.db.NewsAuthor;
import flex.backend.news.services.AbstractDBService;
import flex.frontend.ui.GraphEntityView;
import org.utils.ServiceLocator;


/**
 * Created by zua on 13/04/17.
 */
public class AuthorView extends GraphEntityView<NewsAuthor> {
    private Label name;

    public AuthorView(NewsAuthor author) {
        super(author);
    }

    
    private void initName() {
        if(getItem() != null && getItem().getName() != null) {
            name = new Label(getItem().getName());
        }
    }

    public Label getName() {
        return name;
    }


    public NewsAuthor getAuthor() {
        return getItem();
    }

    @Override
    public void minimize() {
    }

    @Override
    public void maximize() {
    }

    @Override
    public AbstractOrderedLayout createInfoHeader() {
        initName();
        return new HorizontalLayout(name);
    }

    @Override
    public AbstractOrderedLayout createInfoBody() {
        return new VerticalLayout();
    }

    @Override
    public AbstractDBService<NewsAuthor> getService() {
        return ServiceLocator.getInstance().findAuthorsService();
    }


}
