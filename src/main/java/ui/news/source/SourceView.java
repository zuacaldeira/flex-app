package ui.news.source;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import db.FlexUser;
import db.NewsSource;
import ui.GraphEntityView;

/**
 * Created by zua on 12/04/17.
 */
public class SourceView extends GraphEntityView<NewsSource> {
    private Label name;
    private Label desc;
    private Label category;
    private Label language;
    private Label country;
    

    public SourceView(FlexUser user, NewsSource apiSource) {
        super(user, apiSource);
    }



    public Label getName() {
        return name;
    }

    public Label getDesc() {
        return desc;
    }

    public Label getCategory() {
        return category;
    }

    public Label getLanguage() {
        return language;
    }

    public Label getCountry() {
        return country;
    }

    @Override
    public AbstractOrderedLayout createInfoHeader() {
        initCategory();
        initLanguage();
        return new HorizontalLayout(category, new Label("|"), language);
    }

    @Override
    public AbstractOrderedLayout createInfoBody() {
        initName();
        initDesc();
        return new VerticalLayout(name, desc);
    }

    private void initCategory() {
        category = new Label(getItem().getCategory());
        category.setWidth("100%");
        
    }

    private void initLanguage() {
        language = new Label(getItem().getLanguage());
        language.setWidth("100%");
        
        country = new Label(getItem().getCountry());
        country.setWidth("100%");
    }
    
    private void initName() {
        name = new Label(getItem().getName());
        name.setWidth("100%");
    }
    
    private void initDesc() {
        desc = new Label(getItem().getDescription());
        desc.setWidth("100%");        
    }

    @Override
    public AbstractOrderedLayout createInfoActions() {
        return new HorizontalLayout();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        
    }
    
}
