package factory;

import com.vaadin.ui.*;
import db.auth.FlexUser;
import db.news.NewsAuthor;


/**
 * Created by zua on 13/04/17.
 */
public class AuthorView extends GraphEntityView<NewsAuthor> {

    private static final long serialVersionUID = 2908477970400166461L;
    private Label name;

    public AuthorView(FlexUser user, NewsAuthor author) {
        super(user, author);
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
    public void unselect() {
    }

    @Override
    public void select() {
    }
}
