package flex.frontend.ui.news.source;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import flex.backend.news.db.NewsSource;
import flex.frontend.ui.GraphEntityView;

/**
 * Created by zua on 12/04/17.
 */
public class SourceView extends GraphEntityView {
    private final NewsSource source;
    private final Label name;
    private final Label desc;
    private final Label category;
    private final Label language;
    private final Label country;
    

    public SourceView(NewsSource apiSource) {
        setWidth("100%");
        this.source = apiSource;

        name = new Label(apiSource.getName());
        name.setWidth("100%");

        desc = new Label(apiSource.getDescription());
        desc.setWidth("100%");
        
        category = new Label(apiSource.getCategory());
        category.setWidth("100%");
        
        language = new Label(apiSource.getLanguage());
        language.setWidth("100%");
        
        country = new Label(apiSource.getCountry());
        country.setWidth("100%");

        addComponents(name, desc);
        setExpandRatio(name, 0.2f);
        setExpandRatio(desc, 0.8f);
        setComponentAlignment(name, Alignment.TOP_RIGHT);
        setComponentAlignment(desc, Alignment.TOP_LEFT);
        setStyleName("article");
    }


    public NewsSource getSource() {
        return source;
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

    
}
