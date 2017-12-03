package factory;

import com.vaadin.ui.*;
import db.NewsAuthor;


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
    public void unselect() {
    }

    @Override
    public void select() {
    }
}
