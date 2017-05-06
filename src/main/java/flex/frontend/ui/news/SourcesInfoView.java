package flex.frontend.ui.news;

import flex.backend.news.db.NewsSource;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexViewFactory;
import org.utils.ServiceLocator;

/**
 * Created by zua on 12/04/17.
 */
public class SourcesInfoView extends FlexBody {

    public SourcesInfoView() {
        super();
        initSources();
    }

    private void initSources() {
        System.out.println("Initializing sources...");
        Iterable<NewsSource> data = ServiceLocator.findNewsSourceService().findAll();
        data.forEach(s -> {
            addView(FlexViewFactory.createSourceView(s));        
        });
    }

}
