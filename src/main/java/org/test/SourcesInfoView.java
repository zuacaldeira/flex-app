package org.test;

import com.vaadin.ui.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zua on 12/04/17.
 */
public class SourcesInfoView extends GridLayout {

    public SourcesInfoView() {
        super(1,1);
        setSizeFull();
        setHeightUndefined();
        /* A vertical Layout to hold the information about the news sources */
        addComponent(createSourcesContent());
    }

    private Component createSourcesContent() {
        ApiSources apiSources = NewsApiOrg.GET_ApiSources();

        VerticalLayout sourcesContent = new VerticalLayout();
        sourcesContent.setWidth("100%");
        sourcesContent.setHeightUndefined();
        for(ApiSource api: apiSources.getSources()) {
            sourcesContent.addComponent(FlexViewFactory.createView(api));
        }

        return sourcesContent;
    }


}
