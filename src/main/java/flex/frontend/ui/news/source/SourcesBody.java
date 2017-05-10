package flex.frontend.ui.news.source;

import flex.backend.news.db.NewsSource;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexViewFactory;
import org.utils.ServiceLocator;

/**
 * Created by zua on 12/04/17.
 */
public class SourcesBody extends FlexBody {

    public SourcesBody() {
        super();
        initSources();
    }

    private void initSources() {
        System.out.println("Initializing sources...");
        Iterable<NewsSource> data = ServiceLocator.getInstance().findSourcesService().findAll();
        data.forEach(s -> {
            addView(FlexViewFactory.getInstance().createSourceView(s));        
        });
    }

}
