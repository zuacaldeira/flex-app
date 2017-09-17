package ui.news.author;

import com.vaadin.ui.*;
import db.FlexUser;
import db.NewsAuthor;
import ui.GraphEntityView;


/**
 * Created by zua on 13/04/17.
 */
public class AuthorView extends GraphEntityView<NewsAuthor> {
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
    public AbstractOrderedLayout createInfoActions() {
        return new HorizontalLayout();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        
    }

}
