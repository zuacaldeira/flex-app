package flex.frontend.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import flex.backend.db.ApiSource;

/**
 * Created by zua on 12/04/17.
 */
public class SourceView extends HorizontalLayout {
    private final ApiSource apiSource;
    private final Label name;
    private final Label desc;

    public SourceView(ApiSource apiSource) {
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
    }


    public ApiSource getApiSource() {
        return apiSource;
    }
}
