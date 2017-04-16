package flex.frontend.ui;

import com.vaadin.ui.*;
import flex.backend.services.NewsLoaderService;
import org.utils.ServiceLocator;

/**
 * Created by zua on 12/04/17.
 */
public class SourcesInfoView extends GridLayout {
    private NewsLoaderService service;
    

    public SourcesInfoView() {
        super(3, 1);
        setSizeFull();
        setHeightUndefined();
        setSpacing(true);
        initSources();
    }

    private void initSources() {
        service = ServiceLocator.findNewsLoaderService();
        System.out.print("Found Service? " + (service != null));
        service.loadSources().getSources()
                .forEach(s -> {
                    addComponent(FlexViewFactory.createSourceView(s));
                });
    }
}
