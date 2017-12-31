package factory;

import com.vaadin.ui.Label;
import db.auth.FlexUser;
import db.news.NewsSource;

/**
 * Created by zua on 12/04/17.
 */
public class SourceView extends GraphEntityView<NewsSource> {

    private static final long serialVersionUID = -3396435155506131790L;

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

    private void initCategory() {
        category = new Label(getItem().getCategory().getTag());
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

}
