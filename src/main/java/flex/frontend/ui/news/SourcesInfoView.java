package flex.frontend.ui.news;

import com.vaadin.ui.*;
import flex.backend.news.db.ApiArticle;
import flex.backend.news.db.ApiSource;
import flex.frontend.ui.FlexViewFactory;
import java.util.List;
import java.util.Map;

/**
 * Created by zua on 12/04/17.
 */
public class SourcesInfoView extends GridLayout {
    public SourcesInfoView() {
        super(1, 1);
        setSizeFull();
        setHeightUndefined();
        setSpacing(true);
        setMargin(true);
        setStyleName("sources");
    }

    public void initSources(Map<ApiSource, List<ApiArticle>> data) {
        for(ApiSource source: data.keySet()) {
            addComponent(FlexViewFactory.createSourceView(source));
        }
    }
}
