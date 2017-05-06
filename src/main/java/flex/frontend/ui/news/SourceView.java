package flex.frontend.ui.news;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import flex.backend.news.db.NewsSource;

/**
 * Created by zua on 12/04/17.
 */
public class SourceView extends VerticalLayout {
    private final NewsSource apiSource;
    private final Label name;
    private final Label desc;

    public SourceView(NewsSource apiSource) {
        setSizeFull();
        setHeightUndefined();

        this.apiSource = apiSource;

        name = new Label(apiSource.getName());
        name.setWidth("100%");

        desc = new Label(apiSource.getDescription());
        desc.setWidth("100%");

        addComponents(name, desc);
        setExpandRatio(name, 0.2f);
        setExpandRatio(desc, 0.8f);
        setComponentAlignment(name, Alignment.TOP_RIGHT);
        setComponentAlignment(desc, Alignment.TOP_LEFT);
        setStyleName("article");
    }


    public NewsSource getApiSource() {
        return apiSource;
    }
}
